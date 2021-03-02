package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.service.IDesensitizeInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 9:57
 */
@Slf4j
@Service
public class PhoneDesensitizeImpl implements IDesensitizeInterface<String> {
    @Override
    public String dataFormat(String original) {
        log.debug("数据脱敏: Phone格式");
        if (StringUtils.isBlank(original)) {
            return original;
        }
        return original.substring(0, 3) + "****" + original.substring(7, 11);
    }
}
