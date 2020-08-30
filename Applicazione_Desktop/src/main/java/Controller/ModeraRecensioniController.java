package Controller;

import Boundary.Messaggio;
import Boundary.ModeraRecensioniForm;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeraRecensioniController {

    private static ModeraRecensioniController controller = null;
    private ModeraRecensioniForm moderaRecensioniForm;
    private Messaggio messaggio;

    private ModeraRecensioniController(){}

    public static ModeraRecensioniController getModeraRecensioniController() {
        if(controller == null)
            controller = new ModeraRecensioniController();
        return controller;
    }

    public void mostraModeraRecensioniForm() throws Exception {
        moderaRecensioniForm = new ModeraRecensioniForm();
        moderaRecensioniForm.start(new Stage());
    }
}
