/**
 * 
 */
package ihm;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameListener;

import utils.UtilsValidation;
import java.awt.ComponentOrientation;
import java.awt.Component;

/**
 * @author Lwaxana
 *
 */
public class WelcomeInscirptionIFrame extends JInternalFrame {
	
	
	private JPanel panel_main;
	private JPanel panel_1;
	
	private JPanel panel_2;

	private boolean prenom = false;
	private boolean nom = false;
	private boolean mail = false;
	private boolean mail2 = false;
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtPrnom;
	private JTextField txtNomDeFamille;
	private JTextField txtEmailUsername;
	private JTextField txtConfirmerLemail;
	
	private JButton btn_continuer;
	private JButton btn_login;
	
	
	public WelcomeInscirptionIFrame() {
		super("Bienvenue");
		setResizable(false);
		setClosable(true);
		setSize(500, 327);
		build();
		
		Dimension desktopSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension jInternalFrameSize = getSize();
		setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		setVisible(true);
		revalidate();
	}
	
	public void build(){
				
		Dimension dim1 = new Dimension(200,20);
		Dimension dim2 = new Dimension(170, 20);
		Dimension dim4 = new Dimension(120,15);
		
		JLabel lblNewLabel = new JLabel("Super Chat");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
		PanelImage panel_img = new PanelImage();
				
		JLabel lblNewLabel_1 = new JLabel("Username (e-mail)");
		lblNewLabel_1.setMinimumSize(new Dimension(120, 15));
		lblNewLabel_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNewLabel_1.setMaximumSize(dim4);
		lblNewLabel_1.setPreferredSize(dim4);
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setPreferredSize(dim4);
		lblNewLabel_2.setMaximumSize(dim4);
		
		btn_login = new JButton("Login");
		btn_login.setSize(new Dimension(90, 30));
		btn_login.setPreferredSize(new Dimension(90, 30));
		btn_login.setMinimumSize(new Dimension(90, 30));
		btn_login.setMaximumSize(new Dimension(90, 25));
		btn_continuer = new JButton("Continuer inscription");		
		btn_continuer.setMaximumSize(new Dimension(140, 95));			
		
		
		textField = new JTextField();
		textField.setAlignmentY(Component.TOP_ALIGNMENT);
		textField.setAlignmentX(Component.LEFT_ALIGNMENT);
		textField.setMaximumSize(dim2);
		textField.setMinimumSize(dim2);
		textField.setPreferredSize(dim2);
		passwordField = new JPasswordField();
		passwordField.setAlignmentY(Component.TOP_ALIGNMENT);
		passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordField.setMaximumSize(dim2);
		passwordField.setMinimumSize(dim2);
		passwordField.setPreferredSize(dim2);
		
		txtPrnom = new JTextField();
		txtPrnom.setText("Prénom");
		txtPrnom.setColumns(50);
		txtPrnom.setMaximumSize(dim1);
						
		txtNomDeFamille = new JTextField();
		txtNomDeFamille.setText("Nom de Famille");
		txtNomDeFamille.setColumns(100);
		txtNomDeFamille.setMaximumSize(dim1);
				
		txtEmailUsername = new JTextField();
		txtEmailUsername.setText("E-mail ( username )");
		txtEmailUsername.setColumns(100);
		txtEmailUsername.setMaximumSize(dim1);
			
		txtConfirmerLemail = new JTextField();
		txtConfirmerLemail.setText("Confirmer l'e-mail");
		txtConfirmerLemail.setColumns(100);
		txtConfirmerLemail.setMaximumSize(dim1);
		
		JPanel panelp = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setMaximumSize(new Dimension(480, 80));
		panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		JPanel panel3 = new JPanel();
		panel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel3.setMaximumSize(new Dimension(480, 130));
		JPanel panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(300, 120));
		
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.PAGE_AXIS));
		panel4.setBorder(BorderFactory.createTitledBorder("Inscription"));
		panel4.add(Box.createRigidArea(new Dimension(0, 3)));
		panel4.add(txtPrnom);
		panel4.add(Box.createRigidArea(new Dimension(0, 3)));
		panel4.add(txtNomDeFamille);
		panel4.add(Box.createRigidArea(new Dimension(0, 3)));
		panel4.add(txtEmailUsername);
		panel4.add(Box.createRigidArea(new Dimension(0, 3)));
		panel4.add(txtConfirmerLemail);
		panel4.add(Box.createRigidArea(new Dimension(250, 0)));
		panel4.add(Box.createRigidArea(new Dimension(0, 3)));
		panel4.setMaximumSize(new Dimension(250, 120));
		
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setMaximumSize(new Dimension(5, 5));
		panel3.add(rigidArea_1);
		panel3.add(panel4);
		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
		rigidArea.setMaximumSize(new Dimension(40, 0));
		panel3.add(rigidArea);
		panel3.add(btn_continuer);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
		panel2.setBorder(BorderFactory.createTitledBorder("Déjà inscrit ?"));
		JPanel panel21 = new JPanel();
		panel21.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel21.setPreferredSize(new Dimension(150, 50));
		Dimension dim3 = new Dimension(170, 50);
		panel21.setMinimumSize(dim3);
		panel21.setMaximumSize(new Dimension(150, 50));
		panel21.setSize(dim3);
		
		panel21.setLayout(new BoxLayout(panel21, BoxLayout.PAGE_AXIS));
		panel21.add(Box.createRigidArea(new Dimension(0, 2)));
		panel21.add(lblNewLabel_1);
		panel21.add(Box.createRigidArea(new Dimension(0, 2)));
		panel21.add(textField);
		JPanel panel22 = new JPanel();
		panel22.setMinimumSize(dim3);
		panel22.setMaximumSize(new Dimension(150, 50));
		panel22.setSize(dim3);
		panel22.setLayout(new BoxLayout(panel22, BoxLayout.PAGE_AXIS));
		panel22.add(Box.createRigidArea(new Dimension(0, 2)));
		panel22.add(lblNewLabel_2);
		panel22.add(Box.createRigidArea(new Dimension(0, 2)));
		panel22.add(passwordField);
		panel2.add(Box.createRigidArea(new Dimension(10,0)));
		panel2.add(panel21);
		panel2.add(Box.createRigidArea(new Dimension(10,0)));
		panel2.add(panel22);
		panel2.add(Box.createRigidArea(new Dimension(10,0)));
		panel2.add(btn_login);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		panel1.add(panel2);
		panel4.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(panel3);
		
		JPanel panel11 = new JPanel();
		panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));
		panel11.add(panel_img);
		Dimension dim5 = new Dimension(50,50);
		panel_img.setPreferredSize(dim5);
		panel_img.setMaximumSize(dim5);
		panel11.add(lblNewLabel);
		
		panelp.setLayout(new BoxLayout(panelp, BoxLayout.PAGE_AXIS));
	
		panelp.add(panel11);
		panelp.add(panel1);
		
		getContentPane().add(panelp);
		
		
		
		
		// FOCUS LISTENER
		txtConfirmerLemail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				mail2 = txtConfirmerLemail.getText().equals(getTxtEmailUsername());			
				if ( !mail2){
					txtConfirmerLemail.setText("Confirmer l'e-mail");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtConfirmerLemail.setText("");
				
			}
		});	
		
		txtEmailUsername.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				mail = UtilsValidation.checkUserName(getTxtEmailUsername());
				if ( !mail){
					txtEmailUsername.setText("E-mail ( username )");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if ( !mail){
					txtEmailUsername.setText("");
				}
				
			}
		});
		
		txtNomDeFamille.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				nom = UtilsValidation.checkNom(getTxtNomDeFamille());
				if ( !nom){
					txtNomDeFamille.setText("Nom de famille");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (!nom){
					txtNomDeFamille.setText("");
				}
				
			}
		});
		
		txtPrnom.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				String txt = getTxtPrnom();
				prenom = UtilsValidation.checkPrenom(txt);
				
				if ( !prenom ){
					txtPrnom.setText("Prénom");
				}								
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if ( !prenom ){
					txtPrnom.setText("");
				}				
			}
		});
		
		btn_continuer.setActionCommand("continuerwf");
		btn_login.setActionCommand("loginwf");		
		passwordField.setToolTipText("Entrez votre mot de passe ( 6 -25 caractères ) ");
		txtPrnom.setToolTipText("Entrez votre prénom (Pas d'espace, ni de tiret)");
		
	}
	
		
	public void setListener(EventListener al){
		btn_continuer.addActionListener((ActionListener)al);	
		btn_login.addActionListener((ActionListener)al);
		addInternalFrameListener((InternalFrameListener)al);
		
	}

	/**
	 * @return the textField
	 */
	public String getTextField() {
		return textField.getText();
	}

	/**
	 * @return the passwordField
	 */
	public String getPasswordField() {
		return new String(passwordField.getPassword());
	}

	/**
	 * @return the txtPrnom
	 */
	public String getTxtPrnom() {
		return txtPrnom.getText();
	}

	/**
	 * @return the txtNomDeFamille
	 */
	public String getTxtNomDeFamille() {
		return txtNomDeFamille.getText();
	}

	/**
	 * @return the txtEmailUsername
	 */
	public String getTxtEmailUsername() {
		return txtEmailUsername.getText();
	}

	/**
	 * @return the txtConfirmerLemail
	 */
	public String getTxtConfirmerLemail() {
		return txtConfirmerLemail.getText();
	}

	/**
	 * @return the prenom
	 */
	public boolean isPrenom() {
		return prenom;
	}

	/**
	 * @return the nom
	 */
	public boolean isNom() {
		return nom;
	}

	/**
	 * @return the mail
	 */
	public boolean isMail() {
		return mail;
	}

	/**
	 * @return the mail2
	 */
	public boolean isMail2() {
		return mail2;
	}

	public boolean validationOk(){
		if ( mail && mail2 && prenom && nom){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}


