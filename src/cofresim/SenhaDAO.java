/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public abstract class SenhaDAO {

    public static boolean insert(Senha senha) {
       
        int result = 0;
        try ( Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO password"
                + " (nome, descricao, senha, senha_master) VALUES"
                + " (?, ?, ?, ?)")) {

            statement.setString(1, senha.getNome());
            statement.setString(2, senha.getDescricao());
            statement.setString(3, senha.getSenha());
            statement.setString(4, senha.getSenhaMaster());
            result = statement.executeUpdate();
            if(result == 1) JOptionPane.showMessageDialog(null, "Inserido com sucesso ", "Sucesso", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return (result == 1);
    }

    public static ArrayList<Senha> getSenhas() {
        

        ArrayList<Senha> senhas = new ArrayList<>();
        try ( Connection connection = ConnectionFactory.getConnection();PreparedStatement statement = connection.prepareStatement("SELECT * FROM password");
                ResultSet resultset = statement.executeQuery()) {

            while (resultset.next()) {
                senhas.add(new Senha(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3),
                        resultset.getString(4),
                        resultset.getString(5))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler dados " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return senhas;
    }

    public static boolean deleteSenha(int id) {
       
        int result = 0;
        try ( Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM password WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
            if(result == 1) JOptionPane.showMessageDialog(null, "Deletado com sucesso ", "Sucesso", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao deletar dados " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return (result == 1);
    }
}
