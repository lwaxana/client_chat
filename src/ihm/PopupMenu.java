package ihm;

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import control.Control;

public class PopupMenu extends JPopupMenu {
	
	
	
	private JMenuItem msgprive = null;
	private JMenuItem convpriv = null;
	private JMenuItem kick = null;
	private JMenuItem infos = null;
	private JMenuItem ban = null;
	private int droits;
	
	public PopupMenu(int droits){
		this.droits = droits;
		if ( droits == 1){
			build1();
		}
		if ( droits == 2){
			build2();
		}
		if ( droits == 3){
			build3();
		}
	}
	
	public void setListener(EventListener al){
		if ( droits == 1){
			msgprive.addActionListener((ActionListener)al);
			convpriv.addActionListener((ActionListener)al);
		}
		if ( droits == 2){
			kick.addActionListener((ActionListener)al);
			msgprive.addActionListener((ActionListener)al);
			convpriv.addActionListener((ActionListener)al);
			
		}
		if ( droits == 3){
			kick.addActionListener((ActionListener)al);
			msgprive.addActionListener((ActionListener)al);
			convpriv.addActionListener((ActionListener)al);
			ban.addActionListener((ActionListener)al);
		}
		
		
	}
	
	public void build1(){
		msgprive = new JMenuItem("Message privé");
		convpriv = new JMenuItem("Conv privée");
		add(msgprive);
		add(convpriv);
		msgprive.setActionCommand("message_prive");
		convpriv.setActionCommand("conv_prive");
	}
	
	public void build2(){
		build1();
		kick = new JMenuItem("Kick");
		add(kick);
		kick.setActionCommand("kick");		
	}
	
	public void build3(){
		build1();
		build2();
		ban = new JMenuItem("Ban");
		add(ban);
		ban.setActionCommand("ban");
	}
	
	
}
