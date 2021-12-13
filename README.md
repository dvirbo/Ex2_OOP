# Ex2 OOP Ariel university
<img src="https://img.freepik.com/free-photo/network-with-nodes-connected-background-technology-concept_34478-108.jpg?size=626&ext=jpg" alt="drawing" width="650"/>



The theme of the project is  Design and implementation of directed and weighted graphs in Java.\
the project contain six packages:

- Interfaces: contain all the Interfaces that we implemented - 
- NodeData: This interface represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.
- EdgeData: represents the set of operations applicable on a directional edge(src,dest) in a (directional) weighted graph.
- GeoLocation: represents a geo location <x,y,z>, (aka Point3D).
- DirectedWeightedGraph: represents a Directional Weighted Graph.
-  DirectedWeightedGraphAlgorithms: represents a Directed (positive) Weighted Graph with Different algorithms.

### Classes: the heart of the project - 
- CEdge: implement EdgeData. 
- CNode: implement NodeData.
- CGeo: implement GeoLocation.
- G: implement DirectedWeightedGraph.
- GA: implement DirectedWeightedGraphAlgorithms.
        
- ClassesTest: Junit 5 test to all the above Classes.


Last but not least, Ex2: the main class for Ex2 - the implementation will be tested using this class.

```
src
|
├───Classes
├───ClassesTest
├───FileHandling
├───FileTest
├───gui
│   ├───buttons
│   ├───graph
│   └───resources
└───Interfaces
```






## Authors

- [@dvir borochov](https://github.com/dvirbo)
- [@Dolev Dublon](https://github.com/dolev146)





## Ex2 - Links:
1. Github link: https://github.com/benmoshe/OOP_2021/tree/main/Assignments/Ex2
2. Document: https://docs.google.com/document/d/17h5VGIHtqWHrzgoRjH05_PjHgCn8-EDcecrkR9sVChQ/edit?usp=sharing
3. Submit form: https://docs.google.com/forms/d/e/1FAIpQLScmgk5yGtBSPvkb1YitSogv7quqSo8HqY-vKcEq656Yh9oRAg/viewform?usp=sf_link
4. Ex2 all submitted: https://docs.google.com/spreadsheets/d/1Is8cuBjo1bOAvPoeLIQ5VCLWvlilEAOzVzv6tbIt-u8/edit?usp=sharing

## Bibliography

1. center implementation https://codeforces.com/blog/entry/17974
2. Graph Implementation in Java using HashMap: https://progressivecoder.com/graph-implementation-in-java-using-hashmap 
3. create and work with Java Swing: https://www.guru99.com/java-swing-gui.html \
4. Deserializing JSON: https://www.youtube.com/watch?v=HSuVtkdej8Q&t=1139s

## UML
![Classes](https://user-images.githubusercontent.com/73783656/145710069-7ad8bc5d-80bb-4c25-bb4c-a927f0caa715.png)

## Main algorithm's
- create a graph (it's optional to add vertexes and edges)
- init: Init the graph on which this set of algorithms operates on
- copy: Compute a deep copy of a weighted graph.
- Isconected: check if the graph is strongly connected (there is a valid path from each node to each other node).
- shortestPathDist: Computes the length of the shortest path between source vertex to destination vertex consider the weight
- shortestPath: returns list of nodes of the shortest path between source vertex to destination vertex consider the weight of each edge.
- center: Finds the node which minimizes the max distance to all the other nodes.
- tsp: Computes a list of consecutive nodes which go over all the nodes in cities.
- save: save your graph in JSON format.
- load: load a graph from a JSON format.


## Usage/Examples

* Example of a test we did:

![image_2021-12-10_10-26-33](https://user-images.githubusercontent.com/73783656/145543692-23f0db48-580c-4fe2-90fc-252460bb36fc.png)

We built a graph whose center is 2 and checked if the algorithm finds it required

![‏‏לכידה78](https://user-images.githubusercontent.com/73783656/145544074-f76ff2a3-b4f0-46b4-b87e-b4422f9f4138.JPG)


## Run Locally

Clone the project

```bash
  git clone https://github.com/dvirbo/Ex2_OOP.git
```

Go to the project directory

```bash
  cd Ex2_OOP
```

Start the server

```bash
  java -jar Ex2.jar G1.json 
```


## Performance results

![image](https://user-images.githubusercontent.com/62290677/145838786-a347efae-cffb-46c5-8739-0e6f15d4db62.png)


## File Handling

FileHandling: save & load to/from a json files and DirectedWeightedGraph.

-    CExport:
-    CImport:
-   StoreNE:
-   JsonEdge
-   JsonNode
-   ArrNE

General Explanation of Files The files are intended to convert between the Jason format and the java object format.We decided to write the files in a separate folder in order to maintain a certain order in the code so that everything related to the algorithm will be in the folder of the algorithms.

## Gui
|![image](https://user-images.githubusercontent.com/62290677/145842349-855db9ff-1741-46e3-b9c2-13a4a3504514.png)  | ![image](https://user-images.githubusercontent.com/62290677/145846306-fbfe323c-cb5d-4f66-950d-27cf5c1f2825.png) | ![image](https://user-images.githubusercontent.com/62290677/145845800-9c050735-7684-48b7-ad0e-6c0e1abae2c6.png)| 
:-----|----------------:|:-------------------------:

### GFrame the heart of the gui

the main control of the gui, the Frame has an event listener for all the buttons in the menu bar,
so every time a button is pressed we need to catch that even and decide what we want to do;
the Graph Panel is the component that draw the nodes and the edges by looping the Edges to draw them and Nodes to draw ;
we used React concept of saving a single source of truth , because we had a lot of confusion about the that path and what needed to be changed in the view;
we used public static GFrameG in every function that need this information, so we don't need to pass parameter;
see more information about how to get data from the gui in YouTube video


[![IMAGE ALT TEXT HERE](https://user-images.githubusercontent.com/62290677/145876651-1d1b17d5-c816-432c-bc1a-ee3f5f3630d1.png)](https://www.youtube.com/watch?v=BAqLrfdZ27Y)





## Screenshots
![image](https://user-images.githubusercontent.com/62290677/145862548-cde34cd1-3f64-41d5-aaf1-ac7c3c52875c.png)
![image](https://user-images.githubusercontent.com/62290677/145862588-5ed1fe57-5f58-4d66-ba18-220845349dd1.png)
![image](https://user-images.githubusercontent.com/62290677/145863194-30b79cdb-e957-4d74-827d-ed5a0fbc3182.png)
![image](https://user-images.githubusercontent.com/62290677/145863323-fe00d6b0-bb6f-41e8-9244-c7ba3e4e81a8.png)
![image](https://user-images.githubusercontent.com/62290677/145863573-33f5abd1-122c-4df4-9b18-9f69967c3aec.png)



