package natsconnectormodule.impl;

import java.util.List;

public class Utils {

    public static String toJson(List<String> names) {
        boolean first = true;
        StringBuilder sb = new StringBuilder("[");
        for (String name : names) {
            if (first) {
                first = false;
            }
            else {
                sb.append(',');
            }
            sb.append('"').append(name).append('"');
        }
        return sb.append(']').toString();
    }
}
