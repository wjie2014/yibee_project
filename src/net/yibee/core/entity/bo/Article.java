package net.yibee.core.entity.bo;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/1/12.
 */
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	private String english;
    private String chinese;
    private String imageUrl;
    private String editor;
    public String getEnglish() {
        return english;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getChinese() {
        return chinese;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
