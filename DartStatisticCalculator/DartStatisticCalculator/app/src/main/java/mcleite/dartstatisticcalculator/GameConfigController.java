package mcleite.dartstatisticcalculator;

/**
 * Created by MCLeite on 26.10.2017.
 */
public class GameConfigController {
    private int bestOf = 3;
    private boolean isBestOf = false;
    public GameConfigController() {

    }

    public void RaiseBestOf() {
        bestOf += 2;
    }

    public void ReduceBestOf(){
        if(bestOf > 3)
            bestOf -= 2;
    }

    public int GetBestOf() {
        return bestOf;
    }

    public boolean IsBestOf(){
        return isBestOf;
    }

    public void SetIsBestOf(boolean isBestOf) {
        this.isBestOf = isBestOf;
    }
}
