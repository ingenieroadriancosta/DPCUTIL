package Pointers;
import javax.swing.JFrame;
public class DPCUTIL {
    private native boolean DpcInitN( int PtrErcDpcInit );
    private native void DpcTermN();
    private native void DvmgStartConfigureDevicesN( int Hwnd, int PtrErcDpcInit);
    private native int  DvmgGetDevCountN( int PtrErcDpcInit );
    private native boolean DpcGetDpcVersionN( int szVersionPtr, int PtrErcDpcInit);
    private native boolean DvmgGetDevNameN( int idvc, int PtrszdvcTemp, int PtrErcDpcInit);
    private native boolean DpcOpenDataN( int Ptrhif, int Ptrszdvc, int PtrErcDpcInit, int PtrTrid);
    private native boolean DpcCloseDataN( int hif, int PtrErcDpcInit );

    private native boolean DpcPutRegN( int hif, int bAddr, int bData,int PtrErcDpc, int PtrTrid);
    private native boolean DpcGetRegN( int hif, int bAddr, int PtrbData,int PtrErcDpc, int PtrTrid);
    private native boolean DpcPutRegSetN( int hif, int rgbAddr, int rgbData,int cbData,
                                          int PtrErcDpc, int PtrTrid);
    private native boolean DpcGetRegSetN( int hif, int rgbAddr, int rgbData,int cbData,
                                          int PtrErcDpc, int PtrTrid);
    private native boolean DpcPutRegRepeatN( int hif, int bAddr, int rgbData,int cbData,
                                          int PtrErcDpc, int PtrTrid);

    private native boolean DpcGetRegRepeatN( int hif, int bAddr, int rgbData,int cbData,
                                          int PtrErcDpc, int PtrTrid);






    private IntPointers PtrPerc = new IntPointers();
    private IntPointers PtrPtrid = new IntPointers();
    private IntPointers phif = new IntPointers();
    private IntPointers PtrByteSingle = new IntPointers();
    private BytePointers PtrByteAdd = new BytePointers();
    private BytePointers PtrByteData = new BytePointers();


    public DPCUTIL(){
        InitClass MNGInitClass = new InitClass();
    }

    /**
     *
     * Inicia la libreria dpcutil contenida en dpcutil.dll
     *
     * int perc[];
     * perc en la posición cero "perc[0]" contiene el error
     * producido al llamar la función DpcInit, su valor sin
     * errores es cero.
     *
     * @author sdfsdfsd
     *
    */

