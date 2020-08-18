package Boundary;

import Controller.GestioneUtentiRegistratiController;
import DAO.DAOfactory;
import DAO.UtenteDao;
import Entity.Utente;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneUtentiForm extends Application implements Initializable {


    @FXML private ListView<String> listaNomiUtenti;
    @FXML private ComboBox<String> filtri;
    @FXML private Button cancellaUtente;
    @FXML private Button aggiornaUtente;
    @FXML private Button indietro;

    @FXML private TableView<UtenteModel> infoUtente;
    @FXML private TableColumn<UtenteModel,String> colonnaNome;
    @FXML private TableColumn<UtenteModel,String> colonnaCognome;
    @FXML private TableColumn<UtenteModel,String> colonnaNickname;
    @FXML private TableColumn<UtenteModel,String> colonnaEmail;
    @FXML private TableColumn<UtenteModel,String> colonnaCellulare;
    @FXML private TableColumn<UtenteModel,Boolean> colonnaMod;
    @FXML private TableColumn<UtenteModel,Boolean> colonnaUseNick;

    private GestioneUtentiRegistratiController gestioneUtentiRegistratiController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestioneUtentiForm.fxml"));
        primaryStage.setTitle("Gestione Utenti");
        primaryStage.setScene(new Scene(root, 815, 488));
        primaryStage.show();
    }

    public void bottoneCancellaCliccato(ActionEvent actionEvent) {
    }

    public void bottoneIndietroPremuto(ActionEvent actionEvent) {
    }

    public void bottoneApplicaCliccato(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gestioneUtentiRegistratiController = GestioneUtentiRegistratiController.gestioneUtentiRegistratiController(null);

        ObservableList<String> listaFiltri = FXCollections.observableArrayList("Username","Cognome","Email","Cellulare");
        filtri.setItems(listaFiltri);
        filtri.getSelectionModel().selectFirst();

        ObservableList<String> listaUsername = FXCollections.observableArrayList(gestioneUtentiRegistratiController.aggiornaLista(filtri.getSelectionModel().getSelectedItem()));
        listaNomiUtenti.setItems(listaUsername);

        colonnaNome.setCellValueFactory(new PropertyValueFactory<UtenteModel,String>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<UtenteModel,String>("cognome"));
        colonnaCellulare.setCellValueFactory(new PropertyValueFactory<UtenteModel,String>("cellulare"));
        colonnaEmail.setCellValueFactory(new PropertyValueFactory<UtenteModel,String>("email"));
        colonnaNickname.setCellValueFactory(new PropertyValueFactory<UtenteModel,String>("nickname"));
        colonnaMod.setCellValueFactory(new PropertyValueFactory<UtenteModel,Boolean>("isMod"));
        colonnaUseNick.setCellValueFactory(new PropertyValueFactory<UtenteModel,Boolean>("useNick"));

    }

    public void filtroCambiato(ActionEvent actionEvent) {
        ObservableList<String> listaUsername = FXCollections.observableArrayList(gestioneUtentiRegistratiController.aggiornaLista(filtri.getSelectionModel().getSelectedItem()));
        listaNomiUtenti.setItems(listaUsername);
    }

    public void nomeListaCliccato(MouseEvent mouseEvent) {
        String username = listaNomiUtenti.getSelectionModel().getSelectedItem();

        gestioneUtentiRegistratiController.mostraInfoUtente(username);
        Node node = (Node)mouseEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    public void aggiornaTabella(String nome,String cognome,String cellulare, String email, String nickname, boolean isMod, boolean useNick){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestioneUtentiForm.fxml"));
        try{
            Parent root = (Parent) fxmlLoader.load();
            GestioneUtentiForm gestioneUtentiForm = fxmlLoader.getController();

            UtenteModel utenteModel = new UtenteModel(nome,cognome,cellulare,email,nickname,isMod,useNick);
            ArrayList<UtenteModel> listaUtenteModel = new ArrayList<UtenteModel>();
            listaUtenteModel.add(utenteModel);

            ObservableList<UtenteModel> observableList = FXCollections.observableArrayList(listaUtenteModel);

            gestioneUtentiForm.infoUtente.setItems(observableList);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestione Utenti");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class UtenteModel{

        private SimpleStringProperty nome;
        private SimpleStringProperty cognome;
        private SimpleStringProperty cellulare;
        private SimpleStringProperty email;
        private SimpleStringProperty nickname;
        private SimpleBooleanProperty isMod;
        private SimpleBooleanProperty useNick;

        public UtenteModel(String nome, String cognome, String cellulare, String email, String nickname, boolean isMod, boolean useNick) {
            this.nome = new SimpleStringProperty(nome);
            this.cognome = new SimpleStringProperty(cognome);
            this.cellulare = new SimpleStringProperty(cellulare);
            this.email = new SimpleStringProperty(email);
            this.nickname = new SimpleStringProperty(nickname);
            this.isMod = new SimpleBooleanProperty(isMod);
            this.useNick = new SimpleBooleanProperty(useNick);
        }

        public String getNome() {
            return nome.get();
        }

        public void setNome(String nome) {
            this.nome = new SimpleStringProperty(nome);
        }

        public String getCognome() {
            return cognome.get();
        }

        public void setCognome(String cognome) {
            this.cognome = new SimpleStringProperty(cognome);
        }

        public String getCellulare() {
            return cellulare.get();
        }

        public void setCellulare(String cellulare) {
            this.cellulare = new SimpleStringProperty(cellulare);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email = new SimpleStringProperty(email);
        }

        public String getNickname() {
            return nickname.get();
        }

        public void setNickname(String nickname) {
            this.nickname = new SimpleStringProperty(nickname);
        }

        public boolean isIsMod() {
            return isMod.get();
        }

        public void setIsMod(boolean isMod) {
            this.isMod = new SimpleBooleanProperty(isMod);
        }

        public boolean isUseNick() {
            return useNick.get();
        }

        public void setUseNick(boolean useNick) {
            this.useNick = new SimpleBooleanProperty(useNick);
        }
    }
}
