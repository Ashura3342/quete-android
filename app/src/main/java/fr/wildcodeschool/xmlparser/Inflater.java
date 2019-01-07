package fr.wildcodeschool.xmlparser;

import android.content.Context;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.xmlparser.builder.ViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildButton;
import fr.wildcodeschool.xmlparser.wildView.WildEditText;
import fr.wildcodeschool.xmlparser.wildView.WildLinearLayout;
import fr.wildcodeschool.xmlparser.wildView.WildTextView;

public class Inflater {
  // Activity context
  private Context ctx;

    private static Map<String, Class<? extends ViewBuilder>> builderMap
            = new HashMap<>();

    static {
        builderMap.put("LinearLayout", WildLinearLayout.Builder.class);
        builderMap.put("EditText", WildEditText.Builder.class);
        builderMap.put("Button", WildButton.Builder.class);
        builderMap.put("TextView", WildTextView.Builder.class);
        builderMap.put("CheckBox", WildTextView.Builder.class);
        builderMap.put("Space", WildTextView.Builder.class);
    }

  // Constructor should only contains initialisation
  Inflater(Context ctx) {
    this.ctx = ctx;
  }

  /**
   * This method parse the xml layout to populate the activity screen
   * @param pParent Parent layout
   * @throws IOException
   * @throws XmlPullParserException
   */
  public void inflate(ViewGroup pParent) throws IOException, XmlPullParserException {
    // Store the parent
    ViewGroup lParentView = pParent;

    // INFO WCS - Here is how to keep a file from the assets directory
    InputStream lXmlStream = this.ctx.getAssets().open("content_assets.xml");

    // XML parser initialization
    XmlPullParser parser = Xml.newPullParser();
    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
    parser.setInput(lXmlStream, null);

    // Loop on XML nodes
    int eventType = parser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      if(eventType == XmlPullParser.START_TAG) {
          View view = createView(parser);
          if (view != null) {
              lParentView.addView(view);
              if (view instanceof WildLinearLayout)
                  lParentView = (WildLinearLayout) view;
        }
      }
      else if (eventType == XmlPullParser.END_TAG) {
        switch (parser.getName()) {
          case "LinearLayout":
            lParentView = (ViewGroup)lParentView.getParent();
            break;
          default:
            break;
        }
      }
      parser.next();
      eventType = parser.getEventType();
    }
  }

    private View createView(XmlPullParser parser) {
        Class<? extends ViewBuilder> classBuilder = builderMap.get(parser.getName());
        if (classBuilder != null) {
            try {
                View view = (View) classBuilder
                        .getDeclaredConstructor(Context.class)
                        .newInstance(ctx)
                        .parseXmlNode(parser)
                        .build();
                return view;
            } catch (IllegalAccessException |
                    NoSuchMethodException |
                    InstantiationException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
