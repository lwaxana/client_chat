package tests;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Test2 {

	public Test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		String s = (String)JOptionPane.showInputDialog(frame, "Entrez un nick :", "Entrez votre pseudo", JOptionPane.QUESTION_MESSAGE);
		System.out.println(s);
		

	}

}
