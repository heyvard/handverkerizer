package no.handverker.config;

import org.restlet.Context;
import org.restlet.ext.jaxrs.JaxRsApplication;

public class HandverkaliserJaxRsApplication extends JaxRsApplication {

    public HandverkaliserJaxRsApplication(Context context) {
        super(context);
        this.add(new HandverkerizerApplication());
    }


}
