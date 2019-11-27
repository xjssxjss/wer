package com.wer.common;

import com.wer.util.JedisUtil;
import com.wer.util.PropertiesListenerConfig;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class BaseObject {
    //获取ResourceBox的实例对象
    public static Map<String,String> resourceMap = null;

    //获取jedis的实例化对象
    public static JedisUtil jedisUtil = JedisUtil.getInstance();

    static {
        resourceMap = PropertiesListenerConfig.propertiesMap;
    }
}
