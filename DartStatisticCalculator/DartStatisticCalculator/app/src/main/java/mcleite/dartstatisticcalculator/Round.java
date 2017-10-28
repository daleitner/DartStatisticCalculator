package mcleite.dartstatisticcalculator;

/**
 * Created by MCLeite on 28.10.2017.
 */
public class Round {
    private int _scores;
    private int _left;
    private int _darts;

    public Round(int scores, int left, int darts) {
        _scores = scores;
        _left = left;
        _darts = darts;
    }

    public int getScores() {
        return _scores;
    }

    public int getLeft() {
        return _left;
    }

    public int getDarts() {
        return _darts;
    }

    public void setScores(int _scores) {
        this._scores = _scores;
    }

    public void setLeft(int _left) {
        this._left = _left;
    }

    public void setDarts(int _darts) {
        this._darts = _darts;
    }
}
