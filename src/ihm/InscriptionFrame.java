package ihm;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EventListener;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.DropMode;

import utils.UtilsValidation;



public class InscriptionFrame extends JInternalFrame {

	private JTextField textField_uname;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_rue;
	private JTextField textField_num;
	private JTextField textField_ville;
	private JTextField textField_cp;
	private JButton btn_annuler;
	private JButton btn_valider;
	private JComboBox<String> comboBox;
	private JTextField textField_prenom;
	private JTextField textField_nom;
	private Set<String> pays;
	private boolean pwd = false;
	private boolean pwd2 = false;
	private boolean rue = true;
	private boolean num = true;
	private boolean ville = true;
	private boolean cp = true;
	
	
	public InscriptionFrame(){
		super("Inscription");		
		setResizable(false);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 442, 508);
		pays = new TreeSet<>();
		getContentPane().setLayout(null);
		build();
		setVisible(true);
	}
	
	public void build(){
		JPanel panel_main = new JPanel();
		panel_main.setBounds(0, 0, 416, 434);
		getContentPane().add(panel_main);
		panel_main.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Super Chat");
		lblNewLabel.setBounds(181, 20, 105, 33);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_main.add(lblNewLabel);
		
		PanelImage panel_img = new PanelImage();
		panel_img.setBounds(121, 11, 50, 50);
		panel_main.add(panel_img);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Inscription", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 64, 413, 359);
		panel_main.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Username & Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 20, 393, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username ( e-mail )");
		lblNewLabel_1.setBounds(10, 18, 101, 14);
		panel_1.add(lblNewLabel_1);
		
		textField_uname = new JTextField("");
		textField_uname.setEnabled(true);
		textField_uname.setEditable(false);
		textField_uname.setBounds(146, 15, 180, 20);
		panel_1.add(textField_uname);
		textField_uname.setColumns(100);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(10, 40, 64, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblConfirmerPassword = new JLabel("Confirmer password");
		lblConfirmerPassword.setBounds(10, 63, 126, 14);
		panel_1.add(lblConfirmerPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(25);
		passwordField.setBounds(146, 37, 180, 20);
		panel_1.add(passwordField);
		passwordField.setToolTipText("Entre 8 et 25 caracteres, 1 majuscule, 1 minuscule, 1 chiffre");
		passwordField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				pwd = UtilsValidation.checkPwd(getPasswordField());
				if ( !pwd ){
					passwordField.setText("");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if ( !pwd){
					passwordField.setText("");
				}
				
			}
		});
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(25);
		passwordField_1.setBounds(146, 60, 180, 20);
		passwordField_1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				pwd2 = getPasswordField_1().equals(getPasswordField());
				if ( !pwd2  ){
					passwordField_1.setText("");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if ( !pwd2){
					passwordField_1.setText("");
				}
				
			}
		});
		panel_1.add(passwordField_1);
		
		JPanel panel_img_username = new JPanel();
		panel_img_username.setBounds(331, 16, 23, 20);
		panel_1.add(panel_img_username);
		
		JPanel panel_pwd = new JPanel();
		panel_pwd.setBounds(331, 38, 23, 20);
		panel_1.add(panel_pwd);
		
		JPanel panel_confirm_pwd = new JPanel();
		panel_confirm_pwd.setBounds(331, 60, 23, 20);
		panel_1.add(panel_confirm_pwd);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adresse ( Obligatoire )", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 189, 393, 159);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Rue");
		lblNewLabel_3.setBounds(10, 25, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Numéro");
		lblNewLabel_4.setBounds(10, 50, 46, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ville");
		lblNewLabel_5.setBounds(10, 75, 46, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("CP");
		lblNewLabel_6.setBounds(10, 99, 46, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Pays");
		lblNewLabel_7.setBounds(10, 124, 46, 14);
		panel_2.add(lblNewLabel_7);
		
		textField_rue = new JTextField("");
		textField_rue.setDropMode(DropMode.INSERT);
		textField_rue.setBounds(87, 22, 238, 20);
		panel_2.add(textField_rue);
		textField_rue.setColumns(100);
	
		
		textField_num = new JTextField("");
		textField_num.setBounds(87, 47, 86, 20);
		panel_2.add(textField_num);
		textField_num.setColumns(10);
		
		textField_ville = new JTextField("");
		textField_ville.setBounds(87, 72, 172, 20);
		panel_2.add(textField_ville);
		textField_ville.setColumns(50);
		
		textField_cp = new JTextField("");
		textField_cp.setBounds(87, 96, 86, 20);
		panel_2.add(textField_cp);
		textField_cp.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(87, 121, 238, 20);
		comboBox.setEditable(false);
		panel_2.add(comboBox);
		
		JPanel panel_rue = new JPanel();
		panel_rue.setBounds(331, 22, 23, 20);
		panel_2.add(panel_rue);
		
		JPanel panel_num = new JPanel();
		panel_num.setBounds(331, 45, 23, 20);
		panel_2.add(panel_num);
		
		JPanel panel_ville = new JPanel();
		panel_ville.setBounds(331, 70, 23, 20);
		panel_2.add(panel_ville);
		
		JPanel panel_cp = new JPanel();
		panel_cp.setBounds(331, 95, 23, 20);
		panel_2.add(panel_cp);
		
		JPanel panel_pays = new JPanel();
		panel_pays.setBounds(331, 120, 23, 20);
		panel_2.add(panel_pays);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Vos informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 111, 393, 79);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Prénom");
		lblNewLabel_8.setBounds(10, 21, 46, 14);
		panel_3.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Nom");
		lblNewLabel_9.setBounds(10, 46, 46, 14);
		panel_3.add(lblNewLabel_9);
		
		textField_prenom = new JTextField("");
		textField_prenom.setEditable(false);
		textField_prenom.setBounds(73, 18, 239, 20);
		panel_3.add(textField_prenom);
		textField_prenom.setColumns(50);
		
		textField_nom = new JTextField("");
		textField_nom.setEditable(false);
		textField_nom.setColumns(100);
		textField_nom.setBounds(73, 43, 239, 20);
		panel_3.add(textField_nom);
		
		JPanel panel_prenom = new JPanel();
		panel_prenom.setBounds(331, 15, 23, 20);
		panel_3.add(panel_prenom);
		
		JPanel panel_nom = new JPanel();
		panel_nom.setBounds(331, 43, 23, 20);
		panel_3.add(panel_nom);
		
		btn_annuler = new JButton("Annuler");
		btn_annuler.setBounds(323, 445, 89, 23);
		getContentPane().add(btn_annuler);
		
		btn_valider = new JButton("Valider");
		btn_valider.setBounds(224, 445, 89, 23);
		getContentPane().add(btn_valider);

		btn_annuler.setActionCommand("inscriptionannuler");
		btn_valider.setActionCommand("inscriptionvalider");
		
		
		String fichier ="src/lib/pays.txt";
		InputStream ips = null;
		BufferedReader br1 = null;
		try {
			ips = new FileInputStream(fichier);
			InputStreamReader ipsr=new InputStreamReader(ips);
			br1=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br1.readLine())!=null){
				pays.add(ligne.trim());				
			}
			br1.close();
			ipsr.close();
			ips.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for ( String s : pays){
			comboBox.addItem(s);
		}
		comboBox.setSelectedIndex(-1);
		
	}
	
	public void setListener(EventListener al){
		btn_annuler.addActionListener((ActionListener)al);
		btn_valider.addActionListener((ActionListener)al);
		
	}

	/**
	 * @return the textField_uname
	 */
	public String getTextField_uname() {
		return textField_uname.getText().toString();
	}

	/**
	 * @return the passwordField
	 */
	public String getPasswordField() {
		return new String(passwordField.getPassword());
	}

	/**
	 * @return the passwordField_1
	 */
	public String getPasswordField_1() {
		return new String(passwordField_1.getPassword());
	}

	/**
	 * @return the textField_rue
	 */
	public String getTextField_rue() {
		return textField_rue.getText().toString();
	}

	/**
	 * @return the textField_num
	 */
	public String getTextField_num() {
		return textField_num.getText();
	}

	/**
	 * @return the textField_ville
	 */
	public String getTextField_ville() {
		return textField_ville.getText();
	}

	/**
	 * @return the textField_cp
	 */
	public String getTextField_cp() {
		return textField_cp.getText();
	}

	/**
	 * @return the textField_prenom
	 */
	public String getTextField_prenom() {
		return textField_prenom.getText();
	}

	/**
	 * @return the textField_nom
	 */
	public String getTextField_nom() {
		return textField_nom.getText();
	}

	/**
	 * @param textField_uname the textField_uname to set
	 */
	public void setTextField_uname(String str) {
		this.textField_uname.setText(str);;
	}

	
	/**
	 * @param textField_prenom the textField_prenom to set
	 */
	public void setTextField_prenom(String str) {
		this.textField_prenom.setText(str);
	}

	/**
	 * @param textField_nom the textField_nom to set
	 */
	public void setTextField_nom(String str) {
		this.textField_nom.setText(str);
	}
	
	public String getPays(){
		return (String) comboBox.getSelectedItem();
	}
	
	
	public boolean validationOk(){
		if ( pwd && pwd2 && rue && num && cp && ville && comboBox.getSelectedIndex()>=0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
