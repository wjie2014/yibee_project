package net.yibee.yoga.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yibee.core.util.DateUtil;
import net.yibee.core.util.PageBean;
import net.yibee.html.parse.HtmlCrawler;
import net.yibee.html.parse.RandomDigital;
import net.yibee.yoga.entity.SportInformation;
import net.yibee.yoga.service.SportInformationBoService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/system/article")
public class SysArticleController {

	private static Logger logger = Logger.getLogger(SysArticleController.class);
	private SportInformationBoService sportInformationBoService;
	private String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
	private int pageSize = 10;

	@Resource
	public void setBaseSevice(
			SportInformationBoService sportInformationBoService) {
		this.sportInformationBoService = sportInformationBoService;
	}

	/**
	 * 系统管理员 获取文章列表
	 * 
	 */
	@RequestMapping("/listArticle")
	public String getArticleList(HttpServletRequest request) throws Exception {
		String mPageIndex = request.getParameter("pageNo");
		int pageIndex = 1;
		if (!"".equals(mPageIndex) && mPageIndex != null) {
			pageIndex = Integer.parseInt(mPageIndex.trim());
		}
		List<Object> mlist = sportInformationBoService.findAll(pageIndex,
				pageSize);
		int pageCount = sportInformationBoService.findByCount();
		logger.info("查询文章列表");
		if (mlist != null && mlist.size() > 0) {
			PageBean pageBean = new PageBean(pageIndex, pageSize, pageCount,
					mlist);
			request.setAttribute("pageBean", pageBean);
		}
		return "system/articleList";
	}

	@RequestMapping("/saveArticle")
	public String saveArticle(HttpServletRequest request) {
		logger.info("保存文章到数据库表");
		String detail = "<style>img{width:100%;max-width:100%;float:left;margin:10px 0;}</style>"
				+ request.getParameter("detail");
		String name = request.getParameter("name");
		String thumb = request.getParameter("thumb");
		String type = request.getParameter("type");
		String times = request.getParameter("times");
		String updateTime = DateUtil.dateToString(new Date(), dateFormatStr);

		// 获取文本域的内容并添加到数据库，返回添加时候成功
		SportInformation sportInformationBo = new SportInformation();
		sportInformationBo.setName(name);
		sportInformationBo.setDetail(detail);
		sportInformationBo.setTypeId(type);
		sportInformationBo.setTimes(times);
		sportInformationBo.setThumb(thumb);
		sportInformationBo.setUpdateTime(updateTime);
		if ("1".equals(type)) {
			sportInformationBo.setTypeName("每日瑜伽");
		} else if ("2".equals(type)) {
			sportInformationBo.setTypeName("瑜伽视频");
		}
		boolean flag = sportInformationBoService.insert(sportInformationBo);
		/*
		 * // 如何添加成功，查询数据库list数据，返回个前台，若添加失败，返回添加失败，前台js提示添加失败 if (list != null
		 * && list.size() > 0) { request.setAttribute("articleList", list); }
		 */
		int pageIndex = 1;
		List<Object> mlist = sportInformationBoService.findAll(pageIndex,
				pageSize);
		int pageCount = sportInformationBoService.findByCount();
		if (mlist != null && mlist.size() > 0) {
			PageBean pageBean = new PageBean(pageIndex, pageSize, pageCount,
					mlist);
			request.setAttribute("pageBean", pageBean);
		}
		return "system/articleList";
	}

	/**
	 * 系统管理员 获取单篇文章
	 */
	@RequestMapping("/getArticle")
	public String getArticle(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		SportInformation sportInformationBo = (SportInformation) sportInformationBoService
				.findById(id);
		request.setAttribute("SportInformationBo", sportInformationBo);
		return "system/updateSportInformation";

	}

	/**
	 * 系统管理员 修改单篇文章
	 */
	@RequestMapping("/updateArticle")
	public void updateArticle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		/*String data = request.getParameter("data");
        JSONObject jsonObj = new JSONObject(data);
        String s  = jsonObj.getString("id");
		String[] datas = request.getParameterValues("data");
		String information = request.getParameter("information");
		String[] informations = request.getParameterValues("information");*/
		String id = request.getParameter("id");
		String detail = "<style>img{width:100%;max-width:100%;float:left;margin:10px 0;}</style>"
				+ request.getParameter("detail");
		String name = request.getParameter("name");
		String thumb = request.getParameter("thumb");
		String type = request.getParameter("type");
		String times = request.getParameter("times");
		String updateTime = DateUtil.dateToString(new Date(), dateFormatStr);

		// 获取文本域的内容并添加到数据库，返回添加时候成功
		SportInformation sportInformationBo = new SportInformation();
		sportInformationBo.setId(id);
		sportInformationBo.setName(name);
		sportInformationBo.setDetail(detail);
		sportInformationBo.setTypeId(type);
		sportInformationBo.setTimes(times);
		sportInformationBo.setThumb(thumb);
		sportInformationBo.setUpdateTime(updateTime);
		if ("1".equals(type)) {
			sportInformationBo.setTypeName("每日瑜伽");
		} else if ("2".equals(type)) {
			sportInformationBo.setTypeName("瑜伽视频");
		}
		sportInformationBoService.update(sportInformationBo);
	}
	@RequestMapping("/deleteArticle")
	public void deleteArticle(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		sportInformationBoService.delete(id);
	}
	/*
	 * 抓取瑜伽文章数据入库
	 */
	@RequestMapping("/addsArticle")
	public void addsArticle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 List<SportInformation> list = new ArrayList<SportInformation>();
			HtmlCrawler parseHtmlTools = new HtmlCrawler();
			parseHtmlTools.getHtml(
					"http://www.jianyumei.com.cn/NewList.aspx?Type_Sec=20",
					"http://www.jianyumei.com.cn",list);
			for(int i=0;i<list.size();i++){
				// 获取文本域的内容并添加到数据库，返回添加时候成功
				SportInformation sportInformationBo = new SportInformation();
				sportInformationBo.setName(list.get(i).getName());
				sportInformationBo.setDetail(list.get(i).getDetail());
				sportInformationBo.setTypeId(list.get(i).getTypeId());
				sportInformationBo.setTimes(RandomDigital.runVerifyCode(5).trim());
				sportInformationBo.setThumb(list.get(i).getThumb());
				sportInformationBo.setUpdateTime(list.get(i).getUpdateTime());
					sportInformationBo.setTypeName(list.get(i).getTypeName());
				sportInformationBoService.insert(sportInformationBo);
			}
	}
}
