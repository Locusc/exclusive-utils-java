package cn.locusc.exclusive.utils.common.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Pattern;

public class P2Y {

    public static String convert(String ps) throws IOException {
        Properties properties = new Properties();
        properties.load(new StringReader(ps));
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("", hashMap);
        properties.forEach((o, o2) -> {
            join("", o.toString().split("\\."), o2, hashMap1);
        });
        Yaml yaml = new Yaml();
        return yaml.dumpAs(hashMap, Tag.MAP, DumperOptions.FlowStyle.BLOCK);
    }

    private static void join(String parentUrl, String[] keyList, Object value, HashMap<String, Object> pool) {
        if (keyList.length == 0) return;

        String myKey = keyList[0];
        String myUrl = parentUrl + "." + myKey;
        Object myParent = pool.get(parentUrl);
        Object myValue;

        List<String> stringList = new ArrayList<>(Arrays.asList(keyList).subList(1, keyList.length));
        String[] last = stringList.toArray(new String[((ArrayList) stringList).size()]);

        if (!pool.containsKey(myUrl)) {
            if (last.length == 0) {
                myValue = value;
            } else if (isArray(last[0])) {
                myValue = new ArrayList<>();
            } else {
                myValue = new LinkedHashMap<>();
            }
            if (myParent instanceof ArrayList) {
                ((ArrayList) myParent).add(myValue);
            } else if (myParent instanceof LinkedHashMap) {
                ((LinkedHashMap) myParent).put(myKey, myValue);
            }
            pool.put(myUrl, myValue);
        }
        join(myUrl, last, value, pool);
    }

    private static boolean isArray(String k) {
        String s = "";
        if (!k.endsWith("]")) return false;
        char[] chars = k.toCharArray();
        for (int i = chars.length - 2; i >= 0; i--) {
            char c = chars[i];
            if (c != '[') {
                s = c + s;
            } else {
                Pattern pattern = Pattern.compile("^[-+]?[\\d]*$");
                return pattern.matcher(s).matches();
            }
        }
        return false;
    }
}
