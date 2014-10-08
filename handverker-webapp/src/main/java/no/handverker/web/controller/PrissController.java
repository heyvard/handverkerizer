package no.handverker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Controller
public class PrissController {


    @RequestMapping(value = "/api/priss", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String plaintext() {
        return PrissController.generate();
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
