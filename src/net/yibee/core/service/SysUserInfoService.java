package net.yibee.core.service;


import net.yibee.core.entity.vo.SysUserVo;

/*
 * 基本功能：系统用户服务类
 * 创建：王杰
 */
public interface SysUserInfoService extends BaseService{
    public SysUserVo getSysUserById(String id);

}
