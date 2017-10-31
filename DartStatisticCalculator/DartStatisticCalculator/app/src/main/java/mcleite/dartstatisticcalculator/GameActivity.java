package mcleite.dartstatisticcalculator;

import android.annotation.SuppressLint;
import android.opengl.Visibility;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private ArrayList<RoundContainer> roundContainer;
    private GameController controller;
    private Button btnDelete;
    private Button btnEnter;
    private FrameLayout checkLayout;
    private GridLayout numberLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        mVisible = true;
        controller = new GameController();
        InitializeRounds();
        InitializeNumberButtons();

        checkLayout = (FrameLayout) findViewById(R.id.layoutCheck);
        numberLayout = (GridLayout) findViewById(R.id.layoutNumbers);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int actualPosition = controller.getActualPosition();
                String actualValue = controller.DeleteLastInput();
                roundContainer.get(actualPosition).updateScores(actualValue);
                UpdateButtons();
            }
        });

        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.enterRound();
                UpdateRounds();
                UpdateButtons();
                if(controller.isLegFinished()) {
                    ShowCheckLayout();
                }
            }
        });

        UpdateRounds();
        UpdateButtons();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void ShowCheckLayout() {
        checkLayout.setVisibility(View.VISIBLE);
        numberLayout.setVisibility(View.GONE);
    }

    private void UpdateRounds() {
        int actualRound = controller.getActualRound();
        for(int i = 0; i<roundContainer.size(); i++) {
            roundContainer.get(i).setRound(controller.getRound(i+1), i == 3 || (actualRound<4 && i >= actualRound));
        }
        if(actualRound<=3) {
            final TextView scores1 = (TextView) findViewById(R.id.txtScoresOne);
            scores1.setText("   ");
        }
    }

    private void UpdateButtons() {
        btnDelete.setEnabled(controller.canDelete());
        btnEnter.setEnabled(controller.canEnter());
    }

    private void InitializeRounds() {
        final TextView scores1 = (TextView) findViewById(R.id.txtScoresOne);
        final TextView scores2 = (TextView) findViewById(R.id.txtScoresTwo);
        final TextView scores3 = (TextView) findViewById(R.id.txtScoresThree);
        final TextView scores4 = (TextView) findViewById(R.id.txtScoresFour);
        final TextView left1 = (TextView) findViewById(R.id.txtLeftOne);
        final TextView left2 = (TextView) findViewById(R.id.txtLeftTwo);
        final TextView left3 = (TextView) findViewById(R.id.txtLeftThree);
        final TextView left4 = (TextView) findViewById(R.id.txtLeftFour);
        final TextView dart1 = (TextView) findViewById(R.id.txtDartsOne);
        final TextView dart2 = (TextView) findViewById(R.id.txtDartsTwo);
        final TextView dart3 = (TextView) findViewById(R.id.txtDartsThree);
        final TextView dart4 = (TextView) findViewById(R.id.txtDartsFour);
        roundContainer = new ArrayList<>();
        roundContainer.add(new RoundContainer(scores1, left1, dart1));
        roundContainer.add(new RoundContainer(scores2, left2, dart2));
        roundContainer.add(new RoundContainer(scores3, left3, dart3));
        roundContainer.add(new RoundContainer(scores4, left4, dart4));
    }

    private void InitializeNumberButtons() {
        final ArrayList<Button> numberButtons = new ArrayList<>();
        numberButtons.add((Button) findViewById(R.id.btnZero));
        numberButtons.add((Button) findViewById(R.id.btnOne));
        numberButtons.add((Button) findViewById(R.id.btnTwo));
        numberButtons.add((Button) findViewById(R.id.btnThree));
        numberButtons.add((Button) findViewById(R.id.btnFour));
        numberButtons.add((Button) findViewById(R.id.btnFive));
        numberButtons.add((Button) findViewById(R.id.btnSix));
        numberButtons.add((Button) findViewById(R.id.btnSeven));
        numberButtons.add((Button) findViewById(R.id.btnEight));
        numberButtons.add((Button) findViewById(R.id.btnNine));
        for(int i = 0; i<numberButtons.size(); i++) {
            numberButtons.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int actualPosition = controller.getActualPosition();
                    String actualValue = controller.UserClicked(((Button) v).getText());
                    roundContainer.get(actualPosition).updateScores(actualValue);
                    UpdateButtons();
                }
            });
        }
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
