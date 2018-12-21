package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import fr.wildcodeschool.xmlparser.builder.TextViewBuilder;

public class WildButton extends AppCompatButton {
  // Log TAG definition
  private static final String TAG = WildButton.class.getName();

  /**
   * Constructor
   * @param pCtx Activity context
   */
  public WildButton(Context pCtx) {
    super(pCtx);
  }

  public static class Builder
          extends TextViewBuilder<Builder, WildButton> {

    public Builder(Context ctx) { super(new WildButton(ctx), WildButton.TAG); }

  }
}