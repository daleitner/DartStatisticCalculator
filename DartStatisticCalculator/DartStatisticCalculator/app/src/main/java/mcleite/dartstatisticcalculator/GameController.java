package mcleite.dartstatisticcalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MCLeite on 27.10.2017.
 */
public class GameController {
    private int _round;
    private List<Integer> _scores;
    public GameController() {
        _round = 1;
        _scores = new ArrayList<>();
    }

    public int getLeftScore(int round) {
        int leftScore = 501;
        for(int i = 0; i<round-1; i++) {
            leftScore -= _scores.get(i);
        }
        return leftScore;
    }

    public int getRound() {
        return _round;
    }
}
