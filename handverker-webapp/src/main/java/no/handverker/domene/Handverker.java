package no.handverker.domene;

import java.io.Serializable;
import java.util.List;


public class Handverker implements Serializable{
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
