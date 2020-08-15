package Controller;

import Boundary.StatisticheStruttureForm;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;
import Services.AWSMySQLRDS;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StatisticheStruttureController {

    private static StatisticheStruttureController statisticheStruttureController = null;

    private StatisticheStruttureController(){}

    public static StatisticheStruttureController getStatisticheStruttureController(){
        if(statisticheStruttureController == null){
            return new StatisticheStruttureController();
        }return statisticheStruttureController;
    }

    public void mostraStatistiche() throws IOException {

        /*PRIMA PARTE: Recupero delle informazioni*/
        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        StatisticheStruttureDAO S = DAOfactory.getStatisticheStruttureDAO(file1);
        List<StatisticheStrutture> L2 = S.getAllStatisticheStrutture(); /*hai recuperato la lista di statistiche*/

        /*PARTE DUE: sistemi le informazioni come ti servono*/
        /*
        * codice
        *
        * */

        /*PARTE TRE: Passi le informazioni sistemate ad un metodo dell'interfaccia per aggiornarla*/
        StatisticheStruttureForm.popolaInterfaccia(/*passi tutte le liste che hai creato*/);

    }


}
