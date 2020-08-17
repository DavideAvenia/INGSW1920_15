package Controller;

import Boundary.StatisticheStruttureForm;
import DAO.DAOfactory;
import DAO.StatisticheStruttureDAO;
import Entity.StatisticheStrutture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        List<Integer> numVisitatori = new ArrayList<>();
        List<Integer> numClienti = new ArrayList<>();
        List<Integer> numReviews = new ArrayList<>();
        List<String> nomi = new ArrayList<>();
        for(int i = 0; i < L2.size(); i++){
            StatisticheStrutture value = L2.get(i);
            numVisitatori.add(value.getNumVisitatori());
            numClienti.add(value.getNumClienti());
            numReviews.add(value.getNumReviews());
            nomi.add(value.getNome());
        /*PARTE TRE: Passi le informazioni sistemate ad un metodo dell'interfaccia per aggiornarla*/
        StatisticheStruttureForm.popolaInterfaccia(nomi,numVisitatori,numClienti,numReviews);

    }
}}
