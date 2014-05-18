package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilsSHA256pwd {
	
	public static String encoderMotDePasse(String str){
		MessageDigest msgd = null;
		try {
			 msgd = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msgd.update(str.getBytes());
		return javax.xml.bind.DatatypeConverter.printHexBinary( msgd.digest());
		
	}
	
}
