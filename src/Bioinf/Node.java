/**
 *  Die Klasse Node.java ist eine Klasse zum Erzeugen von Knoten
 *  die als Wert einen String (~DNA-Sequenz) haben.
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

public class Node implements Comparable<Node> {

	String sequence;
	int anzKanten;
	ArrayList<Edge> edgeList = new ArrayList<Edge>();	
	boolean hasEdge = false;	
	Node next;
	Node previous;
	
	
	  
	public Node(String sequence) {
		this.sequence = sequence;	
	}
	
	public Node(String sequence, Node next, Node previous) {
		this.sequence = sequence;
		this.next = next;
		this.previous = previous;
	}
	
	void setNext(Node next) {
		this.next = next;
	}
	
	void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	void setSequence(String seq) {
		if (this.sequence!= null) {
			this.sequence = seq;
		}		
	}
	
	String getSequence() {
		return this.sequence;
	}
	
	//Falls nur 1 Knoten, setze eigene Sequenz als Kante
	void makeEdge(Node a) {
		Edge g = new Edge(a);
		a.setEdge(g);
	}
	
	void makeEdge(Node a,Node b) {
		int weight = getEdgeWeight(a,b);
		if (weight >0) {
			Edge e = new Edge(a,b, weight);
			a.setEdge(e);
			a.hasEdge = true;			
		}	
	}
	
	int getEdgeWeight(Node n1, Node n2) {
		String s1 = n1.getSequence();
		String s2 = n2.getSequence();	
		Sequence s = new Sequence();
		int weight = s.maxOverlapCount(s1,s2);			
		return weight;		
	}	
	
	boolean hasWeight(Node a, Node b) {
		if (a ==b ) {
			return false;
		}		
		if (getEdgeWeight(a,b) > 0) {
			return true;
		}
		return false;
	}
	
	
	void constructAllEdges(LinkedList<Node> nodeList) {
		for (Node n : nodeList) {
			if (this==n) {
				break;
			}
			else {
				makeEdge(this,n);
			}
		}
	}
	
	void setEdge(Edge e) {
		edgeList.add(e);
	}	
		
	ArrayList<Edge> getEdgeList() {
		return this.edgeList;
	}
	
	public String toString() {
		return this.sequence;
	}

	int getMaxEdgeWeight() {	
		sortEdgeList();		
		if (edgeList.size() ==0) {
			return -1;			
		}
		return this.edgeList.get(0).getWeight();	
	}
	
	Edge getMaxEdge() {		
		sortEdgeList();	
		if (edgeList.size() ==0) {
			return null;			
		}
		return edgeList.get(0);				
	}

	void sortEdgeList() {
		Collections.sort(this.edgeList,Collections.reverseOrder());		
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.getMaxEdgeWeight() > o.getMaxEdgeWeight())  {return 1;}	
		//if (this.getMaxEdgeWeight() == o.getMaxEdgeWeight())  {return 0;}	
		if (this.getMaxEdgeWeight() < o.getMaxEdgeWeight())  {return -1;}
		else return 0;						
	}	
		
}
