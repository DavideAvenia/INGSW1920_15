package Boundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestioneUtentiForm extends Application implements Initializable {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestioneUtentiForm.fxml"));
        primaryStage.setTitle("Gestione Utenti");
        primaryStage.setScene(new Scene(root,1024,768));
        primaryStage.show();
    }

    public void bottoneCancellaCliccato(ActionEvent actionEvent) {
    }

    public void bottoneIndietroPremuto(ActionEvent actionEvent) {
    }

    public void bottoneApplicaCliccato(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
