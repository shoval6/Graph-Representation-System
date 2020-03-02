package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

class NodeTest {

	static node_data[] nodesTest = new node_data[7];
    static Point3D[] pointsTest = new Point3D[7];
	
    @BeforeEach
	void BeforeEach() {
		pointsTest[0] = new Point3D(0,0);
		pointsTest[1] = new Point3D(1,1);
		pointsTest[2] = new Point3D(2,2);
		pointsTest[3] = new Point3D(3,3);
		pointsTest[4] = new Point3D(4,4);
		pointsTest[5] = new Point3D(5,5);
		pointsTest[6] = new Point3D(6,6);
		
		nodesTest[0] = new Node(pointsTest[0]);
		nodesTest[1] = new Node(pointsTest[1]);
		nodesTest[2] = new Node(pointsTest[2]);
		nodesTest[3] = new Node(pointsTest[3]);
		nodesTest[4] = new Node(pointsTest[4]);
		nodesTest[5] = new Node(pointsTest[5]);
		nodesTest[6] = new Node(pointsTest[6]);
		
		nodesTest[0].setKey(1);
		nodesTest[1].setKey(2);
		nodesTest[2].setKey(3);
		nodesTest[3].setKey(4);
		nodesTest[4].setKey(5);
		nodesTest[5].setKey(6);
		nodesTest[6].setKey(7);
		
	}
    
    @Test
    public void getKey() {
    	int[] expected = {1,2,3,4,5,6,7};
    	for(int i=0; i<expected.length; i++)
    		assertEquals(expected[i], nodesTest[i].getKey());
    }
    
    
    @Test
    public void getLocation() {
    	Point3D[] expected = {pointsTest[0],
    						  pointsTest[1],
    						  pointsTest[2],
    						  pointsTest[3],
    						  pointsTest[4],
    						  pointsTest[5],
    						  pointsTest[6]
    								  		};
    	for(int i=0; i<expected.length; i++)
    		assertEquals(expected[i], nodesTest[i].getLocation());
    	
    }
    
    
    @Test
    public void setLocation() {
    	for(int i=0; i<nodesTest.length; i++) {
    		pointsTest[i].add(1,1,0);
    		nodesTest[i].setLocation(pointsTest[i]);
    	}
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals(pointsTest[i], nodesTest[i].getLocation());
    	
    }
    
    
    @Test
    public void getWeight() {
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals(0, nodesTest[i].getWeight());
    }
    
    
    @Test
    public void setWeight() {
    	for(int i=0; i<nodesTest.length; i++)
    		nodesTest[i].setWeight(5);
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals(5, nodesTest[i].getWeight());
    }
    
    
    @Test
    public void getInfo() {
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals("", nodesTest[i].getInfo());
    }
    
    
    @Test
    public void setInfo() {
    	for(int i=0; i<nodesTest.length; i++)
    		nodesTest[i].setInfo("test");
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals("test", nodesTest[i].getInfo());
    }
    
    
    @Test
    public void getTag() {
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals(0, nodesTest[i].getTag());
    }
    
    
    @Test
    public void setTag() {
    	for(int i=0; i<nodesTest.length; i++)
    		nodesTest[i].setTag(1);
    	for(int i=0; i<nodesTest.length; i++)
    		assertEquals(1, nodesTest[i].getTag());
    }
       	
}
