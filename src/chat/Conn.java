/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author rihem
 */
public class Conn {

    // Parametres de la connexion
    private static String url = "jdbc:derby://localhost:1527/Chat";
    private static String user = "root";
    private static String passwd = "root";

    private static Connection cn = null;

    // Constructeur privé pour limiter l'instanciation
    private Conn() {
        // Etape 1: Chargement driver
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver charger...");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Erreur chargement driver. " + e.getMessage());
            
        }

        // Etape 2: Connexion vers la base
        try {
            cn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion etablie...");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion. " + e.getMessage());
            e.printStackTrace();
            
        }
    }

    // On n'instancie la connexion que si le cn est different de null
    public static Connection getConnection() {
        if (cn == null) {
            new Conn();
        }
        return cn;
    }

    // Méthode de fermeture de la connexion
    public static void Fermer() throws SQLException {
        if (cn != null) {
            cn.close();
            System.out.println("Deconnexion etablie...");
        }
    }

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
