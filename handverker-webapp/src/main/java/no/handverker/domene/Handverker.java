package no.handverker.domene;

/**
 * Created by Håvard on 08.10.2014.
 */
public class Handverker {
    private String yrke;
    private String navn;

    public Handverker(String yrke, String navn) {
        this.yrke = yrke;
        this.navn = navn;
    }

    public String getYrke() {
        return yrke;
    }

    public void setYrke(String yrke) {
        this.yrke = yrke;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
