package DAO;

import Boundary.ModelloStatisticheEStrutture;

import java.util.List;

public interface StatisticheStruttureDAO {
    final String APIGETALLSTATISTICHESTRUTTURE = "https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/getallstatistichestrutture";

    List<ModelloStatisticheEStrutture> getAllStatisticheStrutture();

}

