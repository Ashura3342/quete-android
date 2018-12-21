package fr.wildcodeschool.xmlparser.reflect;

import android.view.Gravity;

import java.util.HashMap;
import java.util.Map;

public class GravityReflect {
    private static final Map<String, Integer> gravityHashMap
            = new HashMap<>();

    static {
        gravityHashMap.put("center", Gravity.CENTER);
        gravityHashMap.put("left", Gravity.LEFT);
        gravityHashMap.put("top", Gravity.TOP);
        gravityHashMap.put("right", Gravity.RIGHT);
        gravityHashMap.put("bottom", Gravity.BOTTOM);
        gravityHashMap.put("center_horizontal", Gravity.CENTER_HORIZONTAL);
        gravityHashMap.put("center_vertical", Gravity.CENTER_VERTICAL);
    }

    public static Integer stringAsGravity(String gravity) {
        return gravityHashMap.get(gravity);
    }
}
