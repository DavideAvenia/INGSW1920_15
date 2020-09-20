package Boundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Messaggio extends Application implements Initializable {

    private static String title;
    private static String messaggio;

    @FXML
    public Label messaggioLabel;


    public Messaggio(String title, String messaggio) {
        this.title = title;
        this.messaggio = messaggio;
    }

    public Messaggio() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Messaggio.fxml"));
        Image iconaStage = new Image("https://progettoingswfedericoii.s3-eu-west-1.amazonaws.com/iconaAppDesktop.png");
        stage.getIcons().add(iconaStage);
        stage.setTitle(title);
        stage.setScene(new Scene(root, 348, 188));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messaggioLabel.setText(messaggio);
    }


    public void bottoneChiudiPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
