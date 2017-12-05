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
            String [] data = new String [3];
            while((line = bufferedReader.readLine()) != null ) {
            	if (startRead==true){
            		data = line.split(" ");
            		/*if (data[0].equals("") || data[1].equals("")){
            			continue;
            		}*/
            		vertexi=new Vertex(data[0],"");
            		vertexj= new Vertex(data[1],"");
            		if(!vertexes.contains(vertexi)){
            			vertexes.add(vertexi);
            		}
            		if(!vertexes.contains(vertexj)){
            			vertexes.add(vertexj);
            		}
            		if (data[2].equals("-1")){
            			edge = new Edge(Integer.toString(count),vertexi,vertexj,90);
                		edges.add(edge);
                		count++;
            		}
            		else{
            			edge = new Edge(Integer.toString(count),vertexi,vertexj,Integer.parseInt(data[2]));
                		edges.add(edge);
                		count++;
            		}
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
	public static Vertex Find_vertex (List <Vertex> vertexes,String id){
		for (Vertex vertex : vertexes) {
			if(vertex.getId().equals(id)){
				return vertex;
			}
        }
		return null;


	}
	public static int Find_edge(List<Edge> edges, Vertex initial, Vertex end ){
		for (Edge edge : edges ){
			if (edge.getSource().equals(initial) && edge.getDestination().equals(end)){
				return edge.getWeight();
			}
		}
		return -5;
	}
	    public static void main(String [] args) {
	    	Graph graph = readMetro("metro.txt");
	    	int time = 0;
	    	String a = null;
	    	List<Edge> listOfedges = new ArrayList<Edge>();
	    	listOfedges= graph.getEdges(); // list containing all the edges 
	    	String[] data= new String [6];
	    	Scanner scan = new Scanner(System.in);
	    	System.out.println("Test----------------------------------------");
	        System.out.println("Input:\n");
	        a = scan.nextLine();
	        data= a.split(" ");
	        
	        if (data.length==1){
	        	Vertex v0 = new Vertex("0","");
	        	Vertex v1 = new Vertex("1","");
	        	Vertex v2 = new Vertex("2","");
	        	Edge e0 = new Edge("0",v0,v1,1);
	        	Edge e1 = new Edge("1",v2,v1,1);
	        	ArrayList<Vertex> v = new ArrayList<Vertex>();
	        	ArrayList<Edge> e = new ArrayList<Edge>();
	        	v.add(v0);v.add(v1);v.add(v2);//v.add(new Vertex("3",""));
	        	e.add(e0);e.add(e1);
	        	Graph g = new Graph(v,e);
	        	List<Vertex> path = DijkstraAlgorithm.BFS(g, v0).getVertexes();
		        for (Vertex vertex : path) {
		            System.out.print(vertex.getId()+" ");
		        }
		        System.out.println("");
	        }
	        if (data.length==6){ // test number 2 
	        	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        	Vertex Vx1 = Find_vertex(graph.getVertexes(),data[2]);
		        Vertex Vx2 = Find_vertex(graph.getVertexes(),data[5]);
		        dijkstra.execute(Vx1);
		        LinkedList<Vertex> path = dijkstra.getPath(Vx2);
		        System.out.println("Output:");
		        System.out.print("Path :");
		        for (Vertex vertex : path) {
		            System.out.print(vertex.getId()+" ");
		        }
		        for(int i = 0;i<path.size()-1;i++){
		        	time += Find_edge(listOfedges,path.get(i),path.get(i+1));
		        }
		        
		        System.out.println("");
		        System.out.println("Time :"+time);
		       
	        }
	  }
}


