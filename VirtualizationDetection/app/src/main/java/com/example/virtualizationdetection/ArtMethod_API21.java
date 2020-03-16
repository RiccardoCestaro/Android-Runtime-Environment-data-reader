/**
 *   Riccardo Cestaro 2020-3-15
 *
 *   API21 - Android 5.0 Lollipop
 *
 *   ArtMethod fields can be found in art_method.h file
 *
 *   art_method.h from lollipop-release url: https://android.googlesource.com/platform/art/+/refs/heads/lollipop-release/runtime/mirror/art_method.h
 *
 *   // Field order required by test "ValidateFieldOrderOfJavaCppUnionClasses".
 *   // The class we are a part of.
 *   HeapReference<Class> declaring_class_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    0        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   HeapReference<ObjectArray<ArtMethod>> dex_cache_resolved_methods_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    4        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   HeapReference<ObjectArray<Class>> dex_cache_resolved_types_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    8        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   HeapReference<ObjectArray<String>> dex_cache_strings_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   12        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from the interpreter invokes this pointer which may cause a bridge into
 *   // compiled code.
 *   uint64_t entry_point_from_interpreter_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   16        8    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Pointer to JNI function registered to this method, or a function to resolve the JNI function.
 *   uint64_t entry_point_from_jni_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   24        8    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from portable compiled code invokes this pointer which may cause bridging into
 *   // quick compiled code or the interpreter.
 * #if defined(ART_USE_PORTABLE_COMPILER)
 *   uint64_t entry_point_from_portable_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   32        8    *
 *   64 bit *                  *
 *   ***************************
 *
 * #endif
 *   // Method dispatch from quick compiled code invokes this pointer which may cause bridging into
 *   // portable compiled code or the interpreter.
 *   uint64_t entry_point_from_quick_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   40        8    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Pointer to a data structure created by the compiler and used by the garbage collector to
 *   // determine which registers hold live references to objects within the heap. Keyed by native PC
 *   // offsets for the quick compiler and dex PCs for the portable.
 *   uint64_t gc_map_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   48        8    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Access flags; low 16 bits are defined by spec.
 *   uint32_t access_flags_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   56        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   / Dex file fields. The defining dex file is available via declaring_class_->dex_cache_
 *   // Offset to the CodeItem.
 *   uint32_t dex_code_item_offset_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   60        4    *
 *   64 bit *   60        4    *
 *   ***************************
 *
 *   // Index into method_ids of the dex file associated with this method.
 *   uint32_t dex_method_index_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   72        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // End of dex file fields.
 *   // Entry within a dispatch table for this method. For static/direct methods the index is into
 *   // the declaringClass.directMethods, for virtual methods the vtable and for interface methods the
 *   // ifTable.
 *   uint32_t method_index_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   76        4    *
 *   64 bit *                  *
 *   ***************************
 *
 */


package com.example.virtualizationdetection;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ArtMethod_API21 extends ArtMethod{

    private StructMember dex_code_item_offset_;

    public ArtMethod_API21(Method method){
        super(method);

        dex_code_item_offset_ = new StructMember(methodAddress,60,4);
    }

    public int getDexCodeItemOffset() {
        try {
            Class<?> abstractMethodClass = Class.forName("java.lang.reflect.AbstractMethod");
            Class<?> artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
            //
            Field artMethodField = abstractMethodClass.getDeclaredField("artMethod");
            artMethodField.setAccessible(true);
            Field dexCodeItemOffset = artMethodClass.getDeclaredField("dexCodeItemOffset");
            Object srcArtMethod = artMethodField.get(associatedMethod);
            dexCodeItemOffset.setAccessible(true);

            Log.d("ArtMethod_API21","DexCodeItemOffset from java ArtMethod class: " + dexCodeItemOffset.get(srcArtMethod).toString());
        }catch(ClassNotFoundException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        return dex_code_item_offset_.readInt();
    }

}
