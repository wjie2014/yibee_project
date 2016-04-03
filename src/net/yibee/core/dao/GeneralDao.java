package net.yibee.core.dao;

import java.util.List;

/**
 * 操作数据库的dao
 * 2014年9月20日
 */
public interface GeneralDao {
	
	/**
	 * 新增数据库数据
	 * 1.sql语句格式：insert into user(username,password,first_name,last_name,birthday,age) 
	 * 					values(:userName,:passWord,:firstName,:lastName,:birthday,:age)
	 * @param sql 
	 * @param obj 实体对象
	 * @return
	 */
	public boolean saveEntity(String sql, Object... obj) throws Exception;
	
	/**
	 * 通过sql查询对象
	 * @param sql 数据库select语句格式：select * from user where userName = ? 
	 * @param values 对象数组，sql语句有几个？，则数组有几个对象，一一对应: new Object[]{"wang"}
	 */
	public <T> T getEntity(Class<T> beanClass, String sql, Object[] values) throws Exception;
	/**
	 * 通过sql查询对象列表
	 * @param <T>
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public <T> List<T>  getEntityList(Class<T> beanClass, String sql, Object[] values)  throws Exception;
	/**
	 * 通过主键值查出对象
	 * @param beanClass 
	 * @param primaryKeyValue 主键的值，根据该值查出对象数据
	 */
	public <T> T getEntity(Class<T> beanClass, Object primaryKeyValue) throws Exception;
	
	/**
	 * 通过sql更新数据库
	 * @param sql 数据库update语句格式：update stu set s_name=?, s_sex=?, s_brith=? where id=?
	 * @param values 对象数组，update的sql语句有几个？，则数组有几个对象，一一对应
	 */
	public boolean updateEntity(String sql, Object[] values) throws Exception;
	
	/**
	 * 更新一个对象的指定字段
	 * @param entity 要更新的对象，主键必须有值
	 * @param updateFields String的数组，更新对象的哪些字段
	 */
	public boolean updateEntity(Object entity, String... updateFields) throws Exception;
    /**
     * 根据指定字段删除数据
     * @param entity
     * @param updateFields
     * @return
     * @throws Exception
     */
	public boolean delEntity(String sql, String... delFields) throws Exception;
}
