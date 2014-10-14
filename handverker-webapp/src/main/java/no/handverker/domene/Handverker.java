package no.handverker.domene;

import java.util.List;

/**
 * Created by Håvard on 08.10.2014.
 */
public class Handverker {
    private List<String> yrke;
    private List<String> fornavn;
    private List<String> etternavn;
    private List<String> steder;

    public void setYrke(List<String> yrke) {
        this.yrke = yrke;
    }

    public void setFornavn(List<String> fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(List<String> etternavn) {
        this.etternavn = etternavn;
    }

    public void setSteder(List<String> steder) {
        this.steder = steder;
    }

    public List<String> getYrke() {
        return yrke;
    }

    public List<String> getFornavn() {
        return fornavn;
    }

    public List<String> getEtternavn() {
        return etternavn;
    }

    public List<String> getSteder() {
        return steder;
    }
}
