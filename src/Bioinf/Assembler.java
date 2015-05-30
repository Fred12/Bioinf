package Bioinf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Assembler {
	
	
	public Assembler(Graph g) {
		g.buildGraph();
		//g.printAllNodeEdges();
		System.out.println("\n");
		while (g.getNodeList().size() > 1) {
			g.greedyAlgorithm();	
			//g.print4GraphVizOnlyMaxEdges();
			g.printAllNodeEdges();
			System.out.println("\n");
		}
		g.print4GraphVizOnlyMaxEdges();
		g.printAllNodeEdges();
	}	

	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		  Graph graph = new Graph();
		  FileReader fr = new FileReader("test.txt");
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
		  
		
		  Assembler assembler = new Assembler(graph);

	}
	
	
}
