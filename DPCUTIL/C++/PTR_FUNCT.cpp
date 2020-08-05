#include "PTRFUNCT.h"
#include "OPEN_AND_SAVE_LibPointers.cpp"


JNIEXPORT jint JNICALL Java_Pointers_PTRFUNCT_MallocPtr
  (JNIEnv * Env, jclass JClass, jint SizePtr){
          unsigned char* PTR = (unsigned char*)malloc( 8*SizePtr );
          if( !PTR )
              MessageBox( NULL, "Bad Malloc.", "Error", 16 );
              int* iptr = (int*)PTR;
          return (DWORD)&iptr[0];
          }
JNIEXPORT jboolean JNICALL Java_Pointers_PTRFUNCT_FreePtr
  (JNIEnv * Env, jclass JClass, jint OwnerPtr){
          int* PTR = (int*)OwnerPtr;
          free( (void*)PTR );
          return true;
          }


JNIEXPORT jint JNICALL Java_Pointers_PTRFUNCT_FindWindowAN
  (JNIEnv * Env, jclass JObj, jint ClassName_Ptr, jint WindowTitlePtr){
          char* ClassNameW = (char*)ClassName_Ptr;
          char* WindowTitleA = (char*)WindowTitlePtr;
          HWND HWnd = FindWindowA( ClassNameW, WindowTitleA);
          return (int)(HWnd);
          }
  
JNIEXPORT jint JNICALL Java_Pointers_PTRFUNCT_GetActiveWindowN
  (JNIEnv * Env, jclass JClass){
          int Resp = (int)GetActiveWindow();
          return Resp;
          }




JNIEXPORT jboolean JNICALL Java_Pointers_PTRFUNCT_ShowWindowN
  (JNIEnv * Env, jclass JClass, jint INT_HWnd, jint WStyle){
          HWND HWnd = HWND(INT_HWnd);
          if( !HWnd )
              HWnd = GetActiveWindow();
          bool Resp = ShowWindow( HWnd, WStyle);
          return Resp;
          }














DWORD WINAPI ThreadPRB( LPVOID pv=NULL )
{
//MessageBoxA( VENTANA_PADRE, " ThreadPRB ", " Info ", 0);
char Textt[256];
OpenFileAs( VENTANA_PADRE, Textt, " ", " Abrir ");
return 0;
}
JNIEXPORT jint JNICALL Java_Pointers_PTRFUNCT_OpenFileDialogN
  (JNIEnv* Env, jclass JClass, jint INT_HWnd, jint CHAR_PTR_FileName, jint CHAR_PTR_Filter){
          char* FileNamePTR = (char*)CHAR_PTR_FileName;
          char* FilterPTR = (char*)CHAR_PTR_Filter;
          FileNamePTR[0] = 0;
          VENTANA_PADRE = (HWND)INT_HWnd;
          
          char TitleHWnd[256];
          ZeroMemory( TitleHWnd, 256 );
          char TitleOpen[280] = "Abrir - ";
          ZeroMemory( TitleOpen, 280 );
          GetWindowTextA( (HWND)INT_HWnd, TitleHWnd, 256 );
          sprintf( TitleOpen, "Abrir - %s", TitleHWnd);
          
          
          DWORD IdTh = 0;
          CreateThread( NULL, 0, ThreadPRB, NULL, 0, &IdTh );
          OpenFileAs( (HWND)INT_HWnd, FileNamePTR, FilterPTR, TitleOpen);
          
          
          int Resp = strlen( FileNamePTR );
          return Resp;
          }










