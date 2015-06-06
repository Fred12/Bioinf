/**
 *  Die Klasse Assembler.java ist eine Klasse mit Main-Methode
 *  zum Starten des Programms. Diese Klasse vereint alle übrigen
 *  Klassen und benutzt diese zum korrekten Ausführen des Programms. 
 *   
 *  
 *
 *  @author Marc Ludovici
 *  @Course Bioinformatik 
 *  @Date	6.05.2015
 **/


package Bioinf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Assembler {	
	boolean existOverlap = false;
	
	public Assembler(Graph g) {
		existOverlap = g.buildGraph();
		g.printAllNodeEdges();		
		System.out.println("\n");
		while (existOverlap) {
			if (g.getNodeList().size() > 1) {
			//g.print4GraphVizOnlyMaxEdges();	
			existOverlap = g.greedyAlgorithm();				
			System.out.println("\n");
			g.printAllNodeEdges();
			//g.print4GraphVizOnlyMaxEdges();	
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
		  //long startTime = System.currentTimeMillis();	   
		  Scanner scanner = new Scanner(System.in); 
		  System.out.println("Bitte den Namen zur einzulesenden Datei angeben (muss im selben Programmverzeichnis liegen!)");
		  String eingabe = scanner.next();
		  long startTime = System.currentTimeMillis();	 
		  Graph graph = new Graph();
		  int lineCount = 0;
		  FileReader fr = new FileReader(eingabe);
		  BufferedReader br = new BufferedReader(fr);
		  String zeile = "";
		  Sequence seq;
		
		  while((zeile = br.readLine()) != null )
		  {
			  ++lineCount;	
			  zeile= zeile.trim();
			  
			  /*
			  if (zeile.contains(" ")) {
				  zeile = zeile.replaceAll(" ", "");				 
			  }*/
			  
			  seq = new Sequence(zeile);
			  
			  if (zeile.equals("") || !seq.isValid(lineCount)) {				  
				  //break;
				  continue;
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
