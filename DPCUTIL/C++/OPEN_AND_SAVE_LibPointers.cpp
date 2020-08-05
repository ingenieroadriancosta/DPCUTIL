char FileNameAux[40*1024];
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
int OpenFileAs( HWND HWnd, char* FileNameOpen, char* FilterOpen, char* TitleOpen )
{

OPENFILENAME  openFileDialogfunction = {0};
FileNameAux[0] = 0;
ZeroMemory(&openFileDialogfunction, sizeof(openFileDialogfunction));
openFileDialogfunction.lStructSize= sizeof(openFileDialogfunction);
openFileDialogfunction.hwndOwner = (HWND)HWnd;
openFileDialogfunction.nFilterIndex=3;
openFileDialogfunction.lpstrTitle=TitleOpen;
openFileDialogfunction.hInstance = NULL;
openFileDialogfunction.lpstrFilter = FilterOpen;
openFileDialogfunction.lpstrFile = FileNameOpen;
openFileDialogfunction.nMaxFile = MAX_PATH;
openFileDialogfunction.Flags = OFN_EXPLORER | OFN_FILEMUSTEXIST | OFN_ALLOWMULTISELECT;
openFileDialogfunction.lpstrDefExt = "";

ZeroMemory(&openFileDialogfunction, sizeof(openFileDialogfunction));
openFileDialogfunction.lStructSize= sizeof(openFileDialogfunction);

if(!GetOpenFileNameA(&openFileDialogfunction))
 return 0;
sprintf( FileNameOpen, "%s", FileNameAux);
return strlen( FileNameOpen );
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
int WINAPI SaveFileAs( char *FileName )
{
OPENFILENAME ofn;
ZeroMemory(&ofn, sizeof(ofn));
ofn.lStructSize = sizeof(ofn);
ofn.hwndOwner = VENTANA_PADRE;
ofn.nFilterIndex = 2;
ofn.lpstrFileTitle = NULL;
ofn.lpstrInitialDir= "C:";
ofn.lpstrFilter = "JPG Files (*.jpg*)\0*.jpg\0BMP Files (*.bmp*)\0*.bmp\0\0";
//  ofn.lpstrFilter = "JPG Files (*.jpg*)\0*.jpg\0\0";
ofn.lpstrFile = FileNameAux;
ofn.nMaxFile = MAX_PATH;
ofn.Flags = OFN_EXPLORER | OFN_PATHMUSTEXIST | OFN_HIDEREADONLY |
 OFN_OVERWRITEPROMPT ;
ofn.lpstrDefExt = ".bmp";
if( !GetSaveFileNameA(&ofn) )
    return 0;
sprintf( FileName, "%s", FileNameAux );

int LenStr = strlen( FileName )-1;

if( FileName[LenStr]=='g' || FileName[LenStr]=='G' )
    return 2;
return 1;
}





