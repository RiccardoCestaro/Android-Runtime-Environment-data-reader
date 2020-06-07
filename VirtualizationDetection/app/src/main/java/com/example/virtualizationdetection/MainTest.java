/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.security.auth.callback.PasswordCallback;

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

                method.invoke(original, args);

                System.out.println("AFTER");
                return null;
            }
        }

    }

    
    public String getStarted() throws ClassNotFoundException {

            JdkProxyDemo.Original a = new JdkProxyDemo.Original();
            PackageManager pm = MainActivity.context.getPackageManager();

            String toPrint =printArtMethodsOf(a.getClass()) + "\n\n\n" +
                    printArtMethodsOf(Class.forName("android.app.ActivityThread")) + "\n\n\n" +
                    printArtMethodsOf(pm.getClass());

            return toPrint;

    }
    public JdkProxyDemo.If ciao;
    public JdkProxyDemo.Original ciao2 = new JdkProxyDemo.Original();

    public Class<?> dynamicProxyTest(){
       JdkProxyDemo.Original original = new JdkProxyDemo.Original();
        JdkProxyDemo.Handler handler = new JdkProxyDemo.Handler(original);
        ciao = (JdkProxyDemo.If) Proxy.newProxyInstance(JdkProxyDemo.If.class.getClassLoader(),
                new Class[] { JdkProxyDemo.If.class },
                handler);
        Log.i("PROXY"   , ciao.getClass().toString());
        ciao2.originalMethod("originalMethod");
        return ciao.getClass();
    }


    private static String printArtMethodsOf(Class<?> clazz) {

        String toReturn = "  Class: " + clazz.getName() + "  \n\n";

        for ( Method targetMethod : clazz.getDeclaredMethods()) {
            targetMethod.setAccessible(true);

            ArtMethod artMethod = ArtMethod.of(targetMethod);
            if (artMethod == null) {
                Log.d("MainTest", "Method not found!");
                return "Method not found!";
            }
            Log.d("MainTest", artMethod.toString());



            toReturn += "\n\n\n Method: " + targetMethod.getName();

            int dexCodeItemOffset = artMethod.getDexCodeItemOffset();
            toReturn += "\n dex_code_item_offset_ = " + dexCodeItemOffset + "  ";
            Log.d("MainTest", "\n dex_code_item_offset_ = " + dexCodeItemOffset + "  ");

            int declaringClass = artMethod.getDeclaringClass();
            toReturn += "\n declaring_class_ = " + declaringClass + "  ";
            Log.d("MainTest", "\n declaring_class_ = " + declaringClass + "  ");

            int accessFlags = artMethod.getAccessFlags();
            toReturn += "\n access_flags_ = " + accessFlags + "  ";
            Log.d("MainTest", "\n access_flags_ = " + accessFlags + "  ");

            int dexMethodIndex = artMethod.getDexMethodIndex();
            toReturn += "\n dex_method_index_ " + dexMethodIndex + "  ";
            Log.d("MainTest", "\n dex_method_index_ " + dexMethodIndex + "  ");

            long methodIndex = artMethod.getMethodIndex();
            toReturn += "\n method_index_ " + methodIndex + "  ";
            Log.d("MainTest", "\n method_index_ " + methodIndex + "  ");

            if (artMethod instanceof ArtMethod_API21) {

                int dexCacheResolvedMethod = ((ArtMethod_API21) artMethod).getDexCacheResolvedMethod();
                int dexCacheResolvedTypes = ((ArtMethod_API21) artMethod).getDexCacheResolvedTypes();
                int dexCacheStrings = ((ArtMethod_API21) artMethod).getDexCacheStrings();
                long entryPointFromInterpreter = ((ArtMethod_API21) artMethod).getEntryPointFromInterpreter();
                long entryPointFromJni = ((ArtMethod_API21) artMethod).getEntryPointFromJni();
                long entryPointFromQuickCompiledCode = ((ArtMethod_API21) artMethod).getEntryPointFromQuickCompiledCode();
                long entryPointFromPortableCompiledCode = ((ArtMethod_API21) artMethod).getEntryPointFromPortableCompiledCode();
                long gcMap = ((ArtMethod_API21) artMethod).getGcMap();


                toReturn += "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ");

                toReturn += "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ");

                toReturn += "\n dex_cache_strings_" + dexCacheStrings + "  ";
                Log.d("MainTest", "\n dex_cache_strings_" + dexCacheStrings + "  ");

                toReturn += "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ";
                Log.d("MainTest", "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ");

                toReturn += "\n entry_point_from_jni_" + entryPointFromJni + "  ";
                Log.d("MainTest", "\n entry_point_from_jni_" + entryPointFromJni + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

                toReturn += "\n entry_point_from_portable_compiled_code_" + entryPointFromPortableCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_portable_compiled_code_" + entryPointFromPortableCompiledCode + "  ");

                toReturn += "\n gc_map_ = " + gcMap + "  ";
                Log.d("MainTest", "\n gc_map_ = " + gcMap + "  ");



            } else if (artMethod instanceof ArtMethod_API22) {

                int dexCacheResolvedMethod = ((ArtMethod_API22) artMethod).getDexCacheResolvedMethod();
                int dexCacheResolvedTypes = ((ArtMethod_API22) artMethod).getDexCacheResolvedTypes();

                int entryPointFromInterpreter = ((ArtMethod_API22) artMethod).getEntryPointFromInterpreter();

                int entryPointFromJni = ((ArtMethod_API22) artMethod).getEntryPointFromJni();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API22) artMethod).getEntryPointFromQuickCompiledCode();
                int entryPointFromPortableCompiledCode = ((ArtMethod_API22) artMethod).getEntryPointFromPortableCompiledCode();


                toReturn += "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ");

                toReturn += "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ");

                toReturn += "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ";
                Log.d("MainTest", "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ");

                toReturn += "\n entry_point_from_jni_" + entryPointFromJni + "  ";
                Log.d("MainTest", "\n entry_point_from_jni_" + entryPointFromJni + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

                toReturn += "\n entry_point_from_portable_compiled_code_" + entryPointFromPortableCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_portable_compiled_code_" + entryPointFromPortableCompiledCode + "  ");


            } else if (artMethod instanceof ArtMethod_API23) {
                int dexCacheResolvedMethod = ((ArtMethod_API23) artMethod).getDexCacheResolvedMethod();
                int dexCacheResolvedTypes = ((ArtMethod_API23) artMethod).getDexCacheResolvedTypes();

                int entryPointFromInterpreter = ((ArtMethod_API23) artMethod).getEntryPointFromInterpreter();

                int entryPointFromJni = ((ArtMethod_API23) artMethod).getEntryPointFromJni();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API23) artMethod).getEntryPointFromQuickCompiledCode();

                toReturn += "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_method_ " + dexCacheResolvedMethod + "  ");

                toReturn += "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_types_" + dexCacheResolvedTypes + "  ");

                toReturn += "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ";
                Log.d("MainTest", "\n entry_point_from_interpreter_ " + entryPointFromInterpreter + "  ");

                toReturn += "\n entry_point_from_jni_" + entryPointFromJni + "  ";
                Log.d("MainTest", "\n entry_point_from_jni_" + entryPointFromJni + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

            } else if (artMethod instanceof ArtMethod_API24_25) {
                int hotnessCount = ((ArtMethod_API24_25) artMethod).getHotnessCount();
                long dexCacheResolvedMethod = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedMethod();
                int dexCacheResolvedTypes = ((ArtMethod_API24_25) artMethod).getDexCacheResolvedTypes();
                int entryPointFromJni = ((ArtMethod_API24_25) artMethod).getEntryPointFromJni();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API24_25) artMethod).getEntryPointFromQuickCompiledCode();

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

                int hotnessCount = ((ArtMethod_API29) artMethod).getHotnessCount();
                int imtIndex = ((ArtMethod_API29) artMethod).getImtIndex();
                int data = ((ArtMethod_API29) artMethod).getData();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API29) artMethod).getEntryPointFromQuickCompiledCode();

                toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                toReturn += "\n imt_index_ " + imtIndex + "  ";
                Log.d("MainTest", "\n imt_index_ " + imtIndex + "  ");

                toReturn += "\n data_ " + data + "  ";
                Log.d("MainTest", "\n data_ " + data + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");


            } else if (artMethod instanceof ArtMethod_API28) {
                int hotnessCount = ((ArtMethod_API28) artMethod).getHotnessCount();
                int data = ((ArtMethod_API28) artMethod).getData();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API28) artMethod).getEntryPointFromQuickCompiledCode();

                toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                toReturn += "\n data_ " + data + "  ";
                Log.d("MainTest", "\n data_ " + data + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

            } else if (artMethod instanceof ArtMethod_API26_27) {
                int hotnessCount = ((ArtMethod_API26_27) artMethod).getHotnessCount();
                if(hotnessCount != 0){toReturn += "-----------------------------------------------------------------------------------------------------";}
                int dexCacheResolvedMethod = ((ArtMethod_API26_27) artMethod).getDexCacheResolvedMethods();
                int data = ((ArtMethod_API26_27) artMethod).getData();
                int entryPointFromQuickCompiledCode = ((ArtMethod_API26_27) artMethod).getEntryPointFromQuickCompiledCode();

                toReturn += "\n hotness_count_ " + hotnessCount + "  ";
                Log.d("MainTest", "\n hotness_count_ " + hotnessCount + "  ");

                toReturn += "\n dex_cache_resolved_methods_ " + dexCacheResolvedMethod + "  ";
                Log.d("MainTest", "\n dex_cache_resolved_methods_ " + dexCacheResolvedMethod + "  ");

                toReturn += "\n data_ " + data + "  ";
                Log.d("MainTest", "\n data_ " + data + "  ");

                toReturn += "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ";
                Log.d("MainTest", "\n entry_point_from_quick_compiled_code_" + entryPointFromQuickCompiledCode + "  ");

            }
        }


        return toReturn;
    }






}
