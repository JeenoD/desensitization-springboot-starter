package com.jeeno.desensitization.service;

import com.jeeno.desensitization.annotation.DesensitizeField;
import com.jeeno.desensitization.annotation.DesensitizeType;
import com.jeeno.desensitization.enums.DesensitizeTypeEnum;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/3 9:31
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class DesensitizeTemplateTest {

    @Resource
    private DesensitizeTemplate<UserInfo> desensitizeTemplate;

    @Test
    public void test() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
            InstantiationException, InvocationTargetException {
        UserInfo userInfo = UserInfo.builder()
                .name("JEENO")
                .address("Zhejianig")
                .phone("188*****110")
                .password("12345678")
                .members(Collections.singletonList(MemberInfo.builder().name("ZhangThree").phone("188*****110").build()))
                .build();
        UserInfo obj = desensitizeTemplate.desensitize(userInfo);
        System.out.println(obj);
    }

}

@Builder
@ToString
class UserInfo {
    @DesensitizeField(type = DesensitizeTypeEnum.NAME)
    private String name;

    @DesensitizeField(type = DesensitizeTypeEnum.PHONE)
    private String phone;

    @DesensitizeField(type = DesensitizeTypeEnum.PASSWORD)
    private String password;

    @DesensitizeField(type = DesensitizeTypeEnum.CUSTOM)
    private String address;

    @DesensitizeType
    private List<MemberInfo> members;
}

@Builder
@ToString
class MemberInfo {

    @DesensitizeField(type = DesensitizeTypeEnum.NAME)
    private String name;

    @DesensitizeField(type = DesensitizeTypeEnum.PHONE)
    private String phone;
}
