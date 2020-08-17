package Boundary;

import Controller.StatisticheStruttureController;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticheStruttureForm extends Application implements Initializable{
    @FXML
    private static TextField search;
    @FXML
    private static TableColumn minicolumn1 = new TableColumn();
    @FXML
    private static TableView<String> lista = new TableView<>();
    @FXML
    private static Button aggiorna;
    @FXML
    private static Button indietro;
    @FXML
    private static TableView<OggettoTabella> statistiche = new TableView<>();
    @FXML
    private static TableColumn<OggettoTabella, Integer> nospiti = new TableColumn<>();
    @FXML
    private static TableColumn<OggettoTabella, Integer> nreview = new TableColumn<>();
    @FXML
    private static TableColumn<OggettoTabella, Integer> nclient = new TableColumn<>();
    @FXML
    private static TableColumn<OggettoTabella, Integer> nstar = new TableColumn<>();
    @FXML
    private AnchorPane pane;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsStructuresForm.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Nome_Software");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public void aggiornaPremuto(ActionEvent actionEvent) {
        StatisticheStruttureController statisticheStruttureController = StatisticheStruttureController.getStatisticheStruttureController();
        try{
            statisticheStruttureController.mostraStatistiche();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class OggettoTabella {
        /*Inserisci gli attributi che ti servono e crea il costrutture con getter e setter*/
        private int numVisitatori;
        private int numClienti;
        private int numReviews;
        private String nomeStruttura;

        public OggettoTabella(int numVisitatori, int numClienti, int numReviews) {
            this.numVisitatori = numVisitatori;
            this.numClienti = numClienti;
            this.numReviews = numReviews;
        }

        public OggettoTabella(String s) {
            this.nomeStruttura = nomeStruttura;
        }


        public int getNumVisitatori() {
            return numVisitatori;
        }

        public void setNumVisitatori(int numVisitatori) {
            this.numVisitatori = numVisitatori;
        }

        public int getNumClienti() {
            return numClienti;
        }

        public void setNumClienti(int numClienti) {
            this.numClienti = numClienti;
        }

        public int getNumReviews() {
            return numReviews;
        }

        public void setNumReviews(int numReviews) {
            this.numReviews = numReviews;
        }
    }

    public static void popolaInterfaccia(List<String> nomiStrutture, List<Integer> numVisitatori, List<Integer> numClienti, List<Integer> numReviews){

       // Popoli una lista di OggettoTabella con i valori delle liste prese in input
        List<OggettoTabella> listaTabellanumVisitatori = new ArrayList<OggettoTabella>();
        List<OggettoTabella> listaTabellaNomi = new ArrayList<>();
        ObservableList<String> oblistNomi = FXCollections.observableArrayList();
        ObservableList<OggettoTabella> oblistNumeri = FXCollections.observableArrayList();
        for(int i=0;i<numVisitatori.size();i++){
            OggettoTabella og = new OggettoTabella(numVisitatori.get(i),numClienti.get(i),numReviews.get(i));
           System.out.println(nomiStrutture.get(i) +" "+numVisitatori.get(i) +" "+ numClienti.get(i) +" "+ numReviews.get(i));
            listaTabellanumVisitatori.add(og);
            oblistNomi.add(nomiStrutture.get(i));
            oblistNumeri.add(og);
            System.out.println(oblistNomi.get(i));
            System.out.println(oblistNumeri.get(i));
        }
        //Inizializzazione tabella piccola
        lista.setItems(oblistNomi);
        // Inizializzazione tabella grande
        statistiche.setItems(oblistNumeri);
    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        StatisticheStruttureController statisticheStruttureController = StatisticheStruttureController.getStatisticheStruttureController();
        try{
            statisticheStruttureController.mostraStatistiche();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

