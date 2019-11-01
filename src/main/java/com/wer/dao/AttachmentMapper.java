package com.wer.dao;

import com.wer.entity.sys.Attachment;

import java.util.List;

public interface AttachmentMapper extends BaseMapper<Attachment>{
    List<Attachment> queryListAttachments();

    Integer queryListAttachmentsCount();
}