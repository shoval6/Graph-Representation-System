package dataStructure;

import java.io.Serializable;

public class Edge implements edge_data,Serializable {

	private int src , dest;
	private double weight;
	private String info;
	private int tag;
	private static final long serialVersionUID = 1L;
	
	
	// default constructor
	public Edge(){
		this.weight = 0;
		this.info = "";
		this.tag = 0;
		this.src = 0;
		this.dest = 0;
	}
	
	public Edge(int src, int dest, double weight) {
		this.weight = weight;
		this.info = "";
		this.tag = 0;
		this.dest = dest;
		this.src = src;
	}
	
	// copy constructor
	public Edge(Edge e) {
		this.weight = e.weight;
		this.info = e.info;
		this.tag = e.tag;
		this.src = e.src;
		this.dest = e.dest;
	}
	
	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}
	
	public String toString() {
		return "Src: "+getSrc()+", Dest: "+getDest()+", Weight: "+getWeight();
	}
	
	

}
