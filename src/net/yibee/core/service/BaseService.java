package net.yibee.core.service;

import java.util.List;

public interface BaseService {
	/**
	 * 保存数据
	 * 
	 * @param obj
	 */
	public boolean insert(Object obj);

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 */
	public boolean delete(String id);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 */
	public boolean update(Object obj);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<Object> findAll(int pageIndex, int pageSize);
	/**
	 * 根据条件查询所有数据
	 * 
	 * @return
	 */
	public List<Object> findAllByConditions(int pageIndex, int pageSize, String[] objs);

	/**
	 * 查询特定数据
	 * 
	 * @param id
	 * @return
	 */
	public Object findById(String id);
	/**
	 * 查询总记录数
	 * @return
	 */
	public int findByCount();
}
