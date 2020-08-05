package RESOURCES;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Hashtable;

public class RESOURCES {

    public static RESOURCES singleton = new RESOURCES();
    private Hashtable<String, Image> ht=new Hashtable<String, Image>();
    public static Image CargarPorNombre( String ByName ){
        return singleton.cargaImagen( ByName );
    }




    public static URL GetUrlFile( String FileNS ){
        URL url = singleton.getClass().getResource(FileNS);
        //javax.swing.JOptionPane.showMessageDialog( null, singleton.getClass().getResource(FileNS) );
        return url;
    }



    public Image cargaImagen(String fichero){
        if (!ht.containsKey(fichero)){
            URL url = singleton.getClass().getResource(fichero);
            ht.put(fichero, Toolkit.getDefaultToolkit().getImage(url));
        }
	return ht.get(fichero);
    }

    public static Image leerFondo(int n){
        return singleton.cargaImagen("" + n + ".jpg");
    }

    public static Image leerDiana(int n){
        return singleton.cargaImagen("D" + n + ".png");
    }

}
