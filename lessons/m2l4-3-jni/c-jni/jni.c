#include "ru_otus_otuskotlin_interop_RustExample.h"

JNIEXPORT jint JNICALL Java_ru_otus_otuskotlin_interop_RustExample_rust_1add
  (JNIEnv *jni_env, jobject jo, jint a, jint b) {
  return a + b;
}
