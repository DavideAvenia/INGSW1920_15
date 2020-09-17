import Boundary.LoginForm;
import com.amazonaws.services.opsworks.model.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MockApp extends Application {

    private static Boolean isRunning = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Messaggio.fxml"));
        primaryStage.setTitle("MockApp");
        primaryStage.setScene(new Scene(root,200,200));
        primaryStage.show();
    }

    public void init(){
        if(!isRunning){
            isRunning = true;
            try {
                this.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
