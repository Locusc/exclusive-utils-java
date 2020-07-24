package cn.locusc.exclusive.utils.server.format.service.implementation;

import cn.locusc.exclusive.utils.common.utils.P2Y;
import cn.locusc.exclusive.utils.common.utils.Y2P;
import cn.locusc.exclusive.utils.server.format.service.YmlAndPropertiesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.StringJoiner;

/**
 * @author Jay Chan
 * YmlAndProperties格式转换
 * 16:24 2020/7/23
 **/
@Service
public class YmlAndPropertiesImpl implements YmlAndPropertiesService {

    @Override
    public String yml2PropertiesService(String content) {
        String[] convert = Y2P.convert(content);
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (String s : convert) {
            stringJoiner.add(s);
        }
        return stringJoiner.toString();
    }

    @Override
    public String properties2YmlService(String content) throws IOException {
        return P2Y.convert(content);
    }
}
