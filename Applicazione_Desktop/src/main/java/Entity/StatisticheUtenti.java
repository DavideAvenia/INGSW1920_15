package Entity;

public class StatisticheUtenti {
    private int numUtenti;
    private int percentageNumReviews;
    private int avgScore;
    private int loginCounter;
    private int numTotReviews;

    public StatisticheUtenti(int numUtenti, int percentageNumReviews, int avgScore, int loginCounter, int numTotReviews) {
        this.numUtenti = numUtenti;
        this.percentageNumReviews = percentageNumReviews;
        this.avgScore = avgScore;
        this.loginCounter = loginCounter;
        this.numTotReviews = numTotReviews;
    }

    public int getNumUtenti() {
        return numUtenti;
    }

    public void setNumUtenti(int numUtenti) {
        this.numUtenti = numUtenti;
    }

    public int getPercentageNumReviews() {
        return percentageNumReviews;
    }

    public void setPercentageNumReviews(int percentageNumReviews) {
        this.percentageNumReviews = percentageNumReviews;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
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
