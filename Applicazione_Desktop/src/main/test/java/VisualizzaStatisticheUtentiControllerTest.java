import DAO.DAOfactory;
import DAO.StatisticheUtentiDAO;
import Entity.StatisticheUtenti;
import junit.framework.TestCase;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class VisualizzaStatisticheUtentiControllerTest extends TestCase {

    StatisticheUtentiDAO statisticheUtentiDAO;
    @Before
    public void setUp() throws Exception {
        super.setUp();

        File file = new File("config.txt");
        BufferedReader br = new BufferedReader(new FileReader((file)));
        String file1 = br.readLine();
        statisticheUtentiDAO = DAOfactory.getStatisticheUtentiDAO(file1);
    }

    /***************** TEST CASE BLACK BOX ************************/
    public void testMostraStatisticheUtentiListaNonNull() {

        List<StatisticheUtenti> listaRecuperata = statisticheUtentiDAO.getStats();

        assertTrue(listaRecuperata != null);

    }

    public void testMostraStatisticheUtentiCoerenzaLivelloNumRecensioni() {

        List<StatisticheUtenti> listaRecuperata = statisticheUtentiDAO.getStats();

        for(StatisticheUtenti curr: listaRecuperata){
            int liv = curr.getLivello();
            int numRev = curr.getNumTotReviews();

            switch (liv){
                case 1:
                    assertTrue(numRev >= 1 && numRev < 10);
                    break;
                case 2:
                    assertTrue(numRev >= 10 && numRev < 100);
                    break;
                case 3:
                    assertTrue(numRev >= 100);
                    break;
            }
        }
    }
}