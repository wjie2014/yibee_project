package net.yibee.core.dao;


import net.yibee.core.entity.vo.SysUserVo;

/*
 * 基本功能：系统用户管理接口
 * 创建： 王杰
 *
 */
public interface SysUserDao extends BaseDao{
    public SysUserVo getSysUserById(String id);
}
