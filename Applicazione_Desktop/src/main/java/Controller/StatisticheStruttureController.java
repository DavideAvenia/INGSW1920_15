package Controller;

import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import Services.AWSMySQLRDS;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.List;

public class StatisticheStruttureController extends Application {
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
        AWSMySQLRDS aws = new AWSMySQLRDS();
        List<StatisticheStrutture> Lista;
        ObservableList<StatisticheStrutture> oblist;
        Lista = mostraStatistiche();
        oblist = (ObservableList) Lista;
        initializeBigTable(oblist);
        aggiorna.setOnAction(e -> aws.aggiornaStatistiche());
        indietro.setOnAction(e -> System.out.println(("Qui bisogna metterci la finestra precedente!")));
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Nome_Software");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public List<StatisticheStrutture> mostraStatistiche() throws IOException {
        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        StatisticheStruttureDAO S = DAOfactory.getStatisticheStruttureDAO(file1);
        List<StatisticheStrutture> L2 = null;
        L2 = S.getAllStatisticheStrutture(null);
        return L2;
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
}
