package Boundary;

import Controller.LoginDesktopController;
import Controller.ModeraRecensioniController;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaPrincipaleModForm extends Application {
    public ModeraRecensioniController moderaRecensioniController;
    @FXML
    private Button review;
    @FXML
    private Button strutture;
    @FXML
    private Button logout;
    @FXML
    private Label label;

    private Image iconaStage = new Image("https://progettoingswfedericoii.s3-eu-west-1.amazonaws.com/iconaAppDesktop.png");

    public void moderaPremuto(ActionEvent actionEvent) throws Exception {
        moderaRecensioniController.mostraModeraRecensioniForm();
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
        primaryStage.getIcons().add(iconaStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
