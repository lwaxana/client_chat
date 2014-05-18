/**
 * 
 */
package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

import message.Message;
import utils.UtilsMessage;

/**
 * @author Lwaxana
 *
 */
public class Read extends Observable implements Runnable{

	private Socket socket;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	private boolean connect;
	private Message message;
	
	
	public Read(Socket socket){
		this.socket = socket;
		connect = true;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String string;
		try {
			while ( connect ){
				string = br.readLine();
				message = (Message) UtilsMessage.fromString(string);
				setChanged();
				notifyObservers(message);
				string = null;
				message = null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	/**
	 * 
	 * @param b
	 */
	public void setConnect(boolean b){
		this.connect = b;
	}
		
	/**
	 * 
	 */
	public void close(){
		connect = false;
		
		
	}
	
}

