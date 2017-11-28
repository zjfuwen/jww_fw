package com.jww.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @author wanyong
 * @description: 系统日志，切面处理类
 * @date 2017/11/16 17:04
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {
    // 开始时间
    private long startTime = 0L;

    @Pointcut("execution(* *..controller..*.*(..))")
    public void webLogPointCut() {

    }

    @Around("webLogPointCut()")
    public Object doAround(ProceedingJoinPoint pjp){

        startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // 记录下请求内容
        HttpServletRequest request = attributes.getRequest();
        StringBuffer logbf = new StringBuffer();
        logbf.append("URL:").append(request.getRequestURL());
        logbf.append(",HTTP_METHOD:").append(request.getMethod());
        logbf.append(",IP:").append(request.getRemoteAddr());
        logbf.append(",CLASS_METHOD:").append(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logbf.append(",ARGS:").append(Arrays.toString(pjp.getArgs()));
        Object result = null;
        try{
            result = pjp.proceed();
        }catch (Throwable throwable){
            log.error("系统异常",throwable);
        }
        // 处理完请求，返回内容
        logbf.append(",RESPONSE:").append(JSON.toJSONString(result));
        logbf.append(",START_TIME:").append(DateUtil.date(startTime));
        logbf.append(",SEPEND_TIME:").append(System.currentTimeMillis() - startTime + "ms}");
        String logStr = logbf.toString();
        if(result==null){
            log.error(logStr);
        }
        log.info(logStr);
        return result;
    }
}
