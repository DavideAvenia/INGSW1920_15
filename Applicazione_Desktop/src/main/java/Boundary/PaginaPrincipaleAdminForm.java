package Boundary;

import Controller.*;
import Entity.Recensioni;
import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaginaPrincipaleAdminForm extends Application implements Initializable {
    @FXML
    private TextField cercausername;
    @FXML
    private TableView statsutenti;
    @FXML
    private TableColumn userid;
    @FXML
    private TableColumn livello;
    @FXML
    private TableColumn avgscore;
    @FXML
    private TableColumn logincount;
    @FXML
    private TableColumn nreview;
    @FXML
    private Button ricarica;
    @FXML
    private Button logout;

    private GestioneUtentiRegistratiController gestioneUtentiRegistratiController;
    private StatisticheStruttureController controller;
    private ModeraRecensioniController moderaRecensioniController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/PaginaPrincipaleAdminForm.fxml"));
        primaryStage.setTitle("Pagina Principale");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void bottoneGestioneAccountPremuto(ActionEvent actionEvent) throws IOException {
        gestioneUtentiRegistratiController = GestioneUtentiRegistratiController.gestioneUtentiRegistratiController(this);
        gestioneUtentiRegistratiController.mostraGestioneUtentiForm();
    }

    public void bottoneStrutturePremuto(ActionEvent actionEvent) throws Exception {
        controller = StatisticheStruttureController.getStatisticheStruttureController();
        controller.mostra();
    }

    public void bottoneModeraRecensioniPremuto(ActionEvent event) throws Exception{
        moderaRecensioniController = ModeraRecensioniController.getModeraRecensioniController();
        moderaRecensioniController.mostraModeraRecensioniForm();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VisualizzaStatisticheUtentiController controller = VisualizzaStatisticheUtentiController.getVisualizzaStatisticheUtentiController();
        try {
            ObservableList<tabellaUtenti> oblisto = controller.mostraStatisticheUtenti();
            userid.setCellValueFactory(new PropertyValueFactory<>("userID"));
            livello.setCellValueFactory(new PropertyValueFactory<>("livello"));
            avgscore.setCellValueFactory(new PropertyValueFactory<>("avgScore"));
            logincount.setCellValueFactory(new PropertyValueFactory<>("loginCounter"));
            nreview.setCellValueFactory(new PropertyValueFactory<>("numTotReviews"));
            statsutenti.setItems(oblisto);
            FilteredList<PaginaPrincipaleAdminForm.tabellaUtenti> filtro = new FilteredList<>(oblisto, p -> true);
            statsutenti.setItems(filtro);
            cercausername.setPromptText("Scrivi qui!");
            cercausername.setOnKeyReleased(keyEvent ->
                    filtro.setPredicate(p -> p.getUserID().toLowerCase().contains(cercausername.getText().toLowerCase().trim())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        LoginDesktopController loginControl2 = LoginDesktopController.getInstanzaLoginDesktopController();
        loginControl2.mostraPaginaLogin();
    }

    public void ricaricaPremuto(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PaginaPrincipaleAdminForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();


        Stage nuovostage = new Stage();
        nuovostage.initModality(Modality.APPLICATION_MODAL);
        nuovostage.setTitle("Pagina Amministratore");
        nuovostage.setScene(new Scene(root));
        nuovostage.show();
    }

    public static class tabellaUtenti {
        private SimpleStringProperty userID;
        private SimpleIntegerProperty livello;
        private SimpleFloatProperty avgScore;
        private SimpleIntegerProperty loginCounter;
        private SimpleIntegerProperty numTotReviews;

        public tabellaUtenti(String userID, Integer livello, Float avgScore, Integer loginCounter, Integer numTotReviews) {
            this.userID = new SimpleStringProperty(userID);
            this.livello = new SimpleIntegerProperty(livello);
            this.avgScore = new SimpleFloatProperty(avgScore);
            this.loginCounter = new SimpleIntegerProperty(loginCounter);
            this.numTotReviews = new SimpleIntegerProperty(numTotReviews);
        }

        public String getUserID() {
            return userID.get();
        }

        public void setUserID(String userID) {
            this.userID.set(userID);
        }

        public SimpleStringProperty userIDProperty() {
            return userID;
        }

        public int getLivello() {
            return livello.get();
        }

        public void setLivello(int livello) {
            this.livello.set(livello);
        }

        public SimpleIntegerProperty livelloProperty() {
            return livello;
        }

        public float getAvgScore() {
            return avgScore.get();
        }

        public void setAvgScore(float avgScore) {
            this.avgScore.set(avgScore);
        }

        public SimpleFloatProperty avgScoreProperty() {
            return avgScore;
        }

        public int getLoginCounter() {
            return loginCounter.get();
        }

        public void setLoginCounter(int loginCounter) {
            this.loginCounter.set(loginCounter);
        }

        public SimpleIntegerProperty loginCounterProperty() {
            return loginCounter;
        }

        public int getNumTotReviews() {
            return numTotReviews.get();
        }

        public void setNumTotReviews(int numTotReviews) {
            this.numTotReviews.set(numTotReviews);
        }

        public SimpleIntegerProperty numTotReviewsProperty() {
            return numTotReviews;
        }
    }
}
