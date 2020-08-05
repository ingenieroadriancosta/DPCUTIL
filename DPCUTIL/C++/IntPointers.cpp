#include "IntPointers.h"
JNIEXPORT jint JNICALL Java_Pointers_IntPointers_GetValuePtr
  (JNIEnv * PEnv, jobject JObj, jint PTR_OWNER, jint PTR_POS){
          int* INT_PTR = (int*)PTR_OWNER;
          return INT_PTR[PTR_POS];
          }
JNIEXPORT jboolean JNICALL Java_Pointers_IntPointers_SetValuePtr
  (JNIEnv * Env, jobject JObj, jint PTR_OWNER, jint PTR_POS, jint INT_VALUE){
          int* INT_PTR = (int*)PTR_OWNER;
          INT_PTR[PTR_POS] = INT_VALUE;
          return true;
          }
