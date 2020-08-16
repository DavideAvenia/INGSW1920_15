package Boundary;

import Controller.StatisticheStruttureController;
import Entity.StatisticheStrutture;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticheStruttureForm extends Application implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private TableColumn minicolumn1;
    @FXML
    private TableView lista;
    @FXML
    private Button aggiorna;
    @FXML
    private Button indietro;
    @FXML
    private TableView<StatisticheStrutture> statistiche;
    @FXML
    private TableColumn<StatisticheStrutture, Integer> nospiti;
    @FXML
    private TableColumn<StatisticheStrutture, Integer> nreview;
    @FXML
    private TableColumn<StatisticheStrutture, Integer> nclient;
    @FXML
    private TableColumn<StatisticheStrutture, Integer> nstar;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
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

    public static class OggettoTabella{
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

    public void initializeBigTable(ObservableList<StatisticheStrutture> obl2) {
        nospiti.setCellValueFactory(new PropertyValueFactory<>("numVisitatori"));
        nclient.setCellValueFactory((new PropertyValueFactory<>("numClienti")));
        nreview.setCellValueFactory((new PropertyValueFactory<>("numReviews")));
        statistiche.setItems(obl2);
        statistiche.getColumns().addAll(nospiti, nclient, nreview);
    }

    public void initializeSmallTable(ObservableList<StatisticheStrutture> obl1) {
        minicolumn1.setCellValueFactory((new PropertyValueFactory<>("Lista Strutture")));
        lista.setItems(obl1);
        lista.getColumns().add(minicolumn1);
    }

    public static void popolaInterfaccia(List<String> nomiStrutture, List<StatisticheStrutture> numeri){

       // Popoli una lista di OggettoTabella con i valori delle liste prese in input
        List<OggettoTabella> listaTabellaNumeri = new ArrayList<OggettoTabella>();
        List<OggettoTabella> listaTabellaNomi = new ArrayList<>();
        for(int i=0;i<numeri.size();i++){
            OggettoTabella og = new OggettoTabella(Integer.parseInt(String.valueOf(numeri.get(i))),Integer.parseInt(String.valueOf(numeri.get(i))),Integer.parseInt(String.valueOf(numeri.get(i))));
            listaTabellaNumeri.add(og);
        }
        for(int i=0;i<nomiStrutture.size();i++){
            OggettoTabella ogg = new OggettoTabella(nomiStrutture.get(i));
            listaTabellaNomi.add(ogg);
        }

        ObservableList<OggettoTabella> oblistNumeri = FXCollections.observableArrayList(listaTabellaNumeri);
        ObservableList<OggettoTabella> oblistNomi = FXCollections.observableArrayList(listaTabellaNomi);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StatisticheStruttureController statisticheStruttureController = StatisticheStruttureController.getStatisticheStruttureController();
        try{
            statisticheStruttureController.mostraStatistiche();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
