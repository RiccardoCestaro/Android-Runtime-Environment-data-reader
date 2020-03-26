/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainTest {

    public MainTest(){}

    public void test(){ Log.d("MainTest","Default test");}

    public String getStarted() {
        try {


            Class target = Class.forName("android.app.Activity");
            //Method targetMethod = target.getDeclaredMethod("");
            ArrayList<Method> list = new ArrayList<>();
            for ( Method method : target.getDeclaredMethods()) {
                method.setAccessible(true);
                //if (method.getName().equals("startActivity") ){
                    list.add(method);
                //}
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

            return toReturn;

        } catch (ClassNotFoundException e) {
         return e.getLocalizedMessage();
        }
    }
}
