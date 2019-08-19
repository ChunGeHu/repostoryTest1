package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/18/018
 */
public interface SysLogMapper {

    /**
     * 保存日志信息到数据库
     * @param sysLog
     */
    @Insert("insert into SYSLOG values(sys_guid(),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void add(SysLog sysLog);

    /**
     * 查询所有日志信息
     * @return
     */
    @Select("select * from SYSLOG")
    List<SysLog> findAll();

}
