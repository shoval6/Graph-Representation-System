package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{
	
	private static final long serialVersionUID = 1L;
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
	
	// here we do only two DFS.
	// after the first DFS traversal we check if we have visted all the nodes. if not return false.
	// then we reverse the graph directions , and run the second DFS traversal and check the nodes again.
	//  
	@Override
	public boolean isConnected() {
		graph graph = this.copy();
		Iterator it = graph.getV().iterator();
		node_data node = (node_data) it.next();
		DFS(graph,node); // first DFS 
		it = graph.getV().iterator();
		while(it.hasNext()){
			node = (node_data) it.next();
			if(node.getTag() == 0) return false;
		}
		initNodeTag(graph); // initial the nodes as unvisited
		reverseGraph(graph); // reverse graph directions.
		DFS(graph,node); // second DFS
		it = graph.getV().iterator();
		while(it.hasNext()){
			node = (node_data) it.next();
			if(node.getTag() == 0) return false;
		}
		return true;
	}
	
	
	public void DFS(graph g, node_data n){
		n.setTag(1);
		if(g.getE(n.getKey()) != null) {
		Iterator it = g.getE(n.getKey()).iterator();
		while(it.hasNext()){
			edge_data edge = (edge_data) it.next();
			node_data node = g.getNode(edge.getDest());
			if(node.getTag() == 0)
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
							it2 = gr.getE(n.getKey()).iterator();
						}
					}
				}
			}
		}
	}
	
	public void dijkstra(int src){
		PriorityQueue<node_data> queue = new PriorityQueue<>((lhs, rhs) -> 
						 Double.compare(lhs.getWeight(), rhs.getWeight()));
		Iterator it;
		initNodeWeightToInfinit(this.algo);
		node_data n = this.algo.getNode(src);
		n.setWeight(0);
		n.setTag(1);
		queue.add(n);
		
		while(!queue.isEmpty()){
			n = queue.poll();
			Collection<edge_data> coll = this.algo.getE(n.getKey());
			if(coll != null) {
			it = coll.iterator();
			while(it.hasNext()){
				edge_data e = (edge_data) it.next();
				node_data nodeSrc = this.algo.getNode(e.getDest());
				if(e.getTag() != 1 && (n.getWeight() + e.getWeight()) < nodeSrc.getWeight()){
					nodeSrc.setWeight(n.getWeight() + e.getWeight());
					nodeSrc.setInfo("+n.getKey()+");
					queue.add(nodeSrc);
					}
				}
			}
			n.setTag(1);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		dijkstra(src);
		double res = this.algo.getNode(dest).getWeight();
		if(res == Double.MAX_VALUE) return -1;
		return res;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		double weightRes = shortestPathDist(src, dest);
		if(weightRes == Double.MAX_VALUE) return null;
		node_data n = this.algo.getNode(dest);
		LinkedList<node_data> temp = new LinkedList<>();
		List<node_data> ans = new LinkedList<>();
		
		while(n.getKey() != src){
			int nodeKey = Integer.parseInt(n.getInfo());
			temp.add(this.algo.getNode(nodeKey));
			n = this.algo.getNode(nodeKey);
		}
		temp.add(this.algo.getNode(src));
		
		Iterator it = temp.descendingIterator();
		while(it.hasNext())
			ans.add((node_data) it.next());
		
		return ans;
		
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		Set<node_data> temp = new HashSet<>();
		List<node_data> pathList = new LinkedList<>();
		if(targets.size() == 1){
			pathList.add(this.algo.getNode(targets.get(0)));
			return pathList;
		}
		for(int i=0; i<targets.size()-1; i++){
			pathList = shortestPath(targets.get(i), targets.get(i+1));
			if(pathList == null) return null;
			temp.addAll(pathList);
			}
		
		pathList.addAll(temp);
		return pathList;
		
	}

	@Override
	public graph copy() {
		graph g = new DGraph();
		for (node_data n : this.algo.getV()) {
			node_data temp = new Node((Node) n);
			g.addNode(temp);
		}
		for (node_data n : g.getV()) {
			Collection<edge_data> coll = this.algo.getE(n.getKey());
			if(coll!=null) {
				for (edge_data e : this.algo.getE(n.getKey())) {
					edge_data temp = new Edge((Edge) e);
					g.connect(temp.getSrc(), temp.getDest(), temp.getWeight());
				}
			}
		}
		return g;
	}
	
	public void initNodeTag(graph g){
		Iterator it = g.getV().iterator();
		while(it.hasNext()){
			node_data node = (node_data) it.next();
			node.setTag(0);
		}
	}
	
	public void initNodeWeightToInfinit(graph g){
		Iterator it = g.getV().iterator();
		while(it.hasNext()){
			node_data node = (node_data) it.next();
			node.setWeight(Double.MAX_VALUE);
		}
	}
	

}
