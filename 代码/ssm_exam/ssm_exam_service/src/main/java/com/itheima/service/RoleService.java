package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
public interface RoleService {

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色信息
     * @param role
     */
    void add(Role role);

    /**
     * 查询所有用户可以添加的角色信息
     * @param userId
     * @return
     */
    List<Role> findAdditableRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     */
    void addRoles2User(String userId, String[] ids);
}
