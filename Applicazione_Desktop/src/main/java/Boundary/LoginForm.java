package Boundary;

import Controller.LoginDesktopController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginForm extends Application {

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginForm.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 765.0,500.0));
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
}
