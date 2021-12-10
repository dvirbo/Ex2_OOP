
# Ex2 OOP Ariel university

The theme of the project is  Design and implementation of directed and weighted graphs in Java.\
the project contain six packages: 
    
        Interfaces: contain all the Interfaces that we implemented - 
            NodeData: This interface represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.
            EdgeData: represents the set of operations applicable on a directional edge(src,dest) in a (directional) weighted graph.
            GeoLocation: represents a geo location <x,y,z>, (aka Point3D).
            DirectedWeightedGraph: represents a Directional Weighted Graph.
            DirectedWeightedGraphAlgorithms: represents a Directed (positive) Weighted Graph with Different algorithms.

        Classes: the heart of the project - 
            CEdge: implement EdgeData. 
            CNode: implement NodeData.
            CGeo: implement GeoLocation.
            G: implement DirectedWeightedGraph.
            GA: implement DirectedWeightedGraphAlgorithms.
        
        ClassesTest: Junit 5 test to all the above Classes.

        FileHandling: save & load to/from a json files and DirectedWeightedGraph.
            CExport:
            CImport:
            StoreNE:
        
        gui: Responsible for the graphical interface we present.

 Last but not least, Ex2: the main class for Ex2 - the implementation will be tested using this class.
  


    




## Authors

- [@dvir borochov](https://github.com/dvirbo)
- [@Dolev Dublon](https://github.com/dolev146)




## Ex2 - Links:
1. Github link: https://github.com/benmoshe/OOP_2021/tree/main/Assignments/Ex2
2. Document: https://docs.google.com/document/d/17h5VGIHtqWHrzgoRjH05_PjHgCn8-EDcecrkR9sVChQ/edit?usp=sharing
3. Submit form: https://docs.google.com/forms/d/e/1FAIpQLScmgk5yGtBSPvkb1YitSogv7quqSo8HqY-vKcEq656Yh9oRAg/viewform?usp=sf_link
4. Ex2 all submitted: https://docs.google.com/spreadsheets/d/1Is8cuBjo1bOAvPoeLIQ5VCLWvlilEAOzVzv6tbIt-u8/edit?usp=sharing
## Documentation

Graph Implementation in Java using HashMap: https://progressivecoder.com/graph-implementation-in-java-using-hashmap \
create and work with Java Swing: https://www.guru99.com/java-swing-gui.html \
Deserializing JSON: https://www.youtube.com/watch?v=HSuVtkdej8Q&t=1139s

## UML
![src](https://user-images.githubusercontent.com/73783656/145378693-9f518c95-3c60-4ee2-921d-5b8daddfaee9.png)

## Main algorithmes 
- create a grpah (it's aptional to add vertexes and edges)
- init: Inits the graph on which this set of algorithms operates on
- copy: Compute a deep copy of a weighted graph.
- Isconected: check if the graph is strongly connected (there is a valid path from each node to each other node).
- shortestPathDist: Computes the length of the shortest path between source vertex to destination vertex consider the weight
- shortestPath: returns list of nodes of the shortest path between source vertex to destination vertex consider the weight of each edge.
- center: Finds the node which minimizes the max distance to all the other nodes.
- tsp: Computes a list of consecutive nodes which go over all the nodes in cities.
- save: save your graph in JSON format.
- load: load a graph from a JSON format.


## Usage/Examples

* Example of a test we did:\

![image_2021-12-10_10-26-33](https://user-images.githubusercontent.com/73783656/145543692-23f0db48-580c-4fe2-90fc-252460bb36fc.png)

 We built a graph whose center is 2 and checked if the algorithm finds it required

![‏‏לכידה78](https://user-images.githubusercontent.com/73783656/145544074-f76ff2a3-b4f0-46b4-b87e-b4422f9f4138.JPG)
## Run Locally


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


## Running Tests

To run tests, run the following command

```bash
  npm run test
```


## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

