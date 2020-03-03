package gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
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
	private List<node_data> nodes;
	final int X_COORD = 60;
	final int Y_COORD = 720;
	
	public GUIHandler() {
		nodes = null;
		graph = new DGraph();
		graphAlgo = new Graph_Algo();
		graphAlgo.init(graph);
	}
	
	public void draw(Graphics2D graphics) {
		Iterator it1 = graph.getV().iterator();
		while(it1.hasNext()) {
			node_data node = (node_data) it1.next();
			Point3D src = node.getLocation();
			//graphics.setStroke(new BasicStroke(10));
			// set color Blue
			graphics.setColor(Color.BLUE);
			// draw node
			graphics.fillOval(src.ix(), src.iy(), 15, 15);
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
				graphics.setColor(Color.YELLOW);
				int xCoord = (int)(src.ix()*0.1+dest.ix()*0.9);
				int yCoord = (int)(src.iy()*0.1+dest.iy()*0.9);
				graphics.fillOval(xCoord, yCoord, 15, 15);
				}
			}
		}
	}
	
	
	public void drawPath(Graphics2D graphics) {
		if(this.nodes == null) return;
		for(int i=0; i<this.nodes.size()-1;i++) {
				Point3D src = graph.getNode(this.nodes.get(i).getKey()).getLocation();
				Point3D dest = graph.getNode(this.nodes.get(i+1).getKey()).getLocation();
				// set color Green
				graphics.setColor(Color.GREEN);
				// draw line
				graphics.drawLine(src.ix()+5, src.iy()+5, dest.ix()+5, dest.iy()+5);
			
		}
	}
	
	public void addNode() {
		String xCoord = JOptionPane.showInputDialog("Enter x coordinate between 0-65");
		String yCoord = JOptionPane.showInputDialog("Enter y coordinate between 0-30");
		if(xCoord == null || yCoord == null) return;
		double x = Double.parseDouble(xCoord);
		double y = Double.parseDouble(yCoord);
		if(x > 65 || y > 30 || x < 0 || y < 0) {
			JOptionPane.showMessageDialog(null, "The entered coordinates are out of range!");
			return;
		}
		Point3D point = new Point3D(x,y);
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
	
	
	public void removeEdge() {
		String nodeSrc = JOptionPane.showInputDialog("Enter node source number");
		String nodeDest = JOptionPane.showInputDialog("Enter node destination number");
		if(nodeSrc == null || nodeDest == null) return;
		graph.removeEdge(Integer.parseInt(nodeSrc), Integer.parseInt(nodeDest));
		
	}
	
	
	public void isConnected() {
		Boolean result = graphAlgo.isConnected();
		if(result)
			JOptionPane.showMessageDialog(null, "The graph is connected");
		else
			JOptionPane.showMessageDialog(null, "The graph is not connected");
	}
	
	
	public void shortestPathDist() {
		String nodeSrc = JOptionPane.showInputDialog("Enter node source number");
		String nodeDest = JOptionPane.showInputDialog("Enter node destination number");
		double res = graphAlgo.shortestPathDist(Integer.parseInt(nodeSrc), Integer.parseInt(nodeDest));
		String ans = "The shortest path distance between "+nodeSrc+" -> "+nodeDest+" is : "+res;
		JOptionPane.showMessageDialog(null, ans);
	}
	
	
	public void shortestPath() {
		String nodeSrc = JOptionPane.showInputDialog("Enter node source number");
		String nodeDest = JOptionPane.showInputDialog("Enter node destination number");
		this.nodes = graphAlgo.shortestPath(Integer.parseInt(nodeSrc), Integer.parseInt(nodeDest));
		if(this.nodes == null)
			JOptionPane.showMessageDialog(null, "There is no path between "+nodeSrc+" -> "+nodeDest);
		else {
			StringBuilder result = printPath();
			JOptionPane.showMessageDialog(null, "The shortestPath between " + nodeSrc + " to " + nodeDest + " is: "+result);
		}
	}
	
	
	public void TSP() {
		String input = JOptionPane.showInputDialog("Please enter a group of points with ',' between each other");
		String[] split = input.split(",");
		List<Integer> targets = new LinkedList<>();
		for(int i=0; i<split.length; i++)
			targets.add(Integer.parseInt(split[i]));
		this.nodes = graphAlgo.TSP(targets);
		if(nodes == null)
			JOptionPane.showMessageDialog(null, "There is no path between the nodes");
		else {
			StringBuilder result = printPath();
			JOptionPane.showMessageDialog(null, "The TSP between the nodes is: "+result);
		}
	}
	
	
	public void save(JFrame frame) {
		FileDialog chooser = new FileDialog(frame, "Save graph to file", FileDialog.SAVE);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null) {
			graphAlgo.save(chooser.getDirectory() + File.separator + chooser.getFile()); 
			JOptionPane.showMessageDialog(null, "The file saved successfully");
		}
	}
	
	
	public void load(JFrame frame) {
		FileDialog chooser = new FileDialog(frame, "Load graph from file", FileDialog.LOAD);
		chooser.setVisible(true);
		String filename = chooser.getFile();
		if (filename != null) {
			Graph_Algo temp = new Graph_Algo();
			temp.init(chooser.getDirectory() + File.separator + chooser.getFile());
			graph = (DGraph) temp.copy();
			graphAlgo = temp;

		}
	}
	
	
	public StringBuilder printPath() {
		StringBuilder path = new StringBuilder();
		path.append("[");
		for (int i = 0; i < this.nodes.size(); i++) {
			if (i != this.nodes.size() - 1)
				path.append("" + this.nodes.get(i).getKey() + "->");
			else 
				path.append("" + this.nodes.get(i).getKey());
		}
		path.append("]");
		return path;
	}
	
	
	public void calcNodePosition(Node node) {
		double x = X_COORD + (node.getLocation().ix()*20)-5;
		double y = Y_COORD - (node.getLocation().iy()*20)+5;
		Point3D point = new Point3D(x,y);
		node.setLocation(point);
	}
	
	
}