    public boolean DpcInit( int perc[] ){
        PtrPerc.malloc(1);
        boolean RBool = DpcInitN( PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        //PtrPerc.Free();
        return RBool;
    }


    public void DpcTerm(){
        DpcTermN();
    }


    public void DvmgStartConfigureDevices( int Hwnd, int perc[] ){
        PtrPerc.malloc(1);
        DvmgStartConfigureDevicesN( Hwnd, PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
    }


    public void DvmgStartConfigureDevices( JFrame Frame, int perc[] ){
        PtrPerc.malloc(1);
        int wndHandle = PTRFUNCT.FindWindowA( null, Frame.getTitle() );
        DvmgStartConfigureDevicesN( wndHandle, PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
    }


    public int DvmgGetDevCount( int perc[] ){
        PtrPerc.malloc(1);
        int NDevCon =  DvmgGetDevCountN( PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
        return NDevCon;
    }



     //DPCAPI int		DvmgGetDevCount(ERC * perc);

    public String DpcGetDpcVersion( int perc[] ){
        CharPointers PtrszVersion;
        PtrszVersion = new CharPointers( 256 );
        PtrPerc.malloc( 256 );


        DpcGetDpcVersionN( PtrszVersion.Handle(), PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        int i=0;
        for( i=0; (i<256)&&(PtrszVersion.GetValue(i)!=0); i++ ){}
        char[] szVersion;
        szVersion = new char[i];
        for( int N=0; N<i; N++ ){
            szVersion[N] = PtrszVersion.GetValue(N);
        }
        String VersionR;
        VersionR = String.copyValueOf(szVersion, 0, i);
        PtrszVersion.free();
        PtrPerc.free();
        return VersionR;
    }



    public String DvmgGetDevName( int Idvc, int perc[] ){
        CharPointers PtrszDevName;
        PtrszDevName = new CharPointers( 256 );
        PtrPerc.malloc(1);


        DvmgGetDevNameN( Idvc, PtrszDevName.Handle(), PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        int i=0;
        for( i=0; (i<256)&&(PtrszDevName.GetValue(i)!=0); i++ ){}
        char[] szVersion;
        szVersion = new char[i];
        for( int N=0; N<i; N++ ){
            szVersion[N] = PtrszDevName.GetValue(N);
        }
        String VersionR;
        VersionR = String.copyValueOf(szVersion, 0, i);
        PtrszDevName.free();
        PtrPerc.free();
        return VersionR;
    }

    public boolean DpcOpenData( int[] Phif, String DevName, int[] perc ){
        phif.malloc(1);

        CharPointers PtrszDevName;
        PtrszDevName = new CharPointers( DevName.length() );
        for( int i=0; i<DevName.length(); i++){
            PtrszDevName.SetValue(DevName.charAt(i), i);
        }
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);

        boolean RBool = DpcOpenDataN( phif.Handle(), PtrszDevName.Handle(),
                                      PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        Phif[0] = phif.GetValue(0);
        PtrPerc.free();
        phif.free();
        PtrPtrid.free();
        return RBool;
    }

    public boolean DpcCloseData( int hif,  int[] perc ){
        PtrPerc.malloc(1);
        boolean RBool = DpcCloseDataN( hif, PtrPerc.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
        return RBool;
    }


    public boolean DpcPutReg( int hif, int bAddr, int bData, int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        boolean RBool = DpcPutRegN( hif, 255&bAddr, 255&bData, PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
        PtrPtrid.free();
        return RBool;
    }


    public boolean DpcGetReg( int hif, int bAddr, int[] bData, int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        PtrByteSingle.malloc(1);
        boolean RBool = DpcGetRegN( hif, 255&bAddr, PtrByteSingle.Handle(),
                                    PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        bData[0] = (255&PtrByteSingle.GetValue(0));
        PtrPerc.free();
        PtrPtrid.free();
        PtrByteSingle.free();
        return RBool;
    }



    public boolean DpcPutRegSet( int hif, int rgbAddr[], int[] rgbData, int cbData,  int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        PtrByteSingle.malloc(1);
        PtrByteAdd.malloc(cbData);
        PtrByteData.malloc(cbData);
        for( int i=0; i<cbData; i++ ){
            PtrByteAdd.SetValue( (byte)(255&rgbAddr[i]), i);
            PtrByteData.SetValue( (byte)(255&rgbData[i]), i);
        }
        boolean RBool = DpcPutRegSetN( hif, PtrByteAdd.Handle(), PtrByteData.Handle(), cbData,
                                    PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
        PtrPtrid.free();
        PtrByteAdd.free();
        PtrByteData.free();
        PtrByteSingle.free();
        return RBool;
    }


    public boolean DpcGetRegSet( int hif, int rgbAddr[], int[] rgbData, int cbData,  int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        PtrByteSingle.malloc(1);
        PtrByteAdd.malloc(cbData);
        PtrByteData.malloc(cbData);
        for( int i=0; i<cbData; i++ ){
            PtrByteAdd.SetValue( (byte)(255&rgbAddr[i]), i);
        }
        boolean RBool = DpcGetRegSetN( hif, PtrByteAdd.Handle(), PtrByteData.Handle(), cbData,
                                    PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);

        for( int i=0; i<cbData; i++ ){
            rgbData[i] = (255&PtrByteData.GetValue(i));
        }
        PtrPerc.free();
        PtrPtrid.free();
        PtrByteAdd.free();
        PtrByteData.free();
        PtrByteSingle.free();
        return RBool;
    }




    public boolean DpcPutRegRepeat( int hif, int bAddr, int[] rgbData, int cbData,  int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        PtrByteData.malloc(cbData);
        for( int i=0; i<cbData; i++ ){
            PtrByteData.SetValue( (byte)(255&rgbData[i]), i);
        }
        boolean RBool = DpcPutRegRepeatN( hif, bAddr, PtrByteData.Handle(), cbData,
                                    PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        PtrPerc.free();
        PtrPtrid.free();
        PtrByteData.free();
        return RBool;
    }






    public boolean DpcGetRegRepeat( int hif, int bAddr, int[] rgbData, int cbData,  int[] perc ){
        PtrPerc.malloc(1);
        PtrPtrid.malloc(1);
        PtrByteData.malloc(cbData);
        boolean RBool = DpcGetRegRepeatN( hif, bAddr, PtrByteData.Handle(), cbData,
                                    PtrPerc.Handle(), PtrPtrid.Handle() );
        perc[0] = PtrPerc.GetValue(0);
        for( int i=0; i<cbData; i++ ){
            rgbData[i] = (255&PtrByteData.GetValue(i));
        }
        PtrPerc.free();
        PtrPtrid.free();
        PtrByteData.free();
        return RBool;
    }






    //DPCAPI  BOOL    DpcGetRegRepeat(HANDLE hif, BYTE bAddr, BYTE * rgbData,
							   //int cbData, ERC * perc, TRID * ptrid);













    public String GetErrorDecription( int ErrorI ){
        String ErrDescrtn = null;
        switch(ErrorI){
            case 0:
                ErrDescrtn = new String("No error occurred in transaction.");
                break;
            case 3004:
                ErrDescrtn = new String("ercInvParam, Invalid parameter sent in API call.");
                break;
            case 3009:
                ErrDescrtn = new String("ercNoMem, Not enough memory to carry out transaction.");
                break;
            case 3102:
                ErrDescrtn = new String("ercNotInit, Communication device not initialized.");
                break;
            case 3103:
                ErrDescrtn = new String("ercCantConnect, Can’t connect to communication module.");
                break;
            case 3104:
                ErrDescrtn = new String("Already connected to communication device.");
                break;
            case 3105:
                ErrDescrtn = new String("Error occurred while sending data to communication device.");
                break;
            case 3106:
                ErrDescrtn = new String("Error occurred while receiving data from communication device.");
                break;
            case 3107:
                ErrDescrtn = new String("Error occurred while trying to abort transaction(s).");
                break;
            case 3109:
                ErrDescrtn = new String("Completion out of order.");
                break;
            case 3110:
                ErrDescrtn = new String("Too much data received from communication device.");
                break;
            case 3111:
                ErrDescrtn = new String("Nothing to send or data/address mismatched pairs.");
                break;
            case 3201:
                ErrDescrtn = new String("Unable to find matching TRID in transaction queue.");
                break;
            case 3202:
                ErrDescrtn = new String("Transaction being cleared is not complete.");
                break;
            case 3203:
                ErrDescrtn = new String("Not connected to communication device.");
                break;
            case 3204:
                ErrDescrtn = new String("Connected in wrong mode (JTAG or data transfer).");
                break;
            case 3301:
                ErrDescrtn = new String("Device table doesn’t exist (an empty one has been created).");
                break;
            case 3302:
                ErrDescrtn = new String("All or part of the device table is corrupted.");
                break;
            case 3303:
                ErrDescrtn = new String("Device does not exist in device table.");
                break;
            case 3304:
                ErrDescrtn = new String("DpcInit API call failed.");
                break;
            case 3306:
                ErrDescrtn = new String("Communications devices dialog box already open.");
                break;
            case 3307:
                ErrDescrtn = new String("Error occurred while accessing the registry.");
                break;
            default :
                ErrDescrtn = new String("ercUnknown, Internal error. Please report occurrence as a bug.");
                break;
        }

        return ErrDescrtn;
    }



}
