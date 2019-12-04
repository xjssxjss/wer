package com.wer.dao.sys;

import com.wer.dao.BaseMapper;
import com.wer.entity.sys.SysSequenceCounter;

public interface SysSequenceCounterMapper extends BaseMapper<SysSequenceCounter>{
    //id生成器
    String generateCode(String className, String prefixCode);
}