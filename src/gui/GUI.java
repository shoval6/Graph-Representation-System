package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Panel extends JPanel{
	
	private String removeFlag; 
	
	public Panel() {
		removeFlag = null;
		setBackground(Color.WHITE); 
	}
	
	private String getFlag() {
		return removeFlag;
	}
	
	public void setFlag(String str) {
		this.removeFlag = str;
	}
	
	private void initAxis(Graphics2D g2d) {
	
		g2d.setColor(Color.red);
		g2d.setStroke(new BasicStroke(4f));
		g2d.draw(new Line2D.Double(50, 110, 50, 760)); // Y-axis
		g2d.draw(new Line2D.Double(20, 740, 1360, 740)); // X-axis
		
		final int X_COORD = 40;
		final int Y_COORD = 730;
		int index = 30;
		Font f = new Font("Dialog", Font.PLAIN, 18);
		g2d.setFont(f);
		
		// draw Y-axis
		for(int y=130; y<740; y=y+20) {
			g2d.setColor(Color.red);
			g2d.draw(new Line2D.Double(X_COORD, y, X_COORD+20, y));
			g2d.setColor(Color.black);
			g2d.drawString(String.valueOf(index--), X_COORD-30, y+4);
		}
		
		index = 0;
		
		// draw X-axis
		for(int x=60; x<1360; x=x+20) {
			g2d.setColor(Color.red);
			g2d.draw(new Line2D.Double(x, Y_COORD, x, Y_COORD+20));
			g2d.setColor(Color.black);
			if(index%2 != 0) {
				index++;
				continue;
			}
			g2d.drawString(String.valueOf(index++), x-4, Y_COORD+40);
		}
	
		Polygon poly1 = new Polygon(new int[] {50,45,55},new int[] {100,110,110}, 3);
		Polygon poly2 = new Polygon(new int[] {1360,1360,1370},new int[] {735,745,740}, 3);
	
		g2d.setColor(Color.RED);
		g2d.drawPolygon(poly1);
		g2d.drawPolygon(poly2);
		g2d.fillPolygon(poly1);		
		g2d.fillPolygon(poly2);
		g2d.drawString("X", 1374, 748);
		g2d.drawString("Y", 44, 94);

	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
        initAxis(g2d);
        GUIHandler.guiHandler.draw(g2d);
        if(getFlag() != null) {
        if(getFlag().equals("remove")) 
        	setBackground(Color.WHITE);
        if(getFlag().equals("shortestPath") || getFlag().equals("TSP"))
        	GUIHandler.guiHandler.drawPath(g2d);
        setFlag(null);
        	
        }
    }
}

public class GUI implements ActionListener {
	
	private JFrame frame;
	private Panel jpanel;
	private int modeCount;
	
	public GUI() {
		this.jpanel = new Panel();
		this.modeCount = GUIHandler.guiHandler.getMC();
		initFrame();
		initMenu();
	}
	
	private void initFrame() {
		this.frame = new JFrame("Graph-Represenation");
		this.frame.setSize(1400, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);
		this.frame.getContentPane().add(jpanel);
	}

	private void initMenu() {

		JMenuBar menuBar = new JMenuBar();
		this.frame.setJMenuBar(menuBar);

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
		
		JMenuItem removeNode = new JMenuItem("Remove Node");
		removeNode.addActionListener(this);

		JMenuItem addEdge = new JMenuItem("Add Edge");
		addEdge.addActionListener(this);
		KeyStroke addEdgeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		addEdge.setAccelerator(addEdgeKeyStroke);
		
		JMenuItem removeEdge = new JMenuItem("Remove Edge");
		removeEdge.addActionListener(this);
		
		JMenu node = new JMenu("Node");
		JMenu edge = new JMenu("Edge");
		

		node.add(addNode);
		node.add(removeNode);
		edge.add(addEdge);
		edge.add(removeEdge);
		edit.add(node);
		edit.add(edge);

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

		//return menuBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String stringIdentifier = e.getActionCommand();
		
		switch(stringIdentifier) {
//		case "Load": GUIHandler.guiHandler.load();
//		break;
//		case "Save": GUIHandler.guiHandler.save();
//		break;
		case "Add Node": GUIHandler.guiHandler.addNode();
		break;
		case "Add Edge": GUIHandler.guiHandler.addEdge();
		break;
		case "Remove Node": GUIHandler.guiHandler.removeNode();
		break;
//		case "Remove Edge": GUIHandler.guiHandler.removeEdge();
//		break:
//		case "isConnected": GUIHandler.guiHandler.isConnected();
//		break;
//		case "shortestPathDist": GUIHandler.guiHandler.shortestPathDist();
//		break;
//		case "shortestPath": GUIHandler.guiHandler.shortestPath();
//		break;
//		case "TSP": GUIHandler.guiHandler.TSP();
		
		
		}
				
		if(modeCount != GUIHandler.guiHandler.getMC()) {
			modeCount = GUIHandler.guiHandler.getMC();
			if(stringIdentifier.equals("Remove Node") || stringIdentifier.equals("Remove Edge"))
				jpanel.setFlag("remove");
				jpanel.repaint();
		}
		
	}
	
	

	public static void main(String[] args) {
		
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		GUI gui = new GUI();
		
	
	}

}
