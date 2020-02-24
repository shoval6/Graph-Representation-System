package Tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.crypto.NodeSetData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.Edge;
import dataStructure.edge_data;

class EdgeTest {
	
	static edge_data[] edgesTest = new edge_data[7];	

	@BeforeEach
	public void BeforeEach() {
		edgesTest[0] = new Edge(1,2,0);
		edgesTest[1] = new Edge(2,3,1);
		edgesTest[2] = new Edge(3,4,2);
		edgesTest[3] = new Edge(4,5,3);
		edgesTest[4] = new Edge(5,6,4);
		edgesTest[5] = new Edge(6,7,5);
		edgesTest[6] = new Edge(7,8,6);

	}
	
	
	@Test
	public void getSrc() {
		int[] expected = {1,2,3,4,5,6,7};
		for(int i=0; i<expected.length; i++)
			assertEquals(expected[i], edgesTest[i].getSrc());
	}

	
	@Test
	public void getDest() {
		int[] expected = {2,3,4,5,6,7,8};
		for(int i=0; i<expected.length; i++)
			assertEquals(expected[i], edgesTest[i].getDest());
		
	}
	
	
	@Test
	public void getWeight() {
		int[] expected = {0,1,2,3,4,5,6};
		for(int i=0; i<expected.length; i++)
			assertEquals(expected[i], edgesTest[i].getWeight());
	}
	
	
	@Test
	public void getInfo() {
		for(int i=0; i<edgesTest.length; i++)
			assertEquals("", edgesTest[i].getInfo());
	}
	
	
	@Test
	public void setInfo() {
		for(int i=0; i<edgesTest.length; i++)
			edgesTest[i].setInfo("test");
		for(int i=0; i<edgesTest.length; i++)
			assertEquals("test", edgesTest[i].getInfo());
	}
	
	
	@Test
	public void getTag() {
		for(int i=0; i<edgesTest.length; i++)
			assertEquals(0, edgesTest[i].getTag());
	}
	
	
	@Test
	public void setTag() {
		for(int i=0; i<edgesTest.length; i++)
			edgesTest[i].setTag(1);
		for(int i=0; i<edgesTest.length; i++)
			assertEquals(1, edgesTest[i].getTag());
	}
}
