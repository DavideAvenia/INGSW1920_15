package Boundary;

public class ModelloStatisticheEStrutture {
    private int numVisitatori;
    private int numReviews;
    private int numClienti;
    private String nome;
    private String longitudine;
    private String latitudine;
    private String città;
    private String orarioApertura;
    private String valutazioneMedia;
    private String categoria;

    public ModelloStatisticheEStrutture(int numVisitatori, int numReviews, int numClienti, String nome, String longitudine, String latitudine, String categoria, String valutazioneMedia, String orarioApertura, String città) {
        this.numVisitatori = numVisitatori;
        this.numReviews = numReviews;
        this.numClienti = numClienti;
        this.nome = nome;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.categoria = categoria;
        this.città = città;
        this.valutazioneMedia = valutazioneMedia;
        this.orarioApertura = orarioApertura;
    }

    public int getNumVisitatori() {
        return numVisitatori;
    }

    public void setNumVisitatori(int numVisitatori) {
        this.numVisitatori = numVisitatori;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public int getNumClienti() {
        return numClienti;
    }

    public void setNumClienti(int numClienti) {
        this.numClienti = numClienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(String orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public String getValutazioneMedia() {
        return valutazioneMedia;
    }

    public void setValutazioneMedia(String valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}