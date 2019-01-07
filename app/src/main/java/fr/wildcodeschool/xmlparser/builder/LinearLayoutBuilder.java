package fr.wildcodeschool.xmlparser.builder;

import android.util.Log;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.xmlparser.reflect.GravityReflect;

public class LinearLayoutBuilder <B extends LinearLayoutBuilder, V extends LinearLayout>
        extends ViewBuilder<B, V> {

    private static final Map<String, Integer> orientationType = new HashMap<>();

    static {
        orientationType.put("horizontal", LinearLayout.HORIZONTAL);
        orientationType.put("vertical", LinearLayout.VERTICAL);
    }

    public LinearLayoutBuilder(V view, String tag) {
        super(view, tag);
        this.commandMap.put("android:orientation", this::setOrientation);
        this.commandMap.put("android:weightSum", this::setWeightSum);
        this.commandMap.put("android:gravity", this::setLayoutGravity);
    }

    public void setOrientation(String value) {
        Integer orientation = orientationType.get(value);
        if (orientation != null)
            this.object.setOrientation(orientation);
        else
            Log.d(TAG, "Unknown orientation ["+value+"]");
    }

    public void setWeightSum(String value) {
        this.object.setWeightSum(Float.parseFloat(value));
    }

    public void setLayoutGravity(String value) {
        Integer gravity = GravityReflect.stringAsGravity(value);
        if (gravity != null) {
            this.object.setGravity(gravity);
        }
    }
}