package net.yibee.html.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.yibee.core.util.DateUtil;
import net.yibee.yoga.dao.SportInformationDao;
import net.yibee.yoga.dao.impl.SportInformationDaoImpl;
import net.yibee.yoga.entity.SportInformation;
import net.yibee.yoga.service.SportInformationBoService;
import net.yibee.yoga.service.impl.SportInformationBoServiceImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * 解析html 
 * 网址是：http://www.jianyumei.com.cn/NewList.aspx?Type_Sec=20
 */
public class HtmlCrawler {
	private String id;
	private String name;
	private String updateTime;
	private String thumb;
	private String typeid;
	private String typeName;
	private String times;
	private String detail;

	public static void main(String[] args) {
		List<SportInformation> list = new ArrayList<SportInformation>();
		HtmlCrawler parseHtmlTools = new HtmlCrawler();
		parseHtmlTools.getHtml(
				"http://www.jianyumei.com.cn/NewList.aspx?Type_Sec=20",
				"http://www.jianyumei.com.cn", list);
	}

	public void getHtml(String url, String domain, List<SportInformation> list) {
		try {
			Document doc = Jsoup.connect(url).maxBodySize(1024 * 1024 * 10)
					.timeout(6000).get();
			Elements infoTables = doc.getElementsByAttributeValue("class",
					"news_list_top_one");
			for (Element infoTable : infoTables) {
				Elements tableLineInfos = infoTable.select("a");
				for (Element lineInfo : tableLineInfos) {
					String linkHref = lineInfo.attr("href");
					name = lineInfo.text().trim();
					if (!"".equals(name)) {
						System.out.println(domain + "/" + linkHref);
						getHtmlDetail(domain + "/" + linkHref, domain);
						System.out.println("name:" + name);

						System.out.println("times:"
								+ RandomDigital.runVerifyCode(5));
						typeid = "1";
						System.out.println("typeid:" + typeid);
					}
				}
				System.out.println("thumb:" + thumb);
				System.out.println("detail:" + detail);
				times = RandomDigital.runVerifyCode(5);
				if (!"".equals(detail) && detail != null) {
					// 获取文本域的内容并添加到数据库，返回添加时候成功
					SportInformation sportInformationBo = new SportInformation();
					sportInformationBo.setName(name);
					sportInformationBo
							.setDetail("<style>img{width:100%;max-width:100%;float:left;margin:10px 0;}</style>"
									+ detail);
					sportInformationBo.setTypeId(typeid);
					sportInformationBo.setTimes(times);
					sportInformationBo.setThumb(thumb);
					sportInformationBo.setUpdateTime(updateTime);
					if ("1".equals(typeid)) {
						sportInformationBo.setTypeName("每日瑜伽");
					} else if ("2".equals(typeid)) {
						sportInformationBo.setTypeName("瑜伽视频");
					}
					list.add(sportInformationBo);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getHtmlDetail(String url, String domain) {
		try {
			Document doc = Jsoup.connect(url).maxBodySize(1024 * 1024 * 10)
					.timeout(6000).get();
			Element elment = doc.getElementById("lblAddDate");
			if (null != elment) {
				updateTime = elment.text().trim();
				System.out.println("时间：" + updateTime);
			}
			Elements elmentdiv = doc.select("div");
			System.out.println("div：" + elmentdiv.size());
			if (elmentdiv.size() > 27) {
				detail = elmentdiv.get(24).toString();
			}
			Elements elmentdivimg = doc.select("div img");
			for (Element el : elmentdivimg) {
				String linkHref = el.attr("src");
				if (linkHref.startsWith("http:")) {
					thumb = linkHref.toString().trim();
					break;
				}
			}
			int page = getHtmlPageSize(url, domain);
			if (page > 1) {
				getHtmlPage(url, domain, page);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getHtmlPage(String url, String domain, int page) {
		for (int i = 2; i <= page; i++) {
			Document doc;
			try {
				doc = Jsoup.connect(url + "&page=" + i+"&time="+DateUtil.dateToString(new Date(), "yyMMddhhmmss"))
						.maxBodySize(1024 * 1024 * 10).timeout(6000).get();

				Elements elmentdiv = doc.select("div");
				if (elmentdiv.size() > 27) {
					detail += elmentdiv.get(24).toString();
				}
				doc = null;
				elmentdiv=null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int getHtmlPageSize(String url, String domain) {
		int page = 1;
		try {
			Document doc = Jsoup.connect(url).maxBodySize(1024 * 1024 * 10)
					.timeout(6000).get();
			List<Integer> pages = new ArrayList<Integer>();
			Elements elmentdiva = doc.select("div a");
			for (Element els : elmentdiva) {
				String linkHref = els.attr("href");
				if (linkHref.contains("page") && linkHref.contains("ID")) {
					String[] strs = linkHref.trim().split("&");
					String str = strs[0].split("=")[1];

					if (!"".equals(str)) {
						pages.add(Integer.parseInt(str.trim()));
					} else {
						pages.add(1);
					}
				}
			}
			// System.out.println("总页数：" + getMax(pages));
			page = getMax(pages);
			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;

	}

	/**
	 * 
	 * 获取数组中的最大值
	 * 
	 * */

	public int getMax(List<Integer> arr) {

		int max = 1;

		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i) > max) {

				max = arr.get(i);

			}

		}

		return max;
	}

}
