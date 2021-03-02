package com.jeeno.desensitization.advice;

import com.jeeno.desensitization.service.DesensitizeTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/1 9:17
 */
@Slf4j
@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {

    @Resource
    private DesensitizeTemplate<Object> desensitizeTemplate;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.debug("#beforeBodyWrite# ");
        try {
            return desensitizeTemplate.desensitize(o);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException");
            e.printStackTrace();
        } catch (InstantiationException e) {
            log.error("InstantiationException");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException");
            e.printStackTrace();
        }
        return o;
    }

}
