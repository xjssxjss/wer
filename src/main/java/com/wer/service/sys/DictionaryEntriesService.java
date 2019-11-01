package com.wer.service.sys;

import com.wer.common.GlobalConstant;
import com.wer.dao.DictionaryEntriesMapper;
import com.wer.entity.sys.DictionaryEntries;
import com.wer.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.wer.service.sys
 * @data: 2019-10-25 14:32
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class DictionaryEntriesService extends BaseService<DictionaryEntries>{

    private static Logger logger = LoggerFactory.getLogger(DictionaryEntriesService.class);

    @Autowired
    private DictionaryEntriesMapper dictionaryEntriesMapper;

    /**
     * 根据code获取集合
     * @return
     */
    public Map<String,Object> queryDictionaryEntriesByCode(){
        Map resultMap = new HashMap();
        List<DictionaryEntries> dictionary = null;
        try{
            Map map = new HashMap();
            dictionary = dictionaryEntriesMapper.queryByParams(map);

            success = true;
            message = GlobalConstant.SUCCESS_MESSAGE;
            resultMap.put("data",dictionary);
        } catch (Exception e){
            success = false;
            message = e.getMessage();
        }
        for (DictionaryEntries dictionaryEntries : dictionary) {
            logger.info("dictionaryEntries:"+dictionaryEntries.toString());
        }
        return result();
    }
 }
