package Controller;

import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StatisticheStruttureController extends Application {
    public Button indietro;
    public MenuButton filtro;
    public TableView statistiche;
    public TableColumn nospiti;
    public TableColumn nreview;
    public TableColumn nclient;
    public TableColumn nstar;
    public TableView lista;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/StatsStructuresForm.fxml"));
        primaryStage.setTitle("Nome_Software");
        mostraStatistiche();
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
    public void mostraStatistiche() throws IOException {
        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        StatisticheStruttureDAO S = DAOfactory.getStatisticheStruttureDAO(file1);
        //List<StatisticheStrutture> L2 = S.getAllStatisticheStrutture();

        /*Guido, mi sono dimenticato di dirti che questa è una classe boundary, non è un controller.
        * Devi spostare questa classe nel pacchetto boundary e tutto il codice che hai scritto qua sopra
        * lo deve fare la classe controller di questo caso d'uso. Se vedi hai scritto la classe controller
        * e c'è anche il metodo che deve andare a creare questa finestra da qua. Per vedere come creare una
        * finestra da un controller, basta che apri il controller GestioneUtentiREgistrati e sta il metodo
        * "mostraGestioneUtentiForm. E non dimenticari di fare quel controller come un singleton!"*/
    }


}
