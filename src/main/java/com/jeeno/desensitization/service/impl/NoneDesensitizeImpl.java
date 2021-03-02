package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.service.IDesensitizeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 10:09
 */
@Slf4j
@Service
public class NoneDesensitizeImpl<T> implements IDesensitizeInterface<T> {
    @Override
    public T dataFormat(T original) {
        return original;
    }
}
