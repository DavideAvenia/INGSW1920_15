package DAO;

import Entity.Utente;
import Services.AWSCognito;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;

import java.util.List;

public interface UtenteDao {
    public List<String> getAllUtenti(String filtro);

    public Utente getUtenteByUserID(String username);

    public boolean aggiornaUtente(String utente);

    public boolean cancellaUtente(String utente);

    public void effettuaLogin(String email, String password);

}
