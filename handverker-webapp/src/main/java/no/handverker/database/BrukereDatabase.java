package no.handverker.database;

import com.google.appengine.api.datastore.*;
import no.handverker.domene.Bruker;

import java.util.logging.Logger;


public class BrukereDatabase {
    private static final Logger LOGGER = Logger.getLogger(BrukereDatabase.class.getName());
    private final DatastoreService datastore;
    private static final String BRUKER_ENTITY = "bruker";
    private static final String BRUKERID_PROPERTY = "brukerid";
    private static final String EPOST_PROPERTY = "epost";
    private static final String TILGANG_PROPERTY = "tilgang";

    public BrukereDatabase() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public Bruker hentBruker(String brukerID) {

        LOGGER.info("Henter bruker " + brukerID);

        Query q = new Query(BRUKER_ENTITY)
                .setFilter(new Query.FilterPredicate(BRUKERID_PROPERTY,
                        Query.FilterOperator.EQUAL,
                        brukerID));

        PreparedQuery pq = datastore.prepare(q);

        Entity result = pq.asSingleEntity();
        if (result == null) {
            LOGGER.info("Fant ikke bruker " + brukerID);
            return null;
        }

        Bruker bruker = new Bruker();
        bruker.setBrukerID(brukerID);
        bruker.setEpost((String)result.getProperty(EPOST_PROPERTY));
        bruker.setHarTilgang((boolean)result.getProperty(TILGANG_PROPERTY));

        return bruker;

    }

    public void leggTilBruker(Bruker bruker) {
        LOGGER.info("Legger til bruker " + bruker.getEpost());

        Entity greeting = new Entity(BRUKER_ENTITY, bruker.getEpost());
        greeting.setProperty(EPOST_PROPERTY, bruker.getEpost());
        greeting.setProperty(BRUKERID_PROPERTY, bruker.getBrukerID());
        greeting.setProperty(TILGANG_PROPERTY, bruker.isHarTilgang());
        datastore.put(greeting);

    }
}
