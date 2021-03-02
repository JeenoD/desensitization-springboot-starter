package com.jeeno.desensitization.service;

import java.lang.reflect.InvocationTargetException;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/2 14:53
 */

public interface DesensitizeTemplate<T> {
    /**
     * 对对象内容进行脱敏处理
     * @param object 返回对象
     * @return T 泛型对象
     * @throws IllegalAccessException 异常信息
     */
    T desensitize(T object) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
