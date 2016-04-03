package net.yibee.weixin.util;

import com.google.gson.Gson;
import com.zmedu.tools.HttpUtil;
import net.yibee.weixin.entity.*;
import netscape.javascript.JSObject;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjie on 16/2/24.
 */
public class WeiXinUtil {
    private static Logger logger = Logger.getLogger(WeiXinUtil.class);

    private static String CreateMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static String UploadNews = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    public  static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
            button11.setName("home");
        button11.setType("click");
        button11.setKey("V1001_TODAY_MUSIC");

        ClickButton button12 = new ClickButton();
        button12.setName("me");
        button12.setType("click");
        button12.setKey("V1001_TODAY_GOOD");

        ViewButton viewButton = new ViewButton();
        viewButton.setUrl("http://www.baidu.com");
        viewButton.setName("help");
        viewButton.setType("view");

        menu.setButton(new Button[]{button11,button12,viewButton});
        return menu;
    }

    public static String createMenu(String token,Menu menu){
        String result = "";
        String url = CreateMenu.replace("ACCESS_TOKEN",token);
        logger.info(url);

        String menuJson = new Gson().toJson(menu);
        StringBuffer stringBuffer = new StringBuffer(menuJson);
        logger.info(menuJson);
        Map map = new HashMap();
        try {
            result = HttpUtil.postHttpUrl(stringBuffer, map, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ImageArticle initImageArticle(){
        ImageArticle imageArticle = new ImageArticle();

        List<ArticlesEntity> articlesEntities = new ArrayList<ArticlesEntity>();
        ArticlesEntity articlesEntity = new ArticlesEntity();
        articlesEntity.setAuthor("wangjie");
        articlesEntity.setContent("content");
        articlesEntity.setThumb_media_id("qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p");
        articlesEntity.setTitle("title");
        articlesEntity.setContent_source_url("www.qq.com");
        articlesEntity.setShow_cover_pic(1);
        articlesEntities.add(articlesEntity);
        imageArticle.setArticles(articlesEntities);
        return imageArticle;
    }

    public static  String uploadNews(String token,ImageArticle imageArticle){
        String result = "";
        String url = UploadNews.replace("ACCESS_TOKEN",token);
        logger.info(url);

        String menuJson = new Gson().toJson(imageArticle);
        StringBuffer stringBuffer = new StringBuffer(menuJson);
        logger.info(menuJson);
        Map map = new HashMap();
        try {
            result = HttpUtil.postHttpUrl(stringBuffer, map, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
