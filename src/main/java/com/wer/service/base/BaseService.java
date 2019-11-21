package com.wer.service.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wer.common.BaseObject;
import com.wer.common.GlobalConstant;
import com.wer.dao.AttachmentMapper;
import com.wer.dao.BaseMapper;
import com.wer.dao.DictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象类
 * @param <T>
 */
public abstract class BaseService<T> extends BaseObject {

    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private BaseMapper<T> baseMapper;

    @Autowired
    public DictionaryMapper dictionaryMapper;

    @Autowired
    public AttachmentMapper attachmentMapper;

    public boolean success = false;
    public Object data = null;
    public String message = GlobalConstant.ERROR_MESSAGE;

    //返回结果集对象
    private static Map<String,Object> resultMap = new HashMap();

    /**
     * 封装返回前台的结果集
     * @return
     */
    public Map<String,Object> result(){
        logger.info("返回对象数据:success:"+success+">message:"+message+">data:"+data);
        synchronized (resultMap){
            resultMap.put("success",success);
            resultMap.put("message",message);
            resultMap.put("data",success ? data: null);
        }
        return resultMap;
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
        int result= baseMapper.insert(entity);
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
}