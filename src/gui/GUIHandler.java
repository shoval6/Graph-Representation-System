package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

public class GUIHandler {

	public static GUIHandler guiHandler = new GUIHandler();
	private graph_algorithms graphAlgo;
	private DGraph graph;
	final int X_COORD = 60;
	final int Y_COORD = 720;
	
	public GUIHandler() {
		graph = new DGraph();
		graphAlgo = new Graph_Algo();
		graphAlgo.init(graph);
	}
	
	
	public void addNode() {
		String xCoord = JOptionPane.showInputDialog("Enter x coordinate between 1-65");
		String yCoord = JOptionPane.showInputDialog("Enter y coordinate between 1-30");
		Point3D point = new Point3D(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
		Node node = new Node(point);
		calcNodePosition(node);
		graph.addNode(node);
		
	}
	
	public void addEdge() {
		String nodeSrc = JOptionPane.showInputDialog("Enter node source number");
		String nodeDest = JOptionPane.showInputDialog("Enter node destination number");
		String weight = JOptionPane.showInputDialog("Enter weight");
		graph.connect(Integer.parseInt(nodeSrc), Integer.parseInt(nodeDest), Integer.parseInt(weight));
	}
	
	public void calcNodePosition(Node node) {
		double x = X_COORD + (node.getLocation().ix()*20)-5;
		double y = Y_COORD - (node.getLocation().iy()*20)+5;
		Point3D point = new Point3D(x,y);
		node.setLocation(point);
	}
	
	public int getMC() {
		return this.graph.getMC();
	}
	
}
