package fr.wildcodeschool.xmlparser.builder;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.xmlparser.UnitValue;
import fr.wildcodeschool.xmlparser.wildView.WildLinearLayout;

public class ViewBuilder<B extends ViewBuilder, V extends View>
        extends Builder<V> {

    protected String TAG;

    protected Map<String, AttributeCommand> commandMap;

    public ViewBuilder(V object, String tag) {
        super(object);
        this.TAG = tag;
        this.commandMap = new HashMap<>();
        this.commandMap.put("android:paddingHorizontal", this::setPaddingHorizontal);
        this.commandMap.put("android:paddingVertical", this::setPaddingVertical);
        this.commandMap.put("android:background", this::setBackgroundColor);
        this.commandMap.put("android:id", this::setId);
    }

    public B parseXmlNode(XmlPullParser pParser) {
        HashMap<String, String> items = new HashMap<>();
        LinearLayout.LayoutParams params
                = new WildLinearLayout.WildLayoutParams.Builder(this.object.getContext())
                    .parseXmlNode(items, pParser)
                    .build();
        this.object.setLayoutParams(params);
        for (HashMap.Entry<String, String> entry : items.entrySet()) {
            AttributeCommand command = this.commandMap.get(entry.getKey());
            if (command != null)
                command.apply(entry.getValue());
            else
                Log.d(TAG, "Unknown attribute [" + entry.getKey() + "]");
        }
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

    public interface AttributeCommand {
        void apply(String value);
    }
}
