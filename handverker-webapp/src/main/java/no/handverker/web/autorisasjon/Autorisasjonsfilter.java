package no.handverker.web.autorisasjon;


import com.google.appengine.api.users.User;
import no.handverker.appengine.Googlebruker;
import no.handverker.database.BrukereDatabase;
import no.handverker.domene.Bruker;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class Autorisasjonsfilter implements Filter {

    private static Logger LOGGER = Logger.getLogger(Autorisasjonsfilter.class.getName());

    private Googlebruker googlebruker = new Googlebruker();
    private BrukereDatabase brukereDatabase = new BrukereDatabase();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        if (!googlebruker.erBrukerLoggetInn()) {
            //Vi er i veien for google sitt filter
            chain.doFilter(req, res);
            return;
        } else {

            HttpSession session = httpServletRequest.getSession();
            if (erAutorisert(session)) {
                chain.doFilter(req, res);
                return;
            } else {

                User user = googlebruker.getCurrentUser();
                Bruker bruker = brukereDatabase.hentBruker(user.getUserId());
                if (bruker == null) {


                    bruker = new Bruker();
                    bruker.setBrukerID(user.getUserId());
                    bruker.setHarTilgang(true);
                    bruker.setEpost(user.getEmail());
                    brukereDatabase.leggTilBruker(bruker);
                    setAutorisert(session);
                    LOGGER.info(bruker.getEpost() + " lagt til");

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


    private void sendTilFeilside(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
        httpServletResponse.setStatus(403);
        httpServletResponse.sendRedirect("/forbidden.html");
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
