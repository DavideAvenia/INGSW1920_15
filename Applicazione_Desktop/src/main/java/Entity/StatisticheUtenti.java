package Entity;


public class StatisticheUtenti {
    private String userID;
    private int livello;
    private float avgScore;
    private int loginCounter;
    private int numTotReviews;

    public StatisticheUtenti(String userID, int livello, float avgScore, int loginCounter, int numTotReviews) {
        this.userID = userID;
        this.livello = livello;
        this.avgScore = avgScore;
        this.loginCounter = loginCounter;
        this.numTotReviews = numTotReviews;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public int getLoginCounter() {
        return loginCounter;
    }

    public void setLoginCounter(int loginCounter) {
        this.loginCounter = loginCounter;
    }

    public int getNumTotReviews() {
        return numTotReviews;
    }

    public void setNumTotReviews(int numTotReviews) {
        this.numTotReviews = numTotReviews;
    }
}
