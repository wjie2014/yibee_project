package net.yibee.action.system;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.yibee.core.entity.bo.SysUserInfoBo;
import net.yibee.core.entity.vo.SysUserVo;
import net.yibee.core.service.SysUserInfoService;
import net.yibee.core.util.DateUtil;
import net.yibee.core.util.PasswordHash;
import net.yibee.core.util.PageBean;

import org.apache.log4j.Logger;
//import org.codehaus.jackson.node.NodeCursor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/system/user")
public class SysUserController {
	private static Logger logger = Logger.getLogger(SysUserController.class);

	private SysUserInfoService sysUserInfoService;
	private String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
	private int pageSize = 10;

	@Resource
	public void setBaseSevice(SysUserInfoService sysUserInfoService) {
		this.sysUserInfoService = sysUserInfoService;
	}

	/**
	 * 获取用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listUser")
	private String getUserList(HttpServletRequest request) {
		logger.info("查询用户开始...");
		String mPageIndex = request.getParameter("pageNo");
		int pageIndex = 1;
		if (!"".equals(mPageIndex) && mPageIndex != null) {
			pageIndex = Integer.parseInt(mPageIndex.trim());
		}
//		List<NodeCursor.Object> mlist = sysUserInfoService.findAll(pageIndex, pageSize);
		List<Object> mlist = sysUserInfoService.findAll(pageIndex, pageSize);
		int pageCount = sysUserInfoService.findByCount();
		if (mlist != null && mlist.size() > 0) {
			PageBean pageBean = new PageBean(pageIndex, pageSize, pageCount,
					mlist);
			request.setAttribute("pageBean", pageBean);
		}
		logger.info("查询用户列表结束");
		return "/system/userList";
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveUser")
	private String saveUser(HttpServletRequest request) {
		logger.info("新增用户开始...");
		String userPass = request.getParameter("userPass");
		String userName = request.getParameter("userName");
		String userType = request.getParameter("userType");
		String createTime = DateUtil.dateToString(new Date(), dateFormatStr);
		SysUserInfoBo sysUserInfoBo = new SysUserInfoBo();
		sysUserInfoBo.setUserName(userName);
		sysUserInfoBo.setUserPass(PasswordHash.createHash(userPass));
		sysUserInfoBo.setCreateTime(createTime);
		sysUserInfoBo.setAvailable(0);
		boolean flag = sysUserInfoService.insert(sysUserInfoBo);
		if (flag) {
			int pageIndex = 1;
			List<Object> mlist = sysUserInfoService
					.findAll(pageIndex, pageSize);
			int pageCount = sysUserInfoService.findByCount();
			if (mlist != null && mlist.size() > 0) {
				PageBean pageBean = new PageBean(pageIndex, pageSize,
						pageCount, mlist);
				request.setAttribute("pageBean", pageBean);
			}
			logger.info("新增用户结束...");

			return "/system/userList";
		}
		logger.info("新增用户结束...");
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 */
	@RequestMapping("deleteUser")
	private void deleteUser(HttpServletRequest request) {
		logger.info("删除用户开始...");
		String id = request.getParameter("id");
		sysUserInfoService.delete(id);
		logger.info("删除用户结束...");
	}

	@RequestMapping("updateUser")
	private String updateUser(HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
		logger.info("修改用户开始...");
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userNewPassAgain = request.getParameter("userNewPassAgain");
		String userOldPass = request.getParameter("userOldPass");
		String userNewPass = request.getParameter("userNewPass");

		if(userNewPassAgain==null||userNewPass==null){
			return null;
		}else if (!userNewPass.equals(userNewPassAgain)){
			return null;
		}
		String createTime = DateUtil.dateToString(new Date(), dateFormatStr);
        SysUserVo sysUserVo = sysUserInfoService.getSysUserById(id);
		if(sysUserVo!=null){
//			if (sysUserVo.getUserPass().equals(PasswordHash.createHash(userNewPass))){
			if(PasswordHash.validatePassword(userOldPass,sysUserVo.getUserPass())){
				SysUserInfoBo sysUserInfoBo = new SysUserInfoBo();
				sysUserInfoBo.setId(id);
				sysUserInfoBo.setCreateTime(createTime);
				sysUserInfoBo.setUserPass(PasswordHash.createHash(userNewPass));
				sysUserInfoBo.setUserName(userName);
				boolean isSuccess = sysUserInfoService.update(sysUserInfoBo);
				if (isSuccess){
					logger.info("修改用户结束...");
					return "/system/userList";
				}
			}else {
				logger.info("原始密码不正确\n"+sysUserVo.getUserPass()+"\n"+ PasswordHash.createHash(userNewPass));
			}
		}
		logger.info("修改用户失败...");
		return null;
	}

	/**
	 * 获取单个用户
	 */
	@RequestMapping("/getUser")
	public String getArticle(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		SysUserInfoBo sysUserInfoBo = (SysUserInfoBo) sysUserInfoService
				.findById(id);
		request.setAttribute("SysUserInfoBo", sysUserInfoBo);
		return "system/updateUser";

	}

}
