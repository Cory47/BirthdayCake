package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.Switch;

public class CakeController implements View.OnClickListener, Switch.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView cakeview;
    private CakeModel cakeModel;
    public CakeController(CakeView cake) {
        cakeview = cake;
        cakeModel = cake.getCakeModel();
    }

    public void onClick(View view){
        Log.d("Click", "The onClick method was called");
        cakeModel.candlesLit = !cakeModel.candlesLit;
        cakeview.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        cakeModel.hasCandles = !cakeModel.hasCandles;
        cakeview.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        cakeModel.numCandles = i;
        cakeview.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            //this is reporting the beginning of a touch event, as the user touches down
            cakeModel.myBalloon.setXY(motionEvent.getX(), motionEvent.getY()); //updates coordinates to draw balloon at
            cakeModel.drawBalloon = true;

            cakeModel.xcoord = motionEvent.getX();
            cakeModel.ycoord = motionEvent.getY();

            cakeview.invalidate();
            return true;
        }
        return false;
    }
}
