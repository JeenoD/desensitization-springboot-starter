package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.service.IDesensitizeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 16:15
 */
@Slf4j
@Service
public class IdCardDesensitizeImpl implements IDesensitizeInterface<String> {
    @Override
    public String dataFormat(String original) {
        StringBuilder result = new StringBuilder(original);
        for (int i=1; i<original.length()-1;++i){
            result.setCharAt(i, '*');
        }
        return result.toString();
    }
}
