package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.service.IDesensitizeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 9:56
 */
@Slf4j
@Service
public class AddressDesensitizeImpl implements IDesensitizeInterface<String> {
    @Override
    public String dataFormat(String original) {
        log.debug("数据脱敏: Address格式");
        StringBuilder sb = new StringBuilder(original);
        return sb.toString();
    }
}
