package no.handverker.web.controller;

import no.handverker.domene.StringWrapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Random;


@Path("priss")
public class PrissController {


    @GET
    @Produces("application/json")
    public StringWrapper plaintext() {
        return new StringWrapper(PrissController.generate());
    }

    private static String generate() {

        String[] a = {
                "Bj",
                "Bl",
                "Br",
                "Dr",
                "Dl",
                "Fr",
                "Gr",
                "Kr",
                "Kl",
                "Kn",
                "Km",
                "Kv",
                "Pr",
                "Sr",
                "Sk",
                "Sl",
                "Sm",
                "Sn",
                "St",
                "Sv",
                "Tj",
                "Tl",
                "Pl",
                "Vj",
                "Vn",
                "Zl",
                "Zn",
                "Zm",
                "St",
                "Zv"};

        char[] b = {'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};
        char[] c = {'t', 's', 'p', 'r', 'k', 'f'};


        Random r = new Random();

        int c_last = r.nextInt(c.length);

        return a[r.nextInt(a.length)]
                + b[r.nextInt(b.length)]
                + c[c_last]
                + c[c_last];
    }
}
