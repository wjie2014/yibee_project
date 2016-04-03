package net.yibee.core.dao.impl;

import net.yibee.core.dao.GeneralDao;
import net.yibee.core.dao.UserDao;
import net.yibee.core.entity.bo.SysUserBo;
import net.yibee.core.entity.vo.SysUserVo;
import net.yibee.core.util.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;


	@Override
	public SysUserBo getSysUserByEmail(String userEmail) throws Exception {
		if(StringUtil.isEmpty(userEmail)){
			return null;
		}
		return generalDao.getEntity(SysUserBo.class, " select * from sys_user where email = ? ", new Object[]{userEmail});
	}

	@Override
	public boolean updateSysUser(SysUserBo user, String... fileds)
			throws Exception {
		if(user == null){
			return false;
		}
		try {
			return generalDao.updateEntity(user, fileds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public SysUserVo getSysUserByUserName(String userName) throws Exception {
		if(StringUtil.isEmpty(userName)){
			return null;
		}
		return generalDao.getEntity(SysUserVo.class, " select * from sys_user where userName = ? ", new Object[]{userName});
	}

	@Override
	public SysUserVo getSysUserById(String id) throws Exception {
		if(StringUtil.isEmpty(id)){
			return null;
		}
		return generalDao.getEntity(SysUserVo.class, " select * from sys_user where id = ? ", new Object[]{id});
	}

}
