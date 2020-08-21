package DAO;

import Entity.Utente;

import java.util.List;

public interface UtenteDao {
    public List<String> getAllUtenti(String filtro);

    public Utente getUtenteByUserID(String username);

    public boolean aggiornaUtente(Utente utente);

    public boolean cancellaUtente(String utente);

    public String effettuaLogin(String email, String password);

}
