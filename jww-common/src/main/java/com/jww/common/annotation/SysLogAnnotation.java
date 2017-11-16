package com.jww.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author wanyong
 * @email
 * @date 2017年11月08日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {
    String value() default "";
}
