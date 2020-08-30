package DAO;

import Entity.Recensioni;
import java.util.List;

public interface RecensioniDAO {

    public List<Recensioni> getAllRecensioniByPending();
}
