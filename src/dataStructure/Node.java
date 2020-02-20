package dataStructure;

import utils.Point3D;

public class Node implements node_data {

	private int key,tag;
	private double weight;
	private String info;
	private Point3D location;
	public static int keyCount = 1;
	
	
	// default constructor
	public Node(){
		this.key = keyCount++;
		this.tag = 0;
		this.weight = 0;
		this.info = "";
		this.location = Point3D.ORIGIN;
	}
	
	public Node(Point3D p) {
		this.key = keyCount++;
		this.tag = 0;
		this.weight = 0;
		this.info = "";
		this.location = p;
	}
	
	// copy constructor
	public Node(Node n) {
		this.key = n.key;
		this.tag = n.tag;
		this.weight = n.weight;
		this.info = n.info;
		this.location = n.location;
	}
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
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
		return ""+this.getKey();
	}
	
	
}
