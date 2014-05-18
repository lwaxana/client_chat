/**
 * 
 */
package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.print.attribute.AttributeSet;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import listener.PopupListener;
import message.Utilisateur;
import typemessage.TypeMessage;
import userlist.UserRenderer;

/**
 * @author Lwaxana
 *
 */
public class Pane extends JPanel {

	private JTextArea textPane = null;
	private Document document = null;
	private Dimension dimtextpane = null;
	private Dimension dimlisteuser = null;
	private JList<Utilisateur> listeuser = null;
	private DefaultListModel<Utilisateur> listmodel = null;
	private JScrollPane scrolltextpane = null;
	private JScrollPane scrolljliste = null;
	private Font customFont;
	private PopupMenu popup;
	private MouseListener popupListener;
	private int droits;
	
	
	public Pane(int droits){
		super();
		this.droits = droits;
		System.out.println("droits pane "+this.droits);
		try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/Fixedsys500c.ttf")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("lib/Fixedsys500c.ttf")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
		this.setSize(800, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dimlisteuser = new Dimension(150, (int)screenSize.getHeight()-50); 
		dimtextpane = new Dimension( (int)screenSize.getWidth()-300, (int)screenSize.getHeight() );
		
		listeuser = new JList<>();
		listmodel = new DefaultListModel<>();
		listeuser.setModel(listmodel);
		listeuser.setPreferredSize(dimlisteuser);
		listeuser.setMaximumSize(dimlisteuser);
		listeuser.setMinimumSize(dimlisteuser);
		listeuser.setCellRenderer(new UserRenderer());
		
		listeuser.setFont(customFont);
		
		scrolljliste = new JScrollPane(listeuser, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrolljliste.setPreferredSize(dimlisteuser);
		textPane = new JTextArea();
		document = textPane.getDocument();
		textPane.setLineWrap(true);
		textPane.setMaximumSize(dimtextpane);
		
		scrolltextpane = new JScrollPane(textPane);
		scrolltextpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrolltextpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textPane.setEditable(false);
		
		scrolltextpane.setPreferredSize(dimtextpane);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(scrolltextpane);
		add(scrolljliste);
		textPane.setFont(customFont);
		popup = new PopupMenu(droits);
		popupListener = new PopupListener(popup);
		listeuser.addMouseListener(popupListener);
		listeuser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public void setDoc(String str, TypeMessage type){
		final StyleContext cont = StyleContext.getDefaultStyleContext();
		javax.swing.text.AttributeSet normal = cont.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);
		javax.swing.text.AttributeSet prive = cont.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);
		javax.swing.text.AttributeSet valeur = null;
		if ( type == TypeMessage.MessageChannel){
			valeur = normal;
		}
		if ( type == TypeMessage.MessagePrive){
			valeur = prive;
		}
		if ( type == TypeMessage.ChangementNickUser){
			valeur = normal;
		}
		
		try {
			
			document.insertString(document.getEndPosition().getOffset(), str + System.getProperty("line.separator"), (javax.swing.text.AttributeSet) valeur);
			textPane.setCaretPosition(document.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ajouterListUser(Utilisateur[] liste){
		for ( int i = 0; i < liste.length; i++){
			listmodel.addElement(liste[i]);
		}		
	}
	
	public void ajouterUser(Utilisateur user){
		listmodel.addElement(user);
	}
	
	public void retirerUser(Utilisateur user){
		listmodel.removeElement(user);
	}
	
	public void changerNick(Utilisateur[] users){
		listmodel.clear();
		ajouterListUser(users);
	}
		
	public void setListener(EventListener al){
		popup.setListener((ActionListener)al);
	}
	
	public Utilisateur getSelectedUtilisateur(){
		return listmodel.getElementAt(listeuser.getSelectedIndex());
	}
	
	public void setDroits(int droits){
		popup = new PopupMenu(droits);
		popupListener = new PopupListener(popup);
		listeuser.addMouseListener(popupListener);
		listeuser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}
