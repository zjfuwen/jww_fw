package com.jww.common.log.web;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 系统日志，切面处理类
 *
 * @author wanyong
 * @date 2017/11/16 17:04
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    /**
     * 开始时间
     */
    private long startTime = 0L;

    @Pointcut("execution(* *..controller..*.*(..))")
    public void webLogPointCut() {

    }

    @Around("webLogPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        startTime = System.currentTimeMillis();
        //异常标记
        boolean eFlag = false;
        //获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //拼接请求日志
        StringBuffer logbf = appendLogStrPre(pjp, request);
        Object result = null;
        try {
            result = pjp.proceed();
        } finally {
            //拼接返回日志
            String logStr = appendLogStrAfter(logbf, result);
            log.info(logStr);
        }
        return result;
    }

    /**
     * 记录下请求内容
     *
     * @param pjp
     * @param request
     * @return StringBuffer
     * @author wanyong
     * @date 2017-12-12 11:07
     */
    private StringBuffer appendLogStrPre(ProceedingJoinPoint pjp, HttpServletRequest request) {
        startTime = System.currentTimeMillis();
        StringBuffer logbf = new StringBuffer();
        logbf.append("URL:").append(request.getRequestURL());
        logbf.append(",HTTP_METHOD:").append(request.getMethod());
        logbf.append(",IP:").append(HttpUtil.getClientIP(request));
        logbf.append(",CLASS_METHOD:").append(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logbf.append(",ARGS:").append(JSON.toJSONString(pjp.getArgs()));
        return logbf;
    }

    /**
     * 处理完请求，返回内容
     *
     * @param logbf
     * @param result
     * @return String
     * @author wanyong
     * @date 2017-12-12 11:08
     */
    private String appendLogStrAfter(StringBuffer logbf, Object result) {
        logbf.append(",RESPONSE:").append(JSON.toJSONString(result));
        logbf.append(",START_TIME:").append(DateUtil.date(startTime));
        logbf.append(",SEPEND_TIME:").append(System.currentTimeMillis() - startTime + "ms}");
        return logbf.toString();
    }
}
