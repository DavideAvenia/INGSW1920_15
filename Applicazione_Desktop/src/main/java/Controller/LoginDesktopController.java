package Controller;

import Boundary.LoginForm;
import Boundary.Messaggio;
import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.UtenteDao;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginDesktopController {

    private static LoginDesktopController instanza = null;
    private LoginForm loginForm;
    private PaginaPrincipaleAdminForm paginaAdmin;

    private LoginDesktopController(){}

    public LoginDesktopController getInstanza(){
        if(instanza==null)
            instanza = new LoginDesktopController();
        return instanza;
    }

    //Mostrerà una finestra/popup del login non effettuato
    public void mostraMessaggioFallimentoLogin() throws Exception {
        Messaggio messaggio = new Messaggio("Login Fallito","L'email o la password non sono corretti");
        messaggio.start(new Stage());
    }

    //Controlla le credenziali se sono presenti (SOLO ADMIN E MOD)
    public void ControllaCredenzialiPerLogin(String email, String password) throws Exception {
        String service = "";

        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader((file)));
            service = br.readLine();
        } catch (IOException e) {
            System.out.println("Non è stato trovato il file di configurazione!");
        }
        UtenteDao utenteDao = DAOfactory.getUtenteDao(service);

        if(this.isAdmin(email)){
            utenteDao.effettuaLogin(email, password);
            paginaAdmin.start(new Stage());
        }else /*if (this.isMod(email))*/
            this.mostraMessaggioFallimentoLogin();
    }

    private boolean isMod(String email) {
        return email.toLowerCase().contains("mod_".toLowerCase());
    }

    //Deve restiuire un Boolean, controlla se nel nome è presente la parola ADMIN, ricorda il delimitatore tra ADMIN e l'email
    public boolean isAdmin(String email){
        return email.toLowerCase().contains("admin_".toLowerCase());
    }

}
