package gui;

import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Node;
import utils.Point3D;

public class GUIHandler {

	public static GUIHandler guiHandler = new GUIHandler();
	private graph_algorithms graph;
	private DGraph g;
	final int X_COORD = 60;
	final int Y_COORD = 700;
	
	public GUIHandler() {
		g = new DGraph();
		graph = new Graph_Algo();
		graph.init(g);
	}
	
	public void addNode() {
		String xCoord = JOptionPane.showInputDialog("Enter x coordinate between 1-65");
		String yCoord = JOptionPane.showInputDialog("Enter y coordinate between 1-30");
		Point3D point = new Point3D(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
		Node node = new Node(point);
		calcNodePosition(node);
		
	}
	
	public void calcNodePosition(Node node) {
		double x = X_COORD + (node.getLocation().ix()*20);
		double y = Y_COORD + (node.getLocation().iy()*20);
		Point3D point = new Point3D(x,y);
		node.setLocation(point);
	}
	
	public int getMC() {
		return this.g.getMC();
	}
	
}
