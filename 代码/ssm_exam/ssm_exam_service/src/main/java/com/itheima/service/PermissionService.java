package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
public interface PermissionService {

    /**
     * 查询所有权限信息
     * @return
     */
    List<Permission> findAll();

    /**
     * 添加权限信息
     * @param permission
     */
    void add(Permission permission);

    /**
     * 查询所有权限信息包含角色是否选择该权限
     * @param roleId
     * @return
     */
    List<Permission> findAdditablePermission(String roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     */
    void addPermissions2Role(String roleId, String[] ids);

    /**
     * 查询用户菜单
     * @param username
     * @return
     */
    List<Permission> findMenus(String username);
}
