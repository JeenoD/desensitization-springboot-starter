package com.jeeno.desensitization.enums;

/**
 * 自定义的脱敏类型枚举
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/1 11:37
 */
public enum DesensitizeTypeEnum {

    /**
     * 密码脱敏格式
     */
    PASSWORD("password"),
    /**
     * 姓名脱敏格式
     */
    NAME("name"),
    /**
     * 地址脱敏格式
     */
    ADDRESS("address"),
    /**
     * 手机号脱敏格式
     */
    PHONE("phone"),
    /**
     * 银行卡号脱敏格式
     */
    BANKCARD("bankCard"),
    /**
     * 身份证号
     */
    IDCARD("idCard"),
    /**
     * 自定义的定制格式
     */
    CUSTOM("custom");

    private final String type;

    DesensitizeTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
