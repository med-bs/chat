/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rihem
 */
public class Inscription extends JFrame implements ActionListener {

    JPanel panelNorth, panelCenter, panelSouth;
    JLabel lblName, lbl_id, lbl_nom, lbl_pseudo;
    JTextField txt_id, txt_nom, txt_pseudo;
    JTextArea txt;
    JTextField txtMessage;
    JButton btnSend;

    public Inscription() {
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLayout(new BorderLayout());

        panelNorth = new JPanel();
        this.add(panelNorth, BorderLayout.NORTH);
        panelNorth.setLayout(new FlowLayout());

        lblName = new JLabel("S'inscrire");
        panelNorth.add(lblName);

        panelCenter = new JPanel();
        this.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());

        lbl_id = new JLabel("Identifiant:");
        lbl_id.setPreferredSize(new Dimension(150, 50));
        panelCenter.add(lbl_id);

        txt_id = new JTextField(15);
        txt_id.setHorizontalAlignment(JTextField.CENTER);

        panelCenter.add(txt_id);

        lbl_nom = new JLabel("Nom:");
        lbl_nom.setPreferredSize(new Dimension(150, 50));
        panelCenter.add(lbl_nom);

        txt_nom = new JTextField(15);
        txt_nom.setHorizontalAlignment(JTextField.CENTER);

        panelCenter.add(txt_nom);

        lbl_pseudo = new JLabel("Mot de passe:");
        lbl_pseudo.setPreferredSize(new Dimension(150, 50));
        panelCenter.add(lbl_pseudo);

        txt_pseudo = new JPasswordField(15);
        txt_pseudo.setHorizontalAlignment(JTextField.CENTER);

        panelCenter.add(txt_pseudo);

        panelSouth = new JPanel();
        this.add(panelSouth, BorderLayout.SOUTH);

        btnSend = new JButton("S'inscrire");
        panelSouth.add(btnSend);
        btnSend.addActionListener(this);
        btnSend.setActionCommand("Open_mail");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Open_mail")) {
            dispose();
            int a = 0;
            String id = txt_id.getText();
            String nom = txt_nom.getText();
            String pass = txt_pseudo.getText();
            //Connection c = Conn.getConnection();
            /*String rq = "insert into ROOT.\"USER\" (iduser, userName, password) values (?,?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(rq);

			ps.setString(1, id);
			ps.setString(2, nom);
			ps.setString(3, pass);

			a = ps.executeUpdate();

			System.out.println("Ajout d'une persone avec success");
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			System.out.println("erreur d'ajout. " + ee.getMessage());
		}

            if(a>0){*/
            new Chat(id);
            //}
        }
    }
}
