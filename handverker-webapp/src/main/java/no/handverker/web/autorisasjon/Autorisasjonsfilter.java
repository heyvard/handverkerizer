package no.handverker.web.autorisasjon;


import com.google.appengine.api.users.User;
import no.handverker.database.BrukereDatabase;
import no.handverker.domene.Bruker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class Autorisasjonsfilter extends OncePerRequestFilter {

    private static Logger LOGGER = Logger.getLogger(Autorisasjonsfilter.class.getName());

    private final Googlebruker googlebruker;
    private final BrukereDatabase brukereDatabase;

    @Autowired
    public Autorisasjonsfilter(Googlebruker googlebruker, BrukereDatabase brukereDatabase) {
        this.googlebruker = googlebruker;
        this.brukereDatabase = brukereDatabase;
    }

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        if (!googlebruker.erBrukerLoggetInn()) {
            //Vi er i veien for google sitt filter
            chain.doFilter(req, res);
            return;
        } else {

            HttpSession session = req.getSession();
            if (erAutorisert(session)) {
                LOGGER.info("er allerede autorisert");
                chain.doFilter(req, res);
                return;
            } else {

                User user = googlebruker.getCurrentUser();
                Bruker bruker = brukereDatabase.hentBruker(user.getUserId());
                if (bruker == null) {

                    LOGGER.info("Legger til ny bruker: " + user.getEmail());


                    bruker = new Bruker();
                    bruker.setBrukerID(user.getUserId());
                    bruker.setHarTilgang(true);
                    bruker.setEpost(user.getEmail());
                    setAutorisert(session);

                    chain.doFilter(req, res);
                    return;

                } else {
                    if (bruker.isHarTilgang()) {
                        LOGGER.info(bruker.getEpost() + " har allerde tilgang");
                        setAutorisert(session);
                        chain.doFilter(req, res);
                        return;
                    } else {
                        LOGGER.info(bruker.getEpost() + " har ikke tilgang");

                        sendTilFeilside(req, res, chain);
                        return;
                    }
                }
            }
        }
        //Skal være unreachable
    }


    private void sendTilFeilside(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setStatus(403);
        res.sendRedirect("/forbidden.html");
        chain.doFilter(req, res);
    }


    @PostConstruct
    public void initialize() {
        LOGGER.info("AutorisasjonsInterceptor skapt");
    }

    private boolean erAutorisert(HttpSession httpSession) {
        Object o = httpSession.getAttribute("autorisert");
        if (o == null) {
            return false;
        }
        return (boolean) o;
    }

    private void setAutorisert(HttpSession httpSession) {
        httpSession.setAttribute("autorisert", true);
    }

}
