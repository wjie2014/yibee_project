package net.yibee.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.yibee.core.dao.GeneralDao;
import net.yibee.core.dao.SysUserDao;
import net.yibee.core.entity.bo.SysUserInfoBo;
import net.yibee.core.entity.vo.SysUserVo;
import net.yibee.core.util.DateUtil;

import net.yibee.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/*
 * 基本功能：系统用户管理
 * 创建：王杰
 */
@Repository
public class SysUserDaoImpl implements SysUserDao {
	private static final String FINDCOUNT = "select count(*) from sys_user where available=0;";
	private static final String INSERT = "insert into sys_user(userName,userPass,createTime,available) values(?,?,?,?);";
	private static final String DELETE  = "update  sys_user set available='1' where id=?;";
	private static final String FINDBYID = "select * from sys_user where id=? and available=0;";
	private static final String UPDATE = "update sys_user set userName=? , userPass=? , createTime=? where id=?";
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private GeneralDao generalDao;
	@Override
	public boolean insert(Object obj) {
		logger.info("insert SysUserInfoBo is start...");
		SysUserInfoBo sysUserInfoBo = (SysUserInfoBo) obj;
		try {
			jdbcTemplate.update(INSERT, sysUserInfoBo.getUserName(),
					sysUserInfoBo.getUserPass(),
					sysUserInfoBo.getCreateTime(),sysUserInfoBo.getAvailable());
			logger.info("insert SysUserInfoBo is end...");
		} catch (Exception e) {
			logger.info("insert SysUserInfoBo Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		logger.info("delete SysUserInfoBo is start...");

		int result = jdbcTemplate.update(DELETE, new String[]{id});
        if (result > 0) {  
        	logger.info("delete SysUserInfoBo is end...");
            return  true;  
        }  

        return false;
	}

	@Override
	public boolean update(Object obj) {
		logger.info("update SysUserInfoBo is start...");
		SysUserInfoBo sysUserInfoBo = (SysUserInfoBo) obj;
		try {
			jdbcTemplate.update(UPDATE, sysUserInfoBo.getUserName(),
					sysUserInfoBo.getUserPass(),sysUserInfoBo.getCreateTime(),sysUserInfoBo.getId());
			logger.info("update SysUserInfoBo is end...");
		} catch (Exception e) {
			logger.info("update SysUserInfoBo Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public List<Object> findAll(int pageIndex, int pageSize) {
		logger.info("select all SysUserInfoBo is start...");

		List<Object> lists = new ArrayList<Object>();
		String sql = "SELECT * FROM sys_user INNER JOIN (SELECT id FROM sys_user where available=0 ORDER BY id desc LIMIT "
				+ (pageIndex - 1)
				* pageSize
				+ ", "
				+ pageSize
				+ ") sport USING (id)  where available=0 ORDER BY createTime desc;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SysUserInfoBo sysUserInfoBo = new SysUserInfoBo();
			sysUserInfoBo.setId((Integer) row.get("id") + "");
			sysUserInfoBo.setUserName((String) row.get("userName"));
			String createTime = DateUtil.dateToString(
					(Date) row.get("createTime"), "yyyy-MM-dd");
			sysUserInfoBo.setCreateTime(createTime);
			lists.add(sysUserInfoBo);
		}
		logger.info("select all SysUserInfoBo is end...");

		return lists;
	}

	@Override
	public Object findById(String id) {
		logger.info("select  SysUserInfoBo by id is start...");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		SysUserInfoBo sysUserInfoBo = (SysUserInfoBo) jdbcTemplate.queryForObject(
				FINDBYID, new Object[] { id }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						SysUserInfoBo sysUserInfoBo = new SysUserInfoBo();
						sysUserInfoBo.setId((Integer) rs.getInt("id") + "");
						sysUserInfoBo.setUserName((String) rs
								.getString("userName"));
						String createTime = DateUtil.dateToString(
								(Date) rs.getDate("createTime"), "yyyy-MM-dd");
						sysUserInfoBo.setCreateTime(createTime);
						return sysUserInfoBo;
					}
				});
		logger.info("select  SysUserInfoBo by id is end...");

		return sysUserInfoBo;
	}

	@Override
	public int findByCount() {
		return jdbcTemplate.queryForInt(FINDCOUNT);
	}

	@Override
	public Object findByPage() {
		return null;
	}

	@Override
	public List<Object> findAllByConditions(int pageIndex, int pageSize,
			String[] objs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SysUserVo getSysUserById(String id)  {
		if(StringUtil.isEmpty(id)){
			return null;
		}
		try {
			return generalDao.getEntity(SysUserVo.class, " select * from sys_user where id = ? ", new Object[]{id});
		}catch (Exception e){
			return null;
		}
	}
}
