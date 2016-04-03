package net.yibee.weixin.entity;

import java.util.List;

/**
 * Created by wangjie on 16/2/25.
 */
public class ImageArticle {


    /**
     * articles : [{"thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p","author":"xxx","digest":"digest","show_cover_pic":1,"content_source_url":"www.qq.com","title":"Happy Day","content":"content"},{"thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p","author":"xxx","digest":"digest","show_cover_pic":0,"content_source_url":"www.qq.com","title":"Happy Day","content":"content"}]
     */
    private List<ArticlesEntity> articles;

    public void setArticles(List<ArticlesEntity> articles) {
        this.articles = articles;
    }

    public List<ArticlesEntity> getArticles() {
        return articles;
    }


}
