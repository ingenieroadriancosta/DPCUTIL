package Pointers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PTRFUNCT {
    private native static int       MallocPtr( int LenOfPtr );
    private native static boolean   FreePtr( int PtrDir );


    private native static int       LoadLibraryDLLN( int PtrDir );
    private native static boolean   FreeLibraryN( int PtrDirF );
    private native static int       FindWindowAN( int Ptr1, int Ptr2 );
    private native static int       GetActiveWindowN();
    private native static boolean   ShowWindowN( int HWnd, int WStyle);
    private native static int       GetAncestorN( int HWnd );
    private native static int       OpenFileDialogN( int HWnd, int Ptr_charFileName, int Ptr_Filter);

    private native static boolean   SetIconN( int HWnd, int Child_WindowT );

    public native static void       PruebasN( int PtrAny, char Str[] );

    public static void msgbox( String Texto, String Titulo){
        JOptionPane.showMessageDialog(null, Texto, Titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    public static int FindWindowA( String ClassName, String WindowTitle ){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return 0;
        }
        
        int LenStr1 = WindowTitle.length();
        CharPointers WindowTitle1 = null;
        WindowTitle1 = new CharPointers( LenStr1 );

        for( int i=0; i<LenStr1; i++ ){
            WindowTitle1.SetValue( (WindowTitle.charAt(i)), i);
        }
        int Hwnd = FindWindowAN( 0, WindowTitle1.Handle() );
        WindowTitle1.free();
        return (Hwnd);
    }

    public static int malloc(  int PtrDir, int LenOfPtr  ){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return 0;
        }
        int Resp = 0;
        Resp = MallocPtr( LenOfPtr );
        return Resp;
    }
    
    public static boolean free(  int PtrDir  ){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        return FreePtr( PtrDir );
    }



    public static int GetActiveWindow(){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return 0;
        }
        int Hwnd = GetActiveWindowN();
        return (Hwnd);
    }


    public static boolean ShowWindow( int HWnd, int WStyle){
        boolean Resp = false;
        Resp = ShowWindowN( HWnd, WStyle);
        return Resp;
    }

    public static boolean ShowWindow( JFrame MainFrame, int WStyle){
        boolean Resp = false;
        int HWnd = FindWindowA( null, MainFrame.getTitle() );
        Resp = ShowWindowN( HWnd, WStyle);
        return Resp;
    }

    public static int GetAncestor( int HWnd ){
        int Resp = 0;
        Resp = GetAncestorN( HWnd );
        return Resp;
    }











    
    JFrame OwnerFrameG;
    String FilterG;
    public String OpenFileDialog( JFrame OwnerFrame, String Filter ){
        OwnerFrameG = OwnerFrame;
        FilterG = Filter;
        if( OwnerFrame.getTitle().length()<=0 )
            OwnerFrame.setTitle( " \0 " );
        int HWnd = FindWindowA( null, OwnerFrame.getTitle() );
        CharPointers PTR_CHAR = new CharPointers( 1024 );
        CharPointers PTR_FILTER = new CharPointers( Filter );
        int LengthOf = OpenFileDialogN( HWnd, PTR_CHAR.Handle(), PTR_FILTER.Handle() );
        if( LengthOf>0 ){
            PTR_FILTER.free();
            String FileName = new String();
            char[] Chars = new char[LengthOf];
            for( int i=0; i<Chars.length; i++)
                Chars[i] = PTR_CHAR.GetValue(i);
            PTR_CHAR.free();
            FileName = String.copyValueOf(Chars);
            return FileName;
        }else{
            PTR_FILTER.free();
            PTR_CHAR.free();
            return null;
        }
        //*/
    }











    public static boolean SetIcon( int HWnd, String WTitle ){
        CharPointers CHARTITLE = new CharPointers( WTitle.length() );
        for( int i=0; i<WTitle.length(); i++ )
            CHARTITLE.SetValue( WTitle.charAt( i ), i);

        SetIconN( HWnd, CHARTITLE.Handle() );
        CHARTITLE.free();
        return true;
    }


    

}
