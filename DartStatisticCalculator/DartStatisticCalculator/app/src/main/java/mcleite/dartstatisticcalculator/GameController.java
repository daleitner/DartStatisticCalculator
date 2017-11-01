package mcleite.dartstatisticcalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MCLeite on 27.10.2017.
 */
public class GameController {
    private int _actualRound;
    private String _actualInput = "";
    private List<Round> _rounds;
    private GameDataContainer data;
    private boolean gotCheckDarts;
    private int darts;
    private int checkDarts;
    public GameController() {
        data = new GameDataContainer();
        _actualRound = 1;
        gotCheckDarts = false;
        _rounds = new ArrayList<>();
        _rounds.add(new Round(0, 501, 0));
        _rounds.add(new Round(0, 0, 3));
        _rounds.add(new Round(0, 0, 6));
        _rounds.add(new Round(0, 0, 9));
        darts = 0;
        checkDarts = 0;
    }

    public Round getRound(int position) {
        if(_actualRound < 4){
            if(_rounds.size() >= position)
                return _rounds.get(position-1);
            return null;
        }
        return _rounds.get(_actualRound-(4-position));
    }

    public int getActualRound() {
        return _actualRound;
    }

    public int getActualPosition() {
        if(_actualRound > 3)
            return 3;
        return _actualRound;
    }

    public String UserClicked(CharSequence s) {
        if(_actualInput.length()<3)
            _actualInput += s;
        return _actualInput;
    }

    public String DeleteLastInput() {
        if(canDelete())
            _actualInput = _actualInput.substring(0, _actualInput.length()-1);
        return _actualInput;
    }

    public boolean canDelete() {
        return _actualInput.length()>0;
    }

    public boolean canEnter() {
        if(!canDelete())
            return false;
        int number = Integer.parseInt(_actualInput);
        if((number<162 || (number <=180 && number%3 == 0) || (number <= 170 && number%3 == 2))
                && (_rounds.get(_actualRound-1).getLeft() == number || _rounds.get(_actualRound-1).getLeft() > number + 1))
            return true;
        return false;
    }

    public void enterRound() {
        int number = Integer.parseInt(_actualInput);
        Round lastRound = _rounds.get(_actualRound-1);
        _rounds.set(_actualRound, new Round(number, lastRound.getLeft()-number, lastRound.getDarts()+3));
        if(lastRound.getLeft()-number > 0)
            _rounds.add(new Round(0,0,lastRound.getDarts()+12));
        _actualRound++;
        _actualInput = "";
    }

    public boolean isLegFinished() {
        return _rounds.get(_actualRound-1).getLeft() == 0;
    }

    public void UserClickedCheck(CharSequence text) {
        if(isLegFinished() && !gotCheckDarts) {
            darts = Integer.parseInt(text.toString());
            darts += (_rounds.size()-1)*3;
            gotCheckDarts = true;
        } else {
            checkDarts += Integer.parseInt(text.toString());
            if(isLegFinished())
                finishLeg();
        }
    }

    private void finishLeg() {
        data.AddLeg(501-_rounds.get(_actualRound-1).getLeft(), darts, checkDarts);
        _rounds = new ArrayList<>();
        _rounds.add(new Round(0, 501, 0));
        _rounds.add(new Round(0, 0, 3));
        _rounds.add(new Round(0, 0, 6));
        _rounds.add(new Round(0, 0, 9));
        _actualRound = 1;
        gotCheckDarts = false;
        darts = 0;
        checkDarts = 0;
    }

    public boolean couldHaveHadCheckDarts() {
        return false;
    }
}
