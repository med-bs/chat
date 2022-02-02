/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author mohamed
 */
public class Chat extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    JPanel panelNorth, panelSouth;
    JLabel lblName;
    JTextArea txt;
    JTextField txtMessage;
    JButton btnSend;
    String nom;

    public Chat(String n) throws HeadlessException {
        setTitle("Chat");
        this.nom=n;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        this.setLayout(new BorderLayout());

        panelNorth = new JPanel();
        this.add(panelNorth, BorderLayout.NORTH);
        panelNorth.setLayout(new FlowLayout());

        lblName = new JLabel(this.nom);
        panelNorth.add(lblName);

        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane, BorderLayout.CENTER);

        txt = new JTextArea();
        txt.setLineWrap(true);
        txt.setEditable(false);
        scrollPane.setViewportView(txt);

        panelSouth = new JPanel();
        this.add(panelSouth, BorderLayout.SOUTH);

        txtMessage = new JTextField();
        panelSouth.add(txtMessage);
        txtMessage.setColumns(50);

        btnSend = new JButton("Envoi");
        btnSend.addActionListener(this);
        panelSouth.add(btnSend);
        Lecture l =new Lecture();
        l.start();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (ae.getSource() == btnSend) {
            String message = txtMessage.getText();
            if (!message.isEmpty()) {
                Properties props = new Properties();
                props.setProperty("bootstrap.servers", "localhost:9092");
                props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("chat","JAVA : "+ this.nom +">>>" + message);
                producer.send(record);
                producer.close();
                txtMessage.setText("");
            }
        }
    }

    public class Lecture extends Thread {

        @Override
        public void run() {
            try {
                Properties props = new Properties();
                props.setProperty("bootstrap.servers", "localhost:9092");
                props.setProperty("group.id",nom);
                props.setProperty("enable.auto.commit", "true");
                props.setProperty("auto.commit.interval.ms", "1000");
                props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList("chat"));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                        txt.append(record.value() + "\n");
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
