package no.handverker.web.controller;

import no.handverker.database.EtternavnDatabase;
import no.handverker.database.FornavnDatabase;
import no.handverker.database.StedDatabase;
import no.handverker.database.YrkeDatabase;
import no.handverker.domene.Handverker;
import no.handverker.web.autorisasjon.Googlebruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Controller
public class HandverkerController {

    private final YrkeDatabase yrkeDatabase;
    private final EtternavnDatabase etternavnDatabase;
    private final StedDatabase stedDatabase;
    private final FornavnDatabase fornavnDatabase;
    private final Googlebruker googlebruker;


    private static final Logger LOGGER = Logger.getLogger(HandverkerController.class.getName());


    @Autowired
    public HandverkerController(YrkeDatabase yrkeDatabase, EtternavnDatabase etternavnDatabase, StedDatabase stedDatabase, FornavnDatabase fornavnDatabase, Googlebruker googlebruker) {
        this.yrkeDatabase = yrkeDatabase;
        this.etternavnDatabase = etternavnDatabase;
        this.stedDatabase = stedDatabase;
        this.fornavnDatabase = fornavnDatabase;
        this.googlebruker = googlebruker;
    }

    @RequestMapping(value = "/api/handverker", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Handverker handverker() {
        Handverker handverker = new Handverker();
        handverker.setEtternavn(etternavnDatabase.hentAlle());
        handverker.setYrke(yrkeDatabase.hentAlle());
        handverker.setSteder(stedDatabase.hentAlle());
        handverker.setFornavn(fornavnDatabase.hentAlle());

        return handverker;
    }

    @RequestMapping(value = "/api/handverker/yrke", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public void leggTilYrke(@RequestBody String yrke) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            yrkeDatabase.leggTil(yrke, epost);
        }
    }

    @RequestMapping(value = "/api/handverker/sted", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public void leggTilSted(@RequestBody String sted) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            stedDatabase.leggTil(sted, epost);
        }
    }

    @RequestMapping(value = "/api/handverker/fornavn", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public void leggTilFornavn(@RequestBody String fornavn) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            fornavnDatabase.leggTil(fornavn, epost);
        }
    }

    @RequestMapping(value = "/api/handverker/etternavn", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public void leggTilEtternavne(@RequestBody String etternavn) {
        String epost = googlebruker.getCurrentUser().getEmail();
        if (epost != null) {
            etternavnDatabase.leggTil(etternavn, epost);
        }
    }


}
