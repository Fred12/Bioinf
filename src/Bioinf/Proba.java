package Bioinf;

import java.io.File;

public class Proba
{
	
/*
   public static void main(String[] args)
   {
      Proba p = new Proba();
      String s1 = new String("A -> B;" + "\n" + "A -> C;" +"\n" + "B -> D;"+ "\n" + "B -> E;");
  	  int i = 1;
      //p.start();
      p.start2(s1,i);
   } 
*/	
   void start2(String s, int i)
   {
	  String input = s;
      GraphViz gv = new GraphViz();
      //gv.addln(gv.start_graph());
      gv.addln(input);      
      //gv.addln(gv.end_graph());
      //System.out.println(gv.getDotSource());
      
      	String type = "gif";
//      String type = "dot";
//      String type = "fig";    // open with xfig
//      	String type = "pdf";
//      String type = "ps";
//      String type = "svg";    // open with inkscape
//      String type = "png";
//      String type = "plain";
//      File out = new File("/tmp/out." + type);   // Linux
      	File out = new File("C:/Users/Marc/Desktop/Graph/out" +i + "." + type);    // Windows
      	gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
   }
   

   /**
    * Construct a DOT graph in memory, convert it
    * to image and store the image in the file system.
    */
   private void start()
   {
	  int i = 1;
      GraphViz gv = new GraphViz();
      gv.addln(gv.start_graph());
      String s = new String("A -> B;" + "\n" + "A -> C;" +"\n" + "B -> D;");
      //gv.addln("A -> B;");
      //gv.addln("A -> C;");
      gv.addln(s);
      gv.addln(gv.end_graph());
      System.out.println(gv.getDotSource());
      
      String type = "gif";
//      String type = "dot";
//      String type = "fig";    // open with xfig
//      	String type = "pdf";
//      String type = "ps";
//      String type = "svg";    // open with inkscape
//      String type = "png";
//      String type = "plain";
//      File out = new File("/tmp/out." + type);   // Linux
      	File out = new File("C:/Users/Marc/Desktop/out" + i + "." + type);    // Windows
      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
   }
   
   /**
    * Read the DOT source from a file,
    * convert to image and store the image in the file system.
    */
   private void start2()
   {
      //String dir = "/home/jabba/eclipse2/laszlo.sajat/graphviz-java-api";     // Linux
      //String input = dir + "/sample/simple.dot";
	   String input = "c:/eclipse.ws/graphviz-java-api/sample/simple.dot";    // Windows
	   
	   GraphViz gv = new GraphViz();
	   gv.readSource(input);
	   System.out.println(gv.getDotSource());
   		
      String type = "gif";
//    String type = "dot";
//    String type = "fig";    // open with xfig
//    String type = "pdf";
//    String type = "ps";
//    String type = "svg";    // open with inkscape
//    String type = "png";
//      String type = "plain";
//	   File out = new File("/tmp/simple." + type);   // Linux
	   File out = new File("C:/Users/Marc/Desktop/out." + type);   // Windows
	   gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
   }
}
