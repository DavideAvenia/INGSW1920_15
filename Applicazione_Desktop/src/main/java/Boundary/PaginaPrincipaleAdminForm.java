package Boundary;

import Controller.GestioneUtentiRegistratiController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaPrincipaleAdminForm extends Application {

    @FXML
    private Button ricarica;
    @FXML
    private Button logout;
    @FXML
    private TableColumn nreview;
    @FXML
    private TableColumn logincount;
    @FXML
    private TableColumn avgscore;
    @FXML
    private TableColumn pernreviews;
    @FXML
    private TableColumn nutenti;
    @FXML
    private TableView statsutenti;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/PaginaPrincipaleAdminForm.fxml"));
        primaryStage.setTitle("Pagina Principale");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }

    public void bottoneGestioneAccountPremuto(ActionEvent actionEvent) throws IOException {
        GestioneUtentiRegistratiController gestioneUtentiRegistratiController = GestioneUtentiRegistratiController.gestioneUtentiRegistratiController(this);
        gestioneUtentiRegistratiController.mostraGestioneUtentiForm();
    }

    public void bottoneStrutturePremuto(ActionEvent actionEvent) throws Exception {
        /*Guido, devi creare il controller e chiamare queste due funzioni da un metodo del controller
         * di statistiche strutture. Come sta nel metodo subito sopra a questo*/
        StatisticheStruttureForm statisticheStruttureForm = new StatisticheStruttureForm();
        statisticheStruttureForm.start(new Stage());
    }
}
