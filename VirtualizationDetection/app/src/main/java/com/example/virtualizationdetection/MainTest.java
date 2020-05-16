/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

public class MainTest {

    public MainTest(){}

    public void test(){ Log.d("MainTest","Default test");}


    public static class JdkProxyDemo {
         interface If {
            void originalMethod(String s);
        }
         static class Original implements If {
            public void originalMethod(String s) {
                System.out.println(s);
            }
        }
        static class Handler implements InvocationHandler {
            private final If original;
            public Handler(If original) {
                this.original = original;
            }
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException {
                System.out.println("BEFORE");

                try{
                    Class target = Class.forName("com.example.virtualizationdetection.$Proxy0");
                    for ( Method a2 : target.getDeclaredMethods()) {
                        a2.setAccessible(true);
                        Log.i("PROXYYYY  ",a2.getName());
                    }
                    for( Field a : target.getDeclaredFields()){
                        a.setAccessible(true);
                        Log.i("PROXYYYYY   ",a.getName() + "   " + a.getDeclaringClass().getName() + "  " );
                    }

                    new MainTest().getStarted("com.example.virtualizationdetection.$Proxy0");



                } catch (ClassNotFoundException e) {
                    return e.getLocalizedMessage();
                }

                method.invoke(original, args);
                System.out.println("AFTER");
                return null;
            }
        }

    }



    public String getStarted(String classProxyName) {
        try {
            JdkProxyDemo.Original a = new JdkProxyDemo.Original();
            Class originalclass = a.getClass();
            Class target = Class.forName(classProxyName);
            //Method targetMethod = target.getDeclaredMethod("");
            ArrayList<Method> list = new ArrayList<>();
            for ( Method method : target.getDeclaredMethods()) {
                method.setAccessible(true);
                if (method.getName().equals("originalMethod") ){
                    list.add(method);
                }
            }
            ArrayList<Method> originalList = new ArrayList<>();
            for ( Method method : originalclass.getDeclaredMethods()) {
                method.setAccessible(true);
                if (method.getName().equals("originalMethod") ){
                    originalList.add(method);
                }
            }
            String toReturn = "";
            for ( Method targetMethod : list ) {
                targetMethod.setAccessible(true);
                ArtMethod artMethod = ArtMethod.of(targetMethod);
                if (artMethod == null) {
                    Log.d("MainTest", "Method not found!");
                    return "Method not found!";
                }
                Log.d("MainTest", artMethod.toString());

                int dexCodeItemOffset = artMethod.getDexCodeItemOffset();

                Log.d("MainTest", "dex_code_item_offset_=  " + dexCodeItemOffset);

                toReturn += "\n Method: " + targetMethod.getName() + " - " + dexCodeItemOffset + "    ";
            }
            for ( Method targetMethod : originalList ) {
                targetMethod.setAccessible(true);
                ArtMethod artMethod = ArtMethod.of(targetMethod);
                if (artMethod == null) {
                    Log.d("MainTest", "Method not found!");
                    return "Method not found!";
                }
                Log.d("MainTest", artMethod.toString());

                int dexCodeItemOffset = artMethod.getDexCodeItemOffset();

                Log.d("MainTest", "dex_code_item_offset_=  " + dexCodeItemOffset);

                toReturn += "\n Method: " + targetMethod.getName() + " - " + dexCodeItemOffset + "    ";
            }
            return toReturn;

        } catch (ClassNotFoundException e) {
         return e.getLocalizedMessage();
        }
    }
    public JdkProxyDemo.If ciao;

    public void dynamicProxyTest(){
       JdkProxyDemo.Original original = new JdkProxyDemo.Original();
        JdkProxyDemo.Handler handler = new JdkProxyDemo.Handler(original);
        ciao = (JdkProxyDemo.If) Proxy.newProxyInstance(JdkProxyDemo.If.class.getClassLoader(),
                new Class[] { JdkProxyDemo.If.class },
                handler);
        ciao.originalMethod("Hallo");
        Log.i("PROXYYYYYY"   , ciao.getClass().toString());
        //Log.i("PROXYYYYYY   ",Boolean.toString(Proxy.isProxyClass(JdkProxyDemo.class)));
    }





}
