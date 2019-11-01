package com.wer.entity.msg.child;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @description: TODO
 * @package_name: com.wer.entity.msg.child
 * @data: 2019-10-23 14:07
 * @author: Sean
 * @version: V1.0
 */
@XStreamAlias("Image")
public class Image {

    @XStreamAlias("MediaId")
    private String mediaId;

    public Image(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
