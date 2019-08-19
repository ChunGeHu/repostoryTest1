package com.itheima.service;

import com.itheima.domain.UserInfo;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
public interface UserService {
    /**
     * 分页查询用户信息
     * @param pageNo
     * @param rows
     * @return
     */
    List<UserInfo> findByPage(Integer pageNo, Integer rows);

    /**
     * 添加用户信息
     * @param userInfo
     */
    void add(UserInfo userInfo);

    /**
     * 根据用户ID查询用户详情
     * @param id
     * @return
     */
    UserInfo findDetail(String id);
}
