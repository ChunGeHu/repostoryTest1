package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/18/018
 */
public interface SysLogService {

    /**
     * 添加日志信息
     * @param sysLog
     */
    void add(SysLog sysLog);

    /**
     * 分页查询日志信息
     * @param pageNo
     * @param rows
     * @return
     */
    List<SysLog> findByPage(Integer pageNo, Integer rows);
}
