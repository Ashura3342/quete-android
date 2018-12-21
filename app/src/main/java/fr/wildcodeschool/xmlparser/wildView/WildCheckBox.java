package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;

import fr.wildcodeschool.xmlparser.builder.TextViewBuilder;

public class WildCheckBox extends AppCompatCheckBox {

    private static final String TAG = WildCheckBox.class.getName();

    public WildCheckBox(Context context) {
        super(context);
    }

    public static class Builder
        extends TextViewBuilder<Builder, WildCheckBox> {

        public Builder(Context context) {
            super(new WildCheckBox(context), WildCheckBox.TAG);
        }
    }
}
