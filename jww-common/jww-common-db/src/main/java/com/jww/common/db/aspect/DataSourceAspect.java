package com.jww.common.db.aspect;

import com.jww.common.constant.Constants;
import com.jww.common.db.DbContextHolder;
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
 * @author wanyong
 * @description: 数据源切面处理类
 * @date 2017/11/17 13:59
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    public DataSourceAspect() {
        log.info("---------------DataSourceAspect----------------------");
    }

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
            log.info("切换为从数据源：{}", Constants.DataSourceEnum.MASTER.getName());
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
