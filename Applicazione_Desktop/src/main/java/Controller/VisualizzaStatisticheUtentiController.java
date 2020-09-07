package Controller;

import Boundary.PaginaPrincipaleAdminForm;
import DAO.DAOfactory;
import DAO.StatisticheUtentiDAO;
import Entity.StatisticheUtenti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class VisualizzaStatisticheUtentiController {
    private static VisualizzaStatisticheUtentiController visualizzaStatisticheUtentiController = null;
    private PaginaPrincipaleAdminForm paginaPrincipaleAdminForm;

    private VisualizzaStatisticheUtentiController() {
    }

    public static VisualizzaStatisticheUtentiController getVisualizzaStatisticheUtentiController() {
        if (visualizzaStatisticheUtentiController == null) {
            visualizzaStatisticheUtentiController = new VisualizzaStatisticheUtentiController();
            return visualizzaStatisticheUtentiController;
        }
        return visualizzaStatisticheUtentiController;
    }

    public ObservableList<PaginaPrincipaleAdminForm.TabellaUtenti> mostraStatisticheUtenti() throws IOException {
        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        StatisticheUtentiDAO sdao = DAOfactory.getStatisticheUtentiDAO(file1);
        List<StatisticheUtenti> userstats = sdao.getStats();
        ObservableList<PaginaPrincipaleAdminForm.TabellaUtenti> oblist = FXCollections.observableArrayList();
        for (StatisticheUtenti value : userstats) {
            PaginaPrincipaleAdminForm.TabellaUtenti og = new PaginaPrincipaleAdminForm.TabellaUtenti(
                    value.getUserID(),
                    value.getLivello(),
                    value.getAvgScore(),
                    value.getLoginCounter(),
                    value.getNumTotReviews());
            oblist.add(og);
        }
        return oblist;
    }
}
