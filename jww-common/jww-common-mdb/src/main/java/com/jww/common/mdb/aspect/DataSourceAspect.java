package com.jww.common.mdb.aspect;

import com.jww.common.core.Constants;
import com.jww.common.mdb.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切面处理类
 *
 * @author wanyong
 * @date 2017/11/17 13:59
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.jww..mapper.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getName().startsWith(Constants.MAPPER_METHOD_STARTSWITH_SELECT)) {
            log.info("切换为从数据源：{}", Constants.DataSourceEnum.SLAVE.getName());
            // 切换从数据源
            DbContextHolder.setDbType(Constants.DataSourceEnum.SLAVE);
        } else {
            log.info("切换为主数据源：{}", Constants.DataSourceEnum.MASTER.getName());
            // 切换主数据源
            DbContextHolder.setDbType(Constants.DataSourceEnum.MASTER);
        }
    }

    @After("dataSourcePointCut()")
    public void doAfter() {
        log.info("---- 清空数据源 ----");
        // 清空数据源
        DbContextHolder.clearDbType();
    }

}
