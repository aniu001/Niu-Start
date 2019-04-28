/*
package com.rjwl.api.common.aspect;

import com.rjwl.api.common.annotation.OptLogAnnotation;
import com.rjwl.api.entity.Admin;
import com.rjwl.api.entity.OperationLog;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

*/
/**
 * @author aniu
 *//*

@Component
@Aspect
public class OperationLogAspect {
    @Autowired
    private OperationLogService operationLogService;

    */
/**
     * 声明切入点
     *//*

    @Pointcut(value = "@annotation(com.rjwl.api.common.annotation.OptLogAnnotation)")
    public void pointCut() {
    }

    @After("pointCut() && @annotation(optLogAnnotation)")
    public void doAfter(JoinPoint joinPoint, OptLogAnnotation optLogAnnotation) {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        OperationLog operationLog = new OperationLog();
        operationLog.setAdminId(admin.getId());
        operationLog.setAdminName(admin.getAdminName());
        Object[] args = joinPoint.getArgs();
        String operationRecord = optLogAnnotation.value();
        StringBuilder optRecordSb = new StringBuilder(admin.getAdminName()).append(operationRecord);
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                operationLog.setLoginIp(getIpAddr((HttpServletRequest) arg));
                continue;
            }
            optRecordSb.append(arg);
        }
        operationLog.setOperationRecord(optRecordSb.toString());
        Date date = new Date();
        operationLog.setGmt(date);
        operationLog.setUpt(date);
        operationLogService.getMapper().insertSelective(operationLog);
    }

    */
/**
     * 获取登录用户IP地址
     *
     * @param request
     * @return
     *//*

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotBlank(ip)&&ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        return ip;
    }

}
*/
