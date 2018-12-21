package fr.wildcodeschool.xmlparser.builder;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.xmlparser.UnitValue;
import fr.wildcodeschool.xmlparser.reflect.GravityReflect;

public class LayoutParamsBuilder
        <B extends LayoutParamsBuilder, V extends LinearLayout.LayoutParams>
    extends Builder<V> {

    protected String TAG;
    protected Map<String, AttributeCommand> commandMap;
    protected Context context;

    private static final Map<String, Integer> layoutSizeParam =
            new HashMap<>();

    static {
        layoutSizeParam.put("match_parent", LinearLayout.LayoutParams.MATCH_PARENT);
        layoutSizeParam.put("wrap_content", LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public LayoutParamsBuilder(V object, Context context, String TAG) {
        super(object);
        this.TAG = TAG;
        this.context = context;
        this.commandMap = new HashMap<>();
        this.commandMap.put("android:layout_width", this::setWidth);
        this.commandMap.put("android:layout_height", this::setHeight);
        this.commandMap.put("android:layout_weight", this::setWeight);
        this.commandMap.put("android:layout_gravity", this::setGravity);
        this.commandMap.put("android:layout_marginHorizontal", this::setMarginHorizontal);
        this.commandMap.put("android:layout_marginVertical", this::setMarginVertical);
    }

    public B parseXmlNode(HashMap<String, String> items, XmlPullParser pParser) {
        String key, value;
        int lCount = pParser.getAttributeCount();
        for (int lIndex = 0; lIndex < lCount; lIndex++) {
            key = pParser.getAttributeName(lIndex);
            value = pParser.getAttributeValue(lIndex);
            AttributeCommand command = this.commandMap.get(key);
            if (command != null)
                command.apply(value);
            else
                items.put(key, value);
        }
        return (B)this;
    }

    public void setWidth(String value) {
        Integer layoutSize = layoutSizeParam.get(value);
        if (layoutSize != null)
            this.object.width = layoutSize;
        else
            Log.d(TAG, "Unknown layout size ["+value+"]");
    }

    public void setHeight(String value) {
        Integer layoutSize = layoutSizeParam.get(value);
        if (layoutSize != null)
            this.object.height = layoutSize;
        else
            Log.d(TAG, "Unknown layout size ["+value+"]");
    }

    public void setWeight(String value) {
        try {
            this.object.weight = Float.parseFloat(value);
        } catch (NumberFormatException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    public void setGravity(String value) {
        Integer gravity = GravityReflect.stringAsGravity(value);
        if (gravity != null)
            this.object.gravity = gravity;
        else
            Log.d(TAG, "Unknown gravity type ["+value+"]");
    }

    public void setMarginHorizontal(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null) {
            float px = unitValue.toPixel(this.context);
            this.object.leftMargin = (int) px;
            this.object.rightMargin = (int) px;
        }
    }

    public void setMarginVertical(String value) {
        UnitValue unitValue = UnitValue.valueOf(value);
        if (unitValue != null) {
            float px = unitValue.toPixel(this.context);
            this.object.topMargin = (int) px;
            this.object.bottomMargin = (int) px;
        }
    }

    public interface AttributeCommand {
        void apply(String key);
    }
}
