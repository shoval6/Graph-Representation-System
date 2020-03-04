# Graph Representation System
This project is a system that allows the user to create weighted directed graphs ,<br />
and use graph algorithms.<br />
The system displays all the features by a GUI.<br />
This OOP project also contains JUNIT5 Tests.<br />
 
## GUI
The GUI is a coordinates system such that the user can creates customized graphs by choosing\
X and Y coordinates for placing the vertices.<br />

![enter image description here](https://github.com/shoval6/Graph-Representation-System/blob/master/img/1-img.png?raw=true)


## Run
**First option :**  download and run the JAR file.<br />
**Second option:** <br />
1. Download the project as a ZIP file.
2. Open Eclipse.
3. Go to File -> Open Project from File System or Archive.
4. Click -> Archive.
5. Choose the Zip file you have downloaded in Step 1.
6. Go to gui package and run the main in GUI.java .

## Features

* Add and Remove vertices to the graph.
* Add and Remove edges to the graph.
* Create weighted directed graph.
* Save the graph to a file.
* Load the graph from a file, and continue working from the last state.
* Check if the graph is strongly connected.
* Check the shortest distance between two vertices.
* Find the shortest Path between two vertices.
* Compute a relatively short path which visit each vertex in a given list.

**For example :**
![enter image description here](https://github.com/shoval6/Graph-Representation-System/blob/master/img/2-img.png?raw=true)

In the above example , we can see a weighted directed graph with 6 vertices.<br />
The number that appears in the center of each edge , represents its weight.<br />
The yellow circle represents the direct of the edge.<br />
We want to find the shortest path between vertex 1 to vertex 5 , so after running the algorithm we can see<br />
that the shortest path is **1->6->4->5** (green line).<br />

## Project Structure

### (~) Node Class
This class represents a vertex in the graph. 
The Node class implements the node_data Interface.
 * Key - the id of the node.
 * Tag - it's a flag that can be used by algorithms.
 * Weight - represent the cost of the path that take to get from source node to this node (destination node). 
 * Info - same as Tag .
 * Location - represent the location (Point3D) of the node on the axis - X Y .

### (~) Edge Class
This class represents an edge in the graph.
The Edge class implements the edge_data Interface.
* Src - the id of the source node of this edge ,
* Dest - the id of the destination node of the edge.
* Weight - represent the cost of the edge.
* Tag - it's a flag that can be used by algorithms.
* Info - same as Tag.

### (~) DGraph Class
This class represents a weighted directed graph.
This class implements the graph Interface.
* Vertices - a HashMap that contains all the graph vertices.
* Edges - a HashMap the contains all the graph edges.
* EdgesCount - number of the edges in the graph.
* NodesCount - number of the vertices in the graph.
* ModeCount - number of the changes in the graph (add and remove).

### (~) Graph_Algo Class
This class represents the "regular" Graph Theory algorithms,
This class implements the graph_algorithms Interface.
* graph - an instance of DGraph .
 **Main methods:**
* init - initialize a graph from a file.
* save - save a graph to a file.
* isConnected - check if there is a valid path from EVREY node to each other node.
* shortestPathDist - check the shortest length of the path between 2 vertices.
* shortestPath - find the shortest path between 2 vertices.
* TSP - compute a relatively short path which visit each node in the targets List.
 
### (~) GUI 
**GUI Class**
This class manages all the view settings of the system.<br />
The GUI was built in Java Swing.<br />
**GUIHandler Class**<br />
This is the controller of the GUI. All the user inputs and interactions are handle here.<br />
