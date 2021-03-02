package com.jeeno.desensitization.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的脱敏对象注解
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/2 10:54
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DesensitizeType {
}
