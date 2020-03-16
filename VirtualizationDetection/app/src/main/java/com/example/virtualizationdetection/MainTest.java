/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainTest {

    public MainTest(){}

    public void test(){ Log.d("MainTest","Default test");}

    public void getStarted() {
        try {

            Class target = this.getClass();
            Method targetMethod = target.getDeclaredMethod("test");
            ArtMethod artMethod = ArtMethod.of(targetMethod);
            if (artMethod == null) {
                Log.d("MainTest", "Method not found!");
                return;
            }
            Log.d("MainTest", artMethod.toString());
            Log.d("MainTest", "dex_code_item_offset_=  " + artMethod.getDexCodeItemOffset());

            if(Build.VERSION.SDK_INT < 26 && Build.VERSION.SDK_INT > 22)
                Log.d("MainTest", "access_flags_=  " + artMethod.getAccessFlags());

            if(Build.VERSION.SDK_INT < 26) {
                Field field = Class.forName("java.lang.reflect.AbstractMethod").getDeclaredField("accessFlags");
                Log.d("MainTest", "Read");
                field.setAccessible(true);
                Object val = field.get(artMethod.associatedMethod);
                Log.d("MainTest", "Reading field: =" + val + " from " + artMethod.associatedMethod);
            }

        } catch (NoSuchMethodException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
