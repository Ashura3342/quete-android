package fr.wildcodeschool.xmlparser;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.wildcodeschool.xmlparser.reflect.UnitReflect;

public class UnitValue {
    private static final String TAG = UnitValue.class.getName();

    private final float value;
    private final int unit;

    private UnitValue(int unit, float value) {
        this.value = value;
        this.unit = unit;
    }

    public static UnitValue valueOf(String str) throws NumberFormatException{
        Pattern pattern = Pattern.compile("([0-9]*)([a-z]*)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            Integer unit = UnitReflect.stringAsUnit(matcher.group(2));
            try {
                Float value = Float.parseFloat(matcher.group(1));
                if (unit != null)
                    return new UnitValue(unit, value);
                else
                    Log.d(TAG, "Unknown unit value ["+matcher.group(2)+"]");
            } catch (NumberFormatException ex) {
                Log.d(TAG, ex.getMessage());
            }
        }
        return  null;
    }

    public float getValue() {
        return value;
    }

    public int getUnit() {
        return unit;
    }

    public float toPixel(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(this.getUnit(), this.getValue(), metrics);
    }
}
