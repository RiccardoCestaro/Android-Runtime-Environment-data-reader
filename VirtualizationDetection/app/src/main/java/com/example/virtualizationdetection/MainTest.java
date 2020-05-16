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

                    //new MainTest().getStarted("com.example.virtualizationdetection.$Proxy0");



                } catch (ClassNotFoundException e) {
                    return e.getLocalizedMessage();
                }

                method.invoke(original, args);
                System.out.println("AFTER");
                //new MainTest().getStarted("com.example.virtualizationdetection.$Proxy0");
                return null;
            }
        }

    }



    public String getStarted(Class<?> classProxyName) {

            JdkProxyDemo.Original a = new JdkProxyDemo.Original();
            Class originalclass = a.getClass();

            //Method targetMethod = target.getDeclaredMethod("");
            ArrayList<Method> list = new ArrayList<>();
            for ( Method method : classProxyName.getDeclaredMethods()) {
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

                if (artMethod instanceof ArtMethod_API24_25) {
                    int declaringClass = ((ArtMethod_API24_25) artMethod).getDeclaringClass();
                    int accessFlags = ((ArtMethod_API24_25) artMethod).getAccessFlags();
                    int dexMethodIndex = ((ArtMethod_API24_25) artMethod).getDexMethodIndex();
                    long methodIndex = ((ArtMethod_API24_25) artMethod).getMethodIndex();
                    int hotnessCount = ((ArtMethod_API24_25) artMethod).getHotnessCount();
                    long dexCacheResolvedMethod = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedMethod();
                    int dexCacheResolvedTypes = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedTypes();
                    int entryPointFromJni = ((ArtMethod_API24_25) artMethod).getEntryPointFromJni();
                    int entryPointFromQuickCompiledCode = ((ArtMethod_API24_25) artMethod).getEntryPointFromQuickCompiledCode();

                    toReturn += "\n declaring_class_ = " + declaringClass + "  ";
                    Log.d("MainTest", "\n declaring_class_ = " + declaringClass + "  ");

                    toReturn += "\n access_flags_ = " + accessFlags + "  ";
                    Log.d("MainTest", "\n access_flags_ = " + accessFlags + "  ");

                    toReturn += "\n dex_method_index_ " + dexMethodIndex + "  ";
                    Log.d("MainTest", "\n dex_method_index_ " + dexMethodIndex + "  ");

                    toReturn += "\n method_index_ " + methodIndex + "  ";
                    Log.d("MainTest", "\n method_index_ " + methodIndex + "  ");

                    toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                    Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                    toReturn += "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ";
                    Log.d("MainTest", "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ");

                    toReturn += "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ";
                    Log.d("MainTest", "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ");

                    toReturn += "\n entry_point_from_jni_" + entryPointFromJni + "  ";
                    Log.d("MainTest", "\n entry_point_from_jni_" + entryPointFromJni + "  ");

                    toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                    Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

                } else if (artMethod instanceof ArtMethod_API29) {
                    int declaringClass = ((ArtMethod_API29) artMethod).getDeclaringClass();
                    int accessFlags = ((ArtMethod_API29) artMethod).getAccessFlags();
                    int dexMethodIndex = ((ArtMethod_API29) artMethod).getDexMethodIndex();
                    long methodIndex = ((ArtMethod_API29) artMethod).getMethodIndex();
                    int hotnessCount = ((ArtMethod_API29) artMethod).getHotnessCount();
                    int imtIndex = ((ArtMethod_API29) artMethod).getImtIndex();
                    int data = ((ArtMethod_API29) artMethod).getData();
                    int entryPointFromQuickCompiledCode = ((ArtMethod_API29) artMethod).getEntryPointFromQuickCompiledCode();

                    toReturn += "\n declaring_class_ = " + declaringClass + "  ";
                    Log.d("MainTest", "\n declaring_class_ = " + declaringClass + "  ");

                    toReturn += "\n access_flags_ = " + accessFlags + "  ";
                    Log.d("MainTest", "\n access_flags_ = " + accessFlags + "  ");

                    toReturn += "\n dex_method_index_ " + dexMethodIndex + "  ";
                    Log.d("MainTest", "\n dex_method_index_ " + dexMethodIndex + "  ");

                    toReturn += "\n method_index_ " + methodIndex + "  ";
                    Log.d("MainTest", "\n method_index_ " + methodIndex + "  ");

                    toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                    Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                    toReturn += "\n imt_index_ " + imtIndex + "  ";
                    Log.d("MainTest", "\n imt_index_ " + imtIndex + "  ");

                    toReturn += "\n data_ " + data + "  ";
                    Log.d("MainTest", "\n data_ " + data + "  ");

                    toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                    Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");


                }
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

                if( artMethod instanceof ArtMethod_API24_25) {
                    int declaringClass = ((ArtMethod_API24_25) artMethod).getDeclaringClass();
                    int accessFlags = ((ArtMethod_API24_25) artMethod).getAccessFlags();
                    int dexMethodIndex = ((ArtMethod_API24_25) artMethod).getDexMethodIndex();
                    long methodIndex = ((ArtMethod_API24_25) artMethod).getMethodIndex();
                    int hotnessCount = ((ArtMethod_API24_25) artMethod).getHotnessCount();
                    long dexCacheResolvedMethod = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedMethod();
                    int dexCacheResolvedTypes = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedTypes();
                    int entryPointFromJni = ((ArtMethod_API24_25) artMethod).getEntryPointFromJni();
                    int entryPointFromQuickCompiledCode = ((ArtMethod_API24_25) artMethod).getEntryPointFromQuickCompiledCode();

                    toReturn += "\n declaring_class_ = " + declaringClass + "  ";
                    Log.d("MainTest", "\n declaring_class_ = " + declaringClass + "  ");

                    toReturn += "\n access_flags_ = " + accessFlags + "  ";
                    Log.d("MainTest", "\n access_flags_ = " + accessFlags + "  ");

                    toReturn += "\n dex_method_index_ " + dexMethodIndex + "  ";
                    Log.d("MainTest", "\n dex_method_index_ " + dexMethodIndex + "  ");

                    toReturn += "\n method_index_ " + methodIndex + "  ";
                    Log.d("MainTest", "\n method_index_ " + methodIndex + "  ");

                    toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                    Log.d("MainTest",  "\n hotness_count_ " + hotnessCount + "  ");

                    toReturn += "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ";
                    Log.d("MainTest", "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ");

                    toReturn += "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ";
                    Log.d("MainTest", "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ");

                    toReturn += "\n entry_point_from_jni_" + entryPointFromJni + "  ";
                    Log.d("MainTest", "\n entry_point_from_jni_" + entryPointFromJni + "  ");

                    toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                    Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");


                } else if (artMethod instanceof ArtMethod_API29) {
                    int declaringClass = ((ArtMethod_API29) artMethod).getDeclaringClass();
                    int accessFlags = ((ArtMethod_API29) artMethod).getAccessFlags();
                    int dexMethodIndex = ((ArtMethod_API29) artMethod).getDexMethodIndex();
                    long methodIndex = ((ArtMethod_API29) artMethod).getMethodIndex();
                    int hotnessCount = ((ArtMethod_API29) artMethod).getHotnessCount();
                    int imtIndex = ((ArtMethod_API29) artMethod).getImtIndex();
                    int data = ((ArtMethod_API29) artMethod).getData();
                    int entryPointFromQuickCompiledCode = ((ArtMethod_API29) artMethod).getEntryPointFromQuickCompiledCode();

                    toReturn += "\n declaring_class_ = " + declaringClass + "  ";
                    Log.d("MainTest", "\n declaring_class_ = " + declaringClass + "  ");

                    toReturn += "\n access_flags_ = " + accessFlags + "  ";
                    Log.d("MainTest", "\n access_flags_ = " + accessFlags + "  ");

                    toReturn += "\n dex_method_index_ " + dexMethodIndex + "  ";
                    Log.d("MainTest", "\n dex_method_index_ " + dexMethodIndex + "  ");

                    toReturn += "\n method_index_ " + methodIndex + "  ";
                    Log.d("MainTest", "\n method_index_ " + methodIndex + "  ");

                    toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                    Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                    toReturn += "\n imt_index_ " + imtIndex + "  ";
                    Log.d("MainTest", "\n imt_index_ " + imtIndex + "  ");

                    toReturn += "\n data_ " + data + "  ";
                    Log.d("MainTest", "\n data_ " + data + "  ");

                    toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                    Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");


                }
            }


            return toReturn;

    }
    public JdkProxyDemo.If ciao;
    public JdkProxyDemo.Original ciao2 = new JdkProxyDemo.Original();

    public Class<?> dynamicProxyTest(){
       JdkProxyDemo.Original original = new JdkProxyDemo.Original();
        JdkProxyDemo.Handler handler = new JdkProxyDemo.Handler(original);
        ciao = (JdkProxyDemo.If) Proxy.newProxyInstance(JdkProxyDemo.If.class.getClassLoader(),
                new Class[] { JdkProxyDemo.If.class },
                handler);
        ciao.originalMethod("Hadergrtgllo");
        Log.i("PROXYYYYYY"   , ciao.getClass().toString());
        //Log.i("PROXYYYYYY   ",Boolean.toString(Proxy.isProxyClass(JdkProxyDemo.class)));
        ciao2.originalMethod("ciaderfo");
        ciao2.originalMethod("casiao");
        ciao2.originalMethod("ciao");

        return ciao.getClass();
    }

    public void callOriginalMethodNotProxied(){
        new JdkProxyDemo.Original().originalMethod("ciao");
    }





}
