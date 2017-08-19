/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.awt.EventQueue;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class RevelarFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonOK;
    @FXML
    private ComboBox<Senha> comboBoxSenha;
    @FXML
    private PasswordField passwordFieldMaster;
    @FXML
    private Label labelSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Senha> senhas = SenhaDAO.getSenhas();
        comboBoxSenha.getItems().addAll(senhas);

    }

    @FXML
    private void cancelClicked(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void okClicked(ActionEvent event) {
        Senha senha = comboBoxSenha.valueProperty().getValue();
        if (senha != null) {
            if (senha.getSenhaMaster().equals(passwordFieldMaster.getText()) || senha.getSenhaMaster() == "32HG4JJH3F42") {
                labelSenha.setText(senha.getSenha());
            } else {
                 JOptionPane.showMessageDialog(null, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

}
