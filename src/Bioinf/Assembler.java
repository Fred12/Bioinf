package Bioinf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Assembler {	
	boolean existOverlap = false;
	
	public Assembler(Graph g) {
		existOverlap = g.buildGraph();
		g.printAllNodeEdges();
		//g.printAllNodeEdges();
		System.out.println("\n");
		while (existOverlap) {
			if (g.getNodeList().size() > 1) {
			existOverlap = g.greedyAlgorithm();	
			//g.print4GraphVizOnlyMaxEdges();			
			g.printAllNodeEdges();
			System.out.println("\n");
			}
			else {
				existOverlap = false;
			}
		}
		
		//g.print4GraphVizOnlyMaxEdges();
		//g.printAllNodeEdges();
		
		//GraphViz ggg = new GraphViz();
				
	}	

	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		long startTime = System.currentTimeMillis();	       
		  Graph graph = new Graph();
		  FileReader fr = new FileReader("frag.dat");
		  BufferedReader br = new BufferedReader(fr);
		  String zeile = "";
		  Sequence seq;
		
		  while((zeile = br.readLine()) != null )
		  {
			  seq = new Sequence(zeile);
			  if (zeile.equals("") || !seq.isValid()) {
				  break;
			  }
		    Node node = new Node(zeile);
		    graph.addNode(node);
		  }
		
		  br.close();
		  
		
		  new Assembler(graph);
		  long endTime = System.currentTimeMillis();
	        System.out.println("\nExecution took " + (endTime - startTime) + " milliseconds");
	}
		
	
}
