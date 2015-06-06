/**
 *  Die Klasse Graph.java ist eine Klasse zum Erzeugen 
 *  und Aufbau eines Graphen mit gültigen Knoten und Kanten.
 *   
 *  
 *
 *  @author Marc Ludovici
 *  @Course Bioinformatik 
 *  @Date	6.05.2015
 **/


package Bioinf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

	//Erzeuge Graph;
	public class Graph {		
	private LinkedList<Node> nodeList;	
	private int anzKnoten;
	private Edge mightBeEmpty;
	public Graph() {
		 nodeList = new LinkedList<Node>();	
		 new ArrayList<Edge>();
		 this.anzKnoten = 0;
	}	

	//Knoten dem Graphen hinzufügen
	void addNode(Node n) {			
		 this.getNodeList().add(n);
		 this.anzKnoten++;
	}
	
	//Kanten zwischen allen Knoten im Graphen erzeugen
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
	
	//Konstruiere Graph, erzeuge alle Kanten aber prüfe vorher, ob
	//noch Ueberlappungen (~Kanten) zwischen den Knoten existiert
	boolean buildGraph() {		
		if (checkEdges(getNodeList(),getNodeList()) == true) {
			for (Node n: this.getNodeList()) {			 		
				makeAllEdges(n, this.getNodeList());
			}			
			return true;  	//Gibt an die Assembler Klasse den Hinweis zurück, dass es noch Ueberlappungen zwischen den Kanten gibt, 
							//und somit der Greedy-Algorithmus noch gültig ist		
		}				
		return false;			
	}
		
	
	
	//Ueberprüft ob noch Kantenübergänge/Overlaps zwischen den Knoten vorhanden sind
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
		System.out.println("\nKeine Ueberlappung mehr zwischen den Sequenzen vorhanden!");
		return false;
	}
	
	
	
	//Greedy Algorithmus, dieser sortiert alle Knoten und Kanten nach aufsteigendem Gewicht, und führt Knoten
	//mit maximaler Kantenüberlappung in einem Merge-Prozess zusammen
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
		
		sortAllEdgesInNodes(); //Vorsortierung
		sortAllNodesInGraph();		
		/*
		Merge-Prozess, hole Knoten mit max. Ueberlappung und führt diese zusammen.
		Falls der Knoten nur leere Kanten hat, gehe die Knotenliste durch und prüfe 
		ebenso für die anderen Knoten. [veraltet, da vorher schon vorsortiert wird, 
		und dann nur 1 Knoten übrigbleibt]
		Die Methode checkEdges() muss vorher sicherstellen,dass es noch Kanten 
		gibt, sonst würde der Algorithmus unendlich laufen.
		*/
		Node newNode;
		Node actual = this.getNodeList().get(0);
		mightBeEmpty = actual.getMaxEdge();
		if (mightBeEmpty == null) {
			return false;
		}
		/*
		while (mightBeEmpty == null) {
			for ( int i = 1; i< getNodeList().size(); i++) {
				Node next = getNodeList().get(i);
				mightBeEmpty = next.getMaxEdge();
			}
		}
		*/
		
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
	
	
	//Gib alle Knoten mit Kanten aus
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
	
	//Ausgabeformat mit allen Knoten und Kanten für Graphviz
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
	
	/*
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
	*/
	
	//Ausgabeformat nur mit aktuellen maximalen Knoten und Kanten für Graphviz
	void print4GraphVizOnlyMaxEdges() {		
		Edge e;
		System.out.println("digraph Knotenliste {" + "\n" + "   nodesep=1.0");
		for (Node n : getNodeList()) {	
			/*
			if (getNodeList().size() == 1) {
				System.out.println("   "+n.toString() +"\n" + "}");
				return;
			}			*/
			if (n.getEdgeList().size()== 0) {
				System.out.println("   "+n.toString());
				//break;
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
			
}

	
	
	
	



