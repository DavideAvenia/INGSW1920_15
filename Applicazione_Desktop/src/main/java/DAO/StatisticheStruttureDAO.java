package DAO;

import Entity.StatisticheStrutture;

import java.util.List;

public interface StatisticheStruttureDAO {
    public List<StatisticheStrutture> getStatisticheStruttureByFiltro(String filtro);

    public List<StatisticheStrutture> getAllStatisticheStrutture(List<StatisticheStrutture> L1);

    public void aggiornaStatistiche();
}

