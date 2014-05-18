/**
 * 
 */
package main;


import ihm.Fenetre;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.Client;
import control.Control;

/**
 * @author Lwaxana
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Control ctrl = new Control();
					Fenetre fen = new Fenetre();
					Client client = new Client();
					client.setFenetre(fen);
					fen.setListener(ctrl);
					fen.setcontrol(ctrl);
					ctrl.setFen(fen);
					if ( SwingUtilities.isEventDispatchThread()){
						fen.afficher();
						fen.selectWIIF();
					}
					
					ctrl.setClient(client);

				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			}
		});;
		}
				
	}


