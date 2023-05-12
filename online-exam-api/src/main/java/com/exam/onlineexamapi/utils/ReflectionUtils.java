package com.exam.onlineexamapi.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectionUtils {

    /**
     * 利用反射机制处理方法
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object object, String method, Object... args) {
        Object result = null;
        Class<? extends Object> clazz = object.getClass();
        Method queryMethod = getMethod(clazz, method, args);
        if (queryMethod != null) {
            try {
                result = queryMethod.invoke(object, args);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据方法名，参数类型来获取对应方法
     * @param clazz
     * @param name
     * @param args
     * @return
     */
    public static Method getMethod(Class<? extends Object> clazz, String name, Object[] args) {
        Method queryMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length) {
                    boolean isSame = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object arg = args[i];
                        if (arg == null) {
                            arg = "";
                        }
                        if (!parameterTypes[i].equals(args[i].getClass())) {
                            isSame = false;
                        }
                    }
                    if (isSame) {
                        queryMethod = method;
                    }
                }
            }
        }
        return queryMethod;
    }
}
