package no.handverker.config;

import no.handverker.web.controller.HandverkerController;
import no.handverker.web.controller.PrissController;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class HandverkerizerApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(PrissController.class);
        classes.add(HandverkerController.class);
        return classes;
    }

}


