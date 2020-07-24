package cn.locusc.exclusive.utils.server.format.controller;

import cn.locusc.exclusive.utils.common.api.Result;
import cn.locusc.exclusive.utils.server.format.service.implementation.YmlAndPropertiesImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jay Chan
 * YmlAndPropertiesController
 * 16:47 2020/7/23
 **/
@RestController
public class YmlAndPropertiesController {

    @Resource
    private YmlAndPropertiesImpl ymlAndProperties;

    @PostMapping("/ymlToProperties")
    public Result<?> ymlToProperties(@RequestBody JSONObject jsonObject) {
        Result<?> result = new Result<>();
        try {
            String strings = ymlAndProperties.yml2PropertiesService(jsonObject.getString("content"));
            result.setSuccess(true);
            result.setMessage(strings);
        } catch (Exception e) {
            e.printStackTrace();
            Result.error("ymlToProperties转换错误");
        }
        return result;
    }

    @PostMapping("/propertiesToYml")
    public Result<?> propertiesToYml(@RequestBody JSONObject jsonObject) {
        Result<?> result = new Result<>();
        try {
            String strings = ymlAndProperties.properties2YmlService(jsonObject.getString("content"));
            result.setSuccess(true);
            result.setMessage(strings);
        } catch (Exception e) {
            e.printStackTrace();
            Result.error("ymlToProperties转换错误");
        }
        return result;
    }

}
