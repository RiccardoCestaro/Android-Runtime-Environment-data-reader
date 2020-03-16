/**
 *   Riccardo Cestaro 2020-3-15
 *
 *   API28 - Android 9.0 Pie
 *
 *   ArtMethod fields can be found in art_method.h file
 *
 *   art_method.h from pie-release url: https://android.googlesource.com/platform/art/+/refs/heads/pie-release/runtime/art_method.h
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
 *   // Getting and setting this flag needs to be atomic when concurrency is
 *   // possible, e.g. after this method's class is linked. Such as when setting
 *   // verifier flags and single-implementation flag.
 *   std::atomic<std::uint32_t> access_flags_;
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
 *   // The hotness we measure for this method. Not atomic, as we allow
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
 *   struct PtrSizedFields{
 *   // Depending on the method type, the data is
 *   //   - native method: pointer to the JNI function registered to this method
 *   //                    or a function to resolve the JNI function,
 *   //   - conflict method: ImtConflictTable,
 *   //   - abstract/interface method: the single-implementation if any,
 *   //   - proxy method: the original interface method or constructor,
 *   //   - other methods: the profiling data.
 *   void*data_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   20        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   // Method dispatch from quick compiled code invokes this pointer which may cause bridging into
 *   // the interpreter.
 *   void*entry_point_from_quick_compiled_code_;
 *
 *   ***************************
 *          * offset * length  *
 *   32 bit *   24        4    *
 *   64 bit *                  *
 *   ***************************
 *
 *   }ptr_sized_fields_;
 *
 */


package com.example.virtualizationdetection;

import java.lang.reflect.Method;

public class ArtMethod_API28 extends ArtMethod{

    private StructMember dex_code_item_offset_;

    public ArtMethod_API28(Method method){
        super(method);

        dex_code_item_offset_ = new StructMember(methodAddress,8,4);

    }

    public int getDexCodeItemOffset() {
        return dex_code_item_offset_.readInt();
    }

}
