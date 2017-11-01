package mcleite.dartstatisticcalculator;

/**
 * Created by MCLeite on 01.11.2017.
 */

public class Leg {
    private int scores;
    private int darts;
    private int checkDarts;

    public Leg(int scores, int darts, int checkDarts) {
        this.scores = scores;
        this.darts = darts;
        this.checkDarts = checkDarts;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getDarts() {
        return darts;
    }

    public void setDarts(int darts) {
        this.darts = darts;
    }

    public int getCheckDarts() {
        return checkDarts;
    }

    public void setCheckDarts(int checkDarts) {
        this.checkDarts = checkDarts;
    }
}
