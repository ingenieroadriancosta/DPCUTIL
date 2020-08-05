package dpcutil;
import java.io.*;
import javax.swing.*;
import java.awt.*;

import FilesClass.*;
import java.io.File;
import java.awt.event.*;
import java.io.FileInputStream;
import static javax.swing.JFileChooser.*;

import Pointers.DPCUTIL;
import Pointers.IntPointers;


import START.*;




public class Main extends JFrame{
    // Campos, Variables.
    private JFrame MainFrame;
    JFileChooser chooser = new JFileChooser();
    JMenuItem Views[];
    private UIManager.LookAndFeelInfo looks[];
    private JPanel ControlPanel = new JPanel();
    private JList NameDevs = new JList();
    private JScrollPane jscrollpane = new JScrollPane();
    private JLabel Fondo = new JLabel();
    private final String DeviceManagerString = new String( "Device\nManager" );


    private JButton BUTTONPRB = new JButton( "PRUEBAS" );
    private JButton DeviceManager = new JButton( DeviceManagerString );
    private JButton SendData = new JButton( "Enviar" );
    private JButton GetData = new JButton( "Obtener" );
    private JComboBox Address = new JComboBox();
    private JComboBox Datas = new JComboBox();

    
    private ImageIcon ViewIcon = new ImageIcon();
    private int LooksCNT = 0;
    
    private static String PATHJAVAHOMEP;


    public static void main(String[] args) {
        // TODO code application logic here
        String PathJavaHome = new String( System.getProperty( "java.home" ) );
        /*
        FilePointersLibrary CopyLib = new FilePointersLibrary();
        FileIcono IconoF = new FileIcono();
        FileFondo FondoF = new FileFondo();
        FileLibDll filedll = new FileLibDll();
        
        File newfile = new File( "lib\\PointersLibrary.jar" );
        FileViewIcon NewFileViewIcon = new FileViewIcon();
        if( newfile.isFile()==false ){
            newfile = new File( "lib\\" );
            newfile.mkdir();
            CopyLib.WriteFile( "lib\\" );
            IconoF.WriteFile( "lib\\" );
            filedll.WriteFile( PathJavaHome + "\\");
            FondoF.WriteFile( "lib\\" );
            NewFileViewIcon.WriteFile( "lib\\" );


            JFrame DPCDEVICEMANAGER = new JFrame();
            DPCDEVICEMANAGER.setSize( 0, 0);
            DPCDEVICEMANAGER.setLocation(-300 , -300);
            DPCDEVICEMANAGER.setResizable( false );
            //DPCDEVICEMANAGER.setLocationRelativeTo(null);
            DPCDEVICEMANAGER.setTitle( " JDPCUTIL 1.0 " );
            
            DPCDEVICEMANAGER.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            DPCDEVICEMANAGER.setIconImage( Toolkit.getDefaultToolkit().getImage("lib\\USB.png")  );
            DPCDEVICEMANAGER.setVisible(true);
            JOptionPane.showMessageDialog( null, "Se ha escrito una libreria necesaria\n" +
                                                 "para el funcionamiento del programa,\n" +
                                                 "por favor no la elimine.\n\n" +
                                                 "Vuleva a ejecutar el programa.\n\n\n");
            //JOptionPane.showMessageDialog( null, System.getProperty( "java.class.path" ) );
            //JOptionPane.showMessageDialog( null, System.getProperty( "user.dir" ) );
            //try{InitClass Inicio = new InitClass();Inicio.WriteDll();}
            //finally{System.exit(0);}
        }

        

        CopyLib.WriteFile( "lib\\" );
        IconoF.WriteFile( "lib\\" );
        filedll.WriteFile( PathJavaHome + "\\");
        FondoF.WriteFile( "lib\\" );
        NewFileViewIcon.WriteFile( "lib\\" );
//*/

        
        //InitClass Inicio = new InitClass();
        //Inicio.WriteDll();
        
        //application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        PATHJAVAHOMEP = new String( PathJavaHome );
        Main application = new Main();
        application.DesktopTest();
        
    }
   // set up GUI
   public void DesktopTest()
   {


        //DPC.DpcInit(perc);
       IntPointers PtrPerc = new IntPointers();

System.exit(0);
        
       
        MainFrame = new JFrame();
        //MainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	MainFrame.addWindowListener(new WindowAdapter() {
            @Override
           public void windowClosing(WindowEvent e) {
               //JOptionPane.showMessageDialog(  rootPane, "" );
               System.exit(0);
           }});



        MainFrame.setSize( 464, 399);
        MainFrame.setLocation(400, 150);
        MainFrame.setResizable(false);
        MainFrame.setTitle( " JDPCUTIL " );
        MainFrame.setIconImage( Toolkit.getDefaultToolkit().getImage("lib\\USB.png")  );
        ViewIcon = new ImageIcon( "E:\\ENTERPRISE\\JAVA\\EJERCICIOS\\DPCUTIL\\VIEWICON.png" );






        //BUTTONPRB.addActionListener(new ActionListener(){public void actionPerformed( ActionEvent event ){PRUEBAS();}});
        BUTTONPRB.setSize( 200, 100 );
        BUTTONPRB.setLocation( 150, 150 );
        BUTTONPRB.setHorizontalAlignment(SwingConstants.LEFT);
        BUTTONPRB.setVisible(true);
        BUTTONPRB.setVisible( false );
        BUTTONPRB.setText( "" );
        this.add( BUTTONPRB );



        
        // 
        // PopupMenu.
        JMenuBar bar = new JMenuBar();
        // Opciones.
        JMenu Opciones = new JMenu( "Opciones" );
        JMenuItem ChosseFile = new JMenuItem( "Enviar archivo" );
        ChosseFile.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ChosseFileF();}});

        Opciones.add( ChosseFile );
        
        JMenuItem Salir = new JMenuItem( "Salir" );
        Opciones.add( Salir );
        bar.add( Opciones );



        
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
        MainFrame.setContentPane(rootPane);
        
        
        


        
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
                                                          "Acerce de JDPCUTIL 1.0",
                                                          JOptionPane.INFORMATION_MESSAGE);

                }
            }
        );
        
        Salir.addActionListener(
            new ActionListener(){
            public void actionPerformed( ActionEvent event ){
                System.exit(0);
                }
            }
        );




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
        this.add( DeviceManager );



        
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
        
        
    
        int NdevicesCon = DPC.DvmgGetDevCount(perc);
        if( NdevicesCon>0 ){
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DPC.DvmgGetDevName(i, perc);}
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
        //imageIcon = new ImageIcon( "lib\\USB.png" );
        imageIcon = new ImageIcon( "lib\\Fondo.png" );
        Fondo.setIcon( imageIcon );
        Fondo.setVisible( true );
        Fondo.setFocusable( false );
        this.add( Fondo, BorderLayout.PAGE_START );
