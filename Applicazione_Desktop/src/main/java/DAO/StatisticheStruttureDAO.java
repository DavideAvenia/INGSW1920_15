package DAO;

import Entity.StatisticheStrutture;

import java.util.List;

public interface StatisticheStruttureDAO {
    List<StatisticheStrutture> getStatisticheStruttureByFiltro(String filtro);

    List<StatisticheStrutture> getAllStatisticheStrutture();

}

