package net.yibee.yoga.action.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yibee.yoga.action.system.SysArticleController;
import net.yibee.yoga.entity.SportInformation;
import net.yibee.yoga.service.SportInformationBoService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/*
 * 基本功能：健身资讯管理app接口
 * 创建：王杰
 */
@Controller
@RequestMapping("/interface")
public class SportInformationInterfaceController {
	private static Logger logger = Logger.getLogger(SysArticleController.class);
	private SportInformationBoService sportInformationBoService;
	private Gson gson = new Gson();
	private List<SportInformation> list = new ArrayList<SportInformation>();

	@Resource
	public void setBaseSevice(
			SportInformationBoService sportInformationBoService) {
		this.sportInformationBoService = sportInformationBoService;
	}

	@RequestMapping("/SportInformationList")
	public String getSportInformationList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("接口查询文章列表");
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
		
		List<Object> mlist = sportInformationBoService.findAllByConditions(pageIndex,
				pageSize,new String[]{"0"});
		int count = sportInformationBoService.findByCount();
		int size =count/pageSize;
		if(count%pageSize!=0){
			size++;
		}
		if (mlist != null && mlist.size() > 0) {
			for (Object obj : mlist) {
				SportInformation sportInformationBo = (SportInformation) obj;
				sportInformationBo.setPageCount(size+"");
				list.add(sportInformationBo);
			}
		}

		String reponseStr = "";
		if (list != null && list.size() > 0) {
			reponseStr = gson.toJson(list);
		}
		request.setAttribute("list", reponseStr);
		list.clear();
		reponseStr="";
		return "interface/SportInformationList";
	}
	@RequestMapping("/SportInformationScrollList")
	public String getSportInformationScrollList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("接口查询幻灯片列表");
		String mPageIndex = request.getParameter("pageNo");
		String mPageSize = request.getParameter("pageCount");
		int pageIndex = 1;
		int pageSize = 200;
		if (!"".equals(mPageIndex) && mPageIndex != null) {
			pageIndex = Integer.parseInt(mPageIndex.trim());
		}
		if (!"".equals(mPageSize) && mPageSize != null) {
			pageSize = Integer.parseInt(mPageSize.trim());
		}
		
		List<Object> mlist = sportInformationBoService.findAllByConditions(pageIndex,
				pageSize,new String[]{"1"});
		int count = sportInformationBoService.findByCount();
		int size =count/pageSize;
		if(count%pageSize!=0){
			size++;
		}
		if (mlist != null && mlist.size() > 0) {
			for (Object obj : mlist) {
				SportInformation sportInformationBo = (SportInformation) obj;
				sportInformationBo.setPageCount(size+"");
				list.add(sportInformationBo);
			}
		}
		
		String reponseStr = "";
		if (list != null && list.size() > 0) {
			reponseStr = gson.toJson(list);
		}
		request.setAttribute("list", reponseStr);
		list.clear();
		reponseStr="";
		return "interface/SportInformationList";
	}

}
