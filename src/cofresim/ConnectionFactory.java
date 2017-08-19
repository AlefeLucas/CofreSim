/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public class ConnectionFactory {

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite::resource:cofre.db");
            System.out.println("Connection successful");

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de conexão. Verifique a Base de Dados indicada !" + "\n" + ex.getMessage(), "Conexão", 3);
            throw new RuntimeException(ex);
        }
        return connection;
    }
}
