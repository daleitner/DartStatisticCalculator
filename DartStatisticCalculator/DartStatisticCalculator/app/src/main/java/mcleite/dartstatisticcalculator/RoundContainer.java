package mcleite.dartstatisticcalculator;

import android.widget.TextView;

/**
 * Created by MCLeite on 27.10.2017.
 */
public class RoundContainer {
    private TextView _scores;
    private TextView _left;
    private TextView _darts;

    public RoundContainer(TextView scores, TextView left, TextView darts) {
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

    public void setRound(Round round, boolean isFuture) {
        _scores.setText(Integer.toString(round.getScores()));
        _left.setText(Integer.toString(round.getLeft()));
        _darts.setText(Integer.toString(round.getDarts()));
        if(isFuture) {
            _scores.setText("");
            _left.setText("");
        }

    }

    public void updateScores(String scores) {
        _scores.setText(scores);
    }
}
