package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class UtilsMessage {

/**
 * 
 * @param o
 * @return
 * @throws IOException
 */
	public static String toString( Serializable o ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream( baos );
		oos.writeObject( o );
		oos.close();
		return new String( Base64Coder.encode( baos.toByteArray() ) );
	}

/**
 * 
 * @param s
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public static Object fromString( String s ) throws IOException ,ClassNotFoundException {
		byte [] data = Base64Coder.decode( s );
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(  data ) );
		Object o  = ois.readObject();
		ois.close();
		return o;
	}

}
