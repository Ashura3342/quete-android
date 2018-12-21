package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import fr.wildcodeschool.xmlparser.builder.TextViewBuilder;

public class WildTextView extends AppCompatTextView {
    private static final String TAG = WildTextView.class.getName();

    private WildTextView(Context context) {
        super(context);
    }

    public static class Builder
            extends TextViewBuilder<Builder, WildTextView> {

        public Builder(Context context) {
           super(new WildTextView(context), WildTextView.TAG);
        }
    }
}
