package com.wer;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WerApplicationTests {

    @Test
    public void testList(){
        Map<String,Object> jsonMap = new HashMap<>();

        Map<String,Object> data = new HashMap<>();

        //颜色
        List<String> listColor = new ArrayList<>();
        listColor.add("yellow");
        listColor.add("blue");
        listColor.add("red");
        //性别
        List<String> gender = new ArrayList<>();
        gender.add("男");
        gender.add("女");

        List<String> isHat = new ArrayList<>();
        //是否戴帽子
        isHat.add("是");

        data.put("isHat",isHat);
        data.put("color",listColor);
        data.put("gender",gender);

        jsonMap.put("checkbox",data);

        System.out.println(JSON.toJSONString(jsonMap));
    }
}
