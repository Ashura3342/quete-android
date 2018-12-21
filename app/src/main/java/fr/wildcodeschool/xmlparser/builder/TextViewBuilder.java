package fr.wildcodeschool.xmlparser.builder;

import android.graphics.Color;
import android.widget.TextView;

import fr.wildcodeschool.xmlparser.reflect.GravityReflect;
import fr.wildcodeschool.xmlparser.UnitValue;
public class TextViewBuilder<B extends TextViewBuilder, V extends TextView>
        extends ViewBuilder<B, V> {

    public TextViewBuilder(V textView, String TAG) {
        super(textView, TAG);
        this.commandMap.put("android:ems", this::setEms);
        this.commandMap.put("android:textColor", this::setTextColor);
        this.commandMap.put("android:textSize", this::setTextSize);
        this.commandMap.put("android:text", this::setText);
        this.commandMap.put("android:gravity", this::setGravity);
    }

    protected void setEms(String value) {
        this.object.setEms(Integer.parseInt(value));
    }

    protected void setTextColor(String value) {
        this.object.setTextColor(Color.parseColor(value));
    }

    protected void setTextSize(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null)
            this.object.setTextSize(unitValue.getUnit(), unitValue.getValue());
    }

    protected void setText(String value) {
        this.object.setText(value);
    }

    protected void setGravity(String value) {
        this.object.setGravity(GravityReflect.stringAsGravity(value));
    }
}
