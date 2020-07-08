package com.example.appmobile.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.appmobile.Dao.RecensioniDao;
import com.example.appmobile.Dao.StruttureDao;
import com.example.appmobile.entity.Recensioni;
import com.example.appmobile.entity.StatisticheStrutture;
import com.example.appmobile.entity.Strutture;
import com.google.android.gms.maps.model.LatLng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AWSMySQLRds implements StruttureDao, RecensioniDao {

    private Context context;
    Connection conn = null;


    private final String URL = "";
    private final String PORT = "3306";
    private final String USERNAME = "";
    private final String PASSWORD = "";

    public AWSMySQLRds(Context context){
        this.context = context;
    }

    @Override
    public List<Strutture> getStruttureByFiltri(String nome, String città, float valutazioneMedia, int distanzaDaDispositivo, String orarioApertura, String categoria, String rangePrezzo) {
        /*System.out.println("stiamo recuperando le strutture");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://i");
            Statement stmt = conn.createStatement();

            int i = stmt.executeUpdate("insert into test value('Davide','Android');");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        List<Strutture> listaStrutture = new ArrayList<Strutture>();

        listaStrutture.add(new Strutture("Stadio San Paolo","Napoli",4.0f,"50-300€","20:00-23:00","Intrattenimento",40.827967f,14.193008f,
                "Lo stadio San Paolo, già del Sole, è il principale impianto polisportivo della città" +
                        " di Napoli. Di proprietà del Comune di Napoli, è situato a Fuorigrotta, quartiere della X" +
                        " Municipalità. Dotato di palestre polifunzionali, spazi per le arti marziali e di un campo da" +
                        " pallacanestro, è conosciuto soprattutto dal punto di vista calcistico, essendo sede degli" +
                        " incontri interni della squadra del Napoli."));
        listaStrutture.add(new Strutture("Anfiteatro Flavio","Pozzuoli",3.9f,"5-20€","9:00-18:00","Museo",40.82336f,14.12434f,
                "L'Anfiteatro Flavio è uno dei due anfiteatri romani esistenti a Pozzuoli e risale alla seconda metà " +
                        "del I secolo d.C. Venne realizzato per far fronte all'incremento demografico di Puteoli, che aveva reso " +
                        "inadatto il vecchio edificio adibito per spettacoli pubblici in età repubblicana. Secondo solo al Colosseo" +
                        " e all'anfiteatro Campano in quanto capacità di capienza, sorge in concomitanza della convergenza di due" +
                        " vie principali, la Via Domitiana e la via per Napoli"));
        listaStrutture.add(new Strutture("Pompei","Pompei",2.9f,"10-50€","9:00-21:00","Museo",40.749183654785156f,14.500738143920898f,"" +
                "Pompei (in latino: Pompeii) è una città dell'evo antico, corrispondente all'attuale Pompei," +
                " la cui storia ha origine dal IX secolo a.C. per terminare nel 79, quando, a seguito dell'eruzione" +
                " del Vesuvio, viene ricoperta sotto una coltre di ceneri e lapilli alta circa sei metri. La sua" +
                " riscoperta e i relativi scavi, iniziati nel 1748, hanno riportato alla luce un sito archeologico che" +
                " nel 1997 è entrato a far parte della lista dei patrimoni dell'umanità dell'UNESCO, e che è il secondo " +
                "monumento italiano per visite dopo il sistema museale del Colosseo, Foro Romano e Palatino["));
        listaStrutture.add(new Strutture("Piazza del Plebiscito","Napoli",3.5f,"Gratuito","8:00-18:00","Cultura",40.85299f,14.24789f,
                "Piazza del Plebiscito (già largo di Palazzo o Foro Regio) è una piazza di Napoli posizionata al termine di via Toledo, non appena oltrepassata piazza Trieste e Trento.\n" +
                        "\n" +
                        "Ubicata nel centro storico, tra il lungomare e via Toledo, con una superficie di circa 25 000 metri" +
                        " quadrati la piazza si presenta come una delle più grandi della città e d'Italia e per questo è quella" +
                        " più utilizzata per le grandi manifestazioni."));
        listaStrutture.add(new Strutture("Teatro San Carlo","Napoli",5.0f,"50-120€","18:00-23:00","Intrattenimento",40.85299f,14.24789f,
                "l Teatro di San Carlo, noto semplicemente come Teatro San Carlo, è un teatro " +
                        "lirico di Napoli, tra i più famosi e prestigiosi al mondo, fondato nel 1737.[1]" +
                        " Può ospitare 1386 spettatori[2] e conta una vasta platea (22×28×23 m), cinque ordini" +
                        " di palchi disposti a ferro di cavallo più un ampio palco reale, un loggione ed un palcoscenico" +
                        " (34×33 m).[3][4] Date le sue dimensioni, struttura e antichità è stato modello per i successivi teatri d'Europa."));
        listaStrutture.add(new Strutture("Castel dell'ovo","Napoli",4.9f,"Gratuito","8:00-21:00","Culto",40.827222f,14.248611f,
                "Il castel dell'Ovo (castrum Ovi, in latino), è il castello più antico della città di Napoli[1] ed è uno degli elementi che spiccano maggiormente nel celebre panorama del golfo. Si trova tra i quartieri di San Ferdinando e Chiaia, di fronte a via Partenope.\n" +
                        "\n" +
                        "A causa di diversi eventi che hanno in parte distrutto l'originario aspetto normanno e grazie ai successivi lavori di" +
                        " ricostruzione avvenuti durante il periodo angioino ed aragonese, la linea architettonica del castello mutò drasticamente" +
                        " fino a giungere allo stato in cui si presenta oggi."));

        listaStruttureDatabase = listaStrutture;
        initDatabase();
        return listaStrutture;
    }

    @Override
    public Strutture getStrutturaByNomePosizione(String nome, LatLng posizione) {
        return null;
    }

    @Override
    public void incrementaNumeroVisitatori(String nome, LatLng posizione) {

    }

    @Override
    public List<Recensioni> getRecensioniByNomeStrutturaPosizione(String nomeStruttura, LatLng posizione) {
        return null;
    }


    /*
    *
    *   LA PARTE SOTTOSTANTE E' USATA SEMPLICEMENTE PER SOPPERIRE ALLA MANCAZA DELLE REALI IMPLEMENTAZIONI CHE DOVREBBERO FORNIRE TALI DATI.
    *   ALLA FINE DELLO SVILUPPO TALE PARTE VERRA' CANCELLATA.
    *
    * */

    protected static List<Strutture> listaStruttureDatabase;
    protected static List<Recensioni> listaRecensioniDatabase = new ArrayList<Recensioni>();

    private void initDatabase(){
        listaRecensioniDatabase.add(new Recensioni("Lo stadio San Paolo è fighissimo!!","https://www.diretta-napoli.com/wp-content/uploads/2020/03/stadio-san-paolo-di-napoli-oggi.jpg",4.0f,"Giuseppe","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Grande la partita","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Stadio_San_Paolo_Panoramica_Champions_League.jpg/900px-Stadio_San_Paolo_Panoramica_Champions_League.jpg",4.0f,"Marco","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Che figata lo stadio nuovo","https://www.ilpost.it/wp-content/uploads/2019/06/stadio-san-paolo-napoli.jpg",3.9f,"Giuseppe","Stadio San Paolo",40.827967f,14.193008f));
        listaRecensioniDatabase.add(new Recensioni("Nice ;)","https://www.stadi.online/wp-content/uploads/sites/108/stadio-san-paolo-napoli-3d.jpg",4.1f,"Marco","Stadio San Paolo",40.827967f,14.193008f));

        listaRecensioniDatabase.add(new Recensioni("Non mi piace molto Pompei :(","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG",2.5f,"Giuseppe","Pompei",40.749183654785156f,14.500738143920898f));
        listaRecensioniDatabase.add(new Recensioni("Bella Pompei!!","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG",3.3f,"Marco","Pompei",40.749183654785156f,14.500738143920898f));

        listaRecensioniDatabase.add(new Recensioni("Molto bello castell dell'ovo","https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Castelo_do_Ovo.jpg/1200px-Castelo_do_Ovo.jpg",4.9f,"Giuseppe","Castel dell'ovo",40.827222f,14.248611f));

        listaRecensioniDatabase.add(new Recensioni("L'anfiteatro della mia città è figo!","https://lh3.googleusercontent.com/proxy/Ny7s7MCWrUe4y7QHrOPyytvEJ4X_B0l9ZIuOgOcckw9wUCwVlqO2UABcdU-eLnYMzQsRFWtbIZXiODRbXjF6EBeaZ26UqFjv2CiYCNNMxQyq-Fx22b2TltrdtoCRrCBLMDifi4sDfabOYkHMgE5OML1UDgu8",3.9f,"Giuseppe","Anfiteatro Flavio",40.82336f,14.12434f));
        listaRecensioniDatabase.add(new Recensioni("Ma come hanno fatto i romani a costruire tutto ciò? :O","https://www.beniculturali.it/mibac/multimedia/MiBAC/images/small/72/3a9bc83e2df59271821109b781461b781f44b.jpg",3.7f,"Marco","Anfiteatro Flavio",40.82336f,14.12434f));
        listaRecensioniDatabase.add(new Recensioni("Adoro le costruzioni Romane","https://www.lacooltura.com/wp-content/uploads/2015/04/Sotterranei-Anfiteatro-Flavio.jpeg",4.1f,"Marco","Anfiteatro Flavio",40.82336f,14.12434f));

        /*listaRecensioniDatabase.add(new Recensioni());
        listaRecensioniDatabase.add(new Recensioni());
        listaRecensioniDatabase.add(new Recensioni());
        listaRecensioniDatabase.add(new Recensioni());
        listaRecensioniDatabase.add(new Recensioni());*/
    }
}
