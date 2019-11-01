package com.wer.service.visa;

import com.wer.common.GlobalConstant;
import com.wer.dao.visa.VisaArticleMapper;
import com.wer.entity.visa.VisaArticle;
import com.wer.service.base.BaseService;
import com.wer.util.StringUtil;
import com.wer.util.WebServiceClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.wer.service.visa
 * @data: 2019-10-25 15:31
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class VisaClaimService extends BaseService<VisaArticle>{

    private static Logger logger = LoggerFactory.getLogger(VisaClaimService.class);

    @Autowired
    private VisaArticleMapper visaArticleMapper;

    /**
     * 根据国家名称匹配article信息
      * @param countryName
     * @return
     */
    public Map<String,Object> queryVisaArticleByCountryName(String countryName) {
        if(!StringUtil.isEmpty(countryName)){
            try {
                success = true;
                message = GlobalConstant.SUCCESS_MESSAGE;
                data = visaArticleMapper.queryVisaArticleByCountryName(countryName);
            } catch (Exception e){
                e.printStackTrace();
                success = false;
                message = e.getMessage();
            }
        }
        return result();
    }
}
