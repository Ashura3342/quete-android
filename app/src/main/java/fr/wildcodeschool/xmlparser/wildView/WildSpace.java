package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.view.View;

import fr.wildcodeschool.xmlparser.builder.ViewBuilder;


public class WildSpace extends View {

    private static final String TAG = WildSpace.class.getName();

    public WildSpace(Context context) {
        super(context);
    }

    public static class Builder
            extends ViewBuilder<Builder, WildSpace> {

        public Builder(Context context) {
            super(new WildSpace(context), WildSpace.TAG);
        }
    }
}
