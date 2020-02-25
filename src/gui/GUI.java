package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

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
		setSize(1400, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void initAxis(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.setStroke(new BasicStroke(4f));
		g2d.draw(new Line2D.Double(50, 110, 50, 760));
		g2d.draw(new Line2D.Double(20, 740, 1360, 740));
		
		final int X_COORD = 40;
		final int Y_COORD = 730;
		int index = 31	;
		Font f = new Font("Dialog", Font.PLAIN, 18);
		g.setFont(f);
		
		// draw Y-axis
		for(int y=110; y<740; y=y+20) {
			g2d.setColor(Color.red);
			g2d.draw(new Line2D.Double(X_COORD, y, X_COORD+20, y));
			g2d.setColor(Color.black);
			g2d.drawString(String.valueOf(index--), X_COORD-30, y+4);
		}
		
		index = 0;
		
		// draw X-axis
		for(int x=60; x<1380; x=x+20) {
			g2d.setColor(Color.red);
			g2d.draw(new Line2D.Double(x, Y_COORD, x, Y_COORD+20));
			g2d.setColor(Color.black);
			if(index%2 != 0) {
				index++;
				continue;
			}
			g2d.drawString(String.valueOf(index++), x-4, Y_COORD+50);
		}

	}
	
	public void paint(Graphics g) {
        super.paint(g);
        initAxis(g);
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
