package cn.locusc.exclusive.utils.common.utils;

import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Y2P {

    public static String[] convert(String yml) {
        try {
            Yaml yaml = new Yaml();
            Object load = yaml.load(yml);
            List<String> stringList = new ArrayList<>();
            go("", load, stringList);
            return stringList.toArray(new String[stringList.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static void go(String s, Object data, List<String> list) {
        if (data instanceof LinkedHashMap) {
            ((LinkedHashMap) data).forEach((o1, o2) -> {
                go(s + (s.isEmpty() ? "" : ".") + o1.toString(), o2, list);
            });
        } else if (data instanceof ArrayList) {
            int i = 0;
            for (Object o1 : ((ArrayList) data)) {
                go(s + (s.isEmpty() ? "" : ".") + "[" + i + "]", o1, list);
                i += 1;
            }
        } else {
            list.add(s + "=" + data.toString());
        }
    }
}
