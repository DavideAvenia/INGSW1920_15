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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModeraRecensioniForm extends Application implements Initializable {


    //Pezzi da visuallizzare a sinistra della form
    @FXML ComboBox<String> comboModeraRecensioni;
    @FXML ListView<String> listaAnteprimaRecensioni;

    //Componenti della recensione
    @FXML Label testoRecensione;
    @FXML Label numeroValutazione;
    @FXML Label connotatiUtente;
    @FXML ImageView imageViewRecensione;
    @FXML Label nomeStruttura;

    //Bottoni
    @FXML Button indietroButton;
    @FXML Button approvaButton;
    @FXML Button disapprovaButton;

    private ModeraRecensioniController moderaRecensioniController = ModeraRecensioniController.getModeraRecensioniController();
    private List<String> listaAnteprime;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ModeraRecensioniForm.fxml"));
        stage.setTitle("Modera Recensioni");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Deve fare per forza prima getAllRecensioniByPending e poi getListaAnteprime, altrimenti da nullPointer, da correggere
        moderaRecensioniController.getAllRecensioniByPending();
        listaAnteprime = moderaRecensioniController.getListaAnteprime();

        //inserimento oggeti
        listaAnteprimaRecensioni.setEditable(false);
        ObservableList<String> recensioniObs = FXCollections.observableArrayList(listaAnteprime);
        listaAnteprimaRecensioni.setItems(recensioniObs);
        recensioniObs.addAll(listaAnteprime);

        //Devo selezionare il primo oggetto e inizializzare gli elementi all'interno delle label
        listaAnteprimaRecensioni.getSelectionModel().selectFirst();
        moderaRecensioniController.mostraRecensione(0);

        //Ricordati di mettere le credenziali qui e in awscognito
    }

    public void indietroButtonPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    //Devono controllare che la recensione si stata cliccata
    public void disapprovaButtonPremuto(ActionEvent actionEvent) throws Exception {
        moderaRecensioniController.disapprovaRecensione();
    }

    public void approvaButtonPremuto(ActionEvent actionEvent) throws Exception {
        moderaRecensioniController.approvaRecensione();
    }

    public void mostraRecensione(String testoRecensione, String urlImmagine, float valutazione, String userNameUtente, String nomeStruttura){
        //Devo fare le new perché da nullPointer se provo ad impostare il testo senza instanza
        this.testoRecensione = new Label(testoRecensione);
        this.testoRecensione.setText(testoRecensione);

        this.connotatiUtente = new Label(userNameUtente);
        this.connotatiUtente.setText(userNameUtente);

        this.numeroValutazione = new Label(Float.toString(valutazione));
        this.numeroValutazione.setText(Float.toString(valutazione));

        this.nomeStruttura = new Label(nomeStruttura);
        this.nomeStruttura.setText(nomeStruttura);

        /*Image image = new Image(urlImmagine);
        imageViewRecensione = new ImageView(image);
        imageViewRecensione.setImage(image);*/
    }

}
