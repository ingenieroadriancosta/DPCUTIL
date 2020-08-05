#include "dpcutilC++.h"
#include "dpcutil.h"
#include "OTHERSFUNCTIONS.cpp"


void SInfo( void ){MessageBoxA( HWND(65656) , "Before." , "Info", 0);}







JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcInitN
  (JNIEnv * env, jobject obj, jint Perc){
          ERC perc = 0;//((ERC*)Perc);
          return DpcInit( &perc );
}












JNIEXPORT void JNICALL Java_Pointers_DPCUTIL_DpcTermN
  (JNIEnv * env, jobject obj){
          DpcTerm();
          }




JNIEXPORT void JNICALL Java_Pointers_DPCUTIL_DvmgStartConfigureDevicesN
  (JNIEnv * env, jobject obj, jint Hwnd, jint Perc){
          ERC* perc = ((ERC*)Perc);
          DWORD IdTh = 0;
          CreateThread( NULL , 0 , DvmgStartConfigureDevices_INCENTER , NULL , 0 , &IdTh );
          DvmgStartConfigureDevices( (HWND)Hwnd, perc );
          }
JNIEXPORT jint JNICALL Java_Pointers_DPCUTIL_DvmgGetDevCountN
(JNIEnv * env, jobject obj, jint Perc){
          ERC* perc = ((ERC*)Perc);
          return DvmgGetDevCount( perc );
          }
  
  
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcGetDpcVersionN
(JNIEnv * env, jobject obj, jint szVersion, jint Perc){
          char* Version = (char*)szVersion;
          ERC* perc = ((ERC*)Perc);
          bool RBool = DpcGetDpcVersion( Version, perc );
          
          //MessageBox( (HWND)65656, Version, "Version DPC", 0 );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DvmgGetDevNameN
(JNIEnv * env, jobject obj, jint idvc, jint szNameDev, jint Perc){
          char* Version = (char*)szNameDev;
          ERC* perc = ((ERC*)Perc);
          bool RBool = DvmgGetDevName( idvc, Version, perc );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcOpenDataN
(JNIEnv * env, jobject obj, jint Phif, jint SzDevName, jint Perc, jint Ptrid){
          char* Version = (char*)SzDevName;
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          HANDLE* phif = (HANDLE*)( Phif );
          bool RBool = DpcOpenData( phif, Version, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcCloseDataN
(JNIEnv * env, jobject obj, jint hif, jint Perc ){
          ERC* perc = ((ERC*)Perc);
          HANDLE phif = (HANDLE)( hif );
          bool RBool = DpcCloseData( phif, perc );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcPutRegN
  (JNIEnv * env, jobject obj, jint hif, jint bAddr, jint bData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          bool RBool = DpcPutReg( HANDLE(hif), bAddr, bData, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcGetRegN
(JNIEnv * env, jobject obj, jint hif, jint bAddr, jint PtrbData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          BYTE* pbData = (BYTE*)PtrbData;
          bool RBool = DpcGetReg( HANDLE(hif), bAddr, pbData, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcPutRegSetN
(JNIEnv * env, jobject obj, jint hif, jint PtrAddr, jint PtrData, jint cbData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          BYTE* rgbAddr = (BYTE*)PtrAddr;
          BYTE* rgbData = (BYTE*)PtrData;
          bool RBool = DpcPutRegSet( HANDLE(hif), rgbAddr, rgbData, cbData, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcGetRegSetN
(JNIEnv * env, jobject obj, jint hif, jint PtrAddr, jint PtrData, jint cbData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          BYTE* rgbAddr = (BYTE*)PtrAddr;
          BYTE* rgbData = (BYTE*)PtrData;
          bool RBool = DpcGetRegSet( HANDLE(hif), rgbAddr, rgbData, cbData, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcPutRegRepeatN
(JNIEnv * env, jobject obj, jint hif, jint bAddr, jint PtrData, jint cbData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          BYTE* rgbData = (BYTE*)PtrData;
          bool RBool = DpcPutRegRepeat( HANDLE(hif), bAddr, rgbData, cbData, perc, ptrid );
          return RBool;
          }
JNIEXPORT jboolean JNICALL Java_Pointers_DPCUTIL_DpcGetRegRepeatN
(JNIEnv * env, jobject obj, jint hif, jint bAddr, jint PtrData, jint cbData, jint Perc, jint Ptrid){
          ERC* perc = ((ERC*)Perc);
          TRID* ptrid = ((TRID*)Ptrid);
          BYTE* rgbData = (BYTE*)PtrData;
          bool RBool = DpcGetRegRepeat( HANDLE(hif), bAddr, rgbData, cbData, perc, ptrid );
          return RBool;
          }
