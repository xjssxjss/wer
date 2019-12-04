package com.wer.controller.base;

import com.wer.common.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: baseController
 * @package_name: com.wer.controller.base
 * @data: 2019-11-20 14:42
 * @author: Sean
 * @version: V1.0
 */
@Controller
@RequestMapping("/baseController")
public class BaseController extends BaseObject{

    /**
     * 封装返回给前台的数据
     * @param resultMap
     * @return
     */
    public Map<String,Object> getResultMap(Map<String,Object> resultMap){
        resultMap.put("success",resultMap.get("success"));
        resultMap.put("data",resultMap.get("data"));
        resultMap.put("message",resultMap.get("message"));
        return resultMap;
    }
}