//      */
        //JButton Ultimo = new JButton();
        //this.add( Ultimo );
        this.add( ControlPanel );
        UpdateComponent( FeelSelected );
        MainFrame.setVisible(true);
   } // end constructor

    private void UpdateComponent( int Ind ) {
        SwingUtilities.updateComponentTreeUI( MainFrame );
        SwingUtilities.updateComponentTreeUI( ControlPanel );
        SwingUtilities.updateComponentTreeUI( Fondo );
        SwingUtilities.updateComponentTreeUI( SendData );
        SwingUtilities.updateComponentTreeUI( GetData );
        SwingUtilities.updateComponentTreeUI( Address );
        SwingUtilities.updateComponentTreeUI( Datas );
        SwingUtilities.updateComponentTreeUI( jscrollpane );
        SwingUtilities.updateComponentTreeUI( chooser );
        for( int i=0; i<LooksCNT; i++ ){
            if( i==Ind )
                Views[i].setIcon( ViewIcon );
            else
                Views[i].setIcon( null );
            
        }

    }

/* Acciones.
 * 
 * 
 */
private DPCUTIL DPC = new DPCUTIL();
private int[] perc = new int[1];
private int[] phif = new int[1];
private int IndDev = 0;
private String ListDevice[] = null;
private String NameDev = new String( "" );
private int Addr = 0;
private int[] Data = new int[1];
/*private JFrame MainFrame;
    private JPanel ControlPanel = new JPanel();
    JLabel Fondo = new JLabel();
    private final String DeviceManagerString = new String( "Device\nManager" );
    private JButton DeviceManager = new JButton( DeviceManagerString );
    private JButton SendData = new JButton( "Enviar" );
    private JButton GetData = new JButton( "Obtener" );
    private JComboBox Address = new JComboBox();
    private JComboBox Datas = new JComboBox();
 *
 */
