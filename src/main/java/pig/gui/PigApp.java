package pig.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PigApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PigGUI.fxml"));
        stage.setTitle("Pig Dice Game");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
