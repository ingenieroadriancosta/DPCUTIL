#include "CharPointers.h"
JNIEXPORT jchar JNICALL Java_Pointers_CharPointers_GetValuePtr
  (JNIEnv * Env, jobject JObj, jint OWNER_PTR, jint POS_PTR){
          char* CHAR_PTR = (char*)OWNER_PTR;
          return CHAR_PTR[POS_PTR];
          }


JNIEXPORT jboolean JNICALL Java_Pointers_CharPointers_SetValuePtr
  (JNIEnv * Env, jobject JObj, jint OWNER_PTR, jint POS_PTR, jchar CHAR_VALUE){
          char* CHAR_PTR = (char*)OWNER_PTR;
          CHAR_PTR[POS_PTR] = (255&CHAR_VALUE);
          return true;
          }
