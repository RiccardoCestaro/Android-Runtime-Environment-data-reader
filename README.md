# DexCodeItemOffset reader

## What is dex_code_item_offset_?

It is a field in ArtMethod. The value of this field is
defined by either the offset of the bytecode item of
this specific method within the DEX file where it is included,
or 0 if the method is either abstract or native.

This value changes in hooked methods.

## How to use

Simply write the class and the method where we are going to read 
the value.

Example:  android.app.Activity.startActivity(..) inspection

```java
            //GetStarted method inside MainTest class
            Class target = Class.forName("android.app.Activity");
            Method targetMethod = null;
            for ( Method method : target.getDeclaredMethods()) {
                if (method.getName().equals("startActivity") ){
                    targetMethod = method;
                    break;
                }
            }

```
