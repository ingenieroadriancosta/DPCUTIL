package Pointers;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
public class InitClass{
    private static boolean IsInit = false;
    private static String LibName =
            //System.getProperty( "java.home" ) +
            "E:\\ENTERPRISE\\JAVA\\EJERCICIOS\\DPCUTIL\\src\\RESOURCES\\LibPointers.dll";
    @SuppressWarnings("empty-statement")
    public InitClass(){
        if( IsInit )
            return;
        //IsInit = true;
        WriteFile();
        while( !IsInit );
        try{
            System.load( LibName );
        }
        catch( UnsatisfiedLinkError e1){
            JOptionPane.showMessageDialog(null,
                        "Error al cargar la libreria LibPointers.dll\n\n" +
                        e1.toString(),
                        "Error", JOptionPane.ERROR_MESSAGE );
            System.exit( -1 );
        }
        IsInit = true;
    }

    public static boolean IsInitClass(){
        return IsInit;
    }











    private final static String FileNamePrivate = new String( "LibPointers.dll" );
    private static boolean WriteFile(){
        if( IsInit )
            return true;
        String FileName = null;
        DataOutputStream dos = null;
        String PathJavaHome = System.getProperty( "user.dir" );
        //PathJavaHome = System.getProperty( "java.home" );
        LibName = new String( PathJavaHome + "\\" + FileNamePrivate );
        FileName = new String( PathJavaHome + "\\" + FileNamePrivate );
	try{
            dos = new DataOutputStream( new BufferedOutputStream(
			new FileOutputStream( FileName )   )  );
	}catch( java.io.IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
            System.exit( -1 );
            return false;
        }
////////////////////////////////////////////////////////////////////////////////
        int BytesAvailble = 0;
        InputStream inpustr = RESOURCES.RESOURCES.class.getResourceAsStream( FileNamePrivate );
        try {
            BytesAvailble = inpustr.available();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE );
            System.exit( -91 );
            return false;
        }
        byte[] BufferByte = new byte[BytesAvailble];
        try {
            int BytesReaded = 0;
            int Byte_List = 0;
            while( Byte_List<BytesAvailble ){
                 BytesReaded = inpustr.read( BufferByte );
                 dos.write(BufferByte, 0, BytesReaded);
                 Byte_List = Byte_List + BytesReaded;
             }
            dos.close();
            inpustr.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE );
            System.exit( -1 );
        //} catch (FileNotFoundException ex) {JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE );
        }
        IsInit = true;
        return true;
	}
    public static String GetFileName(){return LibName;}


}
