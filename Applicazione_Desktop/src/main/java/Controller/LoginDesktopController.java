package Controller;

import Boundary.LoginForm;
import Boundary.Messaggio;
import Boundary.PaginaPrincipaleAdminForm;
import Boundary.PaginaPrincipaleModForm;
import DAO.DAOfactory;
import DAO.UtenteDao;
import Entity.Utente;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginDesktopController {

    private static LoginDesktopController instanza = null;
    private LoginForm loginForm;
    private PaginaPrincipaleAdminForm paginaAdmin;

    private LoginDesktopController() {
    }

    public static LoginDesktopController getInstanzaLoginDesktopController() {
        if (instanza == null)
            instanza = new LoginDesktopController();
        return instanza;
    }

    public void mostraMessaggio(String title, String msg) throws Exception {
        Messaggio messaggio = new Messaggio(title, msg);
        messaggio.start(new Stage());
    }

    public void mostraPaginaLogin() {
        LoginForm login = new LoginForm();
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean controllaCredenzialiPerLogin(String userid, String password) throws Exception {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }

        UtenteDao utenteDao = DAOfactory.getUtenteDao(service);

        if (utenteDao.effettuaLogin(userid, password)) {
            if (this.isAdmin(userid)) {
                PaginaPrincipaleAdminForm p = new PaginaPrincipaleAdminForm();
                p.start(new Stage());
                return true;
            } else if (this.isMod(userid)) {
                PaginaPrincipaleModForm modp = new PaginaPrincipaleModForm();
                modp.start(new Stage());
                return true;
            } else {
                mostraMessaggio("Login fallito", "Non sei un mod/admin, non puoi accedere");
            }
        } else {
            mostraMessaggio("Login Fallito", "UserId/Email oppure password incorretti");
        }
        return false;
    }

    //Da correggere, l'utente ha una var booleana che s'è true, è un mod
    private boolean isMod(String userid) {
        String service = "";
        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }
        UtenteDao utenteDao = DAOfactory.getUtenteDao(service);
        Utente u = utenteDao.getUtenteByUserID(userid);
        if (u.isMod()) {
            return true;
        }
        return false;
    }

    //Deve restiuire un Boolean, controlla se nel nome è presente la parola ADMIN, ricorda il delimitatore tra ADMIN e l'email
    public boolean isAdmin(String userid) {
        String tokens[] = userid.split("_");
        tokens[0].toLowerCase();
        if (tokens[0].equals("admin"))
            return true;
        else
            return false;
    }
}

