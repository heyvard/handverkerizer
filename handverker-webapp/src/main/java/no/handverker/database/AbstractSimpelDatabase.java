package no.handverker.database;

import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.List;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;


abstract class AbstractSimpelDatabase {
    private final DatastoreService datastore;
    private static final String VERDI_PROPERTY = "verdi";
    private static final String EPOST_PROPERTY = "epost";

    abstract protected String entity();

    public AbstractSimpelDatabase() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public List<String> hentAlle() {

        Query q = new Query(entity());
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> entityList = pq.asList(withLimit(300));

        List<String> stringList = new ArrayList<>(entityList.size());
        for (Entity e : entityList) {
            stringList.add((String) e.getProperty(VERDI_PROPERTY));
        }
        return stringList;

    }

    public void leggTil(String value, String epost) {

        Entity greeting = new Entity(entity(), value);
        greeting.setProperty(EPOST_PROPERTY, epost);
        greeting.setProperty(VERDI_PROPERTY, value);
        datastore.put(greeting);

    }
}
