package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.service.IDesensitizeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 9:55
 */
@Slf4j
@Service
public class PasswordDesensitizeImpl implements IDesensitizeInterface<String> {

    @Override
    public String dataFormat(String original) {
        log.debug("数据脱敏: Password格式");
        return "******";
    }

}
