package Controller;

import Boundary.LoginForm;
import Boundary.Messaggio;
import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.UtenteDao;
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

    //Mostrerà una finestra/popup del login non effettuato
    public void mostraMessaggioFallimentoLogin() throws Exception {
        Messaggio messaggio = new Messaggio("Login Fallito", "L'email o la password non sono corretti");
        messaggio.start(new Stage());
    }

    public void mostraMessaggioTesting(String test) throws Exception {
        Messaggio messaggio = new Messaggio("Test", test);
        messaggio.start(new Stage());
    }

    //Controlla le credenziali se sono presenti (SOLO ADMIN E MOD)
    public void controllaCredenzialiPerLogin(String email, String password) throws Exception {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }

        UtenteDao utenteDao = DAOfactory.getUtenteDao(service);

        if (this.isAdmin(email)) {
            String test = utenteDao.effettuaLogin(email, password);
            this.mostraMessaggioTesting(test);
        } else /*if (this.isMod(email)) quello che c'è scritto sopra ma con il mod, se l'if è true, mostrerà la paginaMod*/
            this.mostraMessaggioFallimentoLogin();
    }

    private boolean isMod(String email) {
        String tokens[] = email.split("_");
        tokens[0].toLowerCase();
        if (tokens[0].equals("mod"))
            return true;
        else
            return false;
    }

    //Deve restiuire un Boolean, controlla se nel nome è presente la parola ADMIN, ricorda il delimitatore tra ADMIN e l'email
    public boolean isAdmin(String email) {
        String tokens[] = email.split("_");
        tokens[0].toLowerCase();
        if (tokens[0].equals("admin"))
            return true;
        else
            return false;
    }


}
