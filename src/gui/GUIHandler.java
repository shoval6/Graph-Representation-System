package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

public class GUIHandler {

	public static GUIHandler guiHandler = new GUIHandler();
	private graph_algorithms graphAlgo;
	private DGraph graph;
	private Collection<node_data> coll;
	final int X_COORD = 60;
	final int Y_COORD = 720;
	
	public GUIHandler() {
		coll = null;
		graph = new DGraph();
		graphAlgo = new Graph_Algo();
		graphAlgo.init(graph);
	}
	
	public void draw(Graphics graphics) {
		Iterator it1 = graph.getV().iterator();
		while(it1.hasNext()) {
			node_data node = (node_data) it1.next();
			Point3D src = node.getLocation();
			// set color Blue
			graphics.setColor(Color.BLUE);
			// draw node
			graphics.fillOval(src.ix(), src.iy(), 10, 10);
			// draw node key
			graphics.drawString(String.valueOf(node.getKey()), src.ix(), src.iy()-5);
			
			if(graph.getE(node.getKey()) != null) {
			Collection<edge_data> edges = graph.getE(node.getKey());
			for(edge_data edge : edges) {
				Point3D dest = graph.getNode(edge.getDest()).getLocation();
				// set color Red
				graphics.setColor(Color.RED);
				// draw line
				graphics.drawLine(src.ix()+5, src.iy()+5, dest.ix()+5, dest.iy()+5);
				int xCenterCoord = (src.ix() + dest.ix() - 10)/2;
				int yCenterCoord = (src.iy() + dest.iy() - 5)/2;
				graphics.setColor(Color.BLACK);
				graphics.drawString(String.valueOf(edge.getWeight()), xCenterCoord, yCenterCoord);
				}
			}
		}
	}
	
	
	public void addNode() {
		String xCoord = JOptionPane.showInputDialog("Enter x coordinate between 1-65");
		String yCoord = JOptionPane.showInputDialog("Enter y coordinate between 1-30");
		if(xCoord == null || yCoord == null) return;
		Point3D point = new Point3D(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
		Node node = new Node(point);
		calcNodePosition(node);
		graph.addNode(node);
		
	}
	
	
	public void addEdge() {
		String nodeSrc = JOptionPane.showInputDialog("Enter node source number");
		String nodeDest = JOptionPane.showInputDialog("Enter node destination number");
		String weight = JOptionPane.showInputDialog("Enter weight");
		if(nodeSrc == null || nodeDest == null || weight == null) return;
		graph.connect(Integer.parseInt(nodeSrc), Integer.parseInt(nodeDest), Double.parseDouble(weight));
	}
	
	
	public void removeNode() {
		String removed = JOptionPane.showInputDialog("Enter node number");
		if(removed == null) return;
		node_data res = graph.removeNode(Integer.parseInt(removed));
		if(res == null) 
			JOptionPane.showMessageDialog(null, "The specified node doesn't exist",
					"Error Messsage",JOptionPane.ERROR_MESSAGE);
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
