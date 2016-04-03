package net.yibee.core.dao.impl;

import net.yibee.core.dao.GeneralDao;
import net.yibee.core.dao.ValidCodeDao;
import net.yibee.core.entity.ValidCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ValidCodeDaoImpl implements ValidCodeDao {
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public boolean remove(ValidCode code) {
		return false;
	}

	@Override
	public ValidCode getValidCode(String codeKey) {
		try {
			return generalDao.getEntity(
					ValidCode.class, 
					" select * from validcode where codeKey = ? ", new Object[]{codeKey});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveValidCode(ValidCode code) {
		try {
			generalDao.saveEntity(
					" insert into validcode (codeKey, code, createTime) values(?, ?, ?)", 
					new Object[]{code.getCodeKey(), code.getCode(), code.getCreateTime()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
