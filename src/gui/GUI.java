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

	
	private static final long serialVersionUID = 1L;

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

	private void initMenu() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//////////////////
		//// File Menu ///
		//////////////////

		JMenu file = new JMenu("File");

		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(this);
		KeyStroke loadKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK);
		load.setAccelerator(loadKeyStroke);

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(this);
		KeyStroke SaveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		save.setAccelerator(SaveKeyStroke);

		file.add(load);
		file.add(save);

		//////////////////
		//// Edit Menu ///
		//////////////////

		JMenu edit = new JMenu("Edit");

		JMenuItem addNode = new JMenuItem("Add Node");
		addNode.addActionListener(this);
		KeyStroke addNodeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
		addNode.setAccelerator(addNodeKeyStroke);

		JMenuItem addEdge = new JMenuItem("Add Edge");
		addNode.addActionListener(this);
		KeyStroke addEdgeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		addEdge.setAccelerator(addEdgeKeyStroke);

		edit.add(addNode);
		edit.add(addEdge);

		////////////////////////
		//// Algorithms Menu ///
		////////////////////////

		JMenu algorithms = new JMenu("Algorithms");

		JMenuItem isConnected = new JMenuItem("isConnected");
		isConnected.addActionListener(this);

		JMenuItem shortestPathDist = new JMenuItem("shortestPathDist");
		shortestPathDist.addActionListener(this);

		JMenuItem shortestPath = new JMenuItem("shortestPath");
		shortestPath.addActionListener(this);

		JMenuItem TSP = new JMenuItem("TSP");
		TSP.addActionListener(this);

		algorithms.add(isConnected);
		algorithms.add(shortestPathDist);
		algorithms.add(shortestPath);
		algorithms.add(TSP);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(algorithms);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String stringIdentifier = e.getActionCommand();
		
		switch(stringIdentifier) {
		case "Load": GUIHandler.load();
		break;
		case "Save": GUIHandler.save();
		break;
		case "Add Node": GUIHandler.addNode();
		break;
		case "Add Edge": GUIHandler.addEdge();
		break;
		case "isConnected": GUIHandler.isConnected();
		break;
		case "shortestPathDist": GUIHandler.shortestPathDist();
		break;
		case "shortestPath": GUIHandler.shortestPath();
		break;
		case "TSP": GUIHandler.TSP();
		
		}
		
	}

	public static void main(String[] args) {
		GUI gui = new GUI();

	}

}
