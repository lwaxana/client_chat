package userlist;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import message.Utilisateur;

public class UserRenderer extends JLabel implements ListCellRenderer<Utilisateur>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRenderer() {
		setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Utilisateur> list,
			Utilisateur value, int index, boolean isSelected, boolean cellHasFocus) {
		
		int type = value.getType();
		
		ImageIcon image = null;
		if ( type == 1){
			image = new ImageIcon(getClass().getResource("/img/blue1.jpg")); 
		}
		if ( type == 2){
			image = new ImageIcon(getClass().getResource("/img/green1.jpg")); 
		}
		if ( type == 3){
			image = new ImageIcon(getClass().getResource("/img/red1.jpg")); 
		}
		
		
		setIcon(image);
        setText(value.getNom()); 
		
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        } 
        
		
		return this;
	}

	
}