HWND HWndDLG = NULL;
char* WWTitle = NULL;
DWORD WINAPI FindAndSetDlgWindow( LPVOID pv=NULL )
{
//MessageBoxA( HWndDLG, WWTitle, "", 0 );
tic
HICON hIcon;
hIcon = (HICON)SendMessage( HWndDLG, WM_GETICON , ICON_SMALL , (LPARAM)0 );
hIcon = (HICON)SendMessage( HWndDLG, WM_GETICON , ICON_BIG , (LPARAM)0 );
HWND HWCHILD = FindWindowA( NULL, WWTitle );
while( !HWCHILD && toc<2 )
    HWCHILD = FindWindowA( NULL, WWTitle );
HWCHILD = FindWindowA( NULL, WWTitle );
Sleep( 100 );
SendMessage( HWCHILD, WM_SETICON , ICON_SMALL , (LPARAM)hIcon );
Sleep( 100 );
SendMessage( HWCHILD, WM_SETICON , ICON_SMALL , (LPARAM)hIcon );
InvalidateRect( HWCHILD, NULL, true);
UpdateWindow( HWCHILD );
return 0;
}

JNIEXPORT jboolean JNICALL Java_Pointers_PTRFUNCT_SetIconN
  (JNIEnv * Env, jclass JClass, jint INT_HWnd, jint CHAR_PTR){
          HWndDLG = (HWND)INT_HWnd;
          WWTitle = (char*)CHAR_PTR;
          DWORD IdTh = 0;
          CreateThread( NULL, 0, FindAndSetDlgWindow, NULL, 0, &IdTh );
          return true;
          }









jchar * (JNICALL *GetCharArrayElements)
      (JNIEnv *env, jcharArray array, jboolean *isCopy);
/*
jchar * GetCharArrayElements(jcharArray array, jboolean *isCopy) {
        return functions->GetCharArrayElements(this,array,isCopy);
    }
//*/

bool IsMeximized = false;
int  WWW_MAIN_WINDOW = 0;
int  HHH_MAIN_WINDOW = 0;
int  XXX_MAIN_WINDOW = 0;
int  YYY_MAIN_WINDOW = 0;
//JNIEXPORT void JNICALL Java_Pointers_PTRFUNCT_PruebasN
//(JNIEnv * Env, jclass JClass, jint INT_PTR){
JNIEXPORT void JNICALL Java_Pointers_PTRFUNCT_PruebasN
  (JNIEnv * Env, jclass JClass, jint INT_PTR, jcharArray JChar){
//  (JNIEnv * Env, jclass JClass, jint INT_PTR , jstring JStr){
//  (JNIEnv* Env, jclass JClass, jint INT_PTR){
          //if( OpenFileAs( (HWND)INT_PTR, FileNameAux) )MessageBoxA( (HWND)INT_PTR, FileNameAux, "", 0 );
          //maximizar()
          if( !IsMeximized ){
              WindowSize( (HWND)INT_PTR, WWW_MAIN_WINDOW, HHH_MAIN_WINDOW );
              WindowPos( (HWND)INT_PTR, XXX_MAIN_WINDOW, YYY_MAIN_WINDOW );
              
              WWW_MAIN_WINDOW = WWW_MAIN_WINDOW + 16;
              HHH_MAIN_WINDOW = HHH_MAIN_WINDOW + 38;
              
              XXX_MAIN_WINDOW = XXX_MAIN_WINDOW - 8;
              YYY_MAIN_WINDOW = YYY_MAIN_WINDOW - 30;
              
              SetWindowLong( (HWND)INT_PTR , GWL_STYLE , WS_POPUP );
              ShowWindow( (HWND)INT_PTR, SW_MAXIMIZE );
              ShowWindow( (HWND)INT_PTR, SW_MAXIMIZE );
              }else{
                    SetWindowLong( (HWND)INT_PTR , GWL_STYLE , (WS_OVERLAPPEDWINDOW) - (WS_MAXIMIZEBOX) );
                    ShowWindow( (HWND)INT_PTR, 1 );
                    MoveWindow( (HWND)INT_PTR, XXX_MAIN_WINDOW, YYY_MAIN_WINDOW, WWW_MAIN_WINDOW, HHH_MAIN_WINDOW, true);
                    ShowWindow( (HWND)INT_PTR, 1 );
                    }
          IsMeximized = !IsMeximized;
          //*/
          }













