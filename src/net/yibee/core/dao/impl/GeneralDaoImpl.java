package net.yibee.core.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.yibee.core.constant.TableConstant;
import net.yibee.core.dao.GeneralDao;
import net.yibee.core.exception.DaoException;
import net.yibee.core.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class GeneralDaoImpl implements GeneralDao {
	
	private static Logger logger = LoggerFactory.getLogger(GeneralDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增数据库数据
	 * 1.sql语句格式：insert into user(username,password,first_name,last_name,birthday,age) 
	 * 					values(:userName,:passWord,:firstName,:lastName,:birthday,:age)
	 * @param sql 
	 * @param obj 实体对象
	 * @return
	 */
	public boolean saveEntity(String sql, Object... obj) throws Exception {
		return addOrUpdate(sql, obj);
	}

	/**
	 * 通过sql查询对象
	 * @param sql 数据库select语句格式：select * from user where userName = ? 
	 * @param values 对象数组，sql语句有几个？，则数组有几个对象，一一对应: new Object[]{"wang"}
	 */
	@Override
	public <T> T getEntity(Class<T> beanClass, String sql, Object[] values) throws Exception{
		List<T> beanList = null;
		try{
			beanList = jdbcTemplate.query(sql.toString(), BeanPropertyRowMapper.newInstance(beanClass), values);
		}catch(EmptyResultDataAccessException e){
			return null;
		}catch(DataAccessException e){
			logger.error("GeneralDao getEntity with sql has failed!"+ sql + e);
		}
		return beanList != null && beanList.size() > 0 ? beanList.get(0) : null;
	}
	
	/**
	 * 通过主键值查出对象
	 * @param beanClass 
	 * @param primaryKeyValue 主键的值，根据该值查出对象数据
	 */
	@Override
	public <T> T getEntity(Class<T> beanClass, Object primaryKeyValue) throws Exception{
		T bean = null;
		String tableName = TableConstant.TABLE_BEAN.get(beanClass.getName());
		String primaryKey = TableConstant.TABLE_PRIMARY_KEY.get(tableName);
		if(StringUtil.isEmpty(tableName)){
			logger.info("对象实体bean未与数据库表名称映射，请维护TableConstant.java");
			return null;
		}
		if(StringUtil.isEmpty(primaryKey)){
			logger.info("数据库表名称未与表主键字段映射，请维护TableConstant.java");
			return null;
		}
		StringBuilder sql = new StringBuilder(" select * from ");
		sql.append(tableName).append(" where ").append(primaryKey).append(" = ? ");
		try{
			bean = jdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(beanClass), primaryKeyValue);
		}catch(EmptyResultDataAccessException e){
			logger.info("There is no result,Incorrect result size: expected 1, actual 0");
			return null;
		}catch (DataAccessException e){
			logger.error("GeneralDao getEntity by primaryKeyValue has failed!" + e);
		}
		return bean;
	}
	
	/**
	 * 通过sql更新数据库
	 * @param sql 数据库update语句格式：update stu set s_name=?, s_sex=?, s_brith=? where id=?
	 * @param values 对象数组，update的sql语句有几个？，则数组有几个对象，一一对应
	 */
	@Override
	public boolean updateEntity(String sql, Object[] values) throws Exception{
		boolean updateFlag = true;
		try{
			int returnFlag = jdbcTemplate.update(sql, values);
			logger.info("returnFlag打印输出：" + returnFlag);
		}catch(DataAccessException e){
			logger.error("GeneralDao updateEntity has failed!" + e);
			updateFlag = false;
		}
		return updateFlag;
	}
	
	/**
	 * 更新一个对象的指定字段
	 * @param entity 要更新的对象，主键必须有值
	 * @param updateFields String的数组，更新对象的哪些字段
	 */
	@Override
	public boolean updateEntity(Object entity, String... updateFields) throws Exception {
		Class<?> clazz = entity.getClass();   
		String tableName = TableConstant.TABLE_BEAN.get(clazz.getName());
		String primaryKey = TableConstant.TABLE_PRIMARY_KEY.get(tableName);
		if(StringUtil.isEmpty(tableName)){
			logger.error("对象实体bean未与数据库表名称映射，请维护TableConstant.java");
			throw new Exception("对象实体bean未与数据库表名称映射，请维护TableConstant.java");
		}
		if(StringUtil.isEmpty(primaryKey)){
			logger.error("数据库表名称未与表主键字段映射，请维护TableConstant.java");
			throw new Exception("数据库表名称未与表主键字段映射，请维护TableConstant.java");
		}
		return updateEntityString(clazz, tableName, primaryKey, entity, updateFields);
    }
	
	private boolean updateEntityString (Class<?> clazz, String tableName, String primaryKey, Object entity, String ... updateFields) throws Exception{
		boolean updateFlag = true;
		StringBuilder sqlBuilder = new StringBuilder("update ").append(tableName);
		sqlBuilder.append(" set ");
		for(int i=0; i<updateFields.length; i++){
			sqlBuilder.append(updateFields[i] + " = ? ").append(i == updateFields.length - 1 ? "" : ", ");
		}
//		jdbcTemplate.update("update stu set s_name=?,s_sex=?,s_brith=? where s_id=?", "a", "b" ,"c", "d");
		List<Object> objList = new ArrayList<Object>();
		for(String field:updateFields){
			//需要利用反射机制，将对象的值取出放到objList中
		    PropertyDescriptor pd = new PropertyDescriptor(field, clazz);   
	        Method getMethod = pd.getReadMethod();  //获得get方法   
			objList.add(getMethod.invoke(entity));
		}
		sqlBuilder.append(" where ");
		sqlBuilder.append(primaryKey).append(" = ? ");   //获得表的主键字段名称
		PropertyDescriptor pd = new PropertyDescriptor(primaryKey, clazz);   
        Method getMethod = pd.getReadMethod();  //获得get方法   
		objList.add(getMethod.invoke(entity));
		try{
			int returnFlag = jdbcTemplate.update(sqlBuilder.toString(), objList.toArray());
			logger.info("returnFlag updateEntityString :" + returnFlag);
		}catch(DataAccessException e){
			logger.error("GeneralDao updateEntityString has failed!" + e);
			updateFlag = false;
		}
        return updateFlag;
	}
	
	private boolean addOrUpdate(String sql, Object... obj){
		try {
			int returnFlag = jdbcTemplate.update(sql, obj);
			logger.info("returnFlag打印输出：" + returnFlag);
		} catch (Exception e) {
			logger.error("GeneralDao addOrUpdate has failed!" + e);
			throw new DaoException("数据库操作失败！",e);
		}
		return true;
	}

	
	@Override
	public <T> List<T> getEntityList(Class<T> beanClass, String sql, Object[] values)
			throws Exception {
		List<T> beanList = null;
		try{
			beanList = jdbcTemplate.query(sql.toString(), BeanPropertyRowMapper.newInstance(beanClass), values);
		}catch(EmptyResultDataAccessException e){
			return null;
		}catch(DataAccessException e){
			logger.error("GeneralDao getEntityList with sql has failed!"+ sql + e);
		}
		return beanList ;
	}

	@Override
	public boolean delEntity(String sql, String... delFields)
			throws Exception {
		 int i = jdbcTemplate.update(sql.toString(),  
				 delFields);  
	        if (i > 0) {  
	        	return true;  
	        }  
		return false;
	}

}
