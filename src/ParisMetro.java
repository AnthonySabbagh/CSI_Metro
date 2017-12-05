import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class ParisMetro {
    private static int[][] matrix = new int[1000][1000];

	public static Graph readMetro (String fileName){
		for (int i = 0; i<1000; i++){
			for (int j = 0; j<1000; j++){
				matrix[i][j]=0;
			}
		}
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
            		matrix[Integer.parseInt(data[0])][Integer.parseInt(data[1])] = Integer.parseInt(data[2]); 
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
                		if (vertexi.getId().equals("0")){
                			System.err.println("adfadfadfadsf"+vertexj.getId());
                		}
                		
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
	public static void BFS(int start)
	{
		System.out.println();
	    int v=matrix.length;//matrix[][] is adj matrix declared globally
	    boolean visited[]=new boolean[v];//indexing done from 1 to n
	    LinkedList<Integer> queue=new LinkedList<Integer>();
	    visited[start]=true;
	    queue.add(start);
	    while(queue.size()!=0)
	    {
	        int x=queue.remove();
	        System.out.print(x+" ");
	        for (int i=1; i < v; i++) 
	            if((matrix[x][i] >0) && (!visited[i]))
	            {
	              queue.add(i);
	              visited[i]=true;
	             }
	     }
	    
	    //for (int i = 0; i<visited.length; i++){
	    	//if (visited[i])
	    		//System.out.print(i+" ");
	    //}
	    System.out.println();
	}
	    public static void main(String [] args) {
	    	Graph graph = readMetro("metro.txt");
	    	int time = 0;
	    	//String a = null;
	    	List<Edge> listOfedges = new ArrayList<Edge>();
	    	listOfedges= graph.getEdges(); // list containing all the edges 
	    	//String[] data= new String [6];
	    	//Scanner scan = new Scanner(System.in);
	    	System.out.println("Test----------------------------------------");
	        //System.out.println("Input:\n");
	        //a = scan.nextLine();
	        //data= a.split(" ");
	        //for (Edge e:graph.getEdges()){
	        	//System.out.println("Source: "+e.getSource().getId()+" Destination: "+e.getDestination().getId()+" Label: "+e.getLabel());
		    	//System.out.flush();

	        //}
	        //if (args.length==4){
	        	//for (Edge e:Find_vertex(graph.getVertexes(),"0").getEdges()){
		        	//System.out.println("Source: "+e.getSource().getId()+" Destination: "+e.getDestination().getId()+" Label: "+e.getLabel()+" Weight: "+e.getWeight());
	        	//}
	        //}
	    	/*
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
	        	ArrayList<Vertex> path = (ArrayList<Vertex>)DijkstraAlgorithm.BFS(g, v0).getVertexes();
		        for (Vertex vertex : path) {
		            System.out.print(vertex.getId()+" ");
		            
		        }
		        System.out.println("");
	        }*/
	        if (args.length==1){
	        	BFS(Integer.parseInt(args[0]));
	        }
	        /*if (data.length==3){
	        	for (Object s:DijkstraAlgorithm.getLine(graph.getEdges(), data[2])){
	        		//System.out.print(s+" ");
	        	}
	        }*/
	        /*if (data.length==3){//test number 1

	        	Vertex Vx1 = Find_vertex(graph.getVertexes(),data[2]);
	        	for (Edge e:graph.getEdges()){
	        		if (e.getDestination()==Vx1){
	        			System.out.println("Source: "+e.getSource().getId());
	        		}
	        		else if (e.getSource()==Vx1){
	        			System.out.println("Destination: "+e.getDestination().getId());
	        		}
	        	}
	        	ArrayList<Vertex> path = (ArrayList<Vertex>)DijkstraAlgorithm.DFS(graph, Vx1).getVertexes();
		        for (Vertex vertex : path) {
		            System.out.print(vertex.getId()+" ");
		        }
		        System.out.println("");
	        }/*
	        if (data.length==3){//test number 1
	        	ArrayList<String> path = new ArrayList<String>();
	        	ArrayList<String> old = new ArrayList<String>();
	        	ArrayList<String> next = new ArrayList<String>();
	        	old.add(data[2]);
	        	path.add(data[2]);
	        	while (!old.isEmpty()){
	        		for (String s:old){
			        	for (Edge e:graph.getEdges()){
			        		if (e.getDestination().getId().equals(s) && e.getWeight()!=-1){
			        			if (!path.contains(e.getSource().getId())){
			        				path.add(e.getSource().getId());
			        				next.add(e.getSource().getId());
			        			}
			        		}else if (e.getSource().getId().equals(s) && e.getWeight()!=-1){
			        			if (!path.contains(e.getDestination().getId())){
			        				path.add(e.getDestination().getId());
			        				next.add(e.getDestination().getId());
			        			}
			        		}
			        		
			        	}
	        		}
	        		old=new ArrayList<String>();
	        		for (String n:next){
	        			old.add(n);
	        		}
	        		next = new ArrayList<String>();
	        		
	        	}
		        for (String vertex : path) {
		            System.out.print(vertex+" ");
		        }
		        System.out.println("");
	        	
	        }*/

	        if (args.length==2){ // test number 2 
	        	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        	Vertex Vx1 = Find_vertex(graph.getVertexes(),args[0]);
		        Vertex Vx2 = Find_vertex(graph.getVertexes(),args[1]);
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


