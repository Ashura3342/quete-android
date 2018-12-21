package fr.wildcodeschool.xmlparser.builder;

import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class EditTextBuilder<B extends EditTextBuilder, V extends EditText>
        extends TextViewBuilder<B, V> {

    private static Map<String, Integer> inputTypeMap = new HashMap<>();

    static {
        inputTypeMap.put("textPersonName", InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
    }

    public EditTextBuilder(V textView, String TAG) {
        super(textView, TAG);
        this.commandMap.put("android:inputType", this::setInputType);
        this.commandMap.put("android:hint", this::setHint);
    }

    public void setInputType(String value) {
        Integer inputType = inputTypeMap.get(value);
        if (inputType != null)
            this.object.setInputType(inputType);
        else
            Log.d(TAG, "Unknown InputType ["+value+"]");
    }

    public void setHint(String value) {
        this.object.setHint(value);
    }
}


