/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alefe Lucas
 */
public class SelectPasswordFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox<Senha> comboBoxCofre;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonDeletar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Senha> senhas = SenhaDAO.getSenhas();
        comboBoxCofre.getItems().addAll(senhas);
        comboBoxCofre.valueProperty().addListener((o, oldV, newV) -> {
            if (newV != null) {
                textAreaDescricao.setText(newV.getDescricao());
            } else {
                textAreaDescricao.setText("");
            }
                
        });
    }

    @FXML
    private void cancelClicked(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void okClicked(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Senha value = comboBoxCofre.valueProperty().getValue();
        if (value != null) {
            SenhaDAO.deleteSenha(value.getId());
            comboBoxCofre.getItems().remove(value);
        }
    }

}
