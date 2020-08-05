#include <time.h>
//MTIME///////////////////////// T I M E___S E R I E S /////////////////////////
double TIME_SERIES_CTIC;//VARIABLES GLOBALES DE TIEMPO.
double TIME_SERIES_CTOC;//VARIABLES GLOBALES DE TIEMPO.
                                       //
inline double tic()   //TIC -TIC-TIC -TIC-TIC -TIC-TIC -TIC-TIC -TIC-TIC -TIC-TIC " TIC "
{//INICIA LA VARIABLE DE TEIMPO GLOBAL adrianti.
     TIME_SERIES_CTIC=clock();
     return(TIME_SERIES_CTIC);
}//END TIC.
                                       //
inline double toc()  //TOC -TOC-TOC -TOC-TOC -TOC-TOC -TOC-TOC -TOC-TOC -TOC-TOC " TOC "
 {//RETORNA EL TIEMPO TRANSCURRIDO DESDE QUE SE LLAMÓ LA FUNCION TOC.
      TIME_SERIES_CTOC=(clock()-TIME_SERIES_CTIC)/1000;
      return(TIME_SERIES_CTOC);
 }//END TOC.
#define tic                    tic();
#define toc                    toc()


inline void WindowPos(HWND hwnd, int &x,int &y)
{
POINT pos;
pos.x=0;
pos.y=0;
ClientToScreen(hwnd, &pos);
//GetWindowPos(hwnd, pos);
x=pos.x;
y=pos.y;
}

inline void WindowSize(HWND hwnd, int &w , int &h)
{
RECT re;
GetClientRect(hwnd, &re);
w=re.right;
h=re.bottom;
}
DWORD WINAPI DvmgStartConfigureDevices_INCENTER( LPVOID pv=NULL )
{
HWND HWDVCMNG = NULL;
tic
while( FindWindowA( NULL, "DPCOMM Device Table Manager 2.4.1" )==NULL && toc<2 );
HWDVCMNG = FindWindowA( NULL, "DPCOMM Device Table Manager 2.4.1" );
int WWW = 9;
int HHH = 9;
WindowSize( HWDVCMNG, WWW, HHH );
int CXSCREEN = GetSystemMetrics( SM_CXSCREEN );
int CYSCREEN = GetSystemMetrics( SM_CYSCREEN );
int NewX = (WWW+CXSCREEN)/2 - WWW;
int NewY = (HHH+CYSCREEN)/2 - HHH;
SetWindowPos( HWDVCMNG, HWND_NOTOPMOST, NewX, NewY, 300, 300, SWP_NOSIZE );
return 0;
}
