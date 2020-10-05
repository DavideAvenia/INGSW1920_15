package Boundary;

import Controller.LoginDesktopController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginForm extends Application implements Initializable {

    @FXML
    private ImageView iconaLogin = new ImageView();

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private Image iconaStage = new Image("https://progettoingswfedericoii.s3-eu-west-1.amazonaws.com/iconaAppDesktop.png");


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginForm.fxml"));
        Scene scene = new Scene(root, 765.0,500.0);
        stage.getIcons().add(iconaStage);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void loginPremuto(ActionEvent actionEvent) {
        boolean islogged;
        LoginDesktopController controller = LoginDesktopController.getInstanzaLoginDesktopController();
        try {
            islogged = controller.controllaCredenzialiPerLogin(username.getText(), password.getText());
            if (islogged == true) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iconaLogin.setImage(new Image("https://progettoingswfedericoii.s3-eu-west-1.amazonaws.com/iconaAppDesktop.png"));
    }
}
