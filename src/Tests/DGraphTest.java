package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

class DGraphTest {

	static DGraph g = new DGraph();
	static Point3D[] points = new Point3D[7];
	static Node[] nodes = new Node[7];

	/**
	 * this method executes before each @Test , and builds a graph.    
	 */
	
	@BeforeEach
	public void BeforeEach() {
		points[0] = new Point3D(1,2);
		points[1] = new Point3D(5,6);
		points[2] = new Point3D(7,8);
		points[3] = new Point3D(2,9);
		points[4] = new Point3D(0,2);
		points[5] = new Point3D(6,6);
		points[6] = new Point3D(3,3);
		
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		nodes[3] = new Node(points[3]);
		nodes[4] = new Node(points[4]);
		nodes[5] = new Node(points[5]);
		nodes[6] = new Node(points[6]);
		
		nodes[0].setKey(1);
		nodes[1].setKey(2);
		nodes[2].setKey(3);
		nodes[3].setKey(4);
		nodes[4].setKey(5);
		nodes[5].setKey(6);
		nodes[6].setKey(7);
		
		for(int i=0; i<nodes.length; i++) {
			g.addNode(nodes[i]);
		}
		
		
		
		g.connect(1,2,5);
		g.connect(1,3,5);
		g.connect(1,4,5);
		g.connect(1,5,5);
		g.connect(1,6,5);
		g.connect(1,7,5);
		
		g.connect(2,4,2);
		g.connect(3,5,5);
		g.connect(4,6,7);
		g.connect(5,3,9);
		g.connect(6,2,1);
		g.connect(7,1,5);
		
	}
	
	@Test
	public void getNode() {
		for(int i=0; i<nodes.length; i++) {
			assertEquals(nodes[i].getLocation().toString(), g.getNode(i+1).getLocation().toString());
		}
	}
	
	@Test
	public void getEdge() {
		Edge[] edges = new Edge[12];
		edges[0] = new Edge(1,2,5);
		edges[1] = new Edge(1,3,5);
		edges[2] = new Edge(1,4,5);
		edges[3] = new Edge(1,5,5);
		edges[4] = new Edge(1,6,5);
		edges[5] = new Edge(1,7,5);
		edges[6] = new Edge(2,4,2);
		edges[7] = new Edge(3,5,5);
		edges[8] = new Edge(4,6,7);
		edges[9] = new Edge(5,3,9);
		edges[10] = new Edge(6,2,1);
		edges[11] = new Edge(7,1,5);
		
		for(int i=0; i<edges.length; i++)
			assertEquals(edges[i].toString(), g.getEdge(edges[i].getSrc(), edges[i].getDest()).toString());
	}
	
	@Test
	public void addNode() {
		Point3D[] points = new Point3D[2];
		Node[] nodes = new Node[2];
		
		points[0] = new Point3D(1,2);
		points[1] = new Point3D(4,5);
		
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		
		nodes[0].setKey(8);
		nodes[1].setKey(9);
		

		for(int i=0; i<nodes.length; i++) {
			g.addNode(nodes[i]);
			assertEquals(nodes[i], g.getNode(i+8));
		}

	}
	
	@Test
	public void connect() {
		// check if all the edges that connected to node src -> 1 , exist.
		for(int i=2; i<7; i++)
		assertEquals(true, g.getE(1).contains(g.getEdge(1, i)));	
	}
	
	@Test
	public void removeNode() {
		node_data removed = g.removeNode(nodes[0].getKey());
		
		// check if the removed node from the graph is match.
		assertEquals(nodes[0], removed);
		
		// check if the removed node doesn't exist.
		assertEquals(null, g.getNode(nodes[0].getKey()));
		
		// check if all the edges that were connected does not exist.
		for(int i=2; i<7; i++)
		assertEquals(null, g.getEdge(nodes[0].getKey(), i));
	}
	
	@Test
	public void nodeSize() {
		
		DGraph graph = new DGraph();
        Point3D[] points = new Point3D[3];
        Node[] nodes = new Node[3];

        points[0] = new Point3D(4,5);
        points[1] = new Point3D(1,6);
        points[2] = new Point3D(4,4);

        nodes[0] = new Node(points[0]);
        nodes[1] = new Node(points[1]);
        nodes[2] = new Node(points[2]);
        
        nodes[0].setKey(1);
		nodes[1].setKey(2);
		nodes[2].setKey(3);

        for(int i=0; i<nodes.length; i++){
            graph.addNode(nodes[i]);
        }
        int expectedSize = 3;
		assertEquals(expectedSize, graph.nodeSize());
	}
	
	@Test
	public void edgeSize() {
		DGraph graph = new DGraph();
		Point3D[] points = new Point3D[3];
		Node[] nodes = new Node[3];

		points[0] = new Point3D(4, 5);
		points[1] = new Point3D(1, 6);
		points[2] = new Point3D(4, 4);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);

		for (int i = 0; i < nodes.length; i++) {
			graph.addNode(nodes[i]);
		}

		graph.connect(nodes[0].getKey(), nodes[1].getKey(), 1);
		graph.connect(nodes[0].getKey(), nodes[2].getKey(), 2);

		int expectedSize = 2;
		assertEquals(expectedSize, graph.edgeSize());
	}
	
	@Test
	public void getMC() {
		DGraph graph = new DGraph();
		Point3D[] points = new Point3D[3];
		Node[] nodes = new Node[3];

		points[0] = new Point3D(4, 5);
		points[1] = new Point3D(1, 6);
		points[2] = new Point3D(4, 4);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		
		nodes[0].setKey(1);
		nodes[1].setKey(2);
		nodes[2].setKey(3);
	

		for (int i = 0; i < nodes.length; i++) {
			graph.addNode(nodes[i]);
		}

		graph.connect(nodes[0].getKey(), nodes[1].getKey(), 1);
		graph.connect(nodes[0].getKey(), nodes[2].getKey(), 2);

		int expectedSize = 5;
		assertEquals(expectedSize, graph.getMC());
	}
	
}

