/**
 * 
 */
package ihm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

/**
 * @author Lwaxana
 *
 */
public class Pane2 extends JPanel {

	private JTextPane textPane = null;
	private Document document = null;
	private Dimension dimtextpane = null;
	private JScrollPane scrolltextpane = null;
	private Font customFont;
	private PopupMenu popup;

	
	public Pane2(){
		super();
		this.setSize(800, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dimtextpane = new Dimension( (int)screenSize.getWidth()-200, (int)screenSize.getHeight() );
		textPane = new JTextPane();

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
		dimtextpane = new Dimension( (int)screenSize.getWidth()-200, (int)screenSize.getHeight() );
		
		textPane = new JTextPane();
		textPane.setFont(customFont);
		document = (DefaultStyledDocument)textPane.getDocument();
		textPane.setMaximumSize(dimtextpane);
		scrolltextpane = new JScrollPane(textPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textPane.setEditable(false);
		scrolltextpane.setPreferredSize(dimtextpane);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(scrolltextpane);
		
	}
	
	public void setDoc(String str){
		try {
			document.insertString(document.getEndPosition().getOffset(), str+"\n", null);
			textPane.setCaretPosition(document.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
