package com.wer.dao;

import com.wer.entity.sys.DictionaryEntries;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Component
public interface DictionaryEntriesMapper extends BaseMapper<DictionaryEntries>{

    //根据code查询单个对象
    DictionaryEntries queryByCode(Map<String, Object> map);

    //根据中文名称查询单个数据字典对象
    DictionaryEntries queryByDictionaryEntriesName(Map<String, Object> map);
}