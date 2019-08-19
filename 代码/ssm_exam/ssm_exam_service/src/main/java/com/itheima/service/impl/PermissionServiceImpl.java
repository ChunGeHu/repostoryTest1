package com.itheima.service.impl;

import com.itheima.dao.PermissionMapper;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper ;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.add(permission);
    }

    @Override
    public List<Permission> findAdditablePermission(String roleId) {
        return permissionMapper.findAdditablePermission(roleId);
    }

    @Override
    public void addPermissions2Role(String roleId, String[] ids) {
        //删除角色之前所关联的权限信息
        permissionMapper.deletePermissionFromRole(roleId);
        //遍历权限ID集合给角色添加权限
        for (String permissionId : ids) {
            permissionMapper.addPermisssion2role(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findMenus(String username) {
        List<Permission> menus = permissionMapper.findMenus(username);
        for (Permission menu : menus) {
            List<Permission> children = permissionMapper.findChildrenByUsernameAndPid(username,menu.getId());
            menu.setChildren(children);
        }

        return menus;
    }
}
