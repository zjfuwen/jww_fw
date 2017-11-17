package com.jww.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description: 数据源切面处理类
 * @author wanyong
 * @date 2017/11/17 13:59
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.jww.common.annotation.DataSourceAnnotation)")
    public void dataSourcePointCut() {
    }

}
