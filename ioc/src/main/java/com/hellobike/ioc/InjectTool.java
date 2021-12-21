package com.hellobike.ioc;

import com.hellobike.ioc.annotation.OnBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Su Hengshuo
 * @Data 12/21/21
 */
public class InjectTool {

    private static final String CLASS_TAG = InjectTool.class.getSimpleName();

    public static void inject(Object object) {
        injectEvent(object);
    }

    private static void injectEvent(Object object) {

        Class<?> mainActivityClass = object.getClass();
        Method[] declaredMethods = mainActivityClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();

            for (Annotation annotation : declaredAnnotations) {


                //获取当前注解的父注解 OnBase
                Class<? extends Annotation> annotationType = annotation.annotationType();

                OnBase onBase = annotationType.getAnnotation(OnBase.class);

                if (null == onBase) {
                    continue;
                }

                //获取事件三要素
                String setCommonListener = onBase.setCommonListener();
                Class<?> setCommonObjectListener = onBase.setCommonObjectListener();
                String callbackMethod = onBase.callbackMethod();

                try {
                    /**
                     * 获取 @OnClickCommon(R.id.bt_1)
                     * value == R.id.bt_1
                     */
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    valueMethod.setAccessible(true);
                    int value = (int) valueMethod.invoke(annotation);

                    //获取 findViewById 方法，拿到 view
                    Method findViewById = mainActivityClass.getMethod("findViewById", int.class);
                    Object resultView = findViewById.invoke(object, value);

                    //再次使用反射获取具体方法
                    Method viewMethod = resultView.getClass().getMethod(setCommonListener, setCommonObjectListener);

                    //动态代理监听第三个要素
                    Object proxy = Proxy.newProxyInstance(
                            setCommonObjectListener.getClassLoader(),
                            new Class[]{setCommonObjectListener},
                            new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    return declaredMethod.invoke(object, null);
                                }
                            }
                    );

                    viewMethod.invoke(resultView, proxy);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }


}
