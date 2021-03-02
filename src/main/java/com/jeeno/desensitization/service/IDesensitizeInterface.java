package com.jeeno.desensitization.service;

/**
 * 数据脱敏公共接口
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 9:42
 */
public interface IDesensitizeInterface<T> {

    /**
     * 数据脱敏处理
     * @param original 原始数据
     * @return T 脱敏后数据
     */
    T dataFormat(T original);

}
