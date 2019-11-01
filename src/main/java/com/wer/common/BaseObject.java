package com.wer.common;

import com.wer.util.PropertiesListenerConfig;
import java.util.Map;

public class BaseObject {
    //获取ResourceBox的实例对象
    public static Map<String,String> resourceMap = null;
    static {
        resourceMap = PropertiesListenerConfig.propertiesMap;
    }
}
