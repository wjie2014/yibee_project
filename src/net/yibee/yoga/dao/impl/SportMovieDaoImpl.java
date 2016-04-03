package net.yibee.yoga.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.yibee.core.util.DateUtil;
import net.yibee.yoga.dao.SportMovieDao;
import net.yibee.yoga.entity.SportInformation;
import net.yibee.yoga.entity.SportMovie;

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
public class SportMovieDaoImpl implements SportMovieDao {
	Logger logger = Logger.getLogger(this.getClass());

	public static final String FINDCOUNT = "select count(*) from sport_movie;";
	public static final String DELETE = "delete from sport_movie where id=?;";

	public static final String UPDATE = "update sport_movie set name=?,updateTime=?,thumb=?,typeid=?,typename=?,times=?,movie_url=? where id=?;";

	public static final String FINDALL = "select * from sport_movie order by updateTime desc;";

	public static final String FINDBYID = "select * from sport_movie where id=?;";
	public static final String FINDPAGE = "SELECT * FROM sport_movie INNER JOIN (SELECT id FROM sport_movie ORDER BY id desc LIMIT ?, ?) sport USING (id); ";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static final String INSERT = "insert into sport_movie(name,updateTime,thumb,typeid,typename,times,movie_url) values(?,?,?,?,?,?,?);";

	@Override
	public boolean insert(Object obj) {
		logger.info("insert SportMovie is start...");
		SportMovie sportMovie
		= (SportMovie) obj;
		try {
			jdbcTemplate.update(INSERT, sportMovie.getName(),
					sportMovie.getUpdateTime(),
					sportMovie.getThumb(),
					sportMovie.getTypeId(),
					sportMovie.getTypeName(),
					sportMovie.getTimes(),
					sportMovie.getMovie_url());
			logger.info("insert SportMovie is end...");
		} catch (Exception e) {
			logger.info("insert Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		logger.info("delete SportMovie is start...");

		int result = jdbcTemplate.update(DELETE, new String[]{id});
        if (result > 0) {  
        	logger.info("delete SportMovie is end...");
            return  true;  
        }  

        return false;
	}

	@Override
	public boolean update(Object obj) {
		logger.info("update SportMovie is start...");
		SportMovie sportMovie = (SportMovie) obj;
		try {
			jdbcTemplate.update(UPDATE, sportMovie.getName(),
					sportMovie.getUpdateTime(),
					sportMovie.getThumb(),
					sportMovie.getTypeId(),
					sportMovie.getTypeName(),
					sportMovie.getTimes(),
					sportMovie.getMovie_url(),
					sportMovie.getId());
			logger.info("update SportMovie is end...");
		} catch (Exception e) {
			logger.info("update Exception :" + e);
			return false;
		}
		return true;
	}

	@Override
	public List<Object> findAll(int pageIndex, int pageSize) {
		logger.info("select all SportMovie is start...");

		List<Object> lists = new ArrayList<Object>();
		String sql = "SELECT * FROM sport_movie INNER JOIN (SELECT id FROM sport_movie ORDER BY id desc LIMIT "
				+ (pageIndex - 1)
				* pageSize
				+ ", "
				+ pageSize
				+ ") sport USING (id) ORDER BY updateTime desc;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SportMovie sportMovie = new SportMovie();
			sportMovie.setId((Integer) row.get("id") + "");
			sportMovie.setName((String) row.get("name"));
			String updateTime = DateUtil.dateToString(
					(Date) row.get("updateTime"), "yyyy-MM-dd");
			sportMovie.setUpdateTime(updateTime);
			sportMovie.setThumb((String) row.get("thumb"));
			sportMovie.setTypeId((Integer) row.get("typeid") + "");
			sportMovie.setTypeName((String) row.get("typeName"));
			sportMovie.setTimes((Integer) row.get("times") + "");
			sportMovie.setMovie_url((String) row.get("movie_url"));
			lists.add(sportMovie);
		}
		logger.info("select all SportMovie is end...");

		return lists;
	}

	@Override
	public Object findById(String id) {
		logger.info("select  SportMovie by id is start...");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		SportInformation SportMovie = (SportInformation) jdbcTemplate.queryForObject(
				FINDBYID, new Object[] { id }, new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						SportMovie sportMovie = new SportMovie();
						sportMovie.setId((Integer) rs.getInt("id") + "");
						sportMovie.setName((String) rs
								.getString("name"));
						String updateTime = DateUtil.dateToString(
								(Date) rs.getDate("updateTime"), "yyyy-MM-dd");
						sportMovie.setUpdateTime(updateTime);
						sportMovie.setThumb((String) rs
								.getString("thumb"));
						sportMovie.setTypeId((Integer) rs
								.getInt("typeid") + "");
						sportMovie.setTypeName((String) rs
								.getString("typeName"));
						sportMovie.setTimes((Integer) rs
								.getInt("times") + "");
						sportMovie.setMovie_url((String) rs
								.getString("movie_url"));
						return sportMovie;
					}
				});
		logger.info("select  SportMovie by id is end...");

		return null;
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
		return null;
	}

}
