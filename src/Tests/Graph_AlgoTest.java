package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {

	@Test
	public void initGraph() {
		Graph_Algo graph1 = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[2];
		Node[] nodes = new Node[2];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 10);
		graph1.init(dg);

		Graph_Algo graph2 = new Graph_Algo();
		graph2.init(dg);

		assertEquals(graph1.isConnected(), graph2.isConnected());

	}

	@Test
	public void save() {
		Graph_Algo graph1 = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[2];
		Node[] nodes = new Node[2];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 10);
		graph1.init(dg);
		graph1.save("testFile");

		Graph_Algo graph2 = new Graph_Algo();
		graph2.init("testFile");

		Boolean result = graph1.isConnected() == graph2.isConnected();
		assertEquals(true, result);

	}

	@Test
	public void initFile() {
		Graph_Algo graph1 = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[2];
		Node[] nodes = new Node[2];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 10);
		graph1.init(dg);

		Graph_Algo graph2 = new Graph_Algo();
		graph2.init("testFile");

		Boolean result = graph1.isConnected() == graph2.isConnected();
		assertEquals(true, result);
	}

	@Test
	public void isConnected() {
		///////////////////////////////
		///////////// Test1/////////////
		///////////////////////////////

		Graph_Algo graph1 = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[3];
		Node[] nodes = new Node[3];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		points[2] = new Point3D(2, 2);
		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);
		dg.addNode(nodes[2]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 10);
		dg.connect(nodes[0].getKey(), nodes[2].getKey(), 5);
		graph1.init(dg);

		assertEquals(false, graph1.isConnected());

		///////////////////////////////
		///////////// Test2/////////////
		///////////////////////////////

		Graph_Algo graph2 = new Graph_Algo();
		DGraph dg2 = new DGraph();
		Point3D[] points2 = new Point3D[6];
		Node[] nodes2 = new Node[6];

		points2[0] = new Point3D(0, 0);
		points2[1] = new Point3D(1, 1);
		points2[2] = new Point3D(2, 2);
		points2[3] = new Point3D(3, 3);
		points2[4] = new Point3D(4, 4);
		points2[5] = new Point3D(5, 5);

		nodes2[0] = new Node(points2[0]);
		nodes2[1] = new Node(points2[1]);
		nodes2[2] = new Node(points2[2]);
		nodes2[3] = new Node(points2[3]);
		nodes2[4] = new Node(points2[4]);
		nodes2[5] = new Node(points2[5]);

		dg2.addNode(nodes2[0]);
		dg2.addNode(nodes2[1]);
		dg2.addNode(nodes2[2]);
		dg2.addNode(nodes2[3]);
		dg2.addNode(nodes2[4]);
		dg2.addNode(nodes2[5]);

		dg2.connect(nodes2[0].getKey(), nodes2[1].getKey(), 1);
		dg2.connect(nodes2[1].getKey(), nodes2[2].getKey(), 2);
		dg2.connect(nodes2[2].getKey(), nodes2[3].getKey(), 3);
		dg2.connect(nodes2[3].getKey(), nodes2[4].getKey(), 4);
		dg2.connect(nodes2[4].getKey(), nodes2[5].getKey(), 5);
		dg2.connect(nodes2[5].getKey(), nodes2[0].getKey(), 6);

		graph2.init(dg2);

		assertEquals(true, graph2.isConnected());

	}

	@Test
	public void shortestPathDist() {
		Graph_Algo graph = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[6];
		Node[] nodes = new Node[6];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		points[2] = new Point3D(2, 2);
		points[3] = new Point3D(3, 3);
		points[4] = new Point3D(4, 4);
		points[5] = new Point3D(5, 5);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		nodes[3] = new Node(points[3]);
		nodes[4] = new Node(points[4]);
		nodes[5] = new Node(points[5]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);
		dg.addNode(nodes[2]);
		dg.addNode(nodes[3]);
		dg.addNode(nodes[4]);
		dg.addNode(nodes[5]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 2);
		dg.connect(nodes[0].getKey(), nodes[2].getKey(), 3);
		dg.connect(nodes[1].getKey(), nodes[3].getKey(), 1);
		dg.connect(nodes[2].getKey(), nodes[3].getKey(), 2);
		dg.connect(nodes[3].getKey(), nodes[4].getKey(), 4);
		dg.connect(nodes[4].getKey(), nodes[5].getKey(), 1);

		graph.init(dg);

		assertEquals(3, graph.shortestPathDist(nodes[0].getKey(), nodes[3].getKey()));
		assertEquals(8, graph.shortestPathDist(nodes[0].getKey(), nodes[5].getKey()));

	}

	@Test
	public void shortestPath() {
		Graph_Algo graph = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[6];
		Node[] nodes = new Node[6];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		points[2] = new Point3D(2, 2);
		points[3] = new Point3D(3, 3);
		points[4] = new Point3D(4, 4);
		points[5] = new Point3D(5, 5);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		nodes[3] = new Node(points[3]);
		nodes[4] = new Node(points[4]);
		nodes[5] = new Node(points[5]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);
		dg.addNode(nodes[2]);
		dg.addNode(nodes[3]);
		dg.addNode(nodes[4]);
		dg.addNode(nodes[5]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 2);
		dg.connect(nodes[0].getKey(), nodes[2].getKey(), 3);
		dg.connect(nodes[1].getKey(), nodes[3].getKey(), 1);
		dg.connect(nodes[2].getKey(), nodes[3].getKey(), 2);
		dg.connect(nodes[3].getKey(), nodes[4].getKey(), 4);
		dg.connect(nodes[4].getKey(), nodes[5].getKey(), 1);

		graph.init(dg);

		List<node_data> ansList = new LinkedList<>();
		ansList = graph.shortestPath(nodes[5].getKey(), nodes[0].getKey());
		assertEquals(null, ansList);
		ansList = graph.shortestPath(nodes[0].getKey(), nodes[5].getKey());
		assertEquals(5, ansList.size());

		String expectedAns = "";
		for (int i = 0; i < nodes.length; i++) {
			if (i == 2)
				continue;
			expectedAns += nodes[i].getKey();
		}

		String ans = "";
		Iterator<node_data> it = ansList.iterator();
		while (it.hasNext())
			ans += it.next().getKey();

		assertEquals(expectedAns, ans);

	}

	@Test
	public void TSP() {
		Graph_Algo graph = new Graph_Algo();
		DGraph dg = new DGraph();
		Point3D[] points = new Point3D[5];
		Node[] nodes = new Node[5];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		points[2] = new Point3D(2, 2);
		points[3] = new Point3D(3, 3);
		points[4] = new Point3D(4, 4);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		nodes[3] = new Node(points[3]);
		nodes[4] = new Node(points[4]);

		dg.addNode(nodes[0]);
		dg.addNode(nodes[1]);
		dg.addNode(nodes[2]);
		dg.addNode(nodes[3]);
		dg.addNode(nodes[4]);

		dg.connect(nodes[0].getKey(), nodes[1].getKey(), 9);
		dg.connect(nodes[1].getKey(), nodes[2].getKey(), 3);
		dg.connect(nodes[2].getKey(), nodes[3].getKey(), 5);
		dg.connect(nodes[3].getKey(), nodes[0].getKey(), 4);
		dg.connect(nodes[1].getKey(), nodes[0].getKey(), 2);
		dg.connect(nodes[1].getKey(), nodes[4].getKey(), 1);
		dg.connect(nodes[4].getKey(), nodes[3].getKey(), 2);
		dg.connect(nodes[3].getKey(), nodes[2].getKey(), 6);
		dg.connect(nodes[2].getKey(), nodes[1].getKey(), 5);

		graph.init(dg);

		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(nodes[1].getKey());
		ll.add(nodes[3].getKey());
		ll.add(nodes[4].getKey());

		String ansl1 = "";
		ansl1 += nodes[1].getKey();
		ansl1 += nodes[4].getKey();
		ansl1 += nodes[3].getKey();

		List<node_data> List = new LinkedList<>();
		List = graph.TSP(ll);

		String check = "";
		Iterator<node_data> it = List.iterator();
		while (it.hasNext()) {
			node_data c = (node_data) it.next();
			check += (c.getKey());
		}

		assertEquals(ansl1.toString(), check.toString());
	}

	@Test
	public void copy() {
		Graph_Algo graph1 = new Graph_Algo();
		DGraph dg1 = new DGraph();
		Point3D[] points = new Point3D[6];
		Node[] nodes = new Node[6];

		points[0] = new Point3D(0, 0);
		points[1] = new Point3D(1, 1);
		points[2] = new Point3D(2, 2);
		points[3] = new Point3D(3, 3);
		points[4] = new Point3D(4, 4);
		points[5] = new Point3D(5, 5);

		nodes[0] = new Node(points[0]);
		nodes[1] = new Node(points[1]);
		nodes[2] = new Node(points[2]);
		nodes[3] = new Node(points[3]);
		nodes[4] = new Node(points[4]);
		nodes[5] = new Node(points[5]);

		dg1.addNode(nodes[0]);
		dg1.addNode(nodes[1]);
		dg1.addNode(nodes[2]);
		dg1.addNode(nodes[3]);
		dg1.addNode(nodes[4]);
		dg1.addNode(nodes[5]);

		dg1.connect(nodes[0].getKey(), nodes[1].getKey(), 2);
		dg1.connect(nodes[0].getKey(), nodes[2].getKey(), 3);
		dg1.connect(nodes[1].getKey(), nodes[3].getKey(), 1);
		dg1.connect(nodes[2].getKey(), nodes[3].getKey(), 2);
		dg1.connect(nodes[3].getKey(), nodes[4].getKey(), 4);
		dg1.connect(nodes[4].getKey(), nodes[5].getKey(), 1);

		graph1.init(dg1);
		Graph_Algo graph2 = new Graph_Algo();
		graph copyG = new DGraph();
		copyG = graph1.copy();
		graph2.init(copyG);

		assertEquals(graph1.isConnected(), graph2.isConnected());
		assertEquals(graph1.shortestPathDist(nodes[0].getKey(), nodes[5].getKey()),
			graph2.shortestPathDist(nodes[0].getKey(), nodes[5].getKey()));
		
		dg1.connect(nodes[5].getKey(), nodes[0].getKey(), 2);
		graph1.init(dg1);
		
		assertNotEquals(graph1.shortestPathDist(nodes[5].getKey(), nodes[0].getKey()),
			graph2.shortestPathDist(nodes[5].getKey(), nodes[0].getKey()));

	}
}
