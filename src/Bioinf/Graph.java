package Bioinf;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {
		
	private ArrayList<Node> nodeList;	
	private ArrayList<Edge> maxEdgeWeightList;
	private int anzKnoten;
	
	
	public Graph() {
		 this.setNodeList(new ArrayList<Node>());	
		 this.maxEdgeWeightList = new ArrayList<Edge>();
		 this.anzKnoten = 0;
	}	
	
	void addNode(Node n) {			
		 this.getNodeList().add(n);
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
	void buildGraph() {		
		for (Node n: this.getNodeList()) {
			makeAllEdges(n, this.getNodeList());
		}		
	}
	
	
	void greedyAlgorithm() {	
		/*
		if (nodeList.size() == 1) {
			System.out.println("Nur noch 1 Knoten Vorhanden!");			
			return;
		}		
		for (Node nn: nodeList) {
			if (nn.getEdgeList().isEmpty()==true) {
				nodeList.remove(nn);
			}
		}
		*/
		sortAllEdgesInNodes();
		sortAllNodesInGraph();
		Edge t;
		String newSeq;
		Node node;	
		Node newNode;
		node = this.getNodeList().get(0);		
		t = node.getMaxEdge();		
		newSeq = t.mergeNodes();		
		this.getNodeList().remove(t.start);
		this.getNodeList().remove(t.end);
		//if (nodeList.size() == 0) {System.out.println("WAHR");}
		newNode = new Node(newSeq);
		getNodeList().add(newNode);
		//if (nodeList.size() == 1) {System.out.println("NUR NOCH 1 DRIN!");}
		for (Node actualNode : this.getNodeList()) {				
			actualNode.getEdgeList().clear();			
		}
		buildGraph();
		sortAllEdgesInNodes();
		sortAllNodesInGraph();		
	}
	
	
	private void sortAllEdgesInNodes() {	
		for (Node n: this.getNodeList()) {
			n.sortEdgeList();  //sortiert Kantenliste pro Knoten absteigend
		}
	}
	
	
	private void sortAllNodesInGraph() {	
		for (Node n : this.getNodeList()) {
		Collections.sort(this.getNodeList(),Collections.reverseOrder());  //sortiert Knotenliste nach Kantengewicht absteigend
		}
	}
	
	
	void printAllNodeEdges() {
		int i = 0;
		for (Node n : getNodeList()) {
			i++;
			ArrayList<Edge> e = n.getEdgeList();
			System.out.println("Knoten: " + i + "	Kanten: " + e.toString());							
			}
	}
	
	private void print4GraphViz() {
		System.out.println("digraph Knotenliste {" + "\n" + "   nodesep=1.0");
		for (Node n : getNodeList()) {			
			ArrayList<Edge> e = n.getEdgeList();
			for (Edge t : e ) {
				System.out.println(t.toGraphViz());
			}								
		}
		System.out.println("}");
	}
	
	void buildMaxWeightGraph() {
		Edge e;
		for (Node n : getNodeList()) {			
			e = n.getMaxEdge();
			System.out.println(e.toGraphViz());								
		}
	}
	
	void mergeAll() {
		for (int i = 0 ; i<= getNodeList().size(); i++) {
			greedyAlgorithm();
		}
	}		
	
	void print4GraphVizOnlyMaxEdges() {		
		Edge e;
		System.out.println("digraph Knotenliste {" + "\n" + "   nodesep=1.0");
		for (Node n : getNodeList()) {
			if (n.getEdgeList().size()== 0) {
				break;
			}
			else {
			e = n.getMaxEdge();
			System.out.println(e.toGraphViz());	
			}
		}		
		System.out.println("}");
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
			
	
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
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
		  
		  while (graph.getNodeList().size() > 1) {
				graph.greedyAlgorithm();
				graph.print4GraphVizOnlyMaxEdges();
			}
			graph.print4GraphVizOnlyMaxEdges();
			graph.printAllNodeEdges();
		  
		  //Assembler assembler = new Assembler(graph);

	}
	
}

	
	
	
	



