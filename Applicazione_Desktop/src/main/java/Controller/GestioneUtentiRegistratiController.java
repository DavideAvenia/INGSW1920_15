package Controller;

import Boundary.GestioneUtentiForm;
import Boundary.Messaggio;
import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.UtenteDao;
import Entity.Utente;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestioneUtentiRegistratiController {

    private static GestioneUtentiRegistratiController gestioneUtentiRegistratiController = null;
    private static GestioneUtentiForm gestioneUtentiForm;
    /*Forms*/
    private PaginaPrincipaleAdminForm paginaPrincipaleAdminForm;
    private UtenteDao utenteDao;
    private DAOfactory daOfactory;

    private GestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm) {
        this.paginaPrincipaleAdminForm = paginaPrincipaleAdminForm;
    }


    public static GestioneUtentiRegistratiController gestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm) {
        if (gestioneUtentiRegistratiController == null) {
            gestioneUtentiRegistratiController = new GestioneUtentiRegistratiController(paginaPrincipaleAdminForm);
            return gestioneUtentiRegistratiController;
        }
        return gestioneUtentiRegistratiController;
    }

    public void mostraGestioneUtentiForm() throws IOException {
        GestioneUtentiForm gestioneUtentiForm = new GestioneUtentiForm();
        this.gestioneUtentiForm = gestioneUtentiForm;
        gestioneUtentiForm.start(new Stage());

    }

    public List<String> aggiornaLista(String filtro) {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }
        UtenteDao utenteDao = DAOfactory.getUtenteDao(service);
        this.utenteDao = utenteDao;

        return utenteDao.getAllUtenti(filtro);
    }

    public void mostraInfoUtente(String username) {
        Utente utente = utenteDao.getUtenteByUserID(username);

        if (gestioneUtentiForm != null) {
            gestioneUtentiForm.aggiornaTabella(username,utente.getNome(), utente.getCognome(), utente.getCellulare(), utente.getEmail(), utente.getNickname(), utente.isMod(), utente.isUseNick());
        }
    }

    public boolean cancellaUtente(String username){
        return utenteDao.cancellaUtente(username);
    }

    public void aggiornaUtente(String userId, String nome, String cognome, String nickname, String cellulare, String email, boolean useNick, boolean isMod){

        if (checkCellulare(cellulare) && checkEmail(email)) {

            Utente utente = new Utente(userId,nome,cognome,nickname,cellulare,email,useNick,isMod);
            utenteDao.aggiornaUtente(utente);

        }else{
            Messaggio messaggio = new Messaggio("Errore","L'email e/o il cellulare iseriti non sono validi!");
            try {
                messaggio.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkEmail(String email){

        if(email==null || email.equals("")){
            return false;
        }
        //EmailValidator provides email address validation according to RFC 822 standards.
        //Documentazione Apache
        EmailValidator emailValidator = EmailValidator.getInstance();

        return emailValidator.isValid(email);
    }

    private boolean checkCellulare(String cellulare){

        //Validazione cellulare secondo standar internazionale E.123 dell'International Telecommunications Union
        Pattern pattern = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
        Matcher matcher = pattern.matcher(cellulare);

        return matcher.matches();
    }

}
