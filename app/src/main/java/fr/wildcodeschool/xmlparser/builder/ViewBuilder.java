package fr.wildcodeschool.xmlparser.builder;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.xmlparser.UnitValue;
import fr.wildcodeschool.xmlparser.reflect.GravityReflect;

public class ViewBuilder<B extends ViewBuilder, V extends View>
        extends Builder<V> {

    protected String TAG;

    protected Map<String, AttributeCommand> commandMap;
    private LinearLayout.LayoutParams layoutParams;
    private static final Map<String, Integer> layoutSizeParam =
            new HashMap<>();

    static {
        layoutSizeParam.put("match_parent", LinearLayout.LayoutParams.MATCH_PARENT);
        layoutSizeParam.put("wrap_content", LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public ViewBuilder(V object, String tag) {
        super(object);
        this.TAG = tag;
        this.commandMap = new HashMap<>();
        this.commandMap.put("android:id", this::setId);
        this.commandMap.put("android:paddingHorizontal", this::setPaddingHorizontal);
        this.commandMap.put("android:paddingVertical", this::setPaddingVertical);
        this.commandMap.put("android:background", this::setBackgroundColor);
        this.commandMap.put("android:layout_width", this::setLayoutWidth);
        this.commandMap.put("android:layout_height", this::setLayoutHeight);
        this.commandMap.put("android:layout_weight", this::setLayoutWeight);
        this.commandMap.put("android:layout_gravity", this::setLayoutGravity);
        this.commandMap.put("android:layout_marginHorizontal", this::setLayoutMarginHorizontal);
        this.commandMap.put("android:layout_marginVertical", this::setLayoutMarginVertical);
        layoutParams = new LinearLayout.LayoutParams (
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
    }

    public B parseXmlNode(XmlPullParser pParser) {
        String key, value;
        int lCount = pParser.getAttributeCount();
        for (int lIndex = 0; lIndex < lCount; lIndex++) {
            key = pParser.getAttributeName(lIndex);
            value = pParser.getAttributeValue(lIndex);
            AttributeCommand command = this.commandMap.get(key);
            if (command != null)
                command.apply(value);
            else
                Log.d(TAG, "Unknown attribute [" + key + "]");
        }
        this.object.setLayoutParams(layoutParams);
        return (B)this;
    }


    private void setPaddingHorizontal(String value) {
        UnitValue paddingHorizontal = UnitValue.valueOf(value);
        if (paddingHorizontal != null) {
            int lPaddingHorizontal = (int) paddingHorizontal.toPixel(this.object.getContext());
            this.object.setPadding(
                    lPaddingHorizontal,
                    this.object.getPaddingTop(),
                    lPaddingHorizontal,
                    this.object.getPaddingBottom());
        }
    }

    private void setPaddingVertical(String value) {
        UnitValue paddingVertical = UnitValue.valueOf(value);
        if (paddingVertical != null) {
            int lPaddingVertical =(int) paddingVertical.toPixel(this.object.getContext());
            this.object.setPadding(
                    this.object.getPaddingLeft(),
                    lPaddingVertical,
                    this.object.getPaddingRight(),
                    lPaddingVertical);
        }
    }

    protected void setId(String value) {
        // doing nothing
    }

    private void setBackgroundColor(String value) {
        this.object.setBackgroundColor(Color.parseColor(value));
    }

    public void setLayoutWidth(String value) {
        Integer layoutSize = layoutSizeParam.get(value);
        if (layoutSize != null)
            this.layoutParams.width = layoutSize;
        else
            Log.d(TAG, "Unknown layout size ["+value+"]");
    }

    public void setLayoutHeight(String value) {
        Integer layoutSize = layoutSizeParam.get(value);
        if (layoutSize != null)
            this.layoutParams.height = layoutSize;
        else
            Log.d(TAG, "Unknown layout size ["+value+"]");
    }

    public void setLayoutWeight(String value) {
        try {
            this.layoutParams.weight = Float.parseFloat(value);
        } catch (NumberFormatException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    public void setLayoutGravity(String value) {
        Integer gravity = GravityReflect.stringAsGravity(value);
        if (gravity != null)
            this.layoutParams.gravity = gravity;
        else
            Log.d(TAG, "Unknown gravity type ["+value+"]");
    }

    public void setLayoutMarginHorizontal(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null) {
            float px = unitValue.toPixel(this.object.getContext());
            this.layoutParams.leftMargin = (int) px;
            this.layoutParams.rightMargin = (int) px;
        }
    }

    public void setLayoutMarginVertical(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null) {
            float px = unitValue.toPixel(this.object.getContext());
            this.layoutParams.topMargin = (int) px;
            this.layoutParams.bottomMargin = (int) px;
        }
    }

    public interface AttributeCommand {
        void apply(String value);
    }
}
