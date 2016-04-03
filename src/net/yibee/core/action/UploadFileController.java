package net.yibee.core.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yibee.core.util.DateUtil;
import net.yibee.core.util.ServerConfig;
import net.yibee.yoga.action.system.SysArticleController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/*
 * 基本功能：文件上传
 * 创建：王杰
 */
@Controller
@RequestMapping("/system/information")
public class UploadFileController {
	private static Logger logger = Logger.getLogger(SysArticleController.class);

	@RequestMapping("/upfile")
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		logger.info("upload file is start ...");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("X-Frame-Options", "SAMEORIGIN");
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				UUID uuid = UUID.randomUUID();
				String filename = uuid
						+ "."
						+ file.getOriginalFilename()
								.substring(
										file.getOriginalFilename().lastIndexOf(
												".") + 1);
				// 二级路径
				/*String tpath = DateUtil.dateToString(new Date(), "yyyyMMdd")
						+ "/";*/
				String realpath = ServerConfig.getValue("sport.realpath");
						//+ tpath;
				File file1 = new File(realpath);
				if (!file1.exists()) {
					file1.mkdirs();
				}
				// 获取webroot目录下的upload文件夹的地址,注意要是加入了spring会报空指针异常要更改为
				// ServletContext servletContext =
				// request.getSession().getServletContext();
				// String url = servletContext.getRealPath("/upload") + "\\";
				// path = request.getRealPath("/upload") + "\\" + filename;
				 String path = realpath + filename;
				//String path = "D:/" + filename;

				File mfile = new File(path);
				try {
					file.transferTo(mfile);
					String callback = request.getParameter("CKEditorFuncNum");
					String CKEditorFuncNum = request
							.getParameter("CKEditorFuncNum");
					PrintWriter out;
					// "/power/static/img/logo.png"
					// String mpath = request.getContextPath() + "/upload/"
					// + filename;

					String mpath = ServerConfig.getValue("sport.virtualpath")
							+ filename;
					String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
							+ CKEditorFuncNum + ", '" + mpath + "');</script>";
					try {
						out = response.getWriter();
						out.print(s);
						out.flush();
						logger.info("upload file is end ...");

					} catch (IOException e) {
						e.printStackTrace();
						logger.info("upload file is error ...");
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
					request.setAttribute("result", "上传文件失败");
					logger.info("upload file is error ...");

				} catch (IOException e) {
					e.printStackTrace();
					request.setAttribute("result", "上传文件失败");
					logger.info("upload file is error ...");

				}
			}

		}
	}
}
