package no.handverker.domene;


public class Bruker {

    private String epost;
    private String brukerID;
    private boolean harTilgang;

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(String brukerID) {
        this.brukerID = brukerID;
    }

    public boolean isHarTilgang() {
        return harTilgang;
    }

    public void setHarTilgang(boolean harTilgang) {
        this.harTilgang = harTilgang;
    }
}
