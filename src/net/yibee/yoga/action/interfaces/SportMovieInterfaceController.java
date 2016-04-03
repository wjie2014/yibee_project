package net.yibee.yoga.action.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yibee.yoga.action.system.SysArticleController;
import net.yibee.yoga.entity.SportMovie;
import net.yibee.yoga.service.SportMovieService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/*
 * 基本功能：健身视频管理app接口
 * 创建：王杰
 */
@Controller
@RequestMapping("/interface")
public class SportMovieInterfaceController {
	private static Logger logger = Logger.getLogger(SysArticleController.class);
	private SportMovieService sportMovieService;
	private Gson gson = new Gson();
	private List<SportMovie> list = new ArrayList<SportMovie>();

	@Resource
	public void setBaseSevice(
			SportMovieService sportMovieService) {
		this.sportMovieService = sportMovieService;
	}

	@RequestMapping("/SportMovieList")
	public String getSportMovieList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("接口查询瑜伽视频列表");
		String mPageIndex = request.getParameter("pageNo");
		String mPageSize = request.getParameter("pageCount");
		int pageIndex = 1;
		int pageSize = 20;
		if (!"".equals(mPageIndex) && mPageIndex != null) {
			pageIndex = Integer.parseInt(mPageIndex.trim());
		}
		if (!"".equals(mPageSize) && mPageSize != null) {
			pageSize = Integer.parseInt(mPageSize.trim());
		}
		List<Object> mlist = sportMovieService.findAll(pageIndex,
				pageSize);
		int count = sportMovieService.findByCount();
		int size =count/pageSize;
		if(count%pageSize!=0){
			size++;
		}
		if (mlist != null && mlist.size() > 0) {
			for (Object obj : mlist) {
				SportMovie sportMovie = (SportMovie) obj;
				sportMovie.setPageCount(size+"");
				list.add(sportMovie);
			}
		}

		String reponseStr = "";
		if (list != null && list.size() > 0) {
			reponseStr = gson.toJson(list);
		}
		request.setAttribute("list", reponseStr);
		list.clear();
		reponseStr="";
		logger.info("接口查询瑜伽视频列表");
		return "interface/SportMovieList";
	}

}
