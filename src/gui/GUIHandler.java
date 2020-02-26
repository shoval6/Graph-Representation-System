package gui;

import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.Node;
import utils.Point3D;

public class GUIHandler {

	public static GUIHandler guiHandler = new GUIHandler();
	private graph_algorithms graph;
	final int X_COORD = 60;
	final int Y_COORD = 700;
	
	public GUIHandler() {
		graph = new Graph_Algo();
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
}
