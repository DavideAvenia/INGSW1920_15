package Boundary;

import Controller.GestioneUtentiRegistratiController;
import Controller.LoginDesktopController;
import Controller.StatisticheStruttureController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaPrincipaleModForm extends Application {
    @FXML
    private Button review;
    @FXML
    private Button strutture;
    @FXML
    private Button logout;
    @FXML
    private Label label;



    public void moderaPremuto(ActionEvent actionEvent) {
        // Inserire codice per moderare le recensioni, come sotto.
    }

    public void StrutturePremuto(ActionEvent actionEvent) throws IOException {
        StatisticheStruttureController controller = StatisticheStruttureController.getStatisticheStruttureController();
        controller.mostra();
    }

    public void logoutPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        LoginDesktopController loginControl = LoginDesktopController.getInstanzaLoginDesktopController();
        loginControl.mostraPaginaLogin();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/PaginaPrincipaleModForm.fxml"));
        primaryStage.setTitle("Pagina Moderatore");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
