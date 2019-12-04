package com.wer.controller.visa;

import com.wer.controller.base.BaseController;
import com.wer.service.visa.VisaRequirementsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @description: 签证要求控制类
 * @package_name: com.wer.controller.visa
 * @data: 2019-12-3 13:51
 * @author: Sean
 * @version: V1.0
 */
@Slf4j
@Controller
@RequestMapping("visaRequirementsController")
public class VisaRequirementsController extends BaseController{

    @Autowired
    private VisaRequirementsService visaRequirementsService;

    /**
     * 获取签证信息，通过国家
     */
    @GetMapping(value = "queryVisaRequirementsInfoBySearchKey")
    @ResponseBody
    public Map<String,Object> queryVisaRequirementsInfoBySearchKey(@RequestParam(value = "searcyKey") String searcyKey){
        return getResultMap(visaRequirementsService.queryVisaRequirementsInfoBySearchKey(searcyKey));
    }

}
