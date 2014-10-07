package no.handverker.web.controller;

import no.handverker.database.BrukereDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Håvard on 07.10.2014.
 */
@Controller
public class SimpleController {

    private final BrukereDatabase brukereDatabase;

    @Autowired
    public SimpleController(BrukereDatabase brukereDatabase) {
        this.brukereDatabase = brukereDatabase;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String plaintext(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return brukereDatabase.hentBruker("sdfsdfsd");
    }
}
