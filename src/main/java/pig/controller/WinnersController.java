package pig.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.tinylog.Logger;
import pig.model.PlayerHelper;

import java.io.FileReader;
import java.io.IOException;

public class WinnersController {
    /*
    @FXML
    private TableColumn<PlayerHelper, String> winner;

    @FXML
    private TableColumn<PlayerHelper, Integer> winner_score;

    @FXML
    private TableView<PlayerHelper> tableview;

    PlayerHelper read_player;

    @FXML
    private void initialize() throws IOException {
        Logger.debug("Winners tábla betöltése...");

        winner.setCellValueFactory(new PropertyValueFactory<>("Winner"));
        winner_score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableview.setItems(observableList);

        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        read_player = objectMapper.readValue(new FileReader("winner.json"), PlayerHelper.class);
        //System.out.println(read_player);

    }

    ObservableList<PlayerHelper> observableList = FXCollections.observableArrayList(
            new PlayerHelper( read_player.getPlayer_name(), read_player.getTotalScore())
    );*/

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PigGUI.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Logger.info("Kilépés...");
        Platform.exit();
    }
}
