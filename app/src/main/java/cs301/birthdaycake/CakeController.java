package cs301.birthdaycake;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.Switch;

public class CakeController implements View.OnClickListener, Switch.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
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
}
