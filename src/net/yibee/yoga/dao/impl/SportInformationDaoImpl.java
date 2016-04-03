package net.yibee.yoga.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.yibee.core.util.DateUtil;
import net.yibee.yoga.dao.SportInformationDao;
import net.yibee.yoga.entity.SportInformation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/*
 * 基本功能：资讯信息列表业务类
 * 创建：王杰
 */
@Repository
public class SportInformationDaoImpl implements SportInformationDao {
	Logger logger = Logger.getLogger(this.getClass());

	public static final String FINDCOUNT = "select count(*) from sport_information;";
	public static final String DELETE = "delete from sport_information where id=?;";

	public static final String UPDATE = "update sport_information set name=?,updateTime=?,thumb=?,typeid=?,typename=?,times=?,detail=? where id=?;";

	public static final String FINDALL = "select * from sport_information where isScroll=? order by updateTime desc;";

	public static final String FINDBYID = "select * from sport_information where id=?;";
	public static final String FINDPAGE = "SELECT * FROM sport_information INNER JOIN (SELECT id FROM sport_information ORDER BY id desc LIMIT ?, ?) sport USING (id); ";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static final String INSERT = "insert into sport_information(name,updateTime,thumb,typeid,typename,times,detail) values(?,?,?,?,?,?,?);";

	@Override
	public boolean insert(Object obj) {
		logger.info("insert SportInformationBO is start...");
		SportInformation sportInformationBo = (SportInformation) obj;
		try {
			jdbcTemplate.update(INSERT, sportInformationBo.getName(),
					sportInformationBo.getUpdateTime(),
					sportInformationBo.getThumb(),
					sportInformationBo.getTypeId(),
					sportInformationBo.getTypeName(),
					sportInformationBo.getTimes(),
					sportInformationBo.getDetail());
			logger.info("insert SportInformationBO is end...");
		} catch (Exception e) {
			logger.info("insert Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		logger.info("delete SportInformationBO is start...");

		int result = jdbcTemplate.update(DELETE, new String[]{id});
        if (result > 0) {  
        	logger.info("delete SportInformationBO is end...");
            return  true;  
        }  

        return false;
	}

	@Override
	public boolean update(Object obj) {
		logger.info("update SportInformationBO is start...");
		SportInformation sportInformationBo = (SportInformation) obj;
		try {
			jdbcTemplate.update(UPDATE, sportInformationBo.getName(),
					sportInformationBo.getUpdateTime(),
					sportInformationBo.getThumb(),
					sportInformationBo.getTypeId(),
					sportInformationBo.getTypeName(),
					sportInformationBo.getTimes(),
					sportInformationBo.getDetail(),
					sportInformationBo.getId());
			logger.info("update SportInformationBO is end...");
		} catch (Exception e) {
			logger.info("update Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public List<Object> findAllByConditions(int pageIndex, int pageSize,String[] objs) {
		logger.info("select all SportInformationBO is start...");
		List<Object> lists = new ArrayList<Object>();
        if(objs!=null&&objs.length>=1){
		String sql = "SELECT * FROM sport_information  where isScroll='"+objs[0]+"' ORDER BY updateTime desc;";
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		logger.info(sql);
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SportInformation sportInformationBo = new SportInformation();
			sportInformationBo.setId((Integer) row.get("id") + "");
			sportInformationBo.setName((String) row.get("name"));
			String updateTime = DateUtil.dateToString(
					(Date) row.get("updateTime"), "yyyy-MM-dd");
			sportInformationBo.setUpdateTime(updateTime);
			sportInformationBo.setThumb((String) row.get("thumb"));
			sportInformationBo.setTypeId((Integer) row.get("typeid") + "");
			sportInformationBo.setTypeName((String) row.get("typeName"));
			sportInformationBo.setTimes((Integer) row.get("times") + "");
			sportInformationBo.setDetail((String) row.get("detail"));
			lists.add(sportInformationBo);
		}
		logger.info("select all SportInformationBO is end...");
        }
		return lists;
	}

	@Override
	public Object findById(String id) {
		logger.info("select  SportInformationBO by id is start...");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		SportInformation sportInformationBo = (SportInformation) jdbcTemplate.queryForObject(
				FINDBYID, new Object[] { id }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						SportInformation sportInformationBo = new SportInformation();
						sportInformationBo.setId((Integer) rs.getInt("id") + "");
						sportInformationBo.setName((String) rs
								.getString("name"));
						String updateTime = DateUtil.dateToString(
								(Date) rs.getDate("updateTime"), "yyyy-MM-dd");
						sportInformationBo.setUpdateTime(updateTime);
						sportInformationBo.setThumb((String) rs
								.getString("thumb"));
						sportInformationBo.setTypeId((Integer) rs
								.getInt("typeid") + "");
						sportInformationBo.setTypeName((String) rs
								.getString("typeName"));
						sportInformationBo.setTimes((Integer) rs
								.getInt("times") + "");
						sportInformationBo.setDetail((String) rs
								.getString("detail"));
						return sportInformationBo;
					}
				});
		logger.info("select  SportInformationBO by id is end...");

		return sportInformationBo;
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
	public List<Object> findAll(int pageIndex, int pageSize) {

		String sql = "SELECT * FROM sport_information INNER JOIN (SELECT id FROM sport_information   ORDER BY id desc LIMIT "
				+ (pageIndex - 1)
				* pageSize
				+ ", "
				+ pageSize
				+ ") sport USING (id) ORDER BY updateTime desc;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<Object> lists = new ArrayList<Object>();

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SportInformation sportInformationBo = new SportInformation();
			sportInformationBo.setId((Integer) row.get("id") + "");
			sportInformationBo.setName((String) row.get("name"));
			String updateTime = DateUtil.dateToString(
					(Date) row.get("updateTime"), "yyyy-MM-dd");
			sportInformationBo.setUpdateTime(updateTime);
			sportInformationBo.setThumb((String) row.get("thumb"));
			sportInformationBo.setTypeId((Integer) row.get("typeid") + "");
			sportInformationBo.setTypeName((String) row.get("typeName"));
			sportInformationBo.setTimes((Integer) row.get("times") + "");
			sportInformationBo.setDetail((String) row.get("detail"));
			lists.add(sportInformationBo);
		}
		logger.info("select all SportInformationBO is end...");
        return lists;
	}

}
