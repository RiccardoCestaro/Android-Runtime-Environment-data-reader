package com.example.virtualizationdetection;

/**
 * @author Lody
 * @version 1.0
 */
public class Runtime {

    private static Boolean gIS_64_BIT = null;
    private static Boolean gIS_LITTLE_ENDIAN = null;


    public static boolean is64Bit() {
        if (gIS_64_BIT == null)
            try {
                gIS_64_BIT = (Boolean) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("is64Bit").invoke(Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getRuntime").invoke(null));
            } catch (Exception e) {
                gIS_64_BIT = Boolean.FALSE;
            }
        return gIS_64_BIT;
    }

    public static boolean isLittleEndian() {
        if (gIS_LITTLE_ENDIAN == null) {
            gIS_LITTLE_ENDIAN = LegendNative.isLittleEndian();
        }
        return gIS_LITTLE_ENDIAN;
    }

}
