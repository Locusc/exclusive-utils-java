package cn.locusc.exclusive.utils.server.format.service.implementation;

import cn.locusc.exclusive.utils.common.utils.Yml2Properties;
import cn.locusc.exclusive.utils.server.format.service.YmlAndPropertiesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jay Chan
 * YmlAndProperties格式转换
 * 16:24 2020/7/23
 **/
@Service
public class YmlAndPropertiesImpl implements YmlAndPropertiesService {

    @Override
    public List<String> yml2PropertiesService(String content) {
        return Yml2Properties.yml2Properties(content);
    }

    @Override
    public String properties2YmlService(String content) {
        return null;
    }
}
