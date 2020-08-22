package DAO;

import Entity.StatisticheUtenti;

import java.util.List;

public interface StatisticheUtentiDAO {
    final String APIGETALLSTATISTICHEUTENTI = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getallstatisticheutenti";

    List<StatisticheUtenti> getStats();
}
