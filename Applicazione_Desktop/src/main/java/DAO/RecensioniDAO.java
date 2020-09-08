package DAO;

import Entity.Recensioni;
import java.util.List;

public interface RecensioniDAO {

    final String APIGETALLRECENSIONIBYPENDING = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getallrecensionibypending";
    final String APIAPPROVARECENSIONE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/approvarecensione";
    final String APIDISAPPROVARECENSIONE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/disapprovarecensione";

    public List<Recensioni> getAllRecensioniByPending();
    //Serve una chiave per poter prendere la recensione dal database da mettere nella firma
    //String usernameUtente, String nomeStruttura, double latitudine, double longitudine
    public boolean approvaRecensione(Recensioni r);
    public boolean disapprovaRecensione(Recensioni r);

}
