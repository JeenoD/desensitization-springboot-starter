package com.jeeno.desensitization.annotation;

import com.jeeno.desensitization.enums.DesensitizeTypeEnum;
import com.jeeno.desensitization.service.IDesensitizeInterface;
import com.jeeno.desensitization.service.impl.PasswordDesensitizeImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的脱敏字段注解
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/1 11:35
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("unused")
public @interface DesensitizeField {
    /**
     * 脱敏格式类型
     */
    DesensitizeTypeEnum type() default  DesensitizeTypeEnum.PASSWORD;

    /**
     * 自定义的脱敏接口实现
     * @return Class
     */
    Class<? extends IDesensitizeInterface> clazz() default PasswordDesensitizeImpl.class;

}
