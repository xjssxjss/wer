package com.wer.entity.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wer.entity.base.BaseMessage;
import com.wer.entity.msg.child.Image;

import java.util.Map;

/**
 * @description: 回复图片消息
 * @package_name: com.wer.entity.msg
 * @data: 2019-10-23 14:07
 * @author: Sean
 * @version: V1.0
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("Image")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageMessage(Map<String, String> requestMap, Image image) {
        super(requestMap);
        super.setMsgType("image");
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "image=" + image +
                '}';
    }
}
