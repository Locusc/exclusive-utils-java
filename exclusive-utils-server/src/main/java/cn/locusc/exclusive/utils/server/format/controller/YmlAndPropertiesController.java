package cn.locusc.exclusive.utils.server.format.controller;

import cn.locusc.exclusive.utils.server.format.service.implementation.YmlAndPropertiesImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jay Chan
 * YmlAndPropertiesController
 * 16:47 2020/7/23
 **/
@RestController
@RequestMapping("/api")
public class YmlAndPropertiesController {

    @Resource
    private YmlAndPropertiesImpl ymlAndProperties;

    @PostMapping("/ymlToProperties")
    public JSONObject ymlToProperties(@RequestBody JSONObject content) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<String> list = ymlAndProperties.yml2PropertiesService(content.getString("rightYamlValue"));
            jsonObject.put("status", true);
            jsonObject.put("properties", JSONObject.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", false);
        }
        return jsonObject;
    }
}
