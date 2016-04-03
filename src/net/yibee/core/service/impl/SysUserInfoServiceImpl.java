package net.yibee.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.yibee.core.dao.SysUserDao;
import net.yibee.core.entity.vo.SysUserVo;
import net.yibee.core.service.SysUserInfoService;

import org.springframework.stereotype.Service;

/*
 * 基本功能：文章服务实现类
 * 创建：王杰
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService{
	private SysUserDao sysUserDao;

	@Resource
	public void setBaseDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	@Override
	public boolean insert(Object obj) {
		return sysUserDao.insert(obj);
	}

	@Override
	public boolean delete(String id) {
		return sysUserDao.delete(id);
	}

	@Override
	public boolean update(Object obj) {
		return sysUserDao.update(obj);
	}

	@Override
	public List<Object> findAll(int pageIndex, int pageSize) {
		return sysUserDao.findAll(pageIndex, pageSize);
	}

	@Override
	public Object findById(String id) {
		return sysUserDao.findById(id);
	}

	@Override
	public int findByCount() {
		return sysUserDao.findByCount();
	}
	@Override
	public List<Object> findAllByConditions(int pageIndex, int pageSize,
			String[] objs) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SysUserVo getSysUserById(String id) {
		return sysUserDao.getSysUserById(id);
	}
}
