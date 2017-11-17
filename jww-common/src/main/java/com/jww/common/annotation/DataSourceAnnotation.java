package com.jww.common.annotation;

import java.lang.annotation.*;

/**
 * @author wanyong
 * @description: 数据源注解
 * @date 2017/11/17 13:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceAnnotation {
    String value() default "";
}
