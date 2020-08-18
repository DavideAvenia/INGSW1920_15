package Controller;

import Boundary.GestioneUtentiForm;
import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.UtenteDao;
import Entity.Utente;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
        } else {
            System.out.println("é null!");
        }
    }

    public boolean cancellaUtente(String username){
        return utenteDao.cancellaUtente(username);
    }
}
