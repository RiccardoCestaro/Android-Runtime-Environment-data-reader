/**
 *   Riccardo Cestaro 2020-3-15
 *
 *   API22 - Android 5.1 Lollipop
 *
 *   ArtMethod fields can be found in art_method.h file
 *
 *   art_method.h from lollipop-mr1-release url: https://android.googlesource.com/platform/art/+/refs/heads/lollipop-mr1-release/runtime/mirror/art_method.h
 *
 *   // Field order required by test "ValidateFieldOrderOfJavaCppUnionClasses".
 *   // The class we are a part of.
 *   HeapReference<Class> declaring_class_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    8        4    *   object start = 0 / object end = 8 (ArtMethod java object)
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   HeapReference<ObjectArray<ArtMethod>> dex_cache_resolved_methods_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   12        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   HeapReference<ObjectArray<Class>> dex_cache_resolved_types_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   16        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Access flags; low 16 bits are defined by spec.
 *   uint32_t access_flags_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   20        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Dex file fields. The defining dex file is available via declaring_class_->dex_cache_
 *   // Offset to the CodeItem.
 *   uint32_t dex_code_item_offset_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   24        4    *
 *   64 bit *   24        4    *
 *   ***************************
 *
 *   // Index into method_ids of the dex file associated with this method.
 *   uint32_t dex_method_index_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   28        4    *
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
 *   32 bit *   32        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Fake padding field gets inserted here.
 *   // Must be the last fields in the method.
 *   struct PACKED(4)PtrSizedFields{
 *   // Method dispatch from the interpreter invokes this pointer which may cause a bridge into
 *   // compiled code.
 *   void*entry_point_from_interpreter_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   36        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Pointer to JNI function registered to this method, or a function to resolve the JNI function.
 *   void*entry_point_from_jni_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   40        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from quick compiled code invokes this pointer which may cause bridging into
 *   // portable compiled code or the interpreter.
 *   void*entry_point_from_quick_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   44        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from portable compiled code invokes this pointer which may cause bridging
 *   // into quick compiled code or the interpreter. Last to simplify entrypoint logic.
 *   #if defined(ART_USE_PORTABLE_COMPILER)
 *   void*entry_point_from_portable_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   48        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   #endif
 *   }ptr_sized_fields_;
 *
 */

package com.example.virtualizationdetection;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ArtMethod_API22 extends ArtMethod{

    private StructMember declaring_class_;

    private StructMember dex_cache_resolved_methods_;

    private StructMember dex_cache_resolved_types_;

    private StructMember access_flags_;

    private StructMember dex_code_item_offset_;

    private StructMember dex_method_index_;

    private StructMember method_index_;

    private StructMember entry_point_from_interpreter_;

    private StructMember entry_point_from_jni_;

    private StructMember entry_point_from_quick_compiled_code_;

    private StructMember entry_point_from_portable_compiled_code_;

    public ArtMethod_API22(Method method){
        super(method);

        declaring_class_ = new StructMember(methodAddress, 8,4);

        dex_cache_resolved_methods_ = new StructMember(methodAddress, 12,4);

        dex_cache_resolved_types_ = new StructMember(methodAddress, 16,4);

        access_flags_ = new StructMember(methodAddress, 20,4);

        dex_code_item_offset_ = new StructMember(methodAddress,24,4);

        dex_method_index_ = new StructMember(methodAddress, 28,4);

        method_index_ = new StructMember(methodAddress, 32,4);

        entry_point_from_interpreter_ = new StructMember(methodAddress, 36,4);

        entry_point_from_jni_ = new StructMember(methodAddress, 40, 4);

        entry_point_from_quick_compiled_code_ = new StructMember(methodAddress,44,4);

        entry_point_from_portable_compiled_code_ = new StructMember(methodAddress, 48,4);
    }

    public int getDeclaringClass() { return declaring_class_.readInt(); }

    public int getDexCacheResolvedMethod() { return dex_cache_resolved_methods_.readInt(); }

    public int getDexCacheResolvedTypes() {return dex_cache_resolved_types_.readInt(); }

    public int getAccessFlags() { return access_flags_.readInt(); }

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

            Log.d("ArtMethod_API22","DexCodeItemOffset from java ArtMethod class: " + dexCodeItemOffset.get(srcArtMethod).toString());
        }catch(ClassNotFoundException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        return dex_code_item_offset_.readInt();
    }

    public int getDexMethodIndex() { return dex_method_index_.readInt(); }

    public int getMethodIndex() { return method_index_.readInt(); }

    public int getEntryPointFromInterpreter() { return entry_point_from_interpreter_.readInt(); }

    public int getEntryPointFromJni() { return entry_point_from_jni_.readInt(); }

    public int getEntryPointFromQuickCompiledCode() { return entry_point_from_quick_compiled_code_.readInt(); }

    public int getEntryPointFromPortableCompiledCode() { return entry_point_from_portable_compiled_code_.readInt(); }

}
