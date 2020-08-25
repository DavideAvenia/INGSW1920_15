package DAO;

import Entity.StatisticheUtenti;

import java.util.List;

public interface StatisticheUtentiDAO {
    String APIGETALLSTATISTICHEUTENTI = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getallstatisticheutenti";
    String APIDELETESTATISTICHEUTENTE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/deletestatisticheutente";
    public List<StatisticheUtenti> getStats();
    public void deleteStatisticheUtente(String userId);
}
