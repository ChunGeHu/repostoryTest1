package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogMapper;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/18/018
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper ;

    @Override
    public void add(SysLog sysLog) {
        //调用数据访问层插入数据到数据库
        sysLogMapper.add(sysLog);
    }

    @Override
    public List<SysLog> findByPage(Integer pageNo, Integer rows) {
        //开启分页查询
        PageHelper.startPage(pageNo,rows);
        //查询数据
        return sysLogMapper.findAll();
    }
}
