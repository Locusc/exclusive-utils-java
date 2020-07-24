package cn.locusc.exclusive.utils.common.utils;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jay Chan
 * .yml转.properties格式
 * 16:11 2020/7/23
 **/
public class Yml2Properties {

    public static List<String> yml2Properties(String path) {
        final String DOT = ".";
        List<String> lines = new LinkedList<String>();
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(
                    path);
            // new InputStreamReader(new FileInputStream(path), Charset.forName(CommonConstants.ENCODING))
            StringBuilder key = new StringBuilder();
            String value = null;
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    // do nothing
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    if (key.length() > 0) {
                        key.append(DOT);
                    }
                    key.append(parser.getCurrentName());

                    token = parser.nextToken();
                    if (JsonToken.START_OBJECT.equals(token)) {
                        continue;
                    }
                    value = parser.getText();
                    lines.add(key + "=" + value);

                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = new StringBuilder(key.substring(0, dotOffset));
                    }
                    value = null;
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = new StringBuilder(key.substring(0, dotOffset));
                    } else {
                        key = new StringBuilder();
                        lines.add("");
                    }
                }
                token = parser.nextToken();
            }
            parser.close();

            System.out.println(lines);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
