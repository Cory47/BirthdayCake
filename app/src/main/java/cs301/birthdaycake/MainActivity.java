package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeview = (CakeView) findViewById(R.id.cakeview);
        CakeController cakecontroller = new CakeController(cakeview);

        Switch candleSwitch = (Switch) findViewById(R.id.candleSwitch);
        candleSwitch.setOnCheckedChangeListener(cakecontroller);

        View blowOutButton = (View) findViewById(R.id.blowOutButton);
        blowOutButton.setOnClickListener(cakecontroller);

        SeekBar candlesSeekBar = (SeekBar) findViewById(R.id.howManyCandlesSeekBar);
        candlesSeekBar.setOnSeekBarChangeListener(cakecontroller);

    }

    public void goodbye(View button) {
        Log.i("button", "Goodbye");
    }

}
