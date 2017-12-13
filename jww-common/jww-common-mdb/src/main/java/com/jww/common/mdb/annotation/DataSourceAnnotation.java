package com.jww.common.mdb.annotation;

import com.jww.common.core.Constants;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author wanyong
 * @date 2017/11/17 13:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceAnnotation {
    Constants.DataSourceEnum value() default Constants.DataSourceEnum.MASTER;
}
