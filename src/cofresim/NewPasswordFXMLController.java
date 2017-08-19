/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class NewPasswordFXMLController implements Initializable {

    @FXML
    private TextField textFieldCofre;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private PasswordField passwordFieldConfirma;
    @FXML
    private PasswordField passwordFieldMasterConfirma;
    @FXML
    private PasswordField passwordFieldMaster;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private Button buttonCancela;
    @FXML
    private Button buttonOK;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonRandom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelClicked(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void okClicked(ActionEvent event) {
        try {
            String nome = textFieldCofre.getText();
            String descricao = textAreaDescricao.getText();
            String senha = passwordFieldSenha.getText();
            String senhaC = passwordFieldConfirma.getText();
            String senhaM = passwordFieldMaster.getText();
            String senhaMC = passwordFieldMasterConfirma.getText();
            if (nome.length() == 0) {
                throw new IllegalStateException("Informe o nome");
            } else if (!senha.equals(senhaC)) {
                throw new IllegalStateException("Senha não corresponde");
            } else if (!senhaM.equals(senhaMC)) {
                throw new IllegalStateException("Senha mestre não corresponde");
            }
            int senhaNumero = Integer.parseInt(senha);
            
            Senha senhaBean = new Senha(nome, descricao, senha, senhaM);
            SenhaDAO.insert(senhaBean);
            ((Stage) rootPane.getScene().getWindow()).close();
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "A senha deve ser numérica, entre " + Integer.MIN_VALUE + " e " + Integer.MAX_VALUE + "." , JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @FXML
    private void randomClicked(ActionEvent event) {
        Random r = new Random();
        int n =  + r.nextInt(9999);
        passwordFieldSenha.setText(String.format("%4d", n));
        passwordFieldConfirma.setText(String.format("%4d", n));
    }

}
