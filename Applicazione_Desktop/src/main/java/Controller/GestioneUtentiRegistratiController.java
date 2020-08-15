package Controller;

import Boundary.GestioneUtentiForm;
import Boundary.PaginaPrincipaleAdminForm;
import javafx.stage.Stage;

import java.io.IOException;

public class GestioneUtentiRegistratiController {

    /*Forms*/
    private PaginaPrincipaleAdminForm paginaPrincipaleAdminForm;
    private GestioneUtentiForm gestioneUtentiForm;

    private static GestioneUtentiRegistratiController gestioneUtentiRegistratiController = null;

    private GestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm){
        this.paginaPrincipaleAdminForm = paginaPrincipaleAdminForm;
    }

    public static GestioneUtentiRegistratiController gestioneUtentiRegistratiController(PaginaPrincipaleAdminForm paginaPrincipaleAdminForm){
        if(gestioneUtentiRegistratiController == null){
            return new GestioneUtentiRegistratiController(paginaPrincipaleAdminForm);
        }return gestioneUtentiRegistratiController;
    }

    public void mostraGestioneUtentiForm() throws IOException {
        GestioneUtentiForm gestioneUtentiForm = new GestioneUtentiForm();
        gestioneUtentiForm.start(new Stage());

        this.gestioneUtentiForm = gestioneUtentiForm;
    }


}
