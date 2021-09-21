package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CakeModel {
    public boolean candlesLit;
    public int numCandles;
    public boolean hasFrosting;
    public boolean hasCandles;

    public CakeModel(){
        candlesLit = true;
        numCandles = 2;
        hasFrosting = true;
        hasCandles = true;
    }
}
