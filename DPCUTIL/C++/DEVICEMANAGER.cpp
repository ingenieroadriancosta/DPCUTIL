#include <time.h>
ERC percGlobal = 9;
char DigilentWindowName[] = "DPCOMM Device Table Manager 2.4.1";
#include "LAUNCH.h"


char* FATHERWINDOWTITLE = NULL;
int ShowStyle = 9;
bool SetVisibleClone = false;
HWND DVCMNG = NULL;
HWND DVCMNG_PARENT = NULL;

bool  FindNow = false;
DWORD WINAPI LaunchDVMNGCONF( LPVOID pv=NULL ){
    DVCMNG_PARENT = CreateWindowEx (
           0,
           "STATIC",
           " STATIC ANCESTOR - 0",
           WS_POPUP,
           0,
           0,
           544,
           375,
           HWND_DESKTOP,
           NULL,
           NULL,
           NULL
           );
    ShowWindow( DVCMNG_PARENT, ShowStyle );
    FindNow = true;
    DvmgStartConfigureDevices( DVCMNG_PARENT, &percGlobal );
    DestroyWindow( DVCMNG_PARENT );
    SetVisibleClone = false;
      }
DWORD WINAPI ThreadFunctionFindWindow( LPVOID pv=NULL )
{
SetVisibleClone = false;
int wParamThread = 0;
DVCMNG = NULL;
//MessageBoxA( NULL, " W Find. ", " Info " , 0);
while( FindNow==false )Sleep( 1 );
FindNow = false;
double ENDTOCT = clock();
while( DVCMNG==NULL && ( (clock()-ENDTOCT)/1000.0<2.0 ) ){
       DVCMNG = FindWindowA( NULL, DigilentWindowName );
       if( GetParent(DVCMNG)==DVCMNG_PARENT ){
           ShowWindow( DVCMNG, ShowStyle );
           break;
           }
       else{
            DVCMNG = NULL;
            }
       }
if( !DVCMNG )
    return 0;
ShowWindow( DVCMNG, ShowStyle );
SetWindowTextA( DVCMNG, " DPCWINDOWJAVA " );
ENDTOCT = clock();
for( int i=0; i<8; i++ ){
     if( ShowStyle==0 ){
         MoveWindow( DVCMNG, -1000, -1000, 300, 300, true );
         ShowWindow( DVCMNG, ShowStyle );
     }else{ShowWindow( DVCMNG, ShowStyle );}
}
//SetParent( DVCMNG, DVCMNG_PARENT );
//ShowWindow( DVCMNG_PARENT, 1 );
SetVisibleClone = true;
//MessageBoxA( NULL, " W Encontrada. ", " Info " , 0);
return 0;
}
JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_DvmgStartConfigureDevicesN
  (JNIEnv* env, jobject obj, jint PtrCharTitle, jint ShStyle){
          //ShowWindow( GetActiveWindow(), 3 );
          if( SetVisibleClone ){
              ShowWindow( DVCMNG, 0);
              PostMessage( DVCMNG, WM_CLOSE, WPARAM(0), LPARAM(0) );
              PostMessage( DVCMNG, WM_DESTROY, WPARAM(0), LPARAM(0) );
              ShowWindow( DVCMNG_PARENT, 0);
              PostMessage( DVCMNG_PARENT, WM_CLOSE, WPARAM(0), LPARAM(0) );
              PostMessage( DVCMNG_PARENT, WM_DESTROY, WPARAM(0), LPARAM(0) );
              SetVisibleClone = false;
              return -1;
              }
          SetVisibleClone = false;
          DWORD IdTh = 9;
          ShowStyle = ShStyle;
          //  VENTANA_PADRE = (HWND)PtrCharTitle;
          CreateThread( NULL , 0 , ThreadFunctionFindWindow , NULL , 0 , &IdTh );
          CreateThread( NULL , 0 , LaunchDVMNGCONF , NULL , 0 , &IdTh );
          //while( !SetVisibleClone )Sleep( 10 );
          return 0;
          }


JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_DvmgStartConfigureDevicesNClose
  (JNIEnv* env, jobject obj){
           //HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           SendMessageA( DVCMNG, WM_CLOSE, 0, 0);
           SendMessageA( DVCMNG, WM_DESTROY, 0, 0);
           while( IsWindow(DVCMNG) ){
               SendMessageA( DVCMNG, WM_CLOSE, 0, 0);
               SendMessageA( DVCMNG, WM_DESTROY, 0, 0);
               //DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
               }
           //Sleep( 50 );
           //MessageBoxA( FindWindowA( NULL, ""), "", "", 0 );
           return percGlobal;
           }
  
JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_DvmgGetDevCountN
  (JNIEnv* env, jobject obj){
          ERC perc = 9;
          return DvmgGetDevCount( &perc );
          }



JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_FreePtrChar
  (JNIEnv * env, jobject obj, jint PtrDir ){
          char* OldChar = (char*)PtrDir;
          free( OldChar );
          return 0;
          }
JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_MallocPtrChar
  (JNIEnv* env, jobject obj, jint PtrDir, jint LenOfChar){
          char* NewChar = (char*)PtrDir;
          NewChar = (char*)malloc( 8*LenOfChar );
          return (int(NewChar));
          }
          
          
          
  
  


JNIEXPORT jboolean JNICALL Java_Pointers_LAUNCH_DvmgGetDevNameN
  (JNIEnv* env, jobject obj, jint IndexDev, jint PtrChar){
           char* DeviceName = (char*)PtrChar;
           ERC perc = 9;
           DvmgGetDevName( IndexDev, DeviceName, &perc );
           return perc;
           }






HANDLE EventControls = NULL;
bool SetError = false;
DWORD WINAPI ThreadFHide( LPVOID pv=NULL )
{
double ENDTOCPP = clock();
while( (clock()-ENDTOCPP)/1000<2 ){
   if( FindWindowA( NULL, "Error" )!=NULL ){
       HWND HwndError = FindWindowA( NULL, "Error" );
       if( GetParent(HwndError)==DVCMNG ){
           //MessageBoxA( HwndError, "", "", 0 );
           ShowWindow( HwndError, 0);
           PostMessage( HwndError, WM_CLOSE, WPARAM(0), LPARAM(0) );
           PostMessage( HwndError, WM_DESTROY, WPARAM(0), LPARAM(0) );
           SetError = true;
           break;
           }
       
       }
}
SetEvent( EventControls );
}
JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_SendComand
  (JNIEnv* env, jobject obj, jint uMsg, jint wParam, jint lParam){
           //HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           if( wParam==1030 ){
               SetError = false;
               DWORD IdTh = 9;
               if( EventControls==NULL )
                   EventControls = CreateEvent(0, FALSE, FALSE, 0);
               CreateThread( NULL , 0 , ThreadFHide , NULL , 0 , &IdTh );
               PostMessageA( DVCMNG, uMsg, wParam, LPARAM(lParam) );
               WaitForSingleObject( EventControls, INFINITE);
               if( SetError==true )
                   return 1;
               else
                   return 0;
               }
           else
               return (SendMessageA( DVCMNG, uMsg, wParam, LPARAM(lParam) ));
           }
JNIEXPORT jint JNICALL Java_Pointers_LAUNCH_SendDlgItemComand
  (JNIEnv* env, jobject obj, jint Item, jint uMsg, jint wParam, jint lParam){
           //HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           
           //SendMessageA( DVCMNG, WM_COMMAND, MAKELONG(1001,LBN_SETFOCUS), LPARAM(0) );
           //SendMessageA( DVCMNG, WM_COMMAND, MAKELONG(1001,LB_SETCURSEL), LPARAM(lParam) );
           if( uMsg==WM_ENABLE ){
               if( !IsWindow(DVCMNG) )
                   return -1;
               return int(IsWindowEnabled( GetDlgItem( DVCMNG, Item)));
               }
           int IRes = SendDlgItemMessageA( DVCMNG, Item, UINT(uMsg), WPARAM(wParam), LPARAM(lParam) );
           if( Item==1001 || Item==1006)
               SendMessage( DVCMNG, WM_COMMAND, MAKELONG(Item,1), LPARAM(0) );
           return (IRes); 
           }





JNIEXPORT jchar JNICALL Java_Pointers_LAUNCH_GetValuePtr
  (JNIEnv *, jobject, jint PtrChar , jint IndexValue){
          char* DeviceName = (char*)PtrChar;
          return (DeviceName[IndexValue]);
          }
JNIEXPORT jboolean JNICALL Java_Pointers_LAUNCH_SetValuePtr
  (JNIEnv* env, jobject obj, jint PtrDir, jint PtrPos, jchar PtrValue){
           if( PtrDir!=0 ){char* CharTemp = (char*)PtrDir;CharTemp[PtrPos] = PtrValue;return true;}
           else{return false;}
           }
           
           
           
           
           
           
           
           
           
           
          
          
          
          
          

  
  
  
