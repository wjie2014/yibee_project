package net.yibee.html.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.yibee.core.entity.bo.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * 瑙ｆ瀽html 
 * 缃戝潃鏄細http://news.iciba.com/dailysentence/history.html
 */
public class ENHtmlCrawler {
    private String id;
    private String name;
    private String chinese;
    private String english;
    private String imageUrl;
    private String editor;
    private String detail;

    public static void main(String[] args) {
        ENHtmlCrawler parseHtmlTools = new ENHtmlCrawler();
        parseHtmlTools.getHtml(
                "http://news.iciba.com/dailysentence/history.html",
                "http://news.iciba.com/dailysentence");
    }

    public List<Article> getHtml(String url, String domain) {
        List<Article> articleList = new ArrayList<Article>();
        try {
            Document doc = Jsoup.connect(url).maxBodySize(1024 * 1024 * 10)
                    .timeout(6000).get();
            //鏂囧瓧
            Elements infoTables = doc.getElementsByAttributeValue("class",
                    "c_l_m_rg fr");
            //鍥剧墖
            Elements images = doc.getElementsByAttributeValue("class",
                    "c_l_m_lf fl");
        /*	for (Element image : images) {
				Elements  linkHref = image.getElementsByTag("img");
				for (Element editor : linkHref) {
					name = editor.attr("src");
					System.out.println("img:"+name);
				}
			}*/
            for (int i = 0; i < infoTables.size(); i++) {
                Elements ens = infoTables.get(i).getElementsByClass("c_l_m_en");
                for (Element en : ens) {
                    english = en.text().trim();
                }
                System.out.println("EN:" + name);
                Elements cns = infoTables.get(i).getElementsByClass("c_l_m_cn");
                for (Element cn : cns) {
                    chinese = cn.text().trim();
                }
                System.out.println("CN:" + name);
                Elements editors = infoTables.get(i).getElementsByClass("c_l_m_editor");
                for (Element myEditor : editors) {
                    editor = myEditor.text().trim();
                }
                System.out.println("editor:" + name);
                Elements linkHref = images.get(i).getElementsByTag("img");
                for (Element editor : linkHref) {
                    imageUrl = editor.attr("src");
                    System.out.println("img:" + name);
                }
                Article article = new Article();
                article.setChinese(chinese);
                article.setEnglish(english);
                article.setImageUrl(imageUrl);
                article.setEditor(editor);
                articleList.add(article);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return articleList;
    }
}
