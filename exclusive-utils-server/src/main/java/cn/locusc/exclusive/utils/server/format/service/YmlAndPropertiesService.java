package cn.locusc.exclusive.utils.server.format.service;

import java.io.IOException;

/**
 * @author Jay Chan
 * YmlAndProperties格式转换
 * 16:21 2020/7/23
 **/
public interface YmlAndPropertiesService {

    /**
     * yml -> properties
     * @param content 输入的内容
     * @return java.lang.String
     **/
    public String yml2PropertiesService(String content);

    /**
     * properties -> yml
     * @param content 输入的内容
     * @return java.lang.String
     **/
    public String properties2YmlService(String content) throws IOException;
}
