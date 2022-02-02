/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rihem
 */
public class Connecter extends JFrame implements ActionListener {

    JPanel pn1, pn2, pn3, pn4, pn5;
    JLabel lbl_bienvenue, lbl_id, lbl_pseudo;
    JTextField txt_id, txt_pseudo;
    JButton btn_con, btn_insc;

    public Connecter() {
        this.setTitle("Chat");
        this.setSize(400, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pn1 = new JPanel();
        this.add(pn1, BorderLayout.NORTH);
        pn1.setLayout(new FlowLayout());

        lbl_bienvenue = new JLabel("Bienvenue");
        pn1.add(lbl_bienvenue);

        pn2 = new JPanel();
        this.add(pn2, BorderLayout.CENTER);
        pn2.setLayout(new FlowLayout());

        lbl_id = new JLabel("Identifiant:");
        lbl_id.setPreferredSize(new Dimension(150, 50));

        pn2.add(lbl_id);

        txt_id = new JTextField(15);
        txt_id.setHorizontalAlignment(JTextField.CENTER);
        //txt_id.setPreferredSize(new Dimension(80,80));
        pn2.add(txt_id);

        lbl_pseudo = new JLabel("mot de passe:");
        lbl_pseudo.setPreferredSize(new Dimension(150, 50));
        pn2.add(lbl_pseudo);
        txt_pseudo = new JPasswordField(15);
        txt_pseudo.setHorizontalAlignment(JTextField.CENTER);

        pn2.add(txt_pseudo);

        btn_con = new JButton("Se Connecter");
        pn2.add(btn_con);
        btn_con.addActionListener(this);
        btn_con.setActionCommand("Open_mail");

        btn_insc = new JButton("S'inscrire");
        pn2.add(btn_insc);
        btn_insc.addActionListener(this);
        btn_insc.setActionCommand("Open_insc");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Open_mail")) {
            dispose();
            String id = txt_id.getText();
            String pass = txt_pseudo.getText();
            /*Connection c = Conn.getConnection();
            String rq = "select * from ROOT.\"USER\"  where iduser = ? and password = ?";
            try {
                PreparedStatement ps = c.prepareStatement(rq);
                ps.setString(1, id);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {*/
            new Chat(id /*(String) rs.getObject("username")*/);
            /*
            } catch (SQLException ee) {
                // TODO Auto-generated catch block
                System.out.println("erreur de selection . " + ee.getMessage());
            }*/
        }
        if (cmd.equals("Open_insc")) {
            dispose();
            new Inscription();
        }
    }
}
