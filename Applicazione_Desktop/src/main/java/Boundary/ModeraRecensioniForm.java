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
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
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
    @FXML ImageView immagineRecensione;

    //Bottoni
    @FXML Button indietroButton;
    @FXML Button approvaButton;
    @FXML Button disapprovaButton;

    private ModeraRecensioniController moderaRecensioniController = ModeraRecensioniController.getModeraRecensioniController();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ModeraRecensioniForm.fxml"));
        stage.setTitle("Modera Recensioni");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaAnteprimaRecensioni.setEditable(false);
        ObservableList<String> listaAnteprimeRecensioniObs = FXCollections.observableArrayList("String");
        listaAnteprimaRecensioni.setItems(listaAnteprimeRecensioniObs);
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



}
