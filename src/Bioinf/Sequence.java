/**
 *  Die Klasse Sequence.java ist eine Hilfsklasse zum Erzeugen und Überprüfen
 *  von gültigen DNA-Sequenzen.
 *  
 *  
 *
 *  @author Marc Ludovici
 *  @Course Bioinformatik 
 *  @Date	6.05.2015
 **/

package Bioinf;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sequence {
	int random;	//generate random number for A,C,G,T
	int j =0;
	int k =0;
	int n =0;
	String commonString;
	String sequence;
	StringBuffer sb;	
	private Pattern noDNA = Pattern.compile("([^ACGT])"); //Leerzeichen ist drin
	
	public Sequence() {
		
	}	
	
	public Sequence(int n) {
		this.sequence = generateSequence(n).toUpperCase();
	}
	
	public Sequence(String seq) {
		this.sequence = seq.toUpperCase();
	}	

	public enum Base {
		A,T,C,G
	}
	
		
	private String generateSequence(int n) {
		sb = new StringBuffer(n);
		
		for (int i = 0; i < n; ++i) {
			random = new Random().nextInt(Base.values().length);
			sb.append(Base.values()[random]);
		}			
		this.sequence = sb.toString();
		return sequence;				
	}
	
	private boolean compare(String seq, int i) {
		if (sequence.substring(i).equals(seq)) {			
			return true;
		}
		else return false;			
	}
	
		
	String overlap(String seq) {
		if (!isValid(seq)) {	
			return null;
		}		
		
		commonString = "";
		
		if (seq.length() <= sequence.length()) {
			j = seq.length(); 				
		}
		else {			
			j = sequence.length();				
		}			
	
		for (k = 1; k <= j; k++) {					
				if ((sequence.substring(sequence.length()-k, sequence.length()).equals(seq.substring(0, k)))) {
				commonString = seq.substring(0, k);
				}			
				else {
					continue;
				}
		}					
		if (commonString.equals("")) {
			return null;
		}
		return commonString;							
	}
	
	
	int maxOverlapCount(String seq) {
		/*
		if (!isValid(seq)) {	
			return 0;
		}*/
		
		int max = 0;
		commonString = "";
		
		if (seq.length() <= sequence.length()) {
			j = seq.length(); 				
		}
		else {			
			j = sequence.length();				
		}
		
		for (k = 1; k <= j; k++) {					
				if ((sequence.substring(sequence.length()-k, sequence.length()).equals(seq.substring(0, k)))) {
					 max = k;
				continue;
			}			
		}			
		
		return max;							
	}
	
	int maxOverlapCount(String seq1, String seq2) {
		/*
		if (!isValid(seq1) || !isValid(seq2)) {	
			return 0;
		}
		*/
		int max = 0;
		commonString = "";
		
		if (seq1.length() <= seq2.length()) {
			j = seq1.length(); 				
		}
		else {			
			j = seq2.length();				
		}
		
		for (k = 1; k <= j; k++) {					
				if ((seq1.substring(seq1.length()-k, seq1.length()).equals(seq2.substring(0, k)))) {
					 max = k;
				continue;
			}			
		}			
		
		return max;							
	}						
	
			
	  boolean isValid()
	   {
	      int length = sequence.length();

	      if (length == 0)
	      {
	    	  System.out.println("Keine gültige DNA Sequenz. Leerer String.");
	         return false;
	      }

	      Matcher m = noDNA.matcher(sequence);
	      boolean isNotDNA = m.find();
	      if ( isNotDNA )
	      {
	         System.out.println("Keine gültige DNA Sequenz. Ungültiges Zeichen '" + m.group() + "' gefunden an Position " + m.start() +". ");
	         return false;
	      }
	      else
	      {
	         return true;
	      }
	   }
	  
	  private boolean isValid(String s)
	   {
	      int length = s.length();

	      if (length == 0)
	      {
	    	  System.out.println("Keine gültige DNA Sequenz. Leerer String.");
	         return false;
	      }

	      Matcher m = noDNA.matcher(s);
	      boolean isNotDNA = m.find();
	      if (isNotDNA)
	      {
	         System.out.println("Keine gültige DNA Sequenz. Ungültiges Zeichen '" + m.group() + "' gefunden an Position " + m.start() +". ");
	         return false;
	      }
	      else
	      {
	         return true;
	      }
	   }	  
	  
	  boolean isValid(int i)
	   {
	      int length = sequence.length();

	      if (length == 0)
	      {
	    	  System.out.println("Keine gültige DNA Sequenz. Leerer String.");
	         return false;
	      }

	      Matcher m = noDNA.matcher(sequence);
	      boolean isNotDNA = m.find();
	      if ( isNotDNA )
	      {
	         System.out.println("Keine gültige DNA Sequenz. Ungültiges Zeichen '" + m.group() + "' gefunden in Zeile " + i +". ");
	         return false;
	      }
	      else
	      {
	         return true;
	      }
	   }
	
	public String toString() {			
			return sequence;
		}
	
	}
		


