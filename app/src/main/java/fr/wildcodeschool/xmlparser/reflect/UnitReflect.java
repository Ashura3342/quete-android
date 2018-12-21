package fr.wildcodeschool.xmlparser.reflect;

import java.util.HashMap;
import java.util.Map;

public class UnitReflect {

    private static final Map<String, Integer> unitType
            = new HashMap<>();

    static {
        unitType.put("dp", android.util.TypedValue.COMPLEX_UNIT_DIP);
        unitType.put("sp", android.util.TypedValue.COMPLEX_UNIT_SP);
        unitType.put("px", android.util.TypedValue.COMPLEX_UNIT_PX);
    }

    public static Integer stringAsUnit(String type) {
        return unitType.get(type);
    }
}
