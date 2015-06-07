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
	int imageCount = 0;
	String graphString;
	Proba p;
	
	public Assembler(Graph g) {
		existOverlap = g.buildGraph();
		g.print4GraphVizOnlyMaxEdges();		
		g.printAllNodeEdges();	
		//g.callGraphViz();	
		System.out.println("\n");
		
		
		while (existOverlap) {
			if (g.getNodeList().size() > 1) {			
			existOverlap = g.greedyAlgorithm();				
			System.out.println("\n");
			
			g.print4GraphVizOnlyMaxEdges();	
			g.printAllNodeEdges();				
			//g.callGraphViz();
			
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
		  BufferedReader br = null;		  
		  boolean weiter = true;
		  boolean fragen = true;
		  
		  //long startTime = System.currentTimeMillis();	   
		  Scanner scanner = new Scanner(System.in); 
		  while (weiter) {			  
		  
		  System.out.println("Bitte den Namen zur einzulesenden Datei eingeben (muss im gleichen Programmverzeichnis liegen!)");
		  String eingabe = scanner.next();
		  
		  
		  long startTime = System.currentTimeMillis();	 
		  Graph graph = new Graph();
		  int lineCount = 0;
		  try {
			  FileReader fr = new FileReader(eingabe);		  
			  br = new BufferedReader(fr);
		  }	
		  catch ( IOException e ) {
			  System.err.println( "Fehler beim Lesen der Datei! (Stellen Sie sicher, dass die Datei existiert!)" );
		  }
		  
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
	      
	      
	      
	      while (fragen) {
	    	  System.out.println("\nWollen Sie weitermachen? Bitte (j/n) eingeben: ");
	    	  eingabe = scanner.next();
	    	  if (eingabe.equals("n") || eingabe.equals("N")) {
	    	 	weiter = false;	
	    	 	fragen = false;
	    	 	System.out.println("Auf Wiedersehen!");
	    	  }
	    	  else if (eingabe.equals("j") || eingabe.equals("J")) {
	    		  fragen = false;    		  
	    		  continue;
	    	  }
	    	  else {
	    		  System.out.println("Falsche Eingabe!");
	    	  }
	      }
	}
		scanner.close();  
}

		
	
}
