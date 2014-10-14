package no.handverker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class ViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexGet(HttpServletResponse res) {
        res.setHeader("Cache-Control", "private, max-age=0, no-cache");
        return "redirect:index.html" + "?" + UUID.randomUUID().toString();
    }
}
