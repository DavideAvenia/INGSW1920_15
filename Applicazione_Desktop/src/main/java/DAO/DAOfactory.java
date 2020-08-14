package DAO;

import Services.AWSMySQLRDS;

public abstract class DAOfactory {
    public static StatisticheStruttureDAO getStatisticheStruttureDAO(String service){
        //Riconosce AWS
        switch(service){
            case "AWS":
                return new AWSMySQLRDS();
        }
        return null;
    }
}
