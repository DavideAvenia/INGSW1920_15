package Boundary;

import Controller.StatisticheStruttureController;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import Services.AWSMySQLRDS;
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
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticheStruttureForm extends Application implements Initializable {
    @FXML
    private TableColumn minicolumn1;
    @FXML
    private TableView lista;
    @FXML
    private Button aggiorna;
    @FXML
    private Button indietro;
    @FXML
    private MenuButton filtro;
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

    public class OggettoTabella{
        /*Inserisci gli attributi che ti servono e crea il costrutture con getter e setter*/
    }

    public void initializeBigTable(ObservableList<StatisticheStrutture> obl2) {
        initalizeSmallTable(obl2);
        nospiti.setCellValueFactory(new PropertyValueFactory<>("numVisitatori"));
        nclient.setCellValueFactory((new PropertyValueFactory<>("numClienti")));
        nreview.setCellValueFactory((new PropertyValueFactory<>("numReviews")));
        statistiche.setItems(obl2);
        statistiche.getColumns().addAll(nospiti, nclient, nreview);
    }

    public void initalizeSmallTable(ObservableList<StatisticheStrutture> obl1) {
        minicolumn1.setCellValueFactory((new PropertyValueFactory<>("Lista Strutture")));
        lista.setItems(obl1);
        lista.getColumns().add(minicolumn1);
    }

    public static void popolaInterfaccia(/*Inserisci le liste che ti servono*/){
        /*
        Popoli una lista di OggettoTabella con i valori delle liste prese in input
        List<OggettoTabella> listaTabellas = new ArrayList<OggettoTabella>();
        for(int i=0;i<nomiStruttre.size();i++){
            OggettoTabella og = new OggettoTAbella(nomiStruttre.get(i),numVisitatori.get(i));
            listaTabellas.add(og);
        }

        ObservableList<OggettoTabella> oblist = FXCollections.observableArrayList(listaTabellas);
        ObservableList<String> oblistNomi = FXCollections.observableArrayList(nomiStrutture)

        */

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
