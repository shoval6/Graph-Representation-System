package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph,Serializable{
	
	private int edgesCount , modeCount , nodesCount;
	private HashMap<Integer,node_data> vertices;
	private HashMap<Integer,HashMap<Integer,edge_data>> edges;
	private static final long serialVersionUID = 1L;

	
	public DGraph(){
		this.nodesCount = 1;
		this.edgesCount = 0;
		this.modeCount = 0;
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}
	
	
	/**
	 * @param key - the node_id.
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_data getNode(int key) {
			return this.vertices.get(key);
		}	

	
	/**
	 * @param src - the node_id of the source vertex.
	 * @param dest - the node_id of the destination vertex.
	 * @return the edge_data between (src,dest) , null if none.
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		try {
			return this.edges.get(src).get(dest);
		} catch (NullPointerException e) {
			return null;
		}	
	}
	
	
	/**
	 * add a new node to the graph with the given node_data.
	 * if the given node is exist , it replaces the old one. 
	 * @param n - node_data.
	 */
	@Override
	public void addNode(node_data n) {
		n.setKey(this.nodesCount++);
		this.vertices.put(n.getKey(), n);
		modeCount++;
	}

	
	
	/**
	 * Connect an edge with weight w between node src to node dest.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 * @throws Exception 
	 */
	@Override
	public void connect(int src, int dest, double w){
		
		node_data tempSrc = this.vertices.get(src); // get src node
		node_data tempDest = this.vertices.get(dest); // get dest node
		edge_data edge = new Edge(src,dest,w);

		if(tempSrc != null && tempDest != null && w>0){
			if(this.getEdge(src,dest) == null){ // the edge doesn't exist  
				if(this.edges.get(src) == null){ // the HashMap of neighburs doesn't exist
					HashMap<Integer,edge_data> tempMap = new HashMap<>();
					this.edges.put(src, tempMap);
					this.edges.get(src).put(dest,edge);
				}else
					this.edges.get(src).put(dest,edge);
			
			}else{
				this.removeEdge(src,dest);
				this.connect(src,dest,w);
			}
			edgesCount++;
			modeCount++;
		
		}else
			throw new RuntimeException("ERR: the both src and dest must be exist");
		
	}
	
	/**
	 * returns the collection of all the nodes. 
	 * @return Collection<node_data>
	 */
	@Override
	public Collection<node_data> getV() {
		return this.vertices.values();
	}
	
	/**
	 * returns the collection representing all the edges getting out of the given node.
	 * @param node_id - the src node id 
	 * @return Collection<edge_data>
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.edges.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}

	
	/**
	* Delete the node (with the given ID) from the graph -
	* and removes all edges which starts or ends at this node.
	* @param key - the node id for remove.
	* @return the removed node , or null if doesn't exist. 
	*/
	@Override
	public node_data removeNode(int key) {
		node_data node = this.getNode(key);
		if(node != null){
			this.vertices.remove(key);
			if(this.edges.get(key) != null) this.edges.remove(key);
			Iterator it = this.edges.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mp = (Map.Entry) it.next();  // grab the key
				int tempKey = ((int)mp.getKey());
				if(this.edges.get(tempKey).get(key) != null)
					removeEdge(tempKey, key);
				
			}
			modeCount++;
		}
		return node;
		
	}
		
	/**
	 * Delete the edge from the graph.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data edge = getEdge(src, dest);
		if(edge != null){
			this.edges.get(src).remove(dest);
			edgesCount--;
		}
		return edge;
	}

	
	/**
	 * @return the nodesCount.
	 */
	@Override
	public int nodeSize() {
		return this.vertices.size();
	}

	
	/**
	 * @return the edgesCount.
	 */
	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	
	/**
	 * @return the modeCount
	 */
	@Override
	public int getMC() {
		return this.modeCount;
	}

}
