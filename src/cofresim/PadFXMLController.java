/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public class PadFXMLController implements Initializable {

    @FXML
    private ImageView imageViewLogo;
    @FXML
    private Label labelInfo;

    @FXML
    private Label labelCredits;
    @FXML
    private Button buttonN1;
    @FXML
    private Button buttonN2;
    @FXML
    private Button buttonN3;
    @FXML
    private Button buttonN4;
    @FXML
    private Button buttonN5;
    @FXML
    private Button buttonN6;
    @FXML
    private Button buttonN7;
    @FXML
    private Button buttonN8;
    @FXML
    private Button buttonN9;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonN0;

    private Button[] buttonN;

    @FXML
    private BorderPane rootPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuFile;
    @FXML
    private MenuItem menuItemNew;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private Menu menuOptions;
    @FXML
    private MenuItem menuItemShowPassword;
    @FXML
    private Menu menuOther;

    private ArrayList<Senha> senhas;
    @FXML
    private MenuItem menuItemManage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button buttonEnter;

    MediaPlayer openPlayer;
    MediaPlayer errorPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        senhas = SenhaDAO.getSenhas();
        setButtons();
        setPasswordField();
        setTheme();
        openPlayer = new MediaPlayer(new Media(getClass().getResource("/cofresim/sound/open.mp3").toString()));
        errorPlayer = new MediaPlayer(new Media(getClass().getResource("/cofresim/sound/error.mp3").toString()));
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public ArrayList<Senha> getSenhas() {
        return senhas;
    }

    private void setButtons() {
        this.buttonN = new Button[]{buttonN0, buttonN1, buttonN2,
            buttonN3, buttonN4, buttonN5,
            buttonN6, buttonN7, buttonN8, buttonN9};

        for (byte i = 0; i < buttonN.length; i++) {
            buttonN[i].setOnAction(new ButtonPasswordClickEvent(i, this, new Media(getClass().getResource("/cofresim/sound/" + i + ".mp3").toString())));
        }

    }

    private void setPasswordField() {
        passwordField.setEditable(false);
        passwordField.textProperty().addListener((o, oldV, newV) -> {
            System.out.println(newV);
        });

        rootPane.setOnKeyReleased(
                (KeyEvent event) -> {
                    switch (event.getCode()) {
                        case BACK_SPACE:
                            backspaceAction(event);
                            break;
                        case DELETE:
                            clearAction(event);
                            break;
                        case ENTER:
                            enterAction(event);
                    }

                    int digit = -1;
                    switch (event.getCode()) {
                        case DIGIT0:
                            digit = 0;
                            break;
                        case DIGIT1:
                            digit = 1;
                            break;
                        case DIGIT2:
                            digit = 2;
                            break;
                        case DIGIT3:
                            digit = 3;
                            break;
                        case DIGIT4:
                            digit = 4;
                            break;
                        case DIGIT5:
                            digit = 5;
                            break;
                        case DIGIT6:
                            digit = 6;
                            break;
                        case DIGIT7:
                            digit = 7;
                            break;
                        case DIGIT8:
                            digit = 8;
                            break;
                        case DIGIT9:
                            digit = 9;
                            break;
                    }

                    if (digit != -1) {
                        buttonN[digit].fire();
                    }
                });
    }

    private void setTheme() {
        ObservableList<String> stylesheets = rootPane.getStylesheets();
        stylesheets.clear();
        stylesheets.add("cofresim/PadStyle.css");
    }

    @FXML
    private void clearAction(Event event) {
        passwordField.setText("");
    }

    private void backspaceAction(Event event) {
        String password = passwordField.getText();
        if (password.length() > 0) {
            passwordField.setText(password.substring(0, password.length() - 1));
        }
    }

    @FXML
    private void newClicked(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("NewPasswordFXML.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Scene scene = new Scene(root);
        //Stage stage = (Stage)rootPane.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Novo Cofre");
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(e -> {
            senhas = SenhaDAO.getSenhas();
            passwordField.setText("");
        });
        stage.getIcons().add(new Image("icon.png"));
    }

    @FXML
    private void manageClicked(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("SelectPasswordFXML.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Scene scene = new Scene(root);
        //Stage stage = (Stage)rootPane.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Selecionar Cofre");
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(e -> {
            senhas = SenhaDAO.getSenhas();
            passwordField.setText("");
        });
        stage.getIcons().add(new Image("icon.png"));
    }

    @FXML
    private void closeClicked(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void aboutClicked(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Desenvolvido por Álefe Lucas S. Torres.\nalefelucas.contato@gmail.com\n31 97596-7134", "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void showPasswordClicked(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("RevelarFXML.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Scene scene = new Scene(root);
        //Stage stage = (Stage)rootPane.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Revelar Senha");
        stage.setResizable(false);
        stage.show();

        stage.setOnHidden(e -> {
            senhas = SenhaDAO.getSenhas();
            passwordField.setText("");
        });
        stage.getIcons().add(new Image("icon.png"));
    }

    @FXML
    private void enterAction(Event event) {
        boolean desbloqueou = false;
        for (Senha senha : senhas) {
            if (senha.getSenha().equals(passwordField.getText())) {
                desbloqueou = true;
                System.out.println("Desbloqueou");
                openPlayer.play();
                openPlayer.seek(Duration.ZERO);
                JOptionPane.showMessageDialog(null, "Cofre desbloqueado: " + senha.getNome(), "Parabéns", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (!desbloqueou) {
            System.out.println("fechado");
            errorPlayer.play();
            errorPlayer.seek(Duration.ZERO);
            passwordField.setText("");
            JOptionPane.showMessageDialog(null, "Senha incorreta.", "Bloqueado", JOptionPane.WARNING_MESSAGE);
        }
    }

}
