package net.yibee.core.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yibee.core.dao.UserDao;
import net.yibee.core.entity.vo.SysUserVo;
import net.yibee.core.exception.ServiceException;
import net.yibee.core.service.UserService;
import net.yibee.core.service.ValidCodeService;
import net.yibee.core.util.PasswordHash;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private ValidCodeService validCodeService;

	@Override
	public SysUserVo sysAdminLogin(SysUserVo user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!validCodeService.checkValidCode(user.getRandomString(),
				user.getType(), user.getAuthCode())) {
			throw new ServiceException("验证码错误！");
		}
		SysUserVo existUser = null;
		existUser = userDao.getSysUserByUserName(user.getUserName());
		if (existUser == null) {
			throw new ServiceException("用户不存在！");
		}
//		if (!existUser.getUserPass().equalsIgnoreCase(
//				PasswordHash.createHash(user.getUserPass()))) {
//			throw new ServiceException("用户密码错误！");
//		}

		if(!PasswordHash.validatePassword(user.getUserPass(),existUser.getUserPass())){
			throw new ServiceException("用户密码错误！");
		}
		request.getSession().setAttribute("sysUser", existUser);
		return existUser;
	}
}
