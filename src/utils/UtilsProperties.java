/**
 * 
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.File;

/**
 * @author Lwaxana
 *
 */
public class UtilsProperties {

	public static void creerFichierProperties(){
		Properties props = new Properties();
		OutputStream os = null;
		
		try {
			os = new FileOutputStream("c:\\chat\\config.properties");
			props.store(os, "null");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if ( os != null ){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean fichierPropExiste(){
		String file = "c:\\chat\\config.properties";
		if ( new File(file).exists()  ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static Properties lireFichierProp(){
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("c:\\chat\\config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}
	
	public static void ajouterProperties(Properties props, String key, String value){
		props.setProperty(key, value);
		OutputStream os = null;
		try {
			os = new FileOutputStream("c:\\chat\\config.properties");
			props.store(os, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if ( os != null ){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
