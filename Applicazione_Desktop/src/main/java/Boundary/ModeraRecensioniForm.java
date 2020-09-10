package Boundary;

import Controller.ModeraRecensioniController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class ModeraRecensioniForm extends Application implements Initializable {

    //Pezzi da visuallizzare a sinistra della form
    @FXML
    ComboBox<String> comboModeraRecensioni;
    @FXML
    ListView<String> listaAnteprimaRecensioni;

    //Componenti della recensione
    @FXML
    Label testoRecensioneLabel;
    @FXML
    Label numeroValutazioneLabel;
    @FXML
    Label connotatiUtenteLabel;
    @FXML
    ImageView imageViewRecensione;
    @FXML
    Label nomeStrutturaLabel;

    //Bottoni
    @FXML
    Button indietroButton;
    @FXML
    Button approvaButton;
    @FXML
    Button disapprovaButton;

    private ModeraRecensioniController moderaRecensioniController = ModeraRecensioniController.getModeraRecensioniController();
    private List<String> listaAnteprime;
    private int indiceSelezionato;

    @Override
    public void start(Stage stage) throws Exception {
        startGui(stage);
    }

    public void startGui(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ModeraRecensioniForm.fxml"));
        stage.setTitle("Modera Recensioni");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void aggiornaPagina(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        startGui(new Stage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Deve fare per forza prima getAllRecensioniByPending e poi getListaAnteprime, altrimenti da nullPointer, da correggere
        moderaRecensioniController.getAllRecensioniByPending();
        listaAnteprime = moderaRecensioniController.getListaAnteprime();

        //inserimento oggetti e LO DEVE FARE UNA SOLA VOLTA
        listaAnteprimaRecensioni.setEditable(false);
        ObservableList<String> recensioniObs = FXCollections.observableArrayList(listaAnteprime);
        listaAnteprimaRecensioni.setItems(recensioniObs);

        //Devo selezionare il primo oggetto e inizializzare gli elementi all'interno delle label
        listaAnteprimaRecensioni.getSelectionModel().selectFirst();
        Map<String, String> mapInit = moderaRecensioniController.mostraRecensione(0);
        indiceSelezionato = 0;
        if (!mapInit.isEmpty()) {
            testoRecensioneLabel.setText(mapInit.get("testoRecensione"));
            connotatiUtenteLabel.setText(mapInit.get("connotatiUtente"));
            numeroValutazioneLabel.setText(mapInit.get("valutazione"));
            nomeStrutturaLabel.setText(mapInit.get("nomeStruttura"));
            /*Image immagineRecensione = new Image(mapInit.get("urlImmagine"));
            imageViewRecensione.setImage(immagineRecensione);*/
        }

        listaAnteprimaRecensioni.setOnMouseClicked(mouseEvent -> {
            indiceSelezionato = listaAnteprimaRecensioni.getSelectionModel().getSelectedIndex();
            Map<String, String> mapToHandle = moderaRecensioniController.mostraRecensione(indiceSelezionato);
            testoRecensioneLabel.setText(mapToHandle.get("testoRecensione"));
            connotatiUtenteLabel.setText(mapToHandle.get("connotatiUtente"));
            numeroValutazioneLabel.setText(mapToHandle.get("valutazione"));
            nomeStrutturaLabel.setText(mapToHandle.get("nomeStruttura"));
            /*Image immagineRecensione = new Image(mapInit.get("urlImmagine"));
            imageViewRecensione.setImage(immagineRecensione);*/
        });
    }

    public void indietroButtonPremuto(ActionEvent actionEvent) {
        listaAnteprime.clear();
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void disapprovaButtonPremuto(ActionEvent actionEvent) throws Exception {
        moderaRecensioniController.disapprovaRecensione(indiceSelezionato);
        listaAnteprime.clear();
        aggiornaPagina(actionEvent);
    }

    public void approvaButtonPremuto(ActionEvent actionEvent) throws Exception {
        moderaRecensioniController.approvaRecensione(indiceSelezionato);
        listaAnteprime.clear();
        aggiornaPagina(actionEvent);

    }

}
