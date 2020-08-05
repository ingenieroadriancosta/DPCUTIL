package Pointers;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class LAUNCH{
    private native int DvmgStartConfigureDevicesN( int PtrcharTitle, int ShowStyle );
    private native int DvmgStartConfigureDevicesNClose();
    private native int  DvmgGetDevCountN();
    private native boolean DvmgGetDevNameN( int idvc, int PtrszdvcTemp );
    private native int SendComand( int uMsg, int wParam, int lParam );
    private native int SendDlgItemComand( int Item, int uMsg, int wParam, int lParam );
    private native int FreePtrChar( int PtrDir );
    private native int MallocPtrChar( int PtrDir, int LenOfPtr );
    private native char GetValuePtr( int PtrDir, int PosOfPtr );
    private native boolean SetValuePtr( int PtrDir, int PosOfPtr, char ByVal );

    
    private JDialog DPCDEVICEMANAGER = new JDialog();
    private JFrame FatherMainFrameP;
    private JButton ADD_DVC = new JButton();
    private JButton REMOVE_DVC = new JButton();
    private JButton SAVE = new JButton();
    private JButton ENUMWRATE = new JButton();
    private JList DeviceInList = new JList();
    private JList ConnectedDevices = new JList();
    private JScrollPane jscrollpane = new JScrollPane();
    private JScrollPane jscrollpaneConnectedDevices = new JScrollPane();

    private JTextField ALIAS = new JTextField();
    private JTextField CONNECT = new JTextField();
    
    JRadioButton USB = new JRadioButton();
    JRadioButton NET = new JRadioButton();
    JRadioButton PAR = new JRadioButton();
    JRadioButton SER = new JRadioButton();

    private int ErrResult = 0;
    private String ListDevice[] = null;
    private DvmgStartConfigureDevicesPrivate NEWTRHEAD = new DvmgStartConfigureDevicesPrivate();



    public static int main( JFrame FatherMainFrame ) {
        // TODO code application logic here
        if( FatherMainFrame==null || FatherMainFrame.getTitle()==null ){
            JOptionPane.showMessageDialog( null, "Debe especificar el un Frame correspondiente\n" +
                                            "que tenga un título",
                                            "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        LAUNCH application = new LAUNCH();

        application.FatherMainFrameP = FatherMainFrame;

        application.DIALOGDVCMNG();
        return application.ErrResult;
    }
    
    private int DIALOGDVCMNG(){
        //DPCDEVICEMANAGER.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        DPCDEVICEMANAGER.addWindowListener(new WindowAdapter() {
            @Override
           public void windowClosing(WindowEvent e) {
               DPCDEVICEMANAGER.dispose();
               ErrResult = DvmgStartConfigureDevicesNClose();
               DPCDEVICEMANAGER.dispose();
           }});
        DPCDEVICEMANAGER.setSize( 599, 265);
        //DPCDEVICEMANAGER.setLocation(300 , 300 );
        DPCDEVICEMANAGER.setLocationRelativeTo( null );
        DPCDEVICEMANAGER.setResizable( false );
        DPCDEVICEMANAGER.setModal( true );
        DPCDEVICEMANAGER.setTitle( " DPCOMM Device Table Manager 2.4.1 " );
        DPCDEVICEMANAGER.setIconImage( RESOURCES.RESOURCES.CargarPorNombre( "ICODIGILENT.png") );
        //FatherMainFrameP.gett

        ListInChange = true;


        JButton DeviceManagerD = new JButton( "Boton" );
        DeviceManagerD.setSize( 120, 40 );
        DeviceManagerD.setLocation( 0, 0);
        DeviceManagerD.setVisible( true );
        //DPCDEVICEMANAGER.add( DeviceManagerD );


        
        Point DVCINTBL = new Point();


        
        DVCINTBL.x = 390;
        DVCINTBL.y = 10;
        JLabel LabelConnectedDevices = new JLabel( "Conneced Devices" );
        LabelConnectedDevices.setLocation( DVCINTBL );
        LabelConnectedDevices.setSize( 125, 18);
        LabelConnectedDevices.setVisible( true );
        DPCDEVICEMANAGER.add( LabelConnectedDevices );
        ConnectedDevices.setVisible( true );
        DVCINTBL.y = DVCINTBL.y + 20;
        ConnectedDevices.setLocation( DVCINTBL );
        final int WWWCONNDVC = 170;
        final int HHHCONNDVC = 150;
        ConnectedDevices.setSize( WWWCONNDVC, HHHCONNDVC );
        DPCDEVICEMANAGER.add( ConnectedDevices );
        ConnectedDevices.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        

        ConnectedDevices.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt){IndexDeviceConn();}});
        jscrollpaneConnectedDevices.setVisible( true );
        jscrollpaneConnectedDevices.setSize( WWWCONNDVC, HHHCONNDVC);
        jscrollpaneConnectedDevices.setLocation( DVCINTBL );
        DPCDEVICEMANAGER.add( jscrollpaneConnectedDevices );
        jscrollpaneConnectedDevices.setViewportView(ConnectedDevices);
        ConnectedDevices.setSelectedIndex( 0 );


        SAVE.setVisible( true );
        DPCDEVICEMANAGER.add( SAVE );

        ENUMWRATE.setText( "Enumerate" );
        ENUMWRATE.setSize( 120, 25 );
        ENUMWRATE.setLocation( ConnectedDevices.getX() + 22,
                                        ConnectedDevices.getY() + ConnectedDevices.getHeight() + 10 );
        ENUMWRATE.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ENUMERATEFUNC();} });
        ENUMWRATE.setVisible( true );
        DPCDEVICEMANAGER.add( ENUMWRATE );
        //*/





        final int WWWCONN = 50;
        Point ALIASCONNECT = new Point();
        ALIASCONNECT.x = 225;
        ALIASCONNECT.y = 30;

        JLabel LabelALIAS = new JLabel( "Alias:" );
        LabelALIAS.setSize( WWWCONN, 20);
        LabelALIAS.setLocation( ALIASCONNECT.x-LabelALIAS.getWidth(), ALIASCONNECT.y );
        LabelALIAS.setVisible( true );
        DPCDEVICEMANAGER.add( LabelALIAS );
        ALIAS.setSize( 150 , 30 );
        ALIAS.setVisible( true );
        ALIAS.setLocation( ALIASCONNECT );

        //  keyTyped
        //  keyReleased
        ALIAS.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ALIASFUNC();
            }
        });
        /*
        ALIAS.addActionListener(new java.awt.event.ActionEvent() {
            public void actionPerformed(java.awt.event.KeyEvent evt) {
                ALIASFUNC();
            }
        });
        //*/

        DPCDEVICEMANAGER.add( ALIAS );


        
        
        ALIASCONNECT.y = ALIASCONNECT.y + 40;
        JLabel LabelCONNECT = new JLabel( "Connect:" );
        LabelCONNECT.setSize( WWWCONN, 20);
        LabelCONNECT.setLocation( ALIASCONNECT.x-LabelALIAS.getWidth(), ALIASCONNECT.y );
        LabelCONNECT.setVisible( true );
        DPCDEVICEMANAGER.add( LabelCONNECT );
        CONNECT.setSize( 150 , 30 );
        CONNECT.setVisible( true );
        CONNECT.setLocation( ALIASCONNECT );
        CONNECT.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CONNECTFUNC();
            }
        });
        DPCDEVICEMANAGER.add( CONNECT );




        
        USB.setLocation( ALIASCONNECT.x, ALIASCONNECT.y+60);
        USB.setSize( 60, 20 );
        USB.setText( "USB" );
        //USB.setSelected( true );
        USB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendComand( WM_COMMAND, WM_USB, 0 );
                USB.setSelected( true );
            }
        });
        









        USB.setVisible( true );
        DPCDEVICEMANAGER.add( USB );

        NET.setLocation( ALIASCONNECT.x + 70, ALIASCONNECT.y+60);
        NET.setSize( 60, 20 );
        NET.setText( "NET" );
        NET.setEnabled( false );
        NET.setVisible( true );
        DPCDEVICEMANAGER.add( NET );


        PAR.setLocation( ALIASCONNECT.x, ALIASCONNECT.y+60 + 30);
        PAR.setSize( 60, 20 );
        PAR.setText( "PAR" );
        PAR.setEnabled( false );
        PAR.setVisible( true );
        DPCDEVICEMANAGER.add( PAR );

        SER.setLocation( ALIASCONNECT.x + 70, ALIASCONNECT.y+60 + 30);
        SER.setSize( 60, 20 );
        SER.setText( "SER" );
        SER.setEnabled( false );
        SER.setVisible( true );
        DPCDEVICEMANAGER.add( SER );
        
        




        










        ALIASCONNECT.y = ALIASCONNECT.y + 30;




        
        
        DVCINTBL.x = 10;
        DVCINTBL.y = 10;
        JLabel LabelDevConn = new JLabel( "Device Table" );
        LabelDevConn.setLocation( DVCINTBL );
        LabelDevConn.setSize( 125, 18);
        LabelDevConn.setVisible( true );
        DPCDEVICEMANAGER.add( LabelDevConn );
        DeviceInList.setVisible( true );
        DVCINTBL.y = DVCINTBL.y + 20;
        DeviceInList.setLocation( DVCINTBL );
        DeviceInList.setSize( 155, 111 );
        DPCDEVICEMANAGER.add( DeviceInList );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            }
        DeviceInList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        
        DeviceInList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt){IndexDeviceInList();}});
        jscrollpane.setVisible( true );
        jscrollpane.setSize( 155, 111);
        jscrollpane.setLocation( DVCINTBL );
        DPCDEVICEMANAGER.add( jscrollpane );
        jscrollpane.setViewportView(DeviceInList);
        //DeviceInList.setSelectedIndex( 0 );



        ADD_DVC.setText( "Add Dvc" );
        ADD_DVC.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 120;
        DVCINTBL.x = DVCINTBL.x + 16;
        ADD_DVC.setLocation( DVCINTBL );
        ADD_DVC.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ADDDVCFunc();} });
        ADD_DVC.setVisible( true );
        DPCDEVICEMANAGER.add( ADD_DVC );


        REMOVE_DVC.setText( "Remove Dvc" );
        REMOVE_DVC.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 30;
        REMOVE_DVC.setLocation( DVCINTBL );
        REMOVE_DVC.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){REMOVEDVCFunc();} });
        REMOVE_DVC.setVisible( true );
        DPCDEVICEMANAGER.add( REMOVE_DVC );

        SAVE.setText( "Save" );
        SAVE.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 30;
        SAVE.setLocation( DVCINTBL );


        SAVE.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){SAVEFunc();} });


        
        


        
        DPCDEVICEMANAGER.add( ENUMWRATE );

        
        JPanel ControlPanel = new JPanel();
        DPCDEVICEMANAGER.add( ControlPanel );




















        
        
        //NEWTRHEAD = new DvmgStartConfigureDevicesPrivate();
        //NEWTRHEAD.start();
        int HWnd = 0;//PTRFUNCT.FindWindowA( null, FatherMainFrameP.getTitle() );
        //DvmgStartConfigureDevicesN( HWnd, 0 );
        //DvmgStartConfigureDevicesNClose();


        DvmgStartConfigureDevicesN( HWnd, 0 );
        //while( HWnd==0  )HWnd = PTRFUNCT.FindWindowA( null, " DPCWINDOWJAVA " );
        
        
        

        ListInChange = false;
        DPCDEVICEMANAGER.setVisible( true );
















        return 0;
    }



    private String DvmgGetDevName( int Idvc ){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        DvmgGetDevNameN( Idvc, PtrszDevName );
        int i=0;
        for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
        char[] szVersion;
        szVersion = new char[i];
        for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
        String VersionR;
        VersionR = String.copyValueOf(szVersion, 0, i);
        FreePtrChar( PtrszDevName );
        return VersionR;
    }
