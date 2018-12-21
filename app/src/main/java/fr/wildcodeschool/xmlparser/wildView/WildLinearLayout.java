package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.widget.LinearLayout;

import fr.wildcodeschool.xmlparser.builder.LayoutParamsBuilder;
import fr.wildcodeschool.xmlparser.builder.LinearLayoutBuilder;

public class WildLinearLayout extends LinearLayout {
  // Log TAG definition
  private static final String TAG = WildLinearLayout.class.getName();

  /**
   * Constructor
   * @param ctx   Activity context
   */
  public WildLinearLayout(Context ctx) {
    super(ctx);
  }

  public static class Builder
    extends LinearLayoutBuilder<Builder, WildLinearLayout> {

    public Builder(Context context) {
      super(new WildLinearLayout(context), WildLinearLayout.TAG);
    }
  }

  public static class WildLayoutParams extends LinearLayout.LayoutParams {
    private static final String TAG = WildLayoutParams.class.getName();

    public WildLayoutParams() {
      super(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static class Builder
      extends LayoutParamsBuilder<Builder, WildLayoutParams> {
      public Builder(Context context) {
        super(new WildLayoutParams(), context, WildLayoutParams.TAG);
      }

    }
  }
}
