package com.wer.service.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wer.common.BusinessException;
import com.wer.entity.wx.UserAuthrize;
import com.wer.service.base.BaseService;
import com.wer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description: 认证信息service服务层
 * @package_name: com.wer.service.wx
 * @data: 2019-11-28 11:07
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class WxUserAuthrizeService extends BaseService<UserAuthrize>{

    /**
     * 新增认证信息
     * @param userId
     * @param devId
     */
    public void insertUserAuthrize(String userId,
                                   String devId) {

        UserAuthrize userAuthrize = new UserAuthrize();

        //把UserId换取OpenId
        String convertToOpenIdUrl = resourceMap.get("convert_to_openid_url").replace("ACCESS_TOKEN", WxService.getAccessToken());

        //换取openID所带的post参数
        Map<String,String> param = new HashMap<>();
        param.put("userid",userId);

        //发起换取openId请求
        String result = HttpClientUtil.doPostJson(convertToOpenIdUrl, JSON.toJSONString(param));

        //获取到JSONObject对象
        JSONObject object = JSON.parseObject(result);

        int errCode = object.getIntValue("errcode");
        if(0 == errCode){
            //获取openId
            String openId = object.getString("openid");

            //新增认证信息
            synchronized (userAuthrize){
                userAuthrize.setAuthId(UUID.randomUUID().toString());
                userAuthrize.setUserId(userId);
                userAuthrize.setDevId(devId);
                userAuthrize.setOpenId(openId);
                userAuthrize.setIsValid(true);
                userAuthrize.setAuthTime(new Date());
                try {
                    insert(userAuthrize);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        }
    }

    /**
     * 通过userId查询用户是否已经认证过
     * @param userId
     * @return
     */
    public boolean isExist(String userId){
        //声明集合对象
        List listUserAuth = null;

        Map<String,String> params = new HashMap<>();
        params.put("userId",userId);
        try {
            //根据userId查询信息
            listUserAuth = queryByParams(params);

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return listUserAuth.size()>0 ? true : false;
    }
}