//*/



    private final int WM_COMMAND = 273;
    private final int WM_ENABLE = 10;

    
    private final int LB_SETCURSEL = 390;
    private final int LB_GETCOUNT = 395;
    private final int LB_GETTEXT = 393;

    
    private final int WM_GETTEXT = 13;
    private final int WM_SETTEXT = 12;
    private final int BM_GETCHECK = 240;
    private final int BST_CHECKED = 1;


    

    private final int WM_ENUMERATE = 1027;
    private final int WM_REMOVE = 1028;
    private final int WM_ADD = 1030;
    private final int WM_SAVE = 1022;
    private final int WM_USB = 1007;
    private final int WM_DEVICEINLIST = 1001;
    private final int WM_DEVICECONN = 1006;
    private final int WM_ALIAS = 1005;
    private final int WM_CONNECT = 1004;


    
    private void SAVEFunc(){
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        SendComand( WM_COMMAND, WM_SAVE, 0 );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
    }

    private boolean ADDDVCFunc(){
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        int ResMess = SendComand( WM_COMMAND, WM_ADD, 0 );
        if( ResMess!=0 ){
            JOptionPane.showMessageDialog(  null, "Failed to add device to device table",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
       return true;
    }

    private void REMOVEDVCFunc(){
        SendComand( WM_COMMAND, WM_REMOVE, 0 );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
        int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, 0, 0);
        USB.setSelected( Result==BST_CHECKED );
        int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);


            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );

    }


    private void ENUMERATEFUNC(){
        /*
        int HWnd = PTRFUNCT.FindWindowA(null, FatherMainFrameP.getTitle() );
        PTRFUNCT.ShowWindow(HWnd, 6);
        PTRFUNCT.ShowWindow(HWnd, 1);
        //*/
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        // ConnectedDevices
        SAVE.setEnabled( false );
        REMOVE_DVC.setEnabled( false );
        SendComand( WM_COMMAND, WM_ENUMERATE, 0 );
        /*
        while( SendDlgItemComand( WM_SAVE, WM_ENABLE, 0, 0)==0 ){
            //SAVE.setEnabled( false );
            //REMOVE_DVC.setEnabled( false );
        }
        //*/
        SAVE.setEnabled( true );
        REMOVE_DVC.setEnabled( true );

        int PtrszDevName = MallocPtrChar( 0, 1024);
        int NDevConn = SendDlgItemComand( WM_DEVICECONN, LB_GETCOUNT, 0, 0);
        String ConnDevice[] = new String[NDevConn];
        
        for( int i=0; i<NDevConn; i++ ){
            SendDlgItemComand( WM_DEVICECONN, LB_GETTEXT, i, PtrszDevName);
            int C = 9;
            for( C=0; (C<256)&&(GetValuePtr( PtrszDevName, C)!=0); C++ ){}
            char[] szVersion;
            szVersion = new char[C];
            for( int N=0; N<C; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            ConnDevice[i] = String.copyValueOf(szVersion, 0, C);
        }
        ConnectedDevices.setListData(ConnDevice);
        //JOptionPane.showMessageDialog(  null, "WM_DEVICECONN\n" + NDevConn + "\n" + ConnDevice[0] );

        
        FreePtrChar( PtrszDevName );
    }

    






    private void ALIASFUNC(){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        String Texto = new String( ALIAS.getText() );
        for( int i=0; i<Texto.length(); i++)
            SetValuePtr( PtrszDevName, i, Texto.charAt(i));
        //
        SendDlgItemComand( WM_ALIAS, WM_SETTEXT, 256, PtrszDevName);


        FreePtrChar( PtrszDevName );
    }

    private void CONNECTFUNC(){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        String Texto = new String( CONNECT.getText() );
        for( int i=0; i<Texto.length(); i++){SetValuePtr( PtrszDevName, i, Texto.charAt(i));}
        SendDlgItemComand( WM_CONNECT, WM_SETTEXT, 256, PtrszDevName);
        FreePtrChar( PtrszDevName );
    }

    



    private boolean ListInChange = false;
    private void IndexDeviceInList(){
        if( ListInChange==false ){
            int SelectedIndex = DeviceInList.getSelectedIndex();
            SendDlgItemComand( WM_DEVICEINLIST, LB_SETCURSEL, SelectedIndex, 0);
            int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, SelectedIndex, 0);
            USB.setSelected( Result==BST_CHECKED );
            int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);


            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );
        }
    }


    

    private void IndexDeviceConn(){
        if( ListInChange==false ){
            int SelectedIndex = ConnectedDevices.getSelectedIndex();
            SendDlgItemComand( WM_DEVICECONN, LB_SETCURSEL, SelectedIndex, 0);
            int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, SelectedIndex, 0);
            USB.setSelected( Result==BST_CHECKED );
            int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);
            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );
        }
    }



    private class DvmgStartConfigureDevicesPrivate extends Thread {
        public boolean ThreadActivo = false;
        @Override
        public void run(){
            String TitleMainF = new String( FatherMainFrameP.getTitle() );
            int LenOfTitle = TitleMainF.length();
            int PtrszDevName = MallocPtrChar( 0, LenOfTitle );
            
            for( int i=0; i<LenOfTitle; i++ ){
                SetValuePtr( PtrszDevName, i, TitleMainF.charAt(i) );
            }
            DvmgStartConfigureDevicesN( PtrszDevName, 0 );
        }
    }
}

