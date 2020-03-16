LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := memory
LOCAL_SRC_FILES := src/main/native/memory.c

LOCAL_LDLIBS  := -llog


include $(BUILD_SHARED_LIBRARY)