private boolean ParseError( int error ){
    if( error==0 ){
        return false;
    }else{
        String StrError = new String(DPC.GetErrorDecription(error));
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
    int DevCount = DPC.DvmgGetDevCount(perc);
    if( DevCount==0 ){
        JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    NameDev = new String( DPC.DvmgGetDevName( IndDev, perc) );
    DPC.DpcOpenData( phif, NameDev, perc);
    if( ParseError( perc[0] )==true ){return false;}
    DPC.DpcPutReg( phif[0], Addr, Data[0], perc);
    if( ParseError( perc[0] )==true ){return false;}
    DPC.DpcCloseData(phif[0], perc);
    return true;
}



private boolean GetDataFunc(){
    Addr = Address.getSelectedIndex();
    Data[0] = Datas.getSelectedIndex();
    int DevCount = DPC.DvmgGetDevCount(perc);
    if( DevCount==0 ){
        JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    NameDev = new String( DPC.DvmgGetDevName( IndDev, perc) );
    DPC.DpcOpenData( phif, NameDev, perc);
    if( ParseError( perc[0] )==true ){return false;}
    DPC.DpcGetReg( phif[0], Addr, Data, perc);
    if( ParseError( perc[0] )==true ){return false;}
    Datas.setSelectedIndex( Data[0] );
    DPC.DpcCloseData(phif[0], perc);
    return true;
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
    private boolean ChosseFileF(){
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
        chooser.setDialogType(OPEN_DIALOG);
        chooser.setApproveButtonText( "Abrir" );
        chooser.showDialog( MainFrame, null );
        if( chooser.getSelectedFile()!=null ){
            //System.out.println( File2Send );
            File2Send = new String( chooser.getSelectedFile().getAbsolutePath() );
            CurrentDir = new String( chooser.getSelectedFile().getPath() );
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



        //JOptionPane.showMessageDialog(  rootPane, File2Send );
        Addr = Address.getSelectedIndex();
        int DevCount = DPC.DvmgGetDevCount(perc);
        if( DevCount==0 ){
            JOptionPane.showMessageDialog(  rootPane, "No hay dispositivos en lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        NameDev = new String( DPC.DvmgGetDevName( IndDev, perc) );
        DPC.DpcOpenData( phif, NameDev, perc);
        if( ParseError( perc[0] )==true ){return false;}


        
        
        DPC.DpcPutRegRepeat( phif[0], Addr, Buffer2send, Datas2send, perc);
        if( ParseError( perc[0] )==true ){return false;}

        DPC.DpcCloseData(phif[0], perc);

        
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
        //System.out.println( PATHJAVAHOMEP );
    }



    
///    MY_NEXIS2
///    SN:10054D273531
    private int FeelSelected = 1;
    private void DeviceManager() {
        //DVMGSTARTCONFIGUREDEVICES FFF;
        LAUNCH DVCMND = new LAUNCH();
        int AAA = LAUNCH.main( MainFrame );
        if( AAA!=0 )
            JOptionPane.showMessageDialog(  rootPane, "Resultado,\n" + AAA );
/*
	try{
            FileReader Fr =
new FileReader( System.getProperty( "user.home" ) + "\\Application Data\\Digilent\\dvctbl.txt" );
            BufferedReader entrada = new BufferedReader( Fr );
            String s;
            String Texto = new String();
            while( (s=entrada.readLine())!=null ){
                Texto = Texto + s;
		}
            Fr.close();
            //JOptionPane.showMessageDialog(  rootPane, "Resultado,\n" + Texto );
	}catch( java.io.IOException e){System.out.println( e );}
        //*/
        /*
        DPC.DvmgStartConfigureDevices( MainFrame, perc);
        int NdevicesCon = DPC.DvmgGetDevCount(perc);
        if( NdevicesCon>0 ){
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DPC.DvmgGetDevName(i, perc);}
            NameDevs.setListData( ListDevice );
            NameDevs.setSelectedIndex( 0 );
            IndDev = 0;
        }else{
            ListDevice = new String[1];
            NameDevs.setListData( ListDevice );
        }
        //*/
    }

    private void InDevFunc() {
        IndDev = NameDevs.getSelectedIndex();
    }





    
    private void PRUEBAS() {
        FileDialog FlD = new FileDialog( MainFrame, "Abrir", FileDialog.SAVE );
        FlD.setIconImage( Toolkit.getDefaultToolkit().getImage("lib\\USB.png") );
        FlD.setLocation( 300 , 300 );
        FlD.setSize( 300 , 300 );
        //SwingUtilities.updateComponentTreeUI( FlD );
        //FlD.setVisible( true );
        FlD.show();
        //File Fl = new File( "C:\\" );
        //FlD.show();
    }

}
//ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE