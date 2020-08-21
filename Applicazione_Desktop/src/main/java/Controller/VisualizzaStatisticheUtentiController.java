package Controller;

import Boundary.PaginaPrincipaleAdminForm;

public class VisualizzaStatisticheUtentiController {
    private static VisualizzaStatisticheUtentiController visualizzaStatisticheUtentiController = null;
    private PaginaPrincipaleAdminForm paginaPrincipaleAdminForm;

    private VisualizzaStatisticheUtentiController(){}

    public static VisualizzaStatisticheUtentiController getVisualizzaStatisticheUtentiController(){
        if(visualizzaStatisticheUtentiController == null){
            visualizzaStatisticheUtentiController = new VisualizzaStatisticheUtentiController();
            return visualizzaStatisticheUtentiController;
        }
        return visualizzaStatisticheUtentiController;
    }

    public void mostraStatisticheUtenti(){

    }

}
