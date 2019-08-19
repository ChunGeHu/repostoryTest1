package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/18/018
 */
@Controller
@RequestMapping(path = "/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService ;

    /**
     * 分页查询系统日志信息
     * @return
     */
    @RequestMapping(path = "/findByPage")
    public String findByPage(Model model ,  @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "rows",defaultValue = "10") Integer rows){
        //调用业务层查询数据
        List<SysLog> sysLogs = sysLogService.findByPage(pageNo,rows);
        //构建分页对象
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        //将数据保存到Model中
        model.addAttribute("pageInfo",pageInfo);
        //跳转视图
        return "syslog-list" ;
    }
}
