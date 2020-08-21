package Controller;

import Boundary.StatisticheStruttureForm;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticheStruttureController {

    private static StatisticheStruttureController statisticheStruttureController = null;
    private StatisticheStruttureForm statisticheStruttureForm;

    private StatisticheStruttureController() {
    }

    public static StatisticheStruttureController getStatisticheStruttureController() {
        if (statisticheStruttureController == null) {
            statisticheStruttureController = new StatisticheStruttureController();
            return statisticheStruttureController;
        }
        return statisticheStruttureController;
    }

    public void setStatisticheStruttureForm(StatisticheStruttureForm statisticheStruttureForm) {
        this.statisticheStruttureForm = statisticheStruttureForm;
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
        ObservableList<StatisticheStruttureForm.OggettoTabella> oblist = FXCollections.observableArrayList();
        for (int i = 0; i < numVisitatori.size(); i++) {
            StatisticheStruttureForm.OggettoTabella og = new StatisticheStruttureForm.OggettoTabella(nomi.get(i), numVisitatori.get(i), numClienti.get(i), numReviews.get(i));
            oblist.add(og);
        }
        TableView<StatisticheStruttureForm.OggettoTabella> tableStats = statisticheStruttureForm.getStatisticheTable();
        tableStats.setItems(oblist);
        FilteredList<StatisticheStruttureForm.OggettoTabella> filtro = new FilteredList<>(oblist, p -> true);
        tableStats.setItems(filtro);
        TextField txt = statisticheStruttureForm.getTextField();
        txt.setPromptText("Scrivi qui!");
        txt.setOnKeyReleased(keyEvent ->
                filtro.setPredicate(p -> p.getNomeStruttura().toLowerCase().contains(txt.getText().toLowerCase().trim())));
    }

    public void mostra() throws IOException {
        StatisticheStruttureForm statisticheStruttureForm = new StatisticheStruttureForm();
        statisticheStruttureForm.start(new Stage());
    }
}
