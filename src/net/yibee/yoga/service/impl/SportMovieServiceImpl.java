package net.yibee.yoga.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.yibee.yoga.dao.SportInformationDao;
import net.yibee.yoga.service.SportInformationBoService;

import org.springframework.stereotype.Service;


@Service
public class SportMovieServiceImpl implements SportInformationBoService {
	private SportInformationDao sportInformationDao;

	@Resource
	public void setBaseDao(SportInformationDao sportInformationDao) {
		this.sportInformationDao = sportInformationDao;
	}

	@Override
	public boolean insert(Object obj) {
		sportInformationDao.insert(obj);
		return false;
	}

	@Override
	public boolean delete(String id) {
		return sportInformationDao.delete(id);
	}

	@Override
	public boolean update(Object obj) {
		return sportInformationDao.update(obj);
	}

	@Override
	public List<Object> findAll(int pageIndex, int pageSize) {
		return sportInformationDao.findAll(pageIndex, pageSize);
	}

	@Override
	public Object findById(String id) {
		return sportInformationDao.findById(id);
	}
	/**
	 * 查询总记录数
	 * @return
	 */
	public int findByCount(){
		return sportInformationDao.findByCount();
	}

	@Override
	public List<Object> findAllByConditions(int pageIndex, int pageSize,
			String[] objs) {
		return sportInformationDao.findAllByConditions(pageIndex, pageSize, objs);
	}

	
}
