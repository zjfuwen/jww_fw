package com.jww.common.core.annotation;

import java.lang.annotation.*;

/**
 * 分布式锁注解
 *
 * @author shadj
 * @date 2017/12/11 14:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    String value() default "";
}
