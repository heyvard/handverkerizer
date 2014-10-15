package no.handverker.web.controller;

import no.handverker.appengine.Googlebruker;
import no.handverker.database.EtternavnDatabase;
import no.handverker.database.FornavnDatabase;
import no.handverker.database.StedDatabase;
import no.handverker.database.YrkeDatabase;
import no.handverker.domene.Handverker;
import no.handverker.domene.StringWrapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("handverker")
public class HandverkerController {

    private YrkeDatabase yrkeDatabase = new YrkeDatabase();
    private EtternavnDatabase etternavnDatabase = new EtternavnDatabase();
    private StedDatabase stedDatabase = new StedDatabase();
    private FornavnDatabase fornavnDatabase = new FornavnDatabase();
    private Googlebruker googlebruker = new Googlebruker();


    private static final Logger LOGGER = Logger.getLogger(HandverkerController.class.getName());


    @GET
    @Produces("application/json")
    public Handverker handverker() {
        Handverker handverker = new Handverker();
        handverker.setEtternavn(etternavnDatabase.hentAlle());
        handverker.setYrke(yrkeDatabase.hentAlle());
        handverker.setSteder(stedDatabase.hentAlle());
        handverker.setFornavn(fornavnDatabase.hentAlle());

        return handverker;
    }

    @POST
    @Consumes("application/json")
    @Path("yrke")
    public Response leggTilYrke(StringWrapper yrke) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            yrkeDatabase.leggTil(yrke.getVerdi(), epost);
        }
        return Response.status(201).build();
    }

    @POST
    @Consumes("application/json")
    @Path("sted")
    public Response leggTilSted(StringWrapper sted) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            stedDatabase.leggTil(sted.getVerdi(), epost);
        }
        return Response.status(201).build();

    }

    @POST
    @Consumes("application/json")
    @Path("fornavn")
    public Response leggTilFornavn(StringWrapper fornavn) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            fornavnDatabase.leggTil(fornavn.getVerdi(), epost);
        }
        return Response.status(201).build();

    }

    @POST
    @Consumes("application/json")
    @Path("etternavn")
    public Response leggTilEtternavne(StringWrapper etternavn) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            etternavnDatabase.leggTil(etternavn.getVerdi(), epost);
        }
        return Response.status(201).build();

    }
}
