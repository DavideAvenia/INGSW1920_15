package Entity;

public class Utente {

    private String userId;
    private String nome;
    private String cognome;
    private String nickname;
    private String cellulare;
    private String email;
    private String password;
    private boolean useNick;
    private boolean isMod;
    private int numeroLogin;

    public Utente(String userId, String cellulare, String email) { //forse da modificare se Davide crea un sottoclasse di utente per l'admin
        this.userId = userId;
        this.cellulare = cellulare;
        this.email = email;
        this.nome = "";
        this.cognome = "";
        this.nickname = "";
        this.isMod = true;
        this.useNick = false;
    }

    public Utente(String userId, String nome, String cognome, String nickname, String cellulare, String email, boolean useNick, boolean isMod) {
        this.userId = userId;
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.cellulare = cellulare;
        this.email = email;
        this.useNick = useNick;
        this.isMod = isMod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMod() {
        return isMod;
    }

    public void setMod(boolean mod) {
        isMod = mod;
    }

    public int getNumeroLogin() {
        return numeroLogin;
    }

    public void setNumeroLogin(int numeroLogin) {
        this.numeroLogin = numeroLogin;
    }

    public boolean isUseNick() {
        return useNick;
    }

    public void setUseNick(boolean useNick) {
        this.useNick = useNick;
    }
}
