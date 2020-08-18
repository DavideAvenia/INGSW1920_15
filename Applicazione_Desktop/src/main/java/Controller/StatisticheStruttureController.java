package Controller;

import Boundary.StatisticheStruttureForm;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticheStruttureController {

    private static StatisticheStruttureController statisticheStruttureController = null;
    private StatisticheStruttureForm statisticheStruttureForm;

    private StatisticheStruttureController(StatisticheStruttureForm S) {
        this.statisticheStruttureForm = S;
    }

    public static StatisticheStruttureController getStatisticheStruttureController(StatisticheStruttureForm S) {
        if (statisticheStruttureController == null) {
            statisticheStruttureController = new StatisticheStruttureController(S);
            return statisticheStruttureController;
        }
        return statisticheStruttureController;
    }

    public void mostraStatistiche() throws IOException {
        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        StatisticheStruttureDAO S = DAOfactory.getStatisticheStruttureDAO(file1);
        assert S != null;
        List<StatisticheStrutture> L2 = S.getAllStatisticheStrutture();
        List<Integer> numVisitatori = new ArrayList<>();
        List<Integer> numClienti = new ArrayList<>();
        List<Integer> numReviews = new ArrayList<>();
        List<String> nomi = new ArrayList<>();
        for (StatisticheStrutture value : L2) {
            numVisitatori.add(value.getNumVisitatori());
            numClienti.add(value.getNumClienti());
            numReviews.add(value.getNumReviews());
            nomi.add(value.getNome());
        }
        ObservableList<StatisticheStruttureForm.OggettoTabella> oblistNumeri = FXCollections.observableArrayList();
        for (int i = 0; i < numVisitatori.size(); i++) {
            StatisticheStruttureForm.OggettoTabella og = new StatisticheStruttureForm.OggettoTabella(numVisitatori.get(i), numClienti.get(i), numReviews.get(i));
            oblistNumeri.add(og);
        }
        ObservableList<String> oblistNomi = FXCollections.observableArrayList(nomi);
        TableView<String> tabella = statisticheStruttureForm.getTable();
        TableView<StatisticheStruttureForm.OggettoTabella> tableStats = statisticheStruttureForm.getStatisticheTable();
        tableStats.setItems(oblistNumeri);
        tabella.setItems(oblistNomi);

    }
}
