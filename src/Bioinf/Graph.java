package Bioinf;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {
		
	private ArrayList<Node> nodeList;	
	private ArrayList<Edge> maxEdgeWeightList;
	private int anzKnoten;
	//private boolean edgeSorted = false;  //check ob alle Knaten pro Knoten absteigend sortiert wurden
	//private boolean nodeSorted = false; //check ob Knoten nach Kantengewicht absteigend sortiert wurden
	//private boolean maxGraph = false; //check ob maximum Graph konstruiert wurde
		
	public Graph() {
		 this.nodeList = new ArrayList<Node>();	
		 this.maxEdgeWeightList = new ArrayList<Edge>();
		 this.anzKnoten = 0;
	}	
	
	void addNode(Node n) {			
		 this.nodeList.add(n);
		 this.anzKnoten++;
	}

	void makeAllEdges(Node a, ArrayList<Node> liste) {
		if (liste.size() == 1) {
			System.out.println("Nur noch 1 Knoten Vorhanden!");
			a.makeEdge(a);
			return;
		}
		for (Node n : liste) {
			if (a==n) {
				continue;
			}
			else {
				a.makeEdge(a,n);
			}
		}
	}	
	
	//Konstruiere alle Kanten für alle Koten im Graph
	void buildGraphEdges() {		
		for (Node n: this.nodeList) {
			makeAllEdges(n, this.nodeList);
		}		
	}
	
	
	void mergeNode() {	
		/*
		if (nodeList.size() == 1) {
			System.out.println("Nur noch 1 Knoten Vorhanden!");			
			return;
		}	*/	
		for (Node nn: nodeList) {
			if (nn.getEdgeList().isEmpty()==true) {
				nodeList.remove(nn);
			}
		}
		sortAllEdgesInNodes();
		sortAllNodesInGraph();
		Edge t;
		String newSeq;
		Node node;	
		Node newNode;
		node = this.nodeList.get(0);		
		t = node.getMaxEdge();			
		newSeq = t.mergeNodes();
		this.nodeList.remove(t.start);
		this.nodeList.remove(t.end);
		//if (nodeList.size() == 0) {System.out.println("WAHR");}
		newNode = new Node(newSeq);
		nodeList.add(newNode);
		//if (nodeList.size() == 1) {System.out.println("NUR NOCH 1 DRIN!");}
		for (Node actualNode : this.nodeList) {				
			actualNode.getEdgeList().clear();			
		}
		buildGraphEdges();
		/*
		for (Node bb: nodeList) {
			System.out.println(bb.getEdgeList().toString());
		}*/
	}
	
	
	private void sortAllEdgesInNodes() {	
		for (Node n: this.nodeList) {
			n.sortEdgeList();  //sortiert Kantenliste pro Knoten absteigend
		}
	}
	
	
	private void sortAllNodesInGraph() {	
		for (Node n : this.nodeList) {
		Collections.sort(this.nodeList,Collections.reverseOrder());  //sortiert Knotenliste nach Kantengewicht absteigend
		}
	}
	
	
	void printAllNodeEdges() {
		int i = 0;
		for (Node n : nodeList) {
			i++;
			ArrayList<Edge> e = n.getEdgeList();
			System.out.println("Knoten: " + i + "	Kanten: " + e.toString());							
			}
	}
	
	private void print4GraphViz() {
		System.out.println("digraph Knotenliste {" + "\n" + "   nodesep=1.0");
		for (Node n : nodeList) {			
			ArrayList<Edge> e = n.getEdgeList();
			for (Edge t : e ) {
				System.out.println(t.toGraphViz());
			}								
		}
		System.out.println("}");
	}
	
	void buildMaxWeightGraph() {
		Edge e;
		for (Node n : nodeList) {			
			e = n.getMaxEdge();
			System.out.println(e.toGraphViz());								
		}
	}
	
	void mergeAll() {
		for (int i = 0 ; i<= nodeList.size(); i++) {
			mergeNode();
		}
	}
		
	
	private void print4GraphVizOnlyMaxEdges() {		
		Edge e;
		System.out.println("digraph Knotenliste {" + "\n" + "   nodesep=1.0");
		for (Node n : nodeList) {			
			e = n.getMaxEdge();
			System.out.println(e.toGraphViz());			
		}		
		System.out.println("}");
	}
		

	public static void main(String[] args) throws IOException
	{
		  Graph graph = new Graph();
		  FileReader fr = new FileReader("frag.dat");
		  BufferedReader br = new BufferedReader(fr);
		  String zeile = "";
		
		  while((zeile = br.readLine()) != null )
		  {
			  if (zeile.equals("")) {
				  break;
			  }
		    Node node = new Node(zeile);
		    graph.addNode(node);
		  }
		
		  br.close();
		  
		  graph.buildGraphEdges();
		  graph.sortAllEdgesInNodes();
		  //graph.sortAllNodesInGraph();
		  //graph.printAllNodeEdges();
		  graph.mergeAll();
		  graph.mergeAll();
		  graph.mergeAll();
		  //graph.mergeNode();
		  //graph.printAllNodeEdges();		  
		  //graph.mergeNode();
		  //graph.mergeNode();
		  /*
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();
		  graph.mergeNode();*/
		  graph.printAllNodeEdges();
		  //graph.print4GraphVizOnlyMaxEdges();
		  
		  //graph.print4GraphVizOnlyMaxEdges();
		  
		  
	}
	
}

	
	
	
	



