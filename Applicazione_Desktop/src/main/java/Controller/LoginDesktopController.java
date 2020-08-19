package Controller;

import org.jetbrains.annotations.NotNull;

public class LoginDesktopController {

    private static LoginDesktopController instanza = null;

    private LoginDesktopController(){}

    public LoginDesktopController getInstanza(){
        if(instanza==null)
            instanza = new LoginDesktopController();
        return instanza;
    }

    //Mostrerà una finestra/popup del login effettutato o meno
    public void mostraMessaggio(){

    }

    //Controlla le credenziali se sono presenti (SOLO ADMIN E MOD)
    public void ControllaCredenziali(String email, String password){

    }

    //Deve restiuire un Boolean, controlla se nel nome è presente la parola ADMIN, ricorda il delimitatore tra ADMIN e l'email
    public boolean isAdmin(@NotNull String email){
        return email.toLowerCase().contains("admin_".toLowerCase());
    }



}
