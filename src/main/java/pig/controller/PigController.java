package pig.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pig.model.Game;
import pig.model.Player;
import pig.model.PlayerHelper;

import java.io.FileWriter;
import java.io.IOException;

public class PigController {

    // Data Fields
    Game pig;

    // FXML Connections
    @FXML
    ImageView dieImage;

    @FXML
    Button rollButton;

    @FXML
    Button holdButton;

    @FXML
    Button resultsButton;

    @FXML
    TextField playerOneTurn;

    @FXML
    TextField playerTwoTurn;

    @FXML
    TextField playerOneTotal;

    @FXML
    TextField playerTwoTotal;

    @FXML
    VBox playerOneBox;

    @FXML
    VBox playerTwoBox;

    @FXML
    Label title;

    private Roller clock;

    private class Roller extends AnimationTimer{

        private final long  FRAMES_PER_SEC = 50L;
        private final long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private final int MAX_ROLLS = 20;

        private long last = 0;
        private int count = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL){
                int r = 2 + (int)(Math.random() * 5);
                setDieImage(r);
                last = now;
                count++;
                if (count > MAX_ROLLS){
                    clock.stop();
                    disableButtons(false);
                    roll();
                    count = 0;
                }
            }
        }
    }

    @FXML
    public void initialize(){
        clock = new Roller();
        pig = new Game("Player 1", "Player 2");
        updateViews();
    }

    public void updateViews(){
        setDieImage(pig.getDie().getTop());
        playerOneTurn.setText(String.valueOf(pig.getPlayerOne().getTurnScore()));
        playerOneTotal.setText(String.valueOf(pig.getPlayerOne().getTotalScore()));
        playerTwoTurn.setText(String.valueOf(pig.getPlayerTwo().getTurnScore()));
        playerTwoTotal.setText(String.valueOf(pig.getPlayerTwo().getTotalScore()));
        if (pig.playerOneTurn()){
            playerOneBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,null, null)));
            playerTwoBox.setBackground(null);
        } else {
            playerTwoBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,null, null)));
            playerOneBox.setBackground(null);
        }
        if (pig.gameOver()){
            var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            var winner_player = new PlayerHelper(pig.getCurrentPlayer().getName(),pig.getCurrentPlayer().getTotalScore());
            //winner_player.setPlayer_name(pig.getCurrentPlayer().getName());
            //winner_player.setTotalScore(pig.getCurrentPlayer().getTotalScore());
            try (var writer = new FileWriter("winner.json")) {
                objectMapper.writeValue(writer, winner_player);
            } catch (IOException e) {
                e.printStackTrace();
            }
            disableButtons(true);
            disableResultButton(false);
            title.setText("Game Over! " + pig.getCurrentPlayer().getName() + " wins!");
        }
    }

    public void setDieImage(int top){
        dieImage.setImage(new Image(getClass().getResource("/images/Dice" + top + ".png").toExternalForm()));
    }

    public void disableButtons(boolean disable){
        rollButton.setDisable(disable);
        holdButton.setDisable(disable);
    }

    public void disableResultButton(boolean disable){
        resultsButton.setDisable(disable);
    }

    public void rollAnimation(){
        clock.start();
        disableButtons(true);
        disableResultButton(true);
    }

    public void roll(){
        pig.roll();
        updateViews();
    }

    public void hold(){
        pig.hold();
        updateViews();
    }

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/results.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
