package no.handverker.web.config;

import no.handverker.database.BrukereDatabase;
import no.handverker.web.controller.HandverkerController;
import no.handverker.web.controller.PrissController;
import no.handverker.web.controller.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import java.util.List;
import java.util.logging.Logger;


@Configuration
public class InitialConfig extends WebMvcConfigurerAdapter implements ServletConfigAware {
    private ServletConfig servletConfig;


    @PostConstruct
    public void initialize() {
        Logger logger = Logger.getLogger(InitialConfig.class.getName());
        logger.info("halla");
    }

    @Bean
    public PrissController prissController(){
        return new PrissController();
    }


    @Bean
    public BrukereDatabase brukereDatabase() {
        return new BrukereDatabase();
    }

    @Bean
    @Autowired
    public HandverkerController simpleController(BrukereDatabase brukereDatabase) {
        return new HandverkerController(brukereDatabase);
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
