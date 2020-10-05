package Boundary;

import Controller.StatisticheStruttureController;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticheStruttureForm extends Application implements Initializable {


    @FXML
    private Label label;
    @FXML
    private Button cerca;
    @FXML
    private TextField cercabox;
    @FXML
    private TableColumn<OggettoTabella, String> nome;
    @FXML
    private TableView<String> lista;
    @FXML
    private Button aggiorna;
    @FXML
    private Button indietro;
    @FXML
    private TableView<OggettoTabella> statistiche;
    @FXML
    private TableColumn<OggettoTabella, Integer> nospiti;
    @FXML
    private TableColumn<OggettoTabella, Integer> nreview;
    @FXML
    private TableColumn<OggettoTabella, Integer> nclient;
    @FXML
    private TableColumn<OggettoTabella, Integer> nstar;
    @FXML
    private TableColumn<OggettoTabella, String> categoria;
    @FXML
    private TableColumn<OggettoTabella, String> orarioApertura;
    @FXML
    private TableColumn<OggettoTabella, String> valutazioneMedia;
    @FXML
    private TableColumn<OggettoTabella, String> città;
    @FXML
    private ComboBox comboFiltri;
    @FXML
    private AnchorPane pane;

    private Image iconaStage = new Image("https://progettoingswfedericoii.s3-eu-west-1.amazonaws.com/iconaAppDesktop.png");

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsStructuresForm.fxml"));
        Parent root = loader.load();
        primaryStage.getIcons().add(iconaStage);
        primaryStage.setTitle("Statistiche Strutture");
        primaryStage.setScene(new Scene(root, 742, 522));
        primaryStage.show();
    }

    public void aggiornaPremuto(ActionEvent actionEvent) {
        StatisticheStruttureController statisticheStruttureController = StatisticheStruttureController.getStatisticheStruttureController();
        try {
            statisticheStruttureController.mostraStatistiche();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableView<OggettoTabella> getStatisticheTable() {
        return statistiche;
    }

    public TextField getTextField() {
        return cercabox;
    }

    public ComboBox getComboFiltri() {
        return comboFiltri;
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        StatisticheStruttureController statisticheStruttureController = StatisticheStruttureController.getStatisticheStruttureController();
        statisticheStruttureController.setStatisticheStruttureForm(this);
        try {
            nome.setCellValueFactory(new PropertyValueFactory<OggettoTabella, String>("nomeStruttura"));
            nospiti.setCellValueFactory(new PropertyValueFactory<OggettoTabella, Integer>("numVisitatori"));
            nclient.setCellValueFactory(new PropertyValueFactory<OggettoTabella, Integer>("numClienti"));
            nreview.setCellValueFactory(new PropertyValueFactory<OggettoTabella, Integer>("numReviews"));
            categoria.setCellValueFactory(new PropertyValueFactory<OggettoTabella, String>("categoria"));
            orarioApertura.setCellValueFactory(new PropertyValueFactory<OggettoTabella, String>("orarioApertura"));
            valutazioneMedia.setCellValueFactory(new PropertyValueFactory<OggettoTabella, String>("valutazioneMedia"));
            città.setCellValueFactory(new PropertyValueFactory<OggettoTabella, String>("città"));

            statisticheStruttureController.mostraStatistiche();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void indietroPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void selectFiltri(ActionEvent event) {
    }

    public static class OggettoTabella {
        private SimpleIntegerProperty numVisitatori;
        private SimpleIntegerProperty numClienti;
        private SimpleIntegerProperty numReviews;
        private SimpleStringProperty nomeStruttura;
        private SimpleStringProperty categoria;
        private SimpleStringProperty valutazioneMedia;
        private SimpleStringProperty orarioApertura;
        private SimpleStringProperty città;

        public OggettoTabella(String nomeStruttura, int numVisitatori, int numClienti, int numReviews, String categoria, String valutazioneMedia, String orarioApertura, String città) {
            this.numVisitatori = new SimpleIntegerProperty(numVisitatori);
            this.numClienti = new SimpleIntegerProperty(numClienti);
            this.numReviews = new SimpleIntegerProperty(numReviews);
            this.nomeStruttura = new SimpleStringProperty(nomeStruttura);
            this.categoria = new SimpleStringProperty(categoria);
            this.valutazioneMedia = new SimpleStringProperty(valutazioneMedia);
            this.orarioApertura = new SimpleStringProperty(orarioApertura);
            this.città = new SimpleStringProperty(città);
        }

        public String getCategoria() {
            return categoria.get();
        }

        public void setCategoria(String categoria) {
            this.categoria.set(categoria);
        }

        public SimpleStringProperty categoriaProperty() {
            return categoria;
        }

        public String getValutazioneMedia() {
            return valutazioneMedia.get();
        }

        public void setValutazioneMedia(String valutazioneMedia) {
            this.valutazioneMedia.set(valutazioneMedia);
        }

        public SimpleStringProperty valutazioneMediaProperty() {
            return valutazioneMedia;
        }

        public String getOrarioApertura() {
            return orarioApertura.get();
        }

        public void setOrarioApertura(String orarioApertura) {
            this.orarioApertura.set(orarioApertura);
        }

        public SimpleStringProperty orarioAperturaProperty() {
            return orarioApertura;
        }

        public String getCittà() {
            return città.get();
        }

        public void setCittà(String città) {
            this.città.set(città);
        }

        public SimpleStringProperty cittàProperty() {
            return città;
        }

        public String getNomeStruttura() {
            return nomeStruttura.get();
        }

        public void setNomeStruttura(String nomeStruttura) {
            this.nomeStruttura.set(nomeStruttura);
        }

        public SimpleStringProperty nomeStrutturaProperty() {
            return nomeStruttura;
        }

        public int getNumVisitatori() {
            return numVisitatori.get();
        }

        public void setNumVisitatori(int numVisitatori) {
            this.numVisitatori = new SimpleIntegerProperty(numVisitatori);
        }

        public int getNumClienti() {
            return numClienti.get();
        }

        public void setNumClienti(int numClienti) {
            this.numClienti = new SimpleIntegerProperty(numClienti);
        }

        public int getNumReviews() {
            return numReviews.get();
        }

        public void setNumReviews(int numReviews) {
            this.numReviews = new SimpleIntegerProperty(numReviews);
        }
    }
}

