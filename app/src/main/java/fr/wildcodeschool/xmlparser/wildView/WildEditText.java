package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import fr.wildcodeschool.xmlparser.builder.EditTextBuilder;

public class WildEditText extends AppCompatEditText {
  // Log TAG definition
  private static final String TAG = WildEditText.class.getName();

  /**
   * Constructor
   * @param ctx Activity context
   */
  public WildEditText(Context ctx) {
    super(ctx);
  }

  public static class Builder
    extends EditTextBuilder<Builder, WildEditText> {

    public Builder(Context context) {
      super(new WildEditText(context), WildEditText.TAG);
    }
  }
}
