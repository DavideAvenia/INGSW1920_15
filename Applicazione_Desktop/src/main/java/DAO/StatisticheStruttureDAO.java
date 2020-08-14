package DAO;

import Entity.StatisticheStrutture;

import java.util.List;

public interface StatisticheStruttureDAO {
    public List<StatisticheStrutture> getStatisticheStrutture ();
    public List<StatisticheStrutture> getAllStatisticheStrutture();
    public void aggiornaStatistiche();
    public boolean cancellaStatisticheStruttureById(int Id);
}

