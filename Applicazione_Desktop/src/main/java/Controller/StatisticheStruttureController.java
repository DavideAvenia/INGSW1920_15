package Controller;

import Boundary.StatisticheStruttureForm;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Boundary.ModelloStatisticheEStrutture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
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
    private String filtroSelezionatoCombo = "";

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
        List<ModelloStatisticheEStrutture> L2 = S.getAllStatisticheStrutture();
        List<Integer> numVisitatori = new ArrayList<>();
        List<Integer> numClienti = new ArrayList<>();
        List<Integer> numReviews = new ArrayList<>();
        List<String> nomi = new ArrayList<>();
        List<String> categoria = new ArrayList<>();
        List<String> orarioApertura = new ArrayList<>();
        List<String> valutazioneMedia = new ArrayList<>();
        List<String> città = new ArrayList<>();
        for (ModelloStatisticheEStrutture value : L2) {
            numVisitatori.add(value.getNumVisitatori());
            numClienti.add(value.getNumClienti());
            numReviews.add(value.getNumReviews());
            nomi.add(value.getNome());
            categoria.add(value.getCategoria());
            orarioApertura.add(value.getOrarioApertura());
            valutazioneMedia.add(value.getValutazioneMedia());
            città.add(value.getCittà());
        }
        ObservableList<StatisticheStruttureForm.OggettoTabella> oblist = FXCollections.observableArrayList();
        for (int i = 0; i < numVisitatori.size(); i++) {
            StatisticheStruttureForm.OggettoTabella og = new StatisticheStruttureForm.OggettoTabella(nomi.get(i), numVisitatori.get(i), numClienti.get(i), numReviews.get(i),
                    categoria.get(i), valutazioneMedia.get(i), orarioApertura.get(i), città.get(i));
            oblist.add(og);
        }
        TableView<StatisticheStruttureForm.OggettoTabella> tableStats = statisticheStruttureForm.getStatisticheTable();
        tableStats.setItems(oblist);

        //Prendere la combobox, selezionare il primo item che sarà per nome
        //inserire nella combobox il resto dei filtri che saranno categoria, valutazioneMedia, orarioApertura
        //Filtrare la lista per il determinato filtro e permettere la ricerca tramite input
        ComboBox<String> comboFiltri = statisticheStruttureForm.getComboFiltri();
        List<String> filtri = new ArrayList<>();
        filtri.add("Nome Struttura");
        filtri.add("Numero Recensioni");
        filtri.add("Numero Clienti");
        filtri.add("Numero Visitatori");
        filtri.add("Categoria");
        filtri.add("Orario Apertura");
        filtri.add("Valutazione Media");
        filtri.add("Città");
        ObservableList<String> filtriObs = FXCollections.observableArrayList(filtri);
        comboFiltri.setItems(filtriObs);

        FilteredList<StatisticheStruttureForm.OggettoTabella> filtro = new FilteredList<>(oblist, p -> true);
        tableStats.setItems(filtro);
        TextField txt = statisticheStruttureForm.getTextField();
        comboFiltri.getSelectionModel().selectFirst();

        //Primo filtro già preimpostato
        txt.setPromptText("Inserisci il nome della struttura");
        txt.setOnKeyReleased(keyEvent ->
                filtro.setPredicate(p -> p.getNomeStruttura().toLowerCase().contains(txt.getText().toLowerCase().trim())));

        comboFiltri.setOnAction(Event -> {
            String output = comboFiltri.getSelectionModel().getSelectedItem();
            System.out.println(output);
            switch (output) {
                case "Nome Struttura":
                    txt.setPromptText("Inserisci il nome della struttura");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> p.getNomeStruttura().toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Numero Recensioni":
                    txt.setPromptText("Inserisci il numero delle recensioni per struttura");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> Integer.toString(p.getNumReviews()).toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Numero Clienti":
                    txt.setPromptText("Inserisci il numero clienti per struttura");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> Integer.toString(p.getNumClienti()).toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Numero Visitatori":
                    txt.setPromptText("Inserisci il numero visitatori per struttura");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> Integer.toString(p.getNumVisitatori()).toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Categoria":
                    txt.setPromptText("Inserisci la categoria");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> p.getCategoria().toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Orario Apertura":
                    txt.setPromptText("Inserisci l'orario d'apertura");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> p.getOrarioApertura().toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Valutazione Media":
                    txt.setPromptText("Inserisci la valutazione media");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> p.getValutazioneMedia().toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
                case "Città":
                    txt.setPromptText("Inserisci la città");
                    txt.setOnKeyReleased(keyEvent ->
                            filtro.setPredicate(p -> p.getCittà().toLowerCase().contains(txt.getText().toLowerCase().trim())));
                    break;
            }
        });
    }

    public void mostra() throws IOException {
        StatisticheStruttureForm statisticheStruttureForm = new StatisticheStruttureForm();
        statisticheStruttureForm.start(new Stage());
    }
}
