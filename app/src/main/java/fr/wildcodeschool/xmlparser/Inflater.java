package fr.wildcodeschool.xmlparser;

import android.content.Context;
import android.util.Xml;
import android.view.ViewGroup;
import android.widget.Space;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import fr.wildcodeschool.xmlparser.wildView.WildButton;
import fr.wildcodeschool.xmlparser.wildView.WildCheckBox;
import fr.wildcodeschool.xmlparser.wildView.WildEditText;
import fr.wildcodeschool.xmlparser.wildView.WildLinearLayout;
import fr.wildcodeschool.xmlparser.wildView.WildSpace;
import fr.wildcodeschool.xmlparser.wildView.WildTextView;

public class Inflater {
  // Activity context
  private Context ctx;

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
        switch (parser.getName()) {
          case "LinearLayout":
            WildLinearLayout lLayout = new WildLinearLayout.Builder(ctx)
                    .parseXmlNode(parser)
                    .build();
            lParentView.addView(lLayout);
            lParentView = lLayout;
            break;
          case "EditText":
            WildEditText lEditText = new WildEditText.Builder(ctx)
                    .parseXmlNode(parser)
                    .build();
            lParentView.addView(lEditText);
            break;
          case "Button":
            WildButton lButton = new WildButton.Builder(ctx)
                    .parseXmlNode(parser)
                    .build();
            lParentView.addView(lButton);
            break;
          case "TextView":
            WildTextView lTextView = new WildTextView.Builder(ctx)
                    .parseXmlNode(parser)
                    .build();
            lParentView.addView(lTextView);
            break;
          case "CheckBox":
            WildCheckBox lCheckBox = new WildCheckBox.Builder(ctx)
                    .parseXmlNode(parser)
                    .build();
            lParentView.addView(lCheckBox);
            break;
          case "Space":
              WildSpace lSpaceView = new WildSpace.Builder(ctx)
                      .parseXmlNode(parser)
                      .build();
              lParentView.addView(lSpaceView);
            break;
          default:
            break;
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
}
