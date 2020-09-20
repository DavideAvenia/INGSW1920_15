package Controller;

import Boundary.GestioneUtentiForm;
import Boundary.Messaggio;
import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.StatisticheUtentiDAO;
import DAO.UtenteDao;
import Entity.Utente;
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
    private Messaggio messaggio;

    private UtenteDao utenteDao;
    private DAOfactory daoFactory;
    private StatisticheUtentiDAO statisticheUtentiDAO;

    private GestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm) {
        this.paginaPrincipaleAdminForm = paginaPrincipaleAdminForm;
    }


    public static GestioneUtentiRegistratiController gestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm) {
        if (gestioneUtentiRegistratiController == null) {
            gestioneUtentiRegistratiController = new GestioneUtentiRegistratiController(paginaPrincipaleAdminForm);
        }
        return gestioneUtentiRegistratiController;
    }

    public void mostraGestioneUtentiForm() throws IOException {
        gestioneUtentiForm = new GestioneUtentiForm();
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

    public void mostraInfoUtente(String username, String filtro, int lastUtenteSelected) {
        Utente utente = utenteDao.getUtenteByUserID(username);

        if (gestioneUtentiForm != null) {
            gestioneUtentiForm.aggiornaTabella(utente.getNome(), utente.getCognome(), utente.getCellulare(), utente.getEmail(), utente.getNickname(), utente.isMod(), utente.isUseNick(), filtro,lastUtenteSelected);
        }
    }

    public boolean cancellaUtente(String username) {
        if (utenteDao.cancellaUtente(username)) {
            Thread thread = new Thread(() -> {
                String service = "";

                File file = new File("config.txt");
                try {
                    BufferedReader br = new BufferedReader(new FileReader((file)));
                    service = br.readLine();
                } catch (IOException e) {
                    System.out.println("Non è stato trovato il file di configurazione!");
                }

                statisticheUtentiDAO = DAOfactory.getStatisticheUtentiDAO(service);
                statisticheUtentiDAO.deleteStatisticheUtente(username);
            });
            thread.start();
            return true;
        }
        return false;
    }

    public void aggiornaUtente(String userId, String nome, String cognome, String nickname, String cellulare, String email, boolean useNick, boolean isMod) {

        if (checkCellulare(cellulare) && checkEmail(email)) {

            Utente utente = new Utente(userId, nome, cognome, nickname, cellulare, email, useNick, isMod);
            if (!utenteDao.aggiornaUtente(utente)) {
                mostraMessaggio("Errore", "Non è stato possibile modificare l'utente!");
            } else {
                mostraMessaggio("Successo", "Le informazioni dell'utente sono state aggiornate!");
            }

        } else {
            mostraMessaggio("Errore", "L'email e/o il cellulare iseriti non sono validi!");
        }
    }

    private boolean checkEmail(String email) {

        if (email == null || email.equals("")) {
            return false;
        }
        //EmailValidator provides email address validation according to RFC 822 standards.
        //Documentazione Apache
        EmailValidator emailValidator = EmailValidator.getInstance();

        return emailValidator.isValid(email);
    }

    private boolean checkCellulare(String cellulare) {

        //Validazione cellulare secondo standar internazionale E.123 dell'International Telecommunications Union
        Pattern pattern = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
        Matcher matcher = pattern.matcher(cellulare);

        return matcher.matches();
    }

    public void mostraMessaggio(String title, String mess) {
        Messaggio messaggio = new Messaggio(title, mess);
        try {
            messaggio.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
