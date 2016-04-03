package net.yibee.weixin.entity;

/**
 * Created by wangjie on 16/2/25.
 */

    public class ArticlesEntity {
        /**
         * thumb_media_id : qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p
         * author : xxx
         * digest : digest
         * show_cover_pic : 1
         * content_source_url : www.qq.com
         * title : Happy Day
         * content : content
         */
        private String thumb_media_id;
        private String author;
        private String digest;
        private int show_cover_pic;
        private String content_source_url;
        private String title;
        private String content;

        public void setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public void setShow_cover_pic(int show_cover_pic) {
            this.show_cover_pic = show_cover_pic;
        }

        public void setContent_source_url(String content_source_url) {
            this.content_source_url = content_source_url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getThumb_media_id() {
            return thumb_media_id;
        }

        public String getAuthor() {
            return author;
        }

        public String getDigest() {
            return digest;
        }

        public int getShow_cover_pic() {
            return show_cover_pic;
        }

        public String getContent_source_url() {
            return content_source_url;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
