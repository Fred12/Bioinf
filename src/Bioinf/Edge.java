/**
 *  Die Klasse Edge.java ist eineKlasse zum Erzeugen von Kanten
 *  zwischen zwei Knoten (~DNA-Sequenzen), falls diese einen Overlap 
 *  gemeinsam haben.
 *  
 *  
 *
 *  @author Marc Ludovici
 *  @Course Bioinformatik 
 *  @Date	6.05.2015
 **/

package Bioinf;

public class Edge implements Comparable<Edge>{
	
	int weight = 0;
	Node start;
	Node end ;
	String commonSeq;	
	int maxOverlap;
	Sequence s = new Sequence();
	
	
	public Edge(Node n){
		this.start = n;
		this.end = null;
		this.weight = this.start.getSequence().length();		
	}
	
	
	//n1 = Startknoten, n2 = Endknoten --> gerichtete Kante
	public Edge(Node n1, Node n2) {
		this.start = n1;
		this.end = n2;	
	}
	//n1 = Startknoten, n2 = Endknoten --> gerichtete Kante mit Gewichtung
	public Edge(Node n1, Node n2, int weight) {
	this.start = n1;
	this.end = n2;		
	this.weight = weight;
	}
	
	
	int getMaxOverlapCount() {		
		return maxOverlap = s.maxOverlapCount(start.getSequence(),end.getSequence());
	}
	
	Node mergeNodes(){	
		/*
		if (this.end == null || this.end.getSequence().length() == 0) {
			return this.start.getSequence();
		}
		else {*/
		String newSequence;
		int startLen = this.start.getSequence().length();
		//int endLen = this.end.getSequence().length();
		String nodeStartCut = this.start.getSequence().substring(0, startLen - getMaxOverlapCount());		
		newSequence = nodeStartCut + this.end.getSequence();	
		Node newNode = new Node(newSequence);
		return newNode;
		//}
	}
	
		void setWeight(int gew) {
		this.weight=  gew;
		}

	int getWeight() {
		return this.weight;
	}
	
	Node getFirstNode() {
		return start;
	}
	
	Node getEndNode() {
		return end;
	}

	
	String getCommonStringSequence() {
		s = new Sequence(start.getSequence());
		if (end != null) {
		return commonSeq = s.overlap(end.getSequence());		
		}
		else {
			return start.getSequence();
		}
	}
	
		
	boolean hasWeight() {
		if (this.weight > 0) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public String toString() {
		if (end == null) { 
			return start.toString() + "(" +getCommonStringSequence() + ")" + "[" + getWeight() + "]"; 
			}
		return start.toString()+ "--->" + end.toString() + "==>(" +getCommonStringSequence() + ")" + "[" + getWeight() + "]";
	}
	
	String toGraphViz() {
		if (end == null) { 
			return  start.toString() + " [label=\"" + getCommonStringSequence() + getWeight() +"\"]";
			}
		return  start.toString()+ " -> " + end.toString() + " [label=\"" + getCommonStringSequence() + getWeight() +"\"]";
	}

	@Override
	public int compareTo(Edge other) {
		if (this.getWeight() > other.getWeight()) { return 1;}
		if (this.getWeight() == other.getWeight()) { return 0;}
		if (this.getWeight() < other.getWeight()) { return -1;}
		return 0;
	}
	
	
}

