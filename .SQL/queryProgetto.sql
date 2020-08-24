create table Strutture (
nome varchar(50) NOT NULL,
città varchar(30) NOT NULL,
valutazioneMedia varchar(3),
maxPrezzo varchar(10),
orarioApertura varchar(15),
categoria varchar(15),
latitudine varchar(30) NOT NULL,
longitudine varchar(30) NOT NULL,
descrizione varchar(1500),

constraint PK_Strutture PRIMARY KEY(nome,latitudine,longitudine)
);

create table `Recensioni`(
`usernameUtente` varchar(20) NOT NULL,
`nomeStruttura` varchar(50) NOT NULL,
`latitudine` varchar(30) NOT NULL,
`longitudine` varchar(30) NOT NULL,

`testoRecensione` varchar(500) NOT NULL,
`urlFoto` varchar(2083),
`valutazione` varchar(3),

`pending` boolean,

constraint PK_Recensioni PRIMARY KEY (usernameUtente, nomeStruttura,latitudine,longitudine)
);

create table StatisticheStrutture(
nomeStruttura varchar(50) NOT NULL,
latitudine varchar(30) NOT NULL,
longitudine varchar(30) NOT NULL,

numeroVisitatori int,

constraint PK_StatisticheStrutture PRIMARY KEY (nomeStruttura,latitudine,longitudine)
);
insert into Strutture value("Anfiteatro Flavio","Pozzuoli",3.5,25,"8:00-13:00","Museo","40.825869","14.125078","L'Anfiteatro Flavio è uno dei due anfiteatri romani esistenti a Pozzuoli e risale alla seconda metà del I secolo d.C. Venne realizzato per far fronte all'incremento demografico di Puteoli, che aveva reso inadatto il vecchio edificio adibito per spettacoli pubblici in età repubblicana. Secondo solo al Colosseo e all'anfiteatro Campano in quanto capacità di capienza, sorge in concomitanza della convergenza di due vie principali, la Via Domitiana e la via per Napoli");
insert into Strutture value("Pompei","Pompei",2.9,"20","8:00-13:00","Museo","40.749183654785156","14.500738143920898","Pompei (in latino: Pompeii) è una città dell'evo antico, corrispondente all'attuale Pompei, la cui storia ha origine dal IX secolo a.C. per terminare nel 79, quando, a seguito dell'eruzione del Vesuvio, viene ricoperta sotto una coltre di ceneri e lapilli alta circa sei metri. La sua riscoperta e i relativi scavi, iniziati nel 1748, hanno riportato alla luce un sito archeologico che nel 1997 è entrato a far parte della lista dei patrimoni dell'umanità dell'UNESCO, e che è il secondo monumento italiano per visite dopo il sistema museale del Colosseo, Foro Romano e Palatino[");
insert into Strutture value("Piazza del Plebiscito","Napoli",3.5,"0","9:00-15:00","Cultura","40.835896","14.248784","Piazza del Plebiscito (già largo di Palazzo o Foro Regio) è una piazza di Napoli posizionata al termine di via Toledo, non appena oltrepassata piazza Trieste e Trento.Ubicata nel centro storico, tra il lungomare e via Toledo, con una superficie di circa 25 000 metri quadrati la piazza si presenta come una delle più grandi della città e d'Italia e per questo è quella più utilizzata per le grandi manifestazioni.");
insert into Strutture value("Teatro San Carlo","Napoli",5.0,"120","12:00-18:00","Intrattenimento","40.85299","14.24789","Il Teatro di San Carlo, noto semplicemente come Teatro San Carlo, è un teatro lirico di Napoli, tra i più famosi e prestigiosi al mondo, fondato nel 1737.[1] Può ospitare 1386 spettatori[2] e conta una vasta platea (22×28×23 m), cinque ordini di palchi disposti a ferro di cavallo più un ampio palco reale, un loggione ed un palcoscenico (34×33 m).[3][4] Date le sue dimensioni, struttura e antichità è stato modello per i successivi teatri d'Europa.");
insert into Strutture value("Castel dell'Ovo","Napoli",4.9,"0","9:00-15:00","Cultura","40.827222","14.248611","Il castel dell'Ovo (castrum Ovi, in latino), è il castello più antico della città di Napoli[1] ed è uno degli elementi che spiccano maggiormente nel celebre panorama del golfo. Si trova tra i quartieri di San Ferdinando e Chiaia, di fronte a via Partenope. A causa di diversi eventi che hanno in parte distrutto l'originario aspetto normanno e grazie ai successivi lavori di ricostruzione avvenuti durante il periodo angioino ed aragonese, la linea architettonica del castello mutò drasticamente fino a giungere allo stato in cui si presenta oggi.");

