package com.wer.service.sys;

import com.wer.entity.sys.Attachment;
import com.wer.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: TODO
 * @package_name: com.wer.service.sys
 * @data: 2019-10-25 14:00
 * @author: Sean
 * @version: V1.0
 */
@Service
@Transactional
public class AttachmentService extends BaseService<Attachment>{

    private static Logger logger = LoggerFactory.getLogger(AttachmentService.class);

    /**
     * 根据业务Id 查询相关附件
     * @param slipId
     */
    public List<Attachment> findAttachmentBySlipId(Integer slipId){
        return null;
    }

    /**
     * insert 录入附件数据信息
     * @return
     */
    public int insertAttachment(Attachment attachment) throws Exception{
        return insert(attachment);
    }


}
