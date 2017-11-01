package mcleite.dartstatisticcalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MCLeite on 01.11.2017.
 */

public class GameDataContainer {
    private ArrayList<Leg> legs;

    public GameDataContainer() {
        legs = new ArrayList<>();
    }

    public void AddLeg(int scores, int darts, int checkDarts) {
        legs.add(new Leg(scores, darts, checkDarts));
    }
}
