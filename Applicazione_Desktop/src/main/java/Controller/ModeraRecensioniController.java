package Controller;

import Boundary.Messaggio;
import Boundary.ModeraRecensioniForm;
import DAO.DAOfactory;
import DAO.RecensioniDAO;
import Entity.Recensioni;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeraRecensioniController {

    private static ModeraRecensioniController controller = null;
    private ModeraRecensioniForm moderaRecensioniForm;
    private Messaggio messaggio;
    private List<String> listaAnteprime = new ArrayList<String>();
    private List<Recensioni> listaRecensioni = new ArrayList<Recensioni>();

    private ModeraRecensioniController(){}

    public static ModeraRecensioniController getModeraRecensioniController() {
        if(controller == null)
            controller = new ModeraRecensioniController();
        return controller;
    }

    public void mostraModeraRecensioniForm() throws Exception {
        moderaRecensioniForm = new ModeraRecensioniForm();
        moderaRecensioniForm.start(new Stage());
    }

    public void mostraMessaggio(String title, String msg) throws Exception {
        Messaggio messaggio = new Messaggio(title, msg);
        messaggio.start(new Stage());
    }

    //Prima prendo le recensioni, le visualizzo a schermo
    //Ricordati di permettere di cliccare approva e disapprova SOLO SE è stata cliccata una recensione

    public boolean getAllRecensioniByPending(){
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }

        RecensioniDAO recensioniDAO = DAOfactory.getRecensioniDAO(service);
        listaRecensioni = recensioniDAO.getAllRecensioniByPending();
        for(Recensioni r: listaRecensioni){
            listaAnteprime.add(r.getTestoRecensione());
        }

        return true;
    }

    public List<String> getListaAnteprime(){
        return listaAnteprime;
    }

    public Map<String, String> mostraRecensione(int i){
        Recensioni r = listaRecensioni.get(i);
        Map<String,String> mapInit = new HashMap<String, String>();

        mapInit.put("testoRecensione",r.getTestoRecensione());
        mapInit.put("urlImmagine",r.getUrlImmagine());
        mapInit.put("valutazione", Float.toString(r.getValutazione()));
        mapInit.put("connotatiUtente", r.getUserNameUtente());
        mapInit.put("nomeStruttura", r.getNomeStruttura());

        return mapInit;
    }

    public void approvaRecensione() throws Exception {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }

        RecensioniDAO recensioniDAO = DAOfactory.getRecensioniDAO(service);
        recensioniDAO.approvaRecensione();

        mostraMessaggio("Approvazione", "La recensione è stata approvata e adesso sarà visibile sull'app");
    }


    public void disapprovaRecensione() throws Exception {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }

        RecensioniDAO recensioniDAO = DAOfactory.getRecensioniDAO(service);

        recensioniDAO.disapprovaRecensione();

        mostraMessaggio("Disapprovazione", "La recensione è stata disapprovata e cancellata");
    }
}
