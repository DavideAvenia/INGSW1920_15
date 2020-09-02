package DAO;

import Entity.Recensioni;
import java.util.List;

public interface RecensioniDAO {

    final String APIGETALLRECENSIONIBYPENDING = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha";
    final String APIAPPROVARECENSIONE = "";
    final String APIDISAPPROVARECENSIONE = "";

    public List<Recensioni> getAllRecensioniByPending();
    //Serve una chiave per poter prendere la recensione dal database da mettere nella firma
    //String usernameUtente, String nomeStruttura, double latitudine, double longitudine
    public boolean approvaRecensione();
    public boolean disapprovaRecensione();

}
