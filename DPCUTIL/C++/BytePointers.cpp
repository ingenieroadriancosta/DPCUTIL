#include "BytePointers.h" 


JNIEXPORT jbyte JNICALL Java_Pointers_BytePointers_GetValuePtr
  (JNIEnv * PEnv, jobject JObj, jint PTR_OWNER, jint PTR_POS){
          BYTE* BYTE_PTR = (BYTE*)PTR_OWNER;
          return BYTE_PTR[PTR_POS];
          }
  


JNIEXPORT jboolean JNICALL Java_Pointers_BytePointers_SetValuePtr
  (JNIEnv * PEnv, jobject JObj, jint PTR_OWNER, jint PTR_POS, jbyte INT_VALUE){
          BYTE* BYTE_PTR = (BYTE*)PTR_OWNER;
          BYTE_PTR[PTR_POS] = (255&INT_VALUE);
          return true;
          }
