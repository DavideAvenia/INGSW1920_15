package DAO;

import Entity.Utente;

import java.util.List;

public interface UtenteDao {
    final String APIINCREMENTALOGINCOUNTER = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/incrementalogincounter";

    public List<String> getAllUtenti(String filtro);

    public Utente getUtenteByUserID(String username);

    public boolean aggiornaUtente(Utente utente);

    public boolean cancellaUtente(String utente);

    public boolean effettuaLogin(String email, String password);

    public void incrementaLoginCounter(String email);
}
