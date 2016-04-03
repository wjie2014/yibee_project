package net.yibee.yoga.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.yibee.yoga.dao.SportMovieDao;
import net.yibee.yoga.service.SportMovieService;

import org.springframework.stereotype.Service;


@Service
public class SportInformationBoServiceImpl implements SportMovieService {
	private SportMovieDao sportMovieDao;

	@Resource
	public void setBaseDao(SportMovieDao sportMovieDao) {
		this.sportMovieDao = sportMovieDao;
	}

	@Override
	public boolean insert(Object obj) {
		sportMovieDao.insert(obj);
		return false;
	}

	@Override
	public boolean delete(String id) {
		return sportMovieDao.delete(id);
	}

	@Override
	public boolean update(Object obj) {
		return sportMovieDao.update(obj);
	}

	@Override
	public List<Object> findAll(int pageIndex, int pageSize) {
		return sportMovieDao.findAll(pageIndex, pageSize);
	}

	@Override
	public Object findById(String id) {
		return sportMovieDao.findById(id);
	}
	/**
	 * 查询总记录数
	 * @return
	 */
	public int findByCount(){
		return sportMovieDao.findByCount();
	}

	@Override
	public List<Object> findAllByConditions(int pageIndex, int pageSize,
			String[] objs) {
		return null;
	}
}
