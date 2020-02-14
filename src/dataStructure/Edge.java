package dataStructure;

public class Edge implements edge_data {

	private Node src , dest;
	private double weight;
	private String info;
	private int tag;
	
	
	// default constructor
	public Edge(){
		this.weight = 0;
		this.info = "";
		this.tag = 0;
		this.src = new Node();
		this.dest = new Node();
	}
	
	public Edge(Node src, Node dest, double weight) {
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
		return this.src.getKey();
	}

	@Override
	public int getDest() {
		return this.dest.getKey();
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
	
	

}
