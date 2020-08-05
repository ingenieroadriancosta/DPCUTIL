package dpcutil;
import Pointers.DPCUTIL;
import Pointers.InitClass;
import Pointers.LAUNCH;
import Pointers.PTRFUNCT;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class Main extends JFrame{
// <editor-fold defaultstate="collapsed" desc="Variables.">
    private JFrame MainFrame = null;
    private JButton BUTTONPRB = new JButton("PRUEBAS");
    private JFileChooser chooser = new JFileChooser();
    private FileDialog fileDialog = null;
    private JLabel Fondo = new JLabel();
    private JPanel ControlPanel = new JPanel();
    private ImageIcon ViewIcon = new ImageIcon();
    private final String DeviceManagerString = new String("Device\nManager");
    private JButton DeviceManager = new JButton(DeviceManagerString);
    private JComboBox Datas = new JComboBox();
    private JButton SendData = new JButton("Enviar");
    private JButton GetData = new JButton("Obtener");
    private JScrollPane jscrollpane = new JScrollPane();
    private JList NameDevs = new JList();
    private JComboBox Address = new JComboBox();
    private int FeelSelected = 1;
    private UIManager.LookAndFeelInfo looks[];
    private int LooksCNT = 0;
    JMenuItem Views[];
    JMenuBar bar = new JMenuBar();
    private DPCUTIL MNGDPC = null;
    private int[] perc = new int[1];
    private int[] phif = new int[1];
    private int IndDev = 0;
    private String ListDevice[] = null;
    private String NameDev = new String("");
    private int Addr = 0;
    private int[] Data = new int[1];
    private String WChildTitle = new String( " Abrir - JAVA DPCUTIL 2.0 " );
    private PTRFUNCT ptrfunct;
// </editor-fold>
    public static void main(String[] args){
        if( args.length==3 ){
            try{Thread.sleep(100);}catch(InterruptedException ex){System.exit( -1 );}
            File file2delete = new File( args[2] );
            file2delete.delete();
            System.exit( 0 );
        }
        Main application = new Main();
        application.DesktopTest();
    }
   // set up GUI
   public void DesktopTest(){
        InitClass MNGInitClass = new InitClass();
        ptrfunct = new PTRFUNCT();
        MNGDPC = new DPCUTIL();
        MNGDPC.DpcInit( perc );

        

        



       
        MainFrame = this;//new JFrame();
        MainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        MainFrame.addWindowListener(new WindowAdapter(){
             @Override
            public void windowClosing(WindowEvent e){
                 Exit();
            }});
        //*/
        MainFrame.setSize( 464, 399);
        MainFrame.setLocation(400, 150);
        MainFrame.setResizable(false);
        MainFrame.setTitle( " JAVA DPCUTIL 2.0 " );
        MainFrame.setLocationRelativeTo( null );
        MainFrame.setIconImage( RESOURCES.RESOURCES.CargarPorNombre("USB.png")  );
        ViewIcon = new ImageIcon( RESOURCES.RESOURCES.GetUrlFile( "VIEWICON.png" ) );
       



        
        BUTTONPRB.setSize( 200, 100 );
        BUTTONPRB.setLocation( 150, 150 );
        BUTTONPRB.setHorizontalAlignment(SwingConstants.LEFT);
        BUTTONPRB.setVisible(true);
        BUTTONPRB.setVisible( true );
        BUTTONPRB.setText( "BUTTONPRB" );
        BUTTONPRB.addActionListener(new ActionListener(){public void actionPerformed( ActionEvent event ){PRUEBAS();}});
        MainFrame.add( BUTTONPRB );
        
        //
        // PopupMenu.
        
        // Opciones.
        JMenu Opciones = new JMenu( "Opciones" );
        JMenuItem ChosseFile = new JMenuItem( "Enviar archivo" );
        ChosseFile.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ChosseFileF();}});
        Opciones.add( ChosseFile );

        JMenuItem Salir = new JMenuItem( "Salir" );
        Salir.addActionListener(
            new ActionListener(){
            public void actionPerformed( ActionEvent event ){
                System.exit(0);
                }
            }
        );
        Opciones.add( Salir );
        bar.add( Opciones );

        fileDialog = new FileDialog( MainFrame );
        fileDialog.setModal( true );

        JMenu Ver = new JMenu( "Ver" );
        JMenu ViewStyles = new JMenu( "Estilos de controles" );
        Ver.add( ViewStyles );
        //import javax.swing.j
        looks = UIManager.getInstalledLookAndFeels();
        try{while( looks[LooksCNT].getName()!=null ){LooksCNT = LooksCNT + 1;}}
        catch( Exception exception ){}
        Views = new JMenuItem[LooksCNT];
        for( int i=0; i<LooksCNT; i++ ){
            Views[i] = new JMenuItem( looks[ i ].getName() );
            final int CodesLook = i;
        Views[i].addActionListener(
            new ActionListener(){
            public void actionPerformed( ActionEvent event ){
                try{
                    UIManager.setLookAndFeel( looks[ CodesLook ].getClassName() );
                    UpdateComponent( CodesLook );
                }catch ( Exception exception ){
                    exception.printStackTrace();
                }
                }
            }
        );
            ViewStyles.add( Views[i] );
        }
        bar.add( Ver );

        try{
            UIManager.setLookAndFeel( looks[ FeelSelected ].getClassName() );
            UpdateComponent( FeelSelected );
        }catch ( Exception exception ){
                    exception.printStackTrace();
                }

        // Ayuda.
        JMenu Ayuda = new JMenu( "Ayuda" );
        JMenuItem About = new JMenuItem( "Acerca de..." );
        Ayuda.add( About );
        bar.add( Ayuda );
        MainFrame.setJMenuBar(bar);

        // set up listener for newFrame menu item
        About.addActionListener(
            new ActionListener(){
            public void actionPerformed( ActionEvent event ) {
                JOptionPane.showMessageDialog(  rootPane, "Uso de la libreria DPCUTIL de Digilent " +
                                                          "en java.\n" +
                                                          "Diseñado por:\n" +
                                                          "Adrian Costa.\n" +
                                                          "email:\n" +
                                                          "adrianjocos@hotmail.com",
                                                          "Acerce de JAVA DPCUTIL 2.0",
                                                          JOptionPane.INFORMATION_MESSAGE);

                }
            }
        );
        MainFrame.setJMenuBar(bar);




        DeviceManager.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){
                DeviceManager();
                }

        });
        DeviceManager.setSize( 125, 40 );
        DeviceManager.setLocation(200, 0);
        DeviceManager.setHorizontalAlignment(SwingConstants.LEFT);
        DeviceManager.setText( "Device Manager" );
        DeviceManager.setVisible(true);
        //this.add( DeviceManager );
        MainFrame.add( DeviceManager );




        JLabel LabelDevConn = new JLabel( "Dispositivos en lista." );
        LabelDevConn.setLocation(1, 20);
        LabelDevConn.setSize( 125, 18);
        LabelDevConn.setVisible( true );
        this.add( LabelDevConn );

        final int WWWDevCon = 200;
        final int YNameDev = 30 + 10;
        NameDevs.setVisible( true );
        NameDevs.setLocation( 1 , YNameDev);
        NameDevs.setSize( 125, WWWDevCon);
        this.add( NameDevs );


        
        int NdevicesCon = MNGDPC.DvmgGetDevCount(perc);
        if( NdevicesCon>0 ){
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = MNGDPC.DvmgGetDevName(i, perc);}
            NameDevs.setListData( ListDevice );
            }


        NameDevs.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        NameDevs.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt){InDevFunc();}});
        jscrollpane.setVisible( true );
        jscrollpane.setSize( 125, WWWDevCon);
        jscrollpane.setLocation( 1 , YNameDev );
        this.add( jscrollpane );
        jscrollpane.setViewportView(NameDevs);
        NameDevs.setSelectedIndex( 0 );




        final int XPosDatas = 340;
        JLabel LabelAdd = new JLabel( "Address" );
        LabelAdd.setLocation( XPosDatas, 0 );
        LabelAdd.setSize( 50, 20 );
        this.add( LabelAdd );
        Address.setLocation( XPosDatas, 20 );
        Address.setSize( 55, 30);
        Address.setVisible( true );
        this.add( Address );

        String strings1[] = null;
        strings1 = new String[256];
        for( int i=0; i<256; i++ ){
            strings1[i] = i + "";
        }
        Address.setModel(new javax.swing.DefaultComboBoxModel(
        strings1
        ));
        Address.setName("Address"); // NOI18N


        JLabel LabelDatas = new JLabel( "Data" );
        LabelDatas.setLocation( XPosDatas+65, 0 );
        LabelDatas.setSize( 50, 20 );
        this.add( LabelDatas );
        Datas.setLocation( XPosDatas+65, 20 );
        Datas.setSize( 55, 30);
        Datas.setVisible( true );

        for( int i=0; i<256; i++ ){
            strings1[i] = i + "";
        }
        Datas.setModel(new javax.swing.DefaultComboBoxModel(
        strings1
        ));
        Datas.setName("Datas"); // NOI18N
        this.add( Datas );


        SendData.setVisible( true );
        SendData.setSize( 110, 30);
        SendData.setLocation( XPosDatas, 60 );
        SendData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendDataFunc();
            }
        });
        this.add( SendData );

        GetData.setVisible( true );
        GetData.setSize( 110, 30);
        GetData.setLocation( XPosDatas, 100 );
        GetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetDataFunc();
            }
        });
        this.add( GetData );








        Fondo.setLocation( 0, 0 );
        Fondo.setSize( 600, 450);
        ImageIcon imageIcon;
        imageIcon = new ImageIcon( RESOURCES.RESOURCES.GetUrlFile( "Fondo.png" ) );
        Fondo.setIcon( imageIcon );
        Fondo.setVisible( true );
        Fondo.setFocusable( false );
        this.add( Fondo, BorderLayout.PAGE_START );
        MainFrame.add( ControlPanel );
        UpdateComponent( FeelSelected );
        MainFrame.setVisible(true);
        MainFrame.setAlwaysOnTop( false );




        


        


        
        

        
   }










    private void UpdateComponent( int Ind ) {
        SwingUtilities.updateComponentTreeUI( MainFrame );
        SwingUtilities.updateComponentTreeUI( ControlPanel );
        SwingUtilities.updateComponentTreeUI( Fondo );
        SwingUtilities.updateComponentTreeUI( bar );
        SwingUtilities.updateComponentTreeUI( DeviceManager );
        SwingUtilities.updateComponentTreeUI( MainFrame );
        SwingUtilities.updateComponentTreeUI( MainFrame );
        SwingUtilities.updateComponentTreeUI( MainFrame );
        
        /*
        SwingUtilities.updateComponentTreeUI( SendData );
        SwingUtilities.updateComponentTreeUI( GetData );
        SwingUtilities.updateComponentTreeUI( Address );
        SwingUtilities.updateComponentTreeUI( Datas );
        SwingUtilities.updateComponentTreeUI( jscrollpane );
        SwingUtilities.updateComponentTreeUI( chooser );
        //*/
        for( int i=0; i<LooksCNT; i++ ){
            if( i==Ind )
                Views[i].setIcon( ViewIcon );
            else
                Views[i].setIcon( null );

        }

    }


    private void DeviceManager() {
        

        LAUNCH.main(MainFrame);
        /*
        MNGDPC.DvmgStartConfigureDevices(MainFrame, perc);
        String DevName = MNGDPC.DvmgGetDevName( 0, perc);
        int DevCount = MNGDPC.DvmgGetDevCount(perc);
        //PTRFUNCT.msgbox(DevName + "\nDevCount, " + DevCount, " ");
        MNGDPC.DpcOpenData( phif, DevName, perc);
        MNGDPC.DpcGetReg( phif[0], Addr, Data, perc);
        MNGDPC.DpcCloseData( phif[0], perc);
        
        IntPointers PtrPerc = new IntPointers();
        PtrPerc.malloc( 16*1024*1024 );
        for( int i=0; i<PtrPerc.Len(); i++ )
            PtrPerc.SetValue(i, i);
        PtrPerc.GetValue( 0 );
        PtrPerc.free();
        //DVMGSTARTCONFIGUREDEVICES FFF;
        //LAUNCH DVCMND = new LAUNCH();
        //int AAA = LAUNCH.main( MainFrame );
        //if( AAA!=0 )
            //JOptionPane.showMessageDialog(  rootPane, "Resultado,\n" + AAA );
        //*/
    }

    private void InDevFunc() {
        IndDev = NameDevs.getSelectedIndex();
    }

   /*  FileChooser
     *
     *
     *
     *
     *
     *
     *
     */
    private String File2Send = null;
    private int[] Buffer2send = null;
    private int Datas2send = 0;
    private String CurrentDir = null;
    boolean Devolver = true;
    private boolean ChosseFileF(){
        //return true;
        //int HWnd = PTRFUNCT.FindWindowA( null, MainFrame.getTitle());
        //PTRFUNCT.SetIcon( HWnd, WChildTitle );
        chooser.setSelectedFile( null );
        chooser.resetChoosableFileFilters();
        //chooser.setLocation( 200, 200 );
        //chooser.setSize( 640, 480);
        //chooser.setVisible( true );
        chooser.setMultiSelectionEnabled( false );
        //chooser.setFileFilter( new FileNameExtensionFilter("Archivos JPEG", "jpg") );
        //chooser.setFileFilter( new FileNameExtensionFilter("Archivos BMP", "bmp") );
        //if( CurrentDir==null )chooser.setCurrentDirectory( new File("C:\\Users\\ADRIAN COSTA\\Desktop") );
        chooser.setMultiSelectionEnabled( false );
        chooser.setDialogType( JFileChooser.OPEN_DIALOG );
        chooser.setApproveButtonText( WChildTitle );
        //chooser.showDialog( MainFrame, null );
        fileDialog.setMode(  FileDialog.LOAD  );
        fileDialog.setFile(null);
        //fileDialog.setIconImage( MainFrame.getIconImage() );
        fileDialog.setIconImage( RESOURCES.RESOURCES.CargarPorNombre("USB.png") );
        fileDialog.setTitle( " Abrir - JAVA DPCUTIL 2.0 " );
        fileDialog.setAlwaysOnTop( true );
        fileDialog.setVisible( true );
        fileDialog.setAlwaysOnTop( false );


        String FileName = null;/*
        String Filter = new String( "MP3 Files (*.mp3*)\0*.mp3\0WAV Files (*.wav*)\0*.wav\0All Files(*.*)\0*.*\0\0" );
        FileName = ptrfunct.OpenFileDialog( MainFrame, Filter );
        if( Devolver ) return false;
        //*/
        if( chooser.getSelectedFile()!=null || fileDialog.getFile() != null || FileName!=null ){
            //System.out.println( File2Send );
            if( fileDialog.getFile() != null ){
                File2Send = new String( fileDialog.getDirectory() + fileDialog.getFile() );
            }
            else{
                if( chooser.getSelectedFile()!=null ){
                    File2Send = new String( chooser.getSelectedFile().getAbsolutePath() );
                    CurrentDir = new String( chooser.getSelectedFile().getPath() );
                }
            }

            if( FileName!=null )
                File2Send = FileName;
            
            try {
                DataInputStream dos = new DataInputStream(new BufferedInputStream(new FileInputStream(File2Send)));
                Datas2send = dos.available();
                Buffer2send = new int[Datas2send];
                for( int i=0; i<Datas2send; i++ ){
                    Buffer2send[i] = dos.read();
                }
                dos.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(  rootPane, ex, "Error", JOptionPane.ERROR_MESSAGE );
                return false;
            }
        }else{ Datas2send = 0;return false;}

        
        Addr = Address.getSelectedIndex();
        int DevCount = MNGDPC.DvmgGetDevCount(perc);
        if( DevCount==0 ){
            JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        NameDev = new String( MNGDPC.DvmgGetDevName( IndDev, perc) );
        MNGDPC.DpcOpenData( phif, NameDev, perc);
        if( ParseError( perc[0] )==true ){return false;}




        MNGDPC.DpcPutRegRepeat( phif[0], Addr, Buffer2send, Datas2send, perc);
        if( ParseError( perc[0] )==true ){return false;}

        MNGDPC.DpcCloseData(phif[0], perc);


        if( Datas2send<1024 )
            JOptionPane.showMessageDialog(  rootPane, "Transmisión exitosa.\n" + Datas2send + " Bytes enviados." );
        else{
            if( Datas2send<1024*1024 ){
                String NMegas = new String( String.format( "%g", (Datas2send/1024.0))  );

                JOptionPane.showMessageDialog(  rootPane, "Transmisión exitosa.\n" + NMegas + " KBytes enviados." );
            }
            else{
                String NMegas = new String( String.format( "%g", (Datas2send/(1024.0*1024)))  );
                JOptionPane.showMessageDialog(  rootPane, "Transmisión exitosa.\n" + NMegas + " MBytes enviados." );
            }
        }
        Buffer2send = null;
        Datas2send = 0;
        return true;
    }


private boolean ParseError( int error ){
    if( error==0 ){
        return false;
    }else{
        String StrError = new String(MNGDPC.GetErrorDecription(error));
        JOptionPane.showMessageDialog(  rootPane,
                                        "Ha ocurrido un error con código (" + perc[0] + ")\n" +
                                        "relacionado con el dispositivo (" + NameDev + ").\n" +
                                        "Descripción del error:\n" +
                                        StrError,
                                        "Error JDPCUTIL 1.0",
                                        JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
private boolean SendDataFunc(){
    Addr = Address.getSelectedIndex();
    Data[0] = Datas.getSelectedIndex();
    int DevCount = MNGDPC.DvmgGetDevCount(perc);
    if( DevCount==0 ){
        JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    NameDev = new String( MNGDPC.DvmgGetDevName( IndDev, perc) );
    MNGDPC.DpcOpenData( phif, NameDev, perc);
    if( ParseError( perc[0] )==true ){return false;}
    MNGDPC.DpcPutReg( phif[0], Addr, Data[0], perc);
    if( ParseError( perc[0] )==true ){return false;}
    MNGDPC.DpcCloseData(phif[0], perc);
    return true;
}




private boolean GetDataFunc(){
    Addr = Address.getSelectedIndex();
    Data[0] = Datas.getSelectedIndex();
    int DevCount = MNGDPC.DvmgGetDevCount(perc);
    if( DevCount==0 ){
        JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    NameDev = new String( MNGDPC.DvmgGetDevName( IndDev, perc) );
    MNGDPC.DpcOpenData( phif, NameDev, perc);
    if( ParseError( perc[0] )==true ){return false;}
    MNGDPC.DpcGetReg( phif[0], Addr, Data, perc);
    if( ParseError( perc[0] )==true ){return false;}
    Datas.setSelectedIndex( Data[0] );
    MNGDPC.DpcCloseData(phif[0], perc);
    return true;
}





private void Exit(){
    MNGDPC.DpcTerm();
    
    
    URL url = RESOURCES.RESOURCES.GetUrlFile( "Fondo.png" );
    String JavaFileName = url.getFile();

    int LenChar = JavaFileName.length() - 27;
    char CharArray[] = new char[LenChar];
    for( int i=0; i<LenChar; i++){
        CharArray[i] = JavaFileName.toCharArray()[i+6];
    }
    JavaFileName = String.copyValueOf(CharArray);
    String Args = new String();

    

    final String JavaProgram = "C:/Windows/System32/java.exe -jar ";
    final String File2Delete = InitClass.GetFileName();
    final String Arguments = " \"DELETE\" " + "\"FILE\"" + " \"" + File2Delete + "\" ";

    JavaFileName = "\"" + JavaFileName + "\"";
    Args = JavaProgram + JavaFileName + Arguments;
        try {
            Runtime.getRuntime().exec(Args);
        } catch (IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


//System.getProperties().list(System.out);
    /*
    //JOptionPane.showMessageDialog( null, JavaFileName );
    System.getProperties().list(System.out);
    System.out.print( "\n\n\n" );
    String tempstr = System.getProperty( "java.home" );
    System.out.print( tempstr );
    System.out.print( "\n\n\n" );
    //*/
    System.exit(0);
}



private void PRUEBAS(){
    int HWnd = PTRFUNCT.FindWindowA( null, MainFrame.getTitle());


    String StrTemp = "0123456789";
    char CharTemp[];
    CharTemp = StrTemp.toCharArray();

    PTRFUNCT.PruebasN( HWnd, CharTemp );
    /*
    //PTRFUNCT.ShowWindow( MainFrame, 3 );

    /*
    String Filter = new String( "MP3 Files (*.mp3*)\0*.mp3\0WAV Files (*.wav*)\0*.wav\0All Files(*.*)\0*.*\0\0" );
    String FileName = PTRFUNCT.OpenFileDialog( MainFrame, Filter );

    if( FileName!=null )
        PTRFUNCT.msgbox( FileName, "");
    
    /*
    int HWnd = PTRFUNCT.FindWindowA( null, MainFrame.getTitle());
    PTRFUNCT.SetIcon( HWnd, WChildTitle );
    
    fileDialog.setMode(  FileDialog.LOAD  );
    fileDialog.setFile(null);
    fileDialog.setIconImage( MainFrame.getIconImage() );
    fileDialog.setTitle( " Abrir - JAVA DPCUTIL 2.0 " );
    fileDialog.setAlwaysOnTop( true );
    fileDialog.setVisible( true );
    fileDialog.setAlwaysOnTop( false );

    
    /*
    MainFrame.setTitle( null );
    if( MainFrame.getTitle()==null || MainFrame.getTitle().length()==0 )
        MainFrame.setTitle( " \0 D" );
    int CHARAT = (int)MainFrame.getTitle().length();
    //PTRFUNCT.msgbox( "CHARAT\n" + CHARAT, "" );
    
    int HWnd = PTRFUNCT.FindWindowA( null, MainFrame.getTitle());
    PTRFUNCT.PruebasN( HWnd );
    //*/
}


}
