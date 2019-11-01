package com.wer.entity.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wer.entity.base.BaseMessage;
import com.wer.entity.msg.child.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @package_name: com.wer.entity.msg
 * @data: 2019-10-23 12:18
 * @author: Sean
 * @version: V1.0
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{

    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articleList = new ArrayList<>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public NewsMessage(Map<String, String> requestMap, List<Article> articleList) {
        super(requestMap);
        super.setMsgType("news");
        this.articleCount = articleList.size()+"";
        this.articleList = articleList;
    }
}
