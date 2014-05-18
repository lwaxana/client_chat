/**
 * 
 */
package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocket;

import utils.UtilsMessage;
import message.Message;


/**
 * @author Lwaxana
 *
 */
public class Write {

	private Socket socket;
	private OutputStream os;
	private SSLSocket sslsocket;
	private Socket currentsocket;
	private Message message;
	private OutputStreamWriter osw;
	private BufferedWriter bw;
	
	public Write(Socket socket){
		this.socket = socket;
		this.currentsocket = socket;
		setWriter(currentsocket);
	}
	
	/**
	 * 
	 * @param message
	 */
	public void envoyerMessage(Message message){
		try {
			bw.write(UtilsMessage.toString(message)+'\n');
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void downSSL() {
		try {
			currentsocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentsocket = socket;
		setWriter(currentsocket);
		
	}

	/**
	 * @param sslsocket2
	 */
	public void upgradeSSL(SSLSocket sslsocket2) {
		currentsocket = sslsocket2;
		setWriter(currentsocket);
		
	}
	
	/**
	 * 
	 */
	public void setWriter(Socket currentsocket){
		try {
			os = currentsocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		osw = new OutputStreamWriter(os);
		bw = new BufferedWriter(osw);		
	}

	/**
	 * 
	 */
	public void close(){
		try {
			bw.close();
			osw.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
