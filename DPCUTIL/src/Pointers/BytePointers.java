package Pointers;

import javax.swing.JOptionPane;

public class BytePointers{
    private int IdPtr = 0;
    private int LenPtr = 0;
    private native byte GetValuePtr( int PtrDir, int PosOfPtr );
    private native boolean SetValuePtr( int PtrDir, int PosOfPtr, byte ByVal );



    public boolean free(){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        PTRFUNCT.free( IdPtr );
        IdPtr = 0;
        LenPtr = 0;
        return true;
    }
    public boolean malloc( int LenP ){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        PTRFUNCT.free( IdPtr );
        IdPtr = PTRFUNCT.malloc( IdPtr, LenP );
        LenPtr = LenP;
        return true;
    }
    
    public byte GetValue(int Pos){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return 0;
        }
        if( Pos<LenPtr ){
            return GetValuePtr( IdPtr, Pos);
        }
        else{
            return 0;
        }
    }
    public boolean SetValue(byte Value, int Pos ){
        if( !InitClass.IsInitClass() ){
            JOptionPane.showMessageDialog(null,
                        "Para utilizar la libreria Pointers debe iniciar creando un manejador\n" +
                        "InitClass de la siguiente forma:\n" +
                        "InitClass MNGInitClass = new InitClass();",
                        "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        
        if( Pos>=LenPtr || IdPtr==0 ){
            return false;
        }
        SetValuePtr( IdPtr, Pos, Value );
        return true;
    }
    public int Len(){return LenPtr;}
    public int Handle(){return IdPtr;}
}
