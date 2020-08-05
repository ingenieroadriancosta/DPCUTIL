#define sleep                   Sleep
#define msgc                    printf
#define Image( H_B, H_W )       SendMessage( H_W, STM_SETIMAGE, IMAGE_BITMAP, (LPARAM)H_B)
#define RETURN                  }
#define FUNCTION                void
#define IF                      if(
#define THEN                    ){
#define ELSE                    }else{
#define ELSIF                   }else if(
#define ENDIF                   }
#define endwhile                }
#define endfor                  }
#define FOR                     for(
#define WHILE(definebools)      while(definebools){
#define LOOP                    ){
#define ENDLOOP                 }
#define BREAK                   break
#define ENDCASE                 }
#define END                     }
#define ENDWHILE                }
#define ENDCONTROLSCOMAND       break;}break;
#define ENDWPROC                return 0;}
#define ENDMESSAGES             }
#define ENDMAINWINDOW           return 0;}
#define MESSAGES                switch( message ){
#define WPROC                   LRESULT CALLBACK WindowProcedure (HWND hwnd, UINT  message, WPARAM wParam, LPARAM lParam){
#define CONTROLS_COMAND         case WM_COMMAND: switch( LOWORD(wParam) ){
#define MAINWINDOW              int WINAPI WinMain (HINSTANCE hThisInstance, HINSTANCE hPrevInstance, LPSTR lpszArgument, int nFunsterStil){
#define CASE                    switch(
#define IS                      ){
#define WHEN(id)                break;case id:
#define DEFAULT                 break;default:
#define GET_BIT(var,pos)        ( (((var) & (1<<(pos))))/( 1<<(pos)) )
#define CHECK_BIT(var,pos)      ( (((var) & (1<<(pos))))/( 1<<(pos)) )
#define CHECK_BITM(var,pos)     ( (((var) & (1<<(pos))))/( 1<<(pos)) )
#define SizeOf(T)               sizeof(T)/sizeof(T[0])
#define BitesOf(T)              sizeof(T[0])
//#define FILLVAR(VAR)            for( int i=0; i<SizeOf(VAR); i++ )VAR[i] = i;
#define KILOBYTES               1024
#define KBs                     KILOBYTES
#define MEGABYTES               1048576
#define MBs                     MEGABYTES
#define MAX_UINT                2147483647
#define MSGBOX                  msgbox
#define pi                      3.141592//653589793
//*/

