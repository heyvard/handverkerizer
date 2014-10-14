package no.handverker.web.autorisasjon;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;


public class Googlebruker {

    public boolean erAdminBruker() {
        if(!erBrukerLoggetInn()){
            return false;
        }
        return UserServiceFactory.getUserService().isUserAdmin();
    }

    public boolean erBrukerLoggetInn() {
        return UserServiceFactory.getUserService().isUserLoggedIn();
    }

    public User getCurrentUser() {
        if(!erBrukerLoggetInn()){
            return null;
        }
        return UserServiceFactory.getUserService().getCurrentUser();
    }
}
