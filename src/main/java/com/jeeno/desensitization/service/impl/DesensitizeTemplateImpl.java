package com.jeeno.desensitization.service.impl;

import com.jeeno.desensitization.annotation.DesensitizeField;
import com.jeeno.desensitization.annotation.DesensitizeType;
import com.jeeno.desensitization.service.DesensitizeTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2020/6/2 19:28
 */
@Slf4j
@Service
public class DesensitizeTemplateImpl<T> implements DesensitizeTemplate<T> {

    @Resource
    private NameDesensitizeImpl nameDesensitize;

    @Resource
    private PasswordDesensitizeImpl passwordDesensitize;

    @Resource
    private AddressDesensitizeImpl addressDesensitize;

    @Resource
    private PhoneDesensitizeImpl phoneDesensitize;

    @Resource
    private BankCardDesensitizeImpl bankCardDesensitize;

    @Resource
    private IdCardDesensitizeImpl idCardDesensitize;

    @Override
    public T desensitize(T object) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
            InstantiationException, InvocationTargetException {
        return organizeDesensitizeType(object);
    }

    /**
     * 对对象类进行脱敏
     * @param field 字段
     * @param object 对象
     * @throws IllegalAccessException 异常信息
     */
    private void updateField(Field field, Object object) throws IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        // 字段脱敏
        organizeDesensitizeField(field, object);

        // 类对象内容脱敏
        DesensitizeType desensitizeType = field.getAnnotation(DesensitizeType.class);
        if (desensitizeType!=null){
            organizeDesensitizeType(field.get(object));
        }
    }

    /**
     * 对对象中的字段逐个进行脱敏
     * @param field 字段
     * @param object 对象
     * @throws IllegalAccessException 异常信息
     */
    private void organizeDesensitizeField(Field field, Object object) throws IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        field.setAccessible(true);
        DesensitizeField annotation = field.getAnnotation(DesensitizeField.class);
        if (annotation != null) {
            Object original = field.get(object);
            if (original == null){
                return;
            }
            Object data = original;
            switch (annotation.type()){
                case NAME:
                    data = nameDesensitize.dataFormat(original.toString());
                    break;
                case PASSWORD:
                    data = passwordDesensitize.dataFormat(original.toString());
                    break;
                case ADDRESS:
                    data = addressDesensitize.dataFormat(original.toString());
                    break;
                case PHONE:
                    data = phoneDesensitize.dataFormat(original.toString());
                    break;
                case BANKCARD:
                    data = bankCardDesensitize.dataFormat(original.toString());
                    break;
                case IDCARD:
                    data = idCardDesensitize.dataFormat(original.toString());
                    break;
                case CUSTOM:
                    // 通过反射，执行自定义扩展部分的脱敏操作
                    Class<?> clazz = Class.forName(annotation.clazz().getName());
                    Method method = clazz.getMethod("dataFormat", Object.class);
                    data = method.invoke(clazz.newInstance(), original);
                    break;
                default:
            }
            field.set(object, data);
        } else if (object instanceof Iterable){
            organizeDesensitizeType(object);
        }
    }

    /**
     * 脱敏类的处理逻辑
     * @param obj 类对象内容
     * @throws IllegalAccessException 异常信息
     */
    @SuppressWarnings("unchecked")
    private T organizeDesensitizeType(Object obj) throws IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (obj == null) {
            return null;
        }
        Object[] array = toArray(obj);
        for (Object x : array) {
            // 循环遍历父类的成员
//            Field[] childFields = x.getClass().getDeclaredFields();
            List<Field> childFields = new ArrayList<>();
            Class<?> clazz = x.getClass();
            while(clazz!=null) {
                List list = Arrays.asList(clazz.getDeclaredFields());
                childFields.addAll(list);
                clazz = clazz.getSuperclass();
            }
            for (Field childField : childFields) {
                updateField(childField, x);
            }
        }
        return (T)obj;
    }

    /**
     * 将对象返回成对象数组
     * @param obj 原始对象
     * @return 对象数组
     */
    private Object[] toArray(Object obj) {
        Object[] array;
        if (obj instanceof Iterable) {
            Iterable iterable = (Iterable) obj;
            Iterator it = iterable.iterator();
            List<Object> list = new ArrayList<>();
            while(it.hasNext()) {
                list.add(it.next());
            }
            array = list.toArray();
        } else {
            array = new Object[1];
            array[0] = obj;
        }
        return array;
    }
}
