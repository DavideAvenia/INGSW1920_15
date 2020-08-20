package DAO;

import Services.AWSCognito;
import Services.AWSMySQLRDS;

public abstract class DAOfactory {
    public static StatisticheStruttureDAO getStatisticheStruttureDAO(String service) {
        //Riconosce AWS
        switch (service) {
            case "AWS":
                return new AWSMySQLRDS();
        }
        return null;
    }

    public static StatisticheUtentiDAO getStatisticheUtentiDAO(String service){
        switch (service){
            case "AWS":
                return new AWSMySQLRDS();
        }
        return null;
    }

    public static UtenteDao getUtenteDao(String service) {
        switch (service) {
            case "AWS":
                return new AWSCognito();
        }
        return null;
    }
}
