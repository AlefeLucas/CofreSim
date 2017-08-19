/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofresim;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Alefe Lucas
 */
public class ButtonPasswordClickEvent implements EventHandler<ActionEvent> {

    private final byte number;
    private final PadFXMLController pad;
    private final MediaPlayer mediaPlayer;

    public ButtonPasswordClickEvent(byte number, PadFXMLController pad, Media sound) {
        this.number = number;
        this.pad = pad;
        this.mediaPlayer = new MediaPlayer(sound);
    }

    @Override
    public void handle(ActionEvent event) {
        pad.getPasswordField().setText(pad.getPasswordField().getText() + Integer.toString(number));
        mediaPlayer.play();
        mediaPlayer.seek(Duration.ZERO);
        
       

    }

}
