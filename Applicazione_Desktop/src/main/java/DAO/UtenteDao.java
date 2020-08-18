package DAO;

import Entity.Utente;

import java.util.List;

public interface UtenteDao {
    public List<String> getAllUtenti(String filtro);

    public Utente getUtenteByUserID(String username);

    public boolean aggiornaUtente(String utente);

    public boolean cancellaUtente(String utente);
}
