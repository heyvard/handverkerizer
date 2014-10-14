package no.handverker.database;

import no.handverker.domene.Bruker;

import java.util.logging.Logger;


public class BrukereDatabase {
    private static final Logger LOGGER = Logger.getLogger(BrukereDatabase.class.getName());


    public Bruker hentBruker(String brukerID) {
        LOGGER.info("Henter bruker " +  brukerID);
        return null;
    }

    public void leggTilBruker(Bruker bruker){
        LOGGER.info("Legger til bruker " +  bruker.getEpost());

    }
}
