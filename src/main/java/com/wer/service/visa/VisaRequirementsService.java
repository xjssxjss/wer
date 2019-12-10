package com.wer.service.visa;

import com.wer.common.GlobalConstant;
import com.wer.entity.test.VisaVisit;
import com.wer.service.base.BaseService;
import com.wer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: TODO
 * @package_name: com.wer.service.visa
 * @data: 2019-12-3 13:53
 * @author: Sean
 * @version: V1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class VisaRequirementsService extends BaseService<VisaVisit>{

    /**
     * 根绝key查询国家签证信息
     * @param searcyKey
     */
    public Map<String,Object> queryVisaRequirementsInfoBySearchKey(String searcyKey){

        StringBuffer contentHtml = new StringBuffer("");
        //获取信息
        Map<String, Object> visaDataMap = GlobalConstant.visaDataMap;

        //返回前台的Map对象
        Map<String,Object> resultMap = new HashMap<>();

        //循环Map
        for(Map.Entry dataMap:visaDataMap.entrySet()){
            if(searcyKey.equals(dataMap.getKey())){
                resultMap.put("countryName",dataMap.getKey());

                contentHtml.append("<div class=\"weui-news\"><ul class=\"weui-news-list\">");
                VisaVisit value = (VisaVisit)dataMap.getValue();

                //国家
                value.getVisitAbbr();

                //时间
                value.getEnterDate();

                String time = "2019-12-03 10:10:30";

                //姓名
                value.getEnterName();

                contentHtml.append("<li class=\"weui-news-item\"> <div class=\"weui-news-inner\"> <div class=\"weui-news-inners\"> <div class=\"weui-news-text\"> <div class=\"weui-news-title\">签证要求-"+value.getVisitAbbr()+"("+value.getEnterDate()+")</div> </div> <div class=\"weui-news-info\"> <div class=\"weui-news-infoitem\"> <span class=\"weui-news-left\">"+value.getEnterName()+"</span> </div> <div class=\"weui-news-infoitem\">2018-08-09 10:31</div> </div> </div> <div class=\"weui-news-media\"> <img src=\"http://192.168.0.100/attachments/images/ruishi.jpg\"> </div> </div> </li>");
                contentHtml.append("</ul></div>");
                break;
            }
        }

        if(!StringUtil.isEmpty(contentHtml.toString())){
            resultMap.put("contentHtml",contentHtml.toString());
            success = true;
            data = resultMap;
        } else {
            success = false;
        }

        return result();
    }

}
