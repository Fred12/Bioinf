package Bioinf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Graph {
		
	private LinkedList<Node> nodeList;	
	private int anzKnoten;
	private Edge mightBeEmpty;
	public Graph() {
		 this.setNodeList(new LinkedList<Node>());	
		 new ArrayList<Edge>();
		 this.anzKnoten = 0;
	}	
	
	void addNode(Node n) {			
		 this.getNodeList().add(n);
		 this.anzKnoten++;
	}

	void makeAllEdges(Node a, LinkedList<Node> liste) {
		if (liste.size() == 1) {
			System.out.println("Dies ist der letzte Knoten:\n");
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
	boolean buildGraph() {		
		if (checkEdges(getNodeList(),getNodeList()) == true) {
			for (Node n: this.getNodeList()) {			 		
				makeAllEdges(n, this.getNodeList());
			}
			
			//printAllNodeEdges();
			//print4GraphVizOnlyMaxEdges();
			return true;
			
		}				
		return false;			
	}
		
	
	
	boolean checkEdges(LinkedList<Node> nodeList1, LinkedList<Node> nodeList2) {
		
		for (Node n1 : nodeList1) {
			for (Node n2 : nodeList2) {
				if (n1 == n2) {
					continue;
				}
				if (n1.hasWeight(n1, n2)== true) {
				return true;
				}			
			}	
		}
		System.out.println("Keine Überlappung mehr zwischen den Sequenzen vorhanden!");
		return false;
	}
	
	
	
	
	boolean greedyAlgorithm() {	
		boolean hasEdge = false;
		if (nodeList.size() == 1) {
			System.out.println("Dies ist der einzig übrige Knoten, keine weitere Bearbeitung mehr möglich!");			
			return false;
		}	
		/*
		for (Node nn: nodeList) {
			if (nn.getEdgeList().isEmpty()==true) {
				nodeList.remove(nn);
			}
		}*/
		
		sortAllEdgesInNodes();
		sortAllNodesInGraph();
		Node newNode;
		Node actual = this.getNodeList().get(0);
		mightBeEmpty = actual.getMaxEdge();
		while (mightBeEmpty == null) {
			for ( int i = 1; i< getNodeList().size(); i++) {
				Node next = getNodeList().get(i);
				mightBeEmpty = next.getMaxEdge();
			}
		}
		//node = this.getNodeList().get(0);		
		//t = node.getMaxEdge();			
		newNode = mightBeEmpty.mergeNodes();		
		this.getNodeList().remove(mightBeEmpty.getFirstNode());
		this.getNodeList().remove(mightBeEmpty.getEndNode());
		getNodeList().add(newNode);
		//if (nodeList.size() == 0) {System.out.println("WAHR");}		
		//if (nodeList.size() == 1) {System.out.println("NUR NOCH 1 DRIN!");}
		for (Node actualNode : this.getNodeList()) {				
			actualNode.getEdgeList().clear();			
		}
		hasEdge = buildGraph();
		sortAllEdgesInNodes();
		sortAllNodesInGraph();	
		return hasEdge;
	}
	
	
	private void sortAllEdgesInNodes() {	
		for (Node n: this.getNodeList()) {
			n.sortEdgeList();  //sortiert Kantenliste pro Knoten absteigend
		}
	}
	
	
	private void sortAllNodesInGraph() {	
		//for (Node n : this.getNodeList()) {
		Collections.sort(this.getNodeList(),Collections.reverseOrder());  //sortiert Knotenliste nach Kantengewicht absteigend
		//}
	}
	
	
	void printAllNodeEdges() {
		sortAllEdgesInNodes();
		sortAllNodesInGraph();
		int i = 0;
		for (Node n : getNodeList()) {
			i++;
			ArrayList<Edge> e = n.getEdgeList();
			System.out.println("Knoten: " + i + " ("+ n.toString() + ")" + "[" + n.toString().length() + "]"  +  "	Kanten: " + e.toString());							
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

	public LinkedList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(LinkedList<Node> nodeList) {
		this.nodeList = nodeList;
	}
			
}

	
	
	
	



