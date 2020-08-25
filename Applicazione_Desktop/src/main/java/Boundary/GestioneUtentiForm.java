package Boundary;

import Controller.GestioneUtentiRegistratiController;
import javafx.application.Application;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneUtentiForm extends Application implements Initializable {


    private static String lastFiltro = "Username";
    @FXML
    private ListView<String> listaNomiUtenti;
    @FXML
    private ComboBox<String> filtri;
    @FXML
    private Button cancellaUtente;
    @FXML
    private Button aggiornaUtente;
    @FXML
    private Button indietro;
    @FXML
    private TableView<UtenteModel> infoUtente;
    @FXML
    private TableColumn<UtenteModel, String> colonnaNome;
    @FXML
    private TableColumn<UtenteModel, String> colonnaCognome;
    @FXML
    private TableColumn<UtenteModel, String> colonnaNickname;
    @FXML
    private TableColumn<UtenteModel, String> colonnaEmail;
    @FXML
    private TableColumn<UtenteModel, String> colonnaCellulare;
    @FXML
    private TableColumn<UtenteModel, String> colonnaMod;
    @FXML
    private TableColumn<UtenteModel, String> colonnaUseNick;
    private GestioneUtentiRegistratiController gestioneUtentiRegistratiController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestioneUtentiForm.fxml"));
        primaryStage.setTitle("Gestione Utenti");
        primaryStage.setScene(new Scene(root, 815, 488));
        primaryStage.show();
    }

    public void bottoneCancellaCliccato(ActionEvent actionEvent) {
        String userId = listaNomiUtenti.getSelectionModel().getSelectedItem();

        boolean result = gestioneUtentiRegistratiController.cancellaUtente(userId);

        if (result == true) {
            /*Chiusura vecchia UI*/
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close(); //GC will free memory

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestioneUtentiForm.fxml"));
            try {
                Parent root = (Parent) fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setTitle("Gestione Utenti");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void bottoneIndietroPremuto(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void bottoneApplicaCliccato(ActionEvent actionEvent) {

        String username = listaNomiUtenti.getSelectionModel().getSelectedItem();
        String tokens[] = username.split(" ");
        String userId = tokens[0];
        UtenteModel utente = infoUtente.getSelectionModel().getSelectedItem();

        gestioneUtentiRegistratiController.aggiornaUtente(userId, utente.getNome(), utente.getCognome(), utente.getNickname(), utente.getCellulare(), utente.getEmail(), Boolean.parseBoolean(utente.getUseNick()), Boolean.parseBoolean(utente.getIsMod()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gestioneUtentiRegistratiController = GestioneUtentiRegistratiController.gestioneUtentiRegistratiController(null);

        ObservableList<String> listaFiltri = FXCollections.observableArrayList("Username", "Cognome", "Email", "Cellulare","Moderatori");
        filtri.setItems(listaFiltri);
        filtri.getSelectionModel().select(lastFiltro);

        ObservableList<String> listaUsername = FXCollections.observableArrayList(gestioneUtentiRegistratiController.aggiornaLista(filtri.getSelectionModel().getSelectedItem()));
        listaNomiUtenti.setItems(listaUsername);

        /*Inizializzazione colonne tableView*/
        infoUtente.setEditable(true);
        colonnaNome.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("cognome"));
        colonnaCellulare.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("cellulare"));
        colonnaEmail.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("email"));
        colonnaNickname.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("nickname"));
        colonnaMod.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("isMod"));
        colonnaUseNick.setCellValueFactory(new PropertyValueFactory<UtenteModel, String>("useNick"));

        colonnaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaNome.setEditable(true);
        colonnaCognome.setEditable(true);
        colonnaCognome.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaCellulare.setEditable(true);
        colonnaCellulare.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaEmail.setEditable(true);
        colonnaEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaNickname.setEditable(true);
        colonnaNickname.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaMod.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaMod.setEditable(true);
        colonnaUseNick.setEditable(true);
        colonnaUseNick.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void filtroCambiato(ActionEvent actionEvent) {
        ObservableList<String> listaUsername = FXCollections.observableArrayList(gestioneUtentiRegistratiController.aggiornaLista(filtri.getSelectionModel().getSelectedItem()));
        listaNomiUtenti.setItems(listaUsername);
    }

    public void nomeListaCliccato(MouseEvent mouseEvent) {
        String username = listaNomiUtenti.getSelectionModel().getSelectedItem();
        gestioneUtentiRegistratiController.mostraInfoUtente(username, filtri.getSelectionModel().getSelectedItem());


        /*Chiusura UI*/
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void aggiornaTabella(String username, String nome, String cognome, String cellulare, String email, String nickname, boolean isMod, boolean useNick, String filtro) {

        /*Aggiornamento UI*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestioneUtentiForm.fxml"));
        lastFiltro = filtro;
        try {
            Parent root = (Parent) fxmlLoader.load();
            GestioneUtentiForm gestioneUtentiForm = fxmlLoader.getController();

            UtenteModel utenteModel = new UtenteModel(nome, cognome, cellulare, email, nickname, String.valueOf(isMod), String.valueOf(useNick));
            ArrayList<UtenteModel> listaUtenteModel = new ArrayList<UtenteModel>();
            listaUtenteModel.add(utenteModel);

            ObservableList<UtenteModel> observableList = FXCollections.observableArrayList(listaUtenteModel);

            gestioneUtentiForm.infoUtente.setItems(observableList);
            gestioneUtentiForm.listaNomiUtenti.getSelectionModel().select(username);
            gestioneUtentiForm.filtri.getSelectionModel().select(filtro);


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Gestione Utenti");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nomeEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setNome(cellEdited.getNewValue());
    }

    public void cognomeEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setCognome(cellEdited.getNewValue());
    }

    public void nicknameEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setNickname(cellEdited.getNewValue());
    }

    public void emailEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setEmail(cellEdited.getNewValue());
    }

    public void cellulareEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setCellulare(cellEdited.getNewValue());
    }

    public void isModEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setIsMod(cellEdited.getNewValue());

    }

    public void useNickEdited(TableColumn.CellEditEvent<UtenteModel, String> cellEdited) {
        cellEdited.getTableView().getItems().get(cellEdited.getTablePosition().getRow()).setUseNick(cellEdited.getNewValue());

    }


    public class UtenteModel {

        private SimpleStringProperty nome;
        private SimpleStringProperty cognome;
        private SimpleStringProperty cellulare;
        private SimpleStringProperty email;
        private SimpleStringProperty nickname;
        private SimpleStringProperty isMod;
        private SimpleStringProperty useNick;

        public UtenteModel(String nome, String cognome, String cellulare, String email, String nickname, String isMod, String useNick) {
            this.nome = new SimpleStringProperty(nome);
            this.cognome = new SimpleStringProperty(cognome);
            this.cellulare = new SimpleStringProperty(cellulare);
            this.email = new SimpleStringProperty(email);
            this.nickname = new SimpleStringProperty(nickname);
            this.isMod = new SimpleStringProperty(isMod);
            this.useNick = new SimpleStringProperty(useNick);
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

        public String getIsMod() {
            return isMod.get();
        }

        public void setIsMod(String isMod) {
            this.isMod = new SimpleStringProperty(isMod);
            ;
        }

        public String getUseNick() {
            return useNick.get();
        }

        public void setUseNick(String useNick) {
            this.useNick = new SimpleStringProperty(useNick);
            ;
        }
    }
}
