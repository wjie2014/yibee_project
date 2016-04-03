package net.yibee.core.dao;

import net.yibee.core.entity.bo.SysUserBo;
import net.yibee.core.entity.vo.SysUserVo;


public interface UserDao {
    /**
     * 根据邮箱获取系统管理员信息
     * 2014年6月24日
     */
    public SysUserBo getSysUserByEmail(String userEmail) throws Exception;

    public SysUserBo getSysUserById(String id) throws Exception;

    public SysUserVo getSysUserByUserName(String userName) throws Exception;

    public boolean updateSysUser(SysUserBo user, String... fileds) throws Exception;

}
