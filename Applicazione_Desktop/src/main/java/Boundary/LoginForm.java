package Boundary;

import Controller.LoginDesktopController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm extends Application {

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginForm.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1024, 576));
        stage.show();
    }


    public void loginPremuto(ActionEvent actionEvent) throws IOException {
        /*Messo solo per aprire la pagina degli admin. Bisogna scrivere il codice correttamente*/
        LoginDesktopController controller = LoginDesktopController.getInstanzaLoginDesktopController();

        try {
            if(controller == null){
                System.out.println("Non fnonzia");
            }
            controller.controllaCredenzialiPerLogin(username.getText(), password.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*PaginaPrincipaleAdminForm p = new PaginaPrincipaleAdminForm();
        p.start(new Stage());*/
    }
}
