package no.handverker.web.config;

import no.handverker.database.BrukereDatabase;
import no.handverker.web.autorisasjon.Autorisasjonsfilter;
import no.handverker.web.autorisasjon.Googlebruker;
import no.handverker.web.controller.PrissController;
import no.handverker.web.controller.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletConfig;


@Configuration
public class InitialConfig extends WebMvcConfigurerAdapter implements ServletConfigAware {
    private ServletConfig servletConfig;


    @Bean
    public PrissController prissController() {
        return new PrissController();
    }


    @Bean
    public BrukereDatabase brukereDatabase() {
        return new BrukereDatabase();
    }


    @Bean
    public Googlebruker googlebruker() {
        return new Googlebruker();
    }


    @Bean(name = "autorisasjonsfilter")
    @Autowired
    public Autorisasjonsfilter autorisasjonsfilter(Googlebruker googlebruker,
                                                   BrukereDatabase brukereDatabase) {

        return new Autorisasjonsfilter(googlebruker, brukereDatabase);
    }

    @Bean
    public ViewController viewController() {
        return new ViewController();
    }


    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
}
