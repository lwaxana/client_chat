package ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import control.Control;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Infos2 extends JInternalFrame {

	private JPanel panel;
	private JLabel nomserveur;
	private JLabel lblipserveur;
	private JTextField lblip;
	private JPanel panel_1;
	private JTree tree;
	private JScrollPane scrollPane;
	private JTextField lblnom;
	private DefaultMutableTreeNode channel;
	private DefaultTreeModel treemodel;
	private Control control;
	private Set<String> listeChans;


	public Infos2(){
		super("Info2");
		build();
		setVisible(true);
		setSize(300, 600);

	}


	public void build(){
		
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Serveur", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl_panel = new GridBagLayout();

		panel.setLayout(gbl_panel);

		nomserveur = new JLabel("Nom :");
		nomserveur.setPreferredSize(new Dimension(50, 20));



		GridBagConstraints gbc_nomserveur = new GridBagConstraints();
		gbc_nomserveur.anchor = GridBagConstraints.NORTH;
		gbc_nomserveur.insets = new Insets(0, 0, 5, 0);
		gbc_nomserveur.gridx = 0;
		gbc_nomserveur.gridy = 0;
		panel.add(nomserveur, gbc_nomserveur);		

		lblipserveur = new JLabel("IP :");
		lblipserveur.setPreferredSize(new Dimension(50, 20));
		


		GridBagConstraints gbc_lblipserveur = new GridBagConstraints();
		gbc_lblipserveur.anchor = GridBagConstraints.NORTH;
		gbc_lblipserveur.insets = new Insets(0, 0, 5, 0);
		gbc_lblipserveur.gridx = 0;
		gbc_lblipserveur.gridy = 1;
		panel.add(lblipserveur, gbc_lblipserveur);

		lblnom = new JTextField("");
		lblnom.setPreferredSize(new Dimension(150, 20));
		lblnom.setEditable(false);


		GridBagConstraints gbc_lblnom = new GridBagConstraints();
		gbc_lblnom.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnom.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblnom.insets = new Insets(0, 0, 5, 0);
		gbc_lblnom.gridx = 1;
		gbc_lblnom.gridy = 0;
		panel.add(lblnom, gbc_lblnom);

		lblip = new JTextField("");
		lblip.setPreferredSize(new Dimension(150, 20));
		lblip.setEditable(false);
		
		GridBagConstraints gbc_lblip = new GridBagConstraints();
		gbc_lblip.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblip.gridx = 1;
		gbc_lblip.gridy = 1;
		panel.add(lblip, gbc_lblip);

		panel_1 = new JPanel();
		panel_1.setSize(new Dimension(250, 250));
		panel_1.setMinimumSize(new Dimension(250, 250));
		panel_1.setMaximumSize(new Dimension(250, 250));
		panel_1.setBorder(new TitledBorder(null, "Channels", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setPreferredSize(new Dimension(260,260));

		channel = new DefaultMutableTreeNode("Canaux");
		treemodel = new DefaultTreeModel(channel);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tree = new JTree(treemodel);
		tree.setBounds(10, 23, 240, 234);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(240, 230));

		scrollPane.setViewportView(tree);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scrollPane);
		listeChans = new TreeSet<>();

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if(selRow != -1) {
					if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
						String str = selPath.getLastPathComponent().toString();
						control.selectChannel(str);

					}
				}
			}
		};
		tree.addMouseListener(ml);
		
		add(panel, BorderLayout.NORTH);
		add(panel_1, BorderLayout.CENTER);
		JPanel panel_2 = new JPanel();
		panel_2.setSize(new Dimension(0, 200));
		panel_2.setMaximumSize(new Dimension(32767, 200));
		panel_2.setMinimumSize(new Dimension(10, 200));
		panel_2.add(Box.createRigidArea(new Dimension(0, 250)));
		add(panel_2, BorderLayout.SOUTH);
		
	}

	public void ajouterChannelTree(String[] liste){
		channel.removeAllChildren();		
		for ( String s : liste){
			listeChans.add(s);
		}
		for ( String s : listeChans){
			channel.add(new DefaultMutableTreeNode(s));
		}
		treemodel.reload(channel);

	}

	public void ajouterChannelTree(String str, Set<String> listeChans ){
		channel.removeAllChildren();
		listeChans.add(str);
		for ( String s : listeChans){
			channel.add(new DefaultMutableTreeNode(s));
		}
		treemodel.reload(channel);		

	}

	public void infosServeur(String[] liste){
		lblnom.setText(liste[0]);
		lblip.setText(liste[1]);
	}

	public void setControl(Control control){
		this.control = control;
	}


}