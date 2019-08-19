package com.itheima.service.impl;

import com.itheima.dao.RoleMapper;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper ;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void add(Role role) {
        roleMapper.add(role);
    }

    @Override
    public List<Role> findAdditableRoles(String userId) {
        return roleMapper.findAdditableRoles(userId);
    }

    @Override
    public void addRoles2User(String userId, String[] ids) {
        //删除用户关联的所有的角色
        roleMapper.deleteRolesFromUser(userId);

        //给用户添加角色
        for (String roleId : ids) {
            roleMapper.addRole2User(userId,roleId);
        }
    }
}
