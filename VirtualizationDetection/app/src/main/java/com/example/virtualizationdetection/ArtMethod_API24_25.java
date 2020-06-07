/**
 *   Riccardo Cestaro 2020-3-15
 *
 *   API24 - Android 7.0 Nougat
 *   API25 - Android 7.1.2 Nougat
 *
 *   ArtMethod fields can be found in art_method.h file
 *
 *   art_method.h from nougat-release url: https://android.googlesource.com/platform/art/+/refs/heads/nougat-release/runtime/art_method.h
 *   art_method.h from nougat-mr2-release url: https://android.googlesource.com/platform/art/+/refs/heads/nougat-mr2-release/runtime/art_method.h
 *
 *   // Field order required by test "ValidateFieldOrderOfJavaCppUnionClasses".
 *   // The class we are a part of.
 *   GcRoot<mirror::Class> declaring_class_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    0        4    *
 *   64 bit *    0        4    *
 *   ***************************
 *
 *   // Access flags; low 16 bits are defined by spec.
 *   uint32_t access_flags_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    4        4    *
 *   64 bit *    4        4    *
 *   ***************************
 *
 *   // Dex file fields. The defining dex file is available via declaring_class_->dex_cache_
 *   // Offset to the CodeItem.
 *   uint32_t dex_code_item_offset_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *    8        4    *
 *   64 bit *    8        4    *
 *   ***************************
 *
 *   // Index into method_ids of the dex file associated with this method.
 *   uint32_t dex_method_index_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   12        4    *
 *   64 bit *   12        4    *
 *   ***************************
 *
 *   // End of dex file fields.
 *   // Entry within a dispatch table for this method. For static/direct methods the index is into
 *   // the declaringClass.directMethods, for virtual methods the vtable and for interface methods the
 *   // ifTable.
 *   uint16_t method_index_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   16        2    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // The hotness we measure for this method. Managed by the interpreter. Not atomic, as we allow
 *   // missing increments: if the method is hot, we will see it eventually.
 *   uint16_t hotness_count_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   18        2    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Fake padding field gets inserted here.
 *   // Must be the last fields in the method.
 *   // PACKED(4) is necessary for the correctness of
 *   // RoundUp(OFFSETOF_MEMBER(ArtMethod, ptr_sized_fields_), pointer_size).
 *   struct PACKED(4)PtrSizedFields{
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   ArtMethod**dex_cache_resolved_methods_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   20        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Short cuts to declaring_class_->dex_cache_ member for fast compiled code access.
 *   GcRoot<mirror::Class>*dex_cache_resolved_types_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   24        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Pointer to JNI function registered to this method, or a function to resolve the JNI function,
 *   // or the profiling data for non-native methods, or an ImtConflictTable.
 *   void*entry_point_from_jni_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   28        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from quick compiled code invokes this pointer which may cause bridging into
 *   // the interpreter.
 *   void*entry_point_from_quick_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   32        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   }ptr_sized_fields_;
 *
 */


package com.example.virtualizationdetection;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ArtMethod_API24_25 extends ArtMethod{

    private StructMember declaring_class_;

    private StructMember access_flags_;

    private StructMember dex_code_item_offset_;

    private StructMember dex_method_index_;

    private StructMember method_index_;

    private StructMember hotness_count_;

    private StructMember dex_cache_resolved_method_;

    private StructMember dex_cache_resolved_types_;

    private StructMember entry_point_from_jni_;

    private StructMember entry_point_from_quick_compiled_code_;


    public ArtMethod_API24_25(Method method){
        super(method);

        declaring_class_ = new StructMember(methodAddress, 0,4);

        access_flags_ = new StructMember(methodAddress, 4,4);

        dex_code_item_offset_ = new StructMember(methodAddress,8,4);

        dex_method_index_ = new StructMember(methodAddress, 12, 4);

        method_index_ = new StructMember(methodAddress, 16, 2);

        hotness_count_ = new StructMember(methodAddress, 18, 2);

        dex_cache_resolved_method_ = new StructMember(methodAddress, 20, 8);

        dex_cache_resolved_types_ = new StructMember(methodAddress, 28, 8);

        entry_point_from_jni_ = new StructMember(methodAddress, 36, 8);

        entry_point_from_quick_compiled_code_ = new StructMember(methodAddress, 44, 8);
    }

    @Override
    public int getDeclaringClass() { return declaring_class_.readInt(); }
    @Override
    public int getAccessFlags() {
        String accessFlagFromJava="";
        try {
            Class<?> abstractMethodClass = Class.forName("java.lang.reflect.AbstractMethod");
            //Class<?> artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
            //
            Field accessField = abstractMethodClass.getDeclaredField("accessFlags");
            accessField.setAccessible(true);

            //Field dexCodeItemOffset = artMethodClass.getDeclaredField("dexCodeItemOffset");
            Object accessFlag = accessField.get(associatedMethod);
            //dexCodeItemOffset.setAccessible(true);
            accessFlagFromJava = accessFlag.toString();

        }catch(ClassNotFoundException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        int accessFlags = access_flags_.readInt();
        if(!accessFlagFromJava.equals(Integer.toString(accessFlags)))
            Log.w("ArtMethod_API24_25","Fail to read accessFlag from the memory !!!");

        return access_flags_.readInt();
    }

    @Override
    public int getDexCodeItemOffset() {
        return dex_code_item_offset_.readInt();
    }
    @Override
    public int getDexMethodIndex() {


        String accessFlagFromJava="";
        try {
            Class<?> abstractMethodClass = Class.forName("java.lang.reflect.AbstractMethod");
            //Class<?> artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
            //
            Field accessField = abstractMethodClass.getDeclaredField("dexMethodIndex");
            accessField.setAccessible(true);

            //Field dexCodeItemOffset = artMethodClass.getDeclaredField("dexCodeItemOffset");
            Object accessFlag = accessField.get(associatedMethod);
            //dexCodeItemOffset.setAccessible(true);
            accessFlagFromJava = accessFlag.toString();


        }catch(ClassNotFoundException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

        int accessFlags = dex_method_index_.readShort();
        if(!accessFlagFromJava.equals(Integer.toString(accessFlags)))
            Log.w("ArtMethod_API24_25","Fail to read dexMethodIndex from the memory !!!    real: " + accessFlagFromJava + "   from memory: " + accessFlags);

        return accessFlags;
    }
    @Override
    public long getMethodIndex() {


        return method_index_.readShort(); }

    public int getHotnessCount() { return hotness_count_.readShort(); }

    public long getDexCacheResolvedMethod() { return dex_cache_resolved_method_.readLong(); }

    public int getDexCacheResolvedTypes() { return dex_cache_resolved_types_.readInt(); }

    public int getEntryPointFromJni() { return entry_point_from_jni_.readInt(); }

    public int getEntryPointFromQuickCompiledCode() { return entry_point_from_quick_compiled_code_.readInt(); }


}
