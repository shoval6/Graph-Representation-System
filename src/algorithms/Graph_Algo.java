package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	private graph algo;
	
	public Graph_Algo(){
		this.algo = new DGraph();
	}
	
	public Graph_Algo(graph g){
		init(g);
	}
	
	
	@Override
	public void init(graph g) {
		this.algo = g;
	}

	@Override
	public void init(String file_name) {
		try{
		FileInputStream file = new FileInputStream(file_name);
		ObjectInputStream in = new ObjectInputStream(file);
		this.algo = (graph) in.readObject();
		in.close();
		file.close();	
		} catch(IOException ex) {
			System.out.println("IOException is caught");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
	}

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this.algo);
			out.close();
			file.close();
		}
		catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	@Override
	public boolean isConnected() {
		graph graph = this.copy();
		Iterator it = graph.getV().iterator();
		node_data node = (node_data) it.next();
		DFS(graph,node);
		it = graph.getV().iterator();
		while(it.hasNext()){
			node = (node_data) it.next();
			if(node.getTag() == 0) return false;
		}
		initNodeTag(graph);
		reverseGraph(graph);
		DFS(graph,node);
		it = graph.getV().iterator();
		while(it.hasNext()){
			node = (node_data) it.next();
			if(node.getTag() == 0) return false;
		}
		return true;
	}
	
	
	public void DFS(graph g, node_data n){
		n.setTag(1);
		Iterator it = g.getE(n.getKey()).iterator();
		while(it.hasNext()){
			edge_data edge = (edge_data) it.next();
			node_data node = g.getNode(edge.getDest());
			if(node.getTag() == 0){
				node.setTag(1);
				DFS(g,node);
			}
		}		
	}
	
	public void reverseGraph(graph gr){
		Iterator it1 = gr.getV().iterator();
		while(it1.hasNext()){
			node_data n = (node_data) it1.next();
			if(gr.getE(n.getKey()) != null){
				Iterator it2 = gr.getE(n.getKey()).iterator();
				while(it2.hasNext()){
					edge_data e = (edge_data) it2.next();
					if(gr.getEdge(e.getDest(), e.getSrc()) == null){ // check if the opposing edge doesn't exist 
						if(e.getTag() == 0){
							gr.connect(e.getDest(), e.getSrc(), e.getWeight()); // reverse the edge direction
							gr.getEdge(e.getDest(), e.getSrc()).setTag(1); // mark current edge as reversed
							gr.removeEdge(e.getSrc(), e.getDest()); // remove the current edge
						}
					}
				}
			}
		}
	}
	

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initNodeTag(graph g){
		Iterator it = g.getV().iterator();
		while(it.hasNext()){
			node_data node = (node_data) it.next();
			node.setTag(0);
		}
	}
	

}
