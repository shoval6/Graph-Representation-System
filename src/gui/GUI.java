package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GUI extends JFrame implements ActionListener {

	public GUI() {
		initGUI();
		initMenu();
		
	}
	
	private void initGUI() {
		setTitle("Graph-Represenation");
		setSize(1200, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		
	}
	
}


