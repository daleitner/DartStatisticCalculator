package mcleite.dartstatisticcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameConfigActivity extends AppCompatActivity {
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

    private GameConfigController _controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_config);
        _controller = new GameConfigController();

        mVisible = true;

        final Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameConfigActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameConfigActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button btnRaise = (Button) findViewById(R.id.btnRaise);
        btnRaise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameConfigActivity.this._controller.RaiseBestOf();
                final TextView txtBestOf = (TextView) findViewById(R.id.txtBestOf);
                CharSequence str = Integer.toString(GameConfigActivity.this._controller.GetBestOf());
                txtBestOf.setText(str);
            }
        });

        final Button btnReduce = (Button) findViewById(R.id.btnReduce);
        btnReduce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameConfigActivity.this._controller.ReduceBestOf();
                final TextView txtBestOf = (TextView) findViewById(R.id.txtBestOf);
                CharSequence str = Integer.toString(GameConfigActivity.this._controller.GetBestOf());
                txtBestOf.setText(str);
            }
        });

        final RadioButton rbtnBestOf = (RadioButton) findViewById(R.id.rbtnBestOf);
        rbtnBestOf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameConfigActivity.this._controller.SetIsBestOf(true);
                final Button btnReduce = (Button) findViewById(R.id.btnReduce);
                final Button btnRaise = (Button) findViewById(R.id.btnRaise);
                btnRaise.setEnabled(true);
                btnReduce.setEnabled(true);
            }
        });

        final RadioButton rbtnOpenEnd = (RadioButton) findViewById(R.id.rbtnOpenEnd);
        rbtnOpenEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameConfigActivity.this._controller.SetIsBestOf(false);
                final Button btnReduce = (Button) findViewById(R.id.btnReduce);
                final Button btnRaise = (Button) findViewById(R.id.btnRaise);
                btnRaise.setEnabled(false);
                btnReduce.setEnabled(false);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
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
