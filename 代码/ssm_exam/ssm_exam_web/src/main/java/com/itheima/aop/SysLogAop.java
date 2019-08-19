package com.itheima.aop;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author 王磊
 * @Date 2019/8/18/018
 */
@Component
@Scope("prototype")
@Aspect
public class SysLogAop {
    @Autowired
    private HttpServletRequest request ;
    @Autowired
    private SysLogService sysLogService ;
    private Date startTime = new Date() ;

    /**
     * 定义切入点表达式
     * 方法名称就是切入点名称
     */
    //@Pointcut("execution(* com.itheima.controller.*.*(..))")
    //public void logPiontcut(){}

    /**
     * 前置通知
     */
    //@Before("logPiontcut()")
    public void beforeLog(){
        //记录用户开始执行时间
        startTime = new Date();
    }

    /**
     * 后置通知
     * JoinPoint  : 连接点 , 其中有目标类以及目标方法的信息
     */
    //@AfterReturning(pointcut = "logPiontcut()")
    public void afterReturningLog(JoinPoint joinPoint){
        //获取执行结束的时间
        Date endTime = new Date() ;

        //获取当前的访问用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //获取用户的远程地址
        String addr = request.getRemoteAddr();
        //获取请求资源URL
        String uri = request.getRequestURI();
        //获取方法的执行时间
        Long millis = endTime.getTime()-startTime.getTime();
        //获取访问的方法名称
        //Object[] args = joinPoint.getArgs(); // 获取目标方法的参数
        //Object target = joinPoint.getTarget(); // 获取目标方法
        //Signature signature = joinPoint.getSignature(); //获取目标方法签名
        //String name = signature.getName();//获取目标方法名称
        //Class clzz = signature.getDeclaringType(); //获取目标方法声明类的字节码
        //String typeName = signature.getDeclaringTypeName(); //获取目标方法声明类的名称
        String methodName = joinPoint.getSignature().getName();

        //将搜集到的信息封装到对象中
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setUsername(username);
        sysLog.setIp(addr);
        sysLog.setUrl(uri);
        sysLog.setExecutionTime(millis);
        sysLog.setMethod(methodName);

        //调用业务层将数据保存到数据库
        sysLogService.add(sysLog);
    }
}
