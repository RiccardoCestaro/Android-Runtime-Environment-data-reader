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
 *   64 bit *                  *
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
 *   64 bit *                  *
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

import java.lang.reflect.Method;

public class ArtMethod_API24_25 extends ArtMethod{

    private StructMember dex_code_item_offset_;

    public ArtMethod_API24_25(Method method){
        super(method);

        dex_code_item_offset_ = new StructMember(methodAddress,8,4);
    }

    public int getDexCodeItemOffset() {
        return dex_code_item_offset_.readInt();
    }


}
