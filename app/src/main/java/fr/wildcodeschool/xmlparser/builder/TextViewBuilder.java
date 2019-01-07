package fr.wildcodeschool.xmlparser.builder;

import android.graphics.Color;
import android.widget.TextView;

import fr.wildcodeschool.xmlparser.reflect.GravityReflect;
import fr.wildcodeschool.xmlparser.UnitValue;
public class TextViewBuilder<B extends TextViewBuilder, V extends TextView>
        extends ViewBuilder<B, V> {

    public TextViewBuilder(V view, String TAG) {
        super(view, TAG);
        this.commandMap.put("android:ems", this::setEms);
        this.commandMap.put("android:textColor", this::setTextColor);
        this.commandMap.put("android:textSize", this::setTextSize);
        this.commandMap.put("android:text", this::setText);
        this.commandMap.put("android:gravity", this::setLayoutGravity);
    }

    public void setEms(String value) {
        this.object.setEms(Integer.parseInt(value));
    }

    public void setTextColor(String value) {
        this.object.setTextColor(Color.parseColor(value));
    }

    public void setTextSize(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null)
            this.object.setTextSize(unitValue.getUnit(), unitValue.getValue());
    }

    public void setText(String value) {
        this.object.setText(value);
    }

    public void setGravity(String value) {
        this.object.setGravity(GravityReflect.stringAsGravity(value));
    }
}
