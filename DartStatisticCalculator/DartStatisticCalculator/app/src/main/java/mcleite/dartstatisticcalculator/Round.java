package mcleite.dartstatisticcalculator;

import android.widget.TextView;

/**
 * Created by MCLeite on 27.10.2017.
 */
public class Round {
    private TextView _scores;
    private TextView _left;
    private TextView _darts;

    public Round(TextView scores, TextView left, TextView darts) {
        _scores = scores;
        _left = left;
        _darts = darts;
    }

    public TextView getScores() {
        return _scores;
    }

    public TextView getLeft() {
        return _left;
    }


    public TextView getDarts() {
        return _darts;
    }

    public void setRound(int scores, int left, int darts) {
        _scores.setText(Integer.toString(scores));
        _left.setText(Integer.toString(left));
        _darts.setText(Integer.toString(darts));
    }

    public void updateScores(int scores) {
        _scores.setText(Integer.toString(scores));
    }
}
