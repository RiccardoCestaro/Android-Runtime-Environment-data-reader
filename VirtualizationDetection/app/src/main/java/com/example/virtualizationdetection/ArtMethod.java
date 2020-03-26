package com.example.virtualizationdetection;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;

public abstract  class ArtMethod {

    public Method associatedMethod;
    public long methodAddress;


    public ArtMethod(Method associatedMethod){
        this.associatedMethod = associatedMethod;
        this.methodAddress = LegendNative.getMethodAddress(associatedMethod);
    }

    public static ArtMethod of(Method method){
        switch (Build.VERSION.SDK_INT) {
            case 21:
                Log.d("ArtMethod","API21 - Android 5.0");
                return new ArtMethod_API21(method);
            case 22:
                Log.d("ArtMethod","API22 - Android 5.1");
                return new ArtMethod_API22(method);
            case 23:
                Log.d("ArtMethod","API23 - Android 6.0");
                return new ArtMethod_API23(method);
            case 24:
                Log.d("ArtMethod","API24 - Android 7.0");
                return new ArtMethod_API24_25(method);
            case 25:
                Log.d("ArtMethod","API25 - Android 7.1");
                return new ArtMethod_API24_25(method);
            case 26:
                Log.d("ArtMethod","API26 - Android 8.0.0");
                return new ArtMethod_API26_27(method);
            case 27:
                Log.d("ArtMethod","API27 - Android 8.1.0");
                return new ArtMethod_API26_27(method);
            case 28:
                Log.d("ArtMethod","API28 - Android 9");
                return new ArtMethod_API28(method);
            case 29:
                Log.d("ArtMethod","API29 - Android 10");
                return new ArtMethod_API29(method);
        }
        return null;
    }

    public abstract int getDexCodeItemOffset();

    @Override
    public String toString(){
        return "[ART-METHOD CLASS] -----> associatedMethod = " + associatedMethod.toString() + "methodAddress = " + methodAddress;
    }

}
