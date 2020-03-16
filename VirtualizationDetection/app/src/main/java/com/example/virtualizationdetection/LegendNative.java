package com.example.virtualizationdetection;

import android.util.Log;

import java.lang.reflect.Method;

/**
 *
 * Legend native helper functions.
 *
 * NOTICE: Don't edit class name of this class.
 *
 * @author Lody
 * @version 1.0
 */
public final class LegendNative {

    static {
        System.loadLibrary("legend");
    }


    public static byte[] read(long address, int size){
        Log.d("#######","Read Memory to 0x" + Long.toHexString(address));
        return memget(address,size);
    }



    public static native void  memcpy(long dest, long src, int size);

    public static native void memput(long dest, byte[] data);

    public static native byte[] memget(long address, int size);

    public static native void munprotect(long address, int size);

    public static native long malloc(int length);

    public static native void free(long pointer, int length);

    public static native long getMethodAddress(Method method);

    public static native long getObjectAddress(Object object);

    public static native boolean isLittleEndian();

    public static native long getPointer(long address);

    public static native int getCharArrayLength(long address, int limit);

    public static int getCharArrayLength(long address) {
        return getCharArrayLength(address,-1);
    }

}
