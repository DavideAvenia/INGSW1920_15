package Controller;

import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
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
import java.util.List;

public class Controller extends Application {
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
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
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
        List<StatisticheStrutture> L2 = S.getAllStatisticheStrutture();
    }


}
