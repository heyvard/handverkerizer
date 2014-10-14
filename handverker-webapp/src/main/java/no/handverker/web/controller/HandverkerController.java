package no.handverker.web.controller;

import no.handverker.database.BrukereDatabase;
import no.handverker.domene.Handverker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Controller
public class HandverkerController {

    private final BrukereDatabase brukereDatabase;
    private static final Logger LOGGER = Logger.getLogger(HandverkerController.class.getName());


    @Autowired
    public HandverkerController(BrukereDatabase brukereDatabase) {
        this.brukereDatabase = brukereDatabase;
    }

    @RequestMapping(value = "/api/hentYrke", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Handverker handverker() {
        LOGGER.info("henter yrke");
        return null;// new Handverker("blikkenslager", "tim");
    }
}
