package com.wer.service.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wer.common.BaseObject;
import com.wer.common.BusinessException;
import com.wer.common.GlobalConstant;
import com.wer.dao.AttachmentMapper;
import com.wer.dao.BaseMapper;
import com.wer.dao.DictionaryEntriesMapper;
import com.wer.dao.DictionaryMapper;
import com.wer.entity.base.EntitySupport;
import com.wer.entity.msg.Message;
import com.wer.enums.ResultCode;
import com.wer.util.ContextUtil;
import com.wer.util.JedisUtil;
import com.wer.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.util.*;

/**
 * 抽象类
 * @param <T>
 */
public abstract class BaseService<T> extends BaseObject {

    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private BaseMapper<T> baseMapper;

    @Autowired
    public AttachmentMapper attachmentMapper;

    public boolean success = false;
    public Object data = null;
    public String message = GlobalConstant.ERROR_MESSAGE;

    //返回结果集对象
    private Map<String,Object> resultMap = new HashMap();

    /**
     * 封装返回前台的结果集
     * @return
     */
    public Map<String,Object> result(){
        logger.info("返回对象数据:success:"+success+">message:"+message+">data:"+data);
        synchronized (resultMap){
            resultMap.put("success",success);
            resultMap.put("message",message);
            resultMap.put("data",data);
        }
        return resultMap;
    }

    /**
     * check实体验证信息
     * @param validate
     * @return
     */
    public String checkEntityResult(Set<ConstraintViolation<T>> validate){

        //返回实体验证信息
        StringBuffer sbf = new StringBuffer("");

        //同步验证
        synchronized (validate){
            //获取迭代器
            Iterator<ConstraintViolation<T>> iterator = validate.iterator();

            while(iterator.hasNext()){
                //拼接错误信息
                sbf.append("<span style='color:red;'>"+iterator.next().getMessage()+"</span></br>");
            }
        }

        return sbf.toString();
    }

    /**
     * 判断微信api访问是否成功
     * @param jsonObject
     * @return
     */
    public boolean getWxApiResult(JSONObject jsonObject){
        if(null != jsonObject && (0 == jsonObject.getIntValue("errcode"))){
            return true;
        } else {
            throw new RuntimeException((String)jsonObject.get("errmsg"));
        }
    }

    /**
     * 添加记录
     * @param entity
     * @return
     * @throws Exception
     */
    public int insert(T entity) throws Exception{
        //说明entity继承了EntitySupport父类
        if(entity instanceof EntitySupport){
            EntitySupport e = (EntitySupport)entity;
            if(null == e.getDeleted()){
                e.setDeleted(false);
            }
            if(null == e.getCreateTime()){
                e.setCreateTime(new Date());
            }
            if(StringUtil.isEmpty(e.getCreateBy())){
                e.setCreateBy(ContextUtil.getCurrentUser());
            }
            //保存日志
        }
        int result = baseMapper.insert(entity);
        return result;
    }

    /**
     * 批量添加记录
     * @param entities
     * @return
     * @throws Exception
     */
    public int insertBatch(List<T> entities) throws Exception{
        return baseMapper.insertBatch(entities);
    }

    public int insertSelective(T entity){
        return baseMapper.insertSelective(entity);
    }

    /**
     * 根据参数统计记录数
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int queryCountByParams(Map map)throws Exception{
        return baseMapper.queryCountByParams(map);
    }

    /**
     * 查询记录通过id
     * @param id
     * @return
     * @throws Exception
     */
    public T queryById(Integer id) throws Exception{
        return (T)baseMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public List<T> queryByParams(Map map)throws Exception{
        return baseMapper.queryByParams(map);
    }

    /**
     * 更新记录
     * @param entity
     * @return
     * @throws Exception
     */
    public int update(T entity)throws Exception{
        //说明entity继承了EntitySupport父类
        if(entity instanceof EntitySupport){
            EntitySupport e = (EntitySupport)entity;
            if(null == e.getDeleted()){
                e.setDeleted(false);
            }
            if(null == e.getUpdateTime()){
                e.setUpdateTime(new Date());
            }
            if(StringUtil.isEmpty(e.getUpdateBy())){
                e.setUpdateBy(ContextUtil.getCurrentUser());
            }
            //保存日志
        }
        return baseMapper.updateByPrimaryKey(entity);
    }

    /**
     * 批量更新
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int updateBatch(Map map) throws Exception{
        return baseMapper.updateBatch(map);
    }

    /**
     * 更新部分字段
     * @param eneity
     * @return
     */
    public int updateByPrimaryKeySelective(T eneity){
        return baseMapper.updateByPrimaryKeySelective(eneity);
    }

    /**
     * 删除记录
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Integer id) throws Exception{
        return  baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteBatch(int[] ids) throws Exception{
        return  baseMapper.deleteBatch(ids);
    }

    public Object parseObject(String userForm,Object obj){
        return JSON.parseObject(userForm, new TypeReference<Object>() {});
    }

    public synchronized void startPage(Map<String,Object> pageMap){
        Integer currentPage = 1;
        Integer pageSize = 10;
        String orderBy = "";
        if(null != pageMap){

            if(null != pageMap.get("currentPage")){
                currentPage = (Integer) pageMap.get("currentPage");
            }

            if(null != pageMap.get("pageSize")){
                pageSize = (Integer) pageMap.get("pageSize");
            }

            if(null != pageMap.get("orderBy")){
                orderBy = (String) pageMap.get("orderBy");
            }
        }
        try{
            PageHelper.startPage(currentPage,pageSize,orderBy);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * base64 转字节数组
     * @param data
     * @return
     */
    public synchronized InputStream base64ToBytes(String data){
        String base64Data = data.replaceAll("data:image/jpeg;base64,", "");
        OutputStream os = null;
        InputStream inputStream = null;
        byte[] bytes = null;

        //将字符串转换为byte数组
        try {
            bytes = new BASE64Decoder().decodeBuffer(base64Data.trim());
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }

        //转化为输入字节流
        inputStream = new ByteArrayInputStream(bytes);

//            os = new FileOutputStream("e:\\"+newFileName);
//
//            byte[] b = new byte[2014];
//            int len;
//            while(((len = inputStream.read(b)) != -1)){
//                os.write(b,0,len);
//            }
        return inputStream;
    }
}