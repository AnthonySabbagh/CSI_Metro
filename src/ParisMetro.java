import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class ParisMetro {
	public static Graph readMetro (String fileName){
		List<Vertex> vertexes = new ArrayList<Vertex>();
	    List<Edge> edges = new ArrayList<Edge>();
	    Vertex vertexi ;
	    Vertex vertexj;
	    Edge edge;
	    int count=0;
		Graph graph = new Graph(null,null);
        // The name of the file to open.
       // String fileName = "metro.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            boolean startRead = false;
            while((line = bufferedReader.readLine()) != null ) {
            	if (startRead==true){
            		//System.out.println(line);
            		String[] data = line.split(" ");
            		//System.out.println(data[0]);
            		vertexi=new Vertex(data[0],"");
            		vertexj= new Vertex(data[1],"");
            		if(!vertexes.contains(vertexi)){
            			vertexes.add(vertexi);
            		}
            		if(!vertexes.contains(vertexj)){
            			vertexes.add(vertexj);
            		}
            		edge = new Edge(Integer.toString(count),vertexi,vertexj,Integer.parseInt(data[2]));
            		edges.add(edge);
            		count++;
            	}
            	else if (line.equals(("$"))){
            		startRead=true;
            	}
            }
            graph = new Graph(vertexes,edges);
            // Always close files.
            bufferedReader.close();
            return graph;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return graph;
      }
	public static Vertex Find (List <Vertex> vertexes,String id){
		for (Vertex vertex : vertexes) {
			if(vertex.getId().equals(id)){
				return vertex;
			}
        }
		return null;


	}
	    public static void main(String [] args) {
	    	Graph graph = readMetro("metro.txt");
	    	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	    	String a = null;
	    	String[] data= new String [6];
	    	Scanner scan = new Scanner(System.in);
	        System.out.println("Input:\n");
	        a = scan.nextLine();
	        data= a.split(" ");
	        Vertex Vx1 = Find(graph.getVertexes(),data[2]);
	        Vertex Vx2 = Find(graph.getVertexes(),data[5]);
	        System.out.println(Vx1);
	        System.out.println(Vx2);
	        
	        dijkstra.execute(Vx1);
	        LinkedList<Vertex> path = dijkstra.getPath(Vx2);

	        for (Vertex vertex : path) {
	            System.out.print(vertex.getId()+" ");
	        }
	  }
}