insert into StatisticheStrutture values("Anfiteatro Flavio","40.825869","14.125078","0");
insert into StatisticheStrutture values("Pompei","40.749183654785156","14.500738143920898","0");
insert into StatisticheStrutture values("Piazza del Plebiscito","40.835896","14.248784","0");
insert into StatisticheStrutture values("Teatro San Carlo","40.85299","14.24789","0");
insert into StatisticheStrutture values("Castel dell'Ovo","40.827222","14.248611","0");

insert into `Recensioni` values("Giuseppe","Pompei","40.749183654785156","14.500738143920898","Non mi piace molto Pompei :(","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG","2.5",false);
insert into `Recensioni` values("Marco","Pompei","40.749183654785156","14.500738143920898","Bella Pompei!!","https://upload.wikimedia.org/wikipedia/commons/7/7b/Via_dell%27Abbondanza_1.JPG","3.3",false);
insert into `Recensioni` values("Giuseppe","Castel dell'Ovo","40.827222","14.248611","Molto bello castell dell'ovo","https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Castelo_do_Ovo.jpg/1200px-Castelo_do_Ovo.jpg","4.9",false);
insert into `Recensioni` values("Giuseppe","Anfiteatro Flavio","40.825869","14.125078","L'anfiteatro della mia città è figo!","https://lh3.googleusercontent.com/proxy/Ny7s7MCWrUe4y7QHrOPyytvEJ4X_B0l9ZIuOgOcckw9wUCwVlqO2UABcdU-eLnYMzQsRFWtbIZXiODRbXjF6EBeaZ26UqFjv2CiYCNNMxQyq-Fx22b2TltrdtoCRrCBLMDifi4sDfabOYkHMgE5OML1UDgu8","3.9",false);
insert into `Recensioni` values("Marco","Anfiteatro Flavio","40.825869","14.125078","Ma come hanno fatto i romani a costruire tutto ciò? :O","https://www.beniculturali.it/mibac/multimedia/MiBAC/images/small/72/3a9bc83e2df59271821109b781461b781f44b.jpg","3.7",false);
insert into `Recensioni` values("Gennaro","Anfiteatro Flavio","40.825869","14.125078","Adoro le costruzioni Romane","https://www.lacooltura.com/wp-content/uploads/2015/04/Sotterranei-Anfiteatro-Flavio.jpeg","4.1",false);
insert into `Recensioni` values("Giuseppe","Teatro San Carlo","40.85299","14.24789","Lo spettacolo era stupendo","https://www.teatrosancarlo.it/files/TSC03.jpg","5.0",false);
insert into `Recensioni` values("Gennaro","Teatro San Carlo","40.85299","14.24789","Il teatro San Carlo è il più bello d'Italia!!","https://tuttodanzaweb.it/wp-content/uploads/2020/06/Teatro-San-Carlo-di-Napoli-Stagione-202021_.jpg","4.9",false);
insert into `Recensioni` values("Marco","Teatro San Carlo","40.85299","14.24789","Non mi piace questo teatro","https://ecampania.it/wp-content/uploads/2020/03/teatro-san-carlo-napoli-1130x650.jpg","5.0",false);
insert into `Recensioni` values("Francesca","Teatro San Carlo","40.85299","14.24789","Lo spettacolo era stupendo","https://www.farodiroma.it/wp-content/uploads/2019/12/101728121-1e70f7dd-9e10-48cc-9959-86e332b2ad28.jpg","4.5",false);
insert into `Recensioni` values("Maria","Teatro San Carlo","40.85299","14.24789","Lo spettacolo era grandioso","https://www.napolidavivere.it/wp-content/uploads/bfi_thumb/Teatro-San-Carlo-festa-della-musica-5sbnlydbvd512i2xc32w0efmcx5drr9vtzi7755te44.jpg","4.5", false);

create table StatisticheUtenti (
userID varchar(20) primary key,
livello int ,
avgScore float,
loginCounter int not null,
numTotaleReviews int );

insert into StatisticheUtenti values ("Gennaro",0,4.5,0,2);



