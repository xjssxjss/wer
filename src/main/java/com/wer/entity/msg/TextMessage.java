package com.wer.entity.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wer.entity.base.BaseMessage;

import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.wer.entity.msg
 * @data: 2019-10-23 10:45
 * @author: Sean
 * @version: V1.0
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{

    @XStreamAlias("Content")
    private String content;

    public TextMessage(Map<String, String> requestMap, String content) {
        super(requestMap);
        super.setMsgType("text");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
