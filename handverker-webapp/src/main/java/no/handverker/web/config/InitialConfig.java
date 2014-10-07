package no.handverker.web.config;

import no.handverker.database.BrukereDatabase;
import no.handverker.web.controller.SimpleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletConfigAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import java.util.logging.Logger;

/**
 * Created by Håvard on 07.10.2014.
 */


@Configuration
public class InitialConfig implements ServletConfigAware {
    private ServletConfig servletConfig;


    @PostConstruct
    public void initialize() {
        Logger logger = Logger.getLogger(InitialConfig.class.getName());
        logger.warning("halla");
    }


    @Bean
    public BrukereDatabase brukereDatabase() {
        return new BrukereDatabase();
    }

    @Bean
    @Autowired
    public SimpleController simpleController(BrukereDatabase brukereDatabase) {
        return new SimpleController(brukereDatabase);
    }


    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
}
