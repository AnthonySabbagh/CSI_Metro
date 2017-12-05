import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

	    private final List<Vertex> nodes;
	    private final List<Edge> edges;
	    private Set<Vertex> settledNodes;
	    private Set<Vertex> unSettledNodes;
	    private Map<Vertex, Vertex> predecessors;
	    private Map<Vertex, Integer> distance;

	    public DijkstraAlgorithm(Graph graph) {
	        // create a copy of the array so that we can operate on this array
	        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
	        this.edges = new ArrayList<Edge>(graph.getEdges());
	    }
	    
	    public DijkstraAlgorithm(Graph graph, Vertex brokenLine) {
	        // create a copy of the array so that we can operate on this array
	        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
	        this.edges = new ArrayList<Edge>(graph.getEdges());
	        
	        Graph broken = BFS(graph,brokenLine);
	        for (Vertex v:nodes){
	        	for (Vertex b:broken.getVertexes()){
	        		if (v.getId()==b.getId()){
	        			nodes.remove(v);
	        		}
	        	}
	        }
	        for (Edge e:edges){
	        	for (Edge b:broken.getEdges()){
	        		if (e.getId()==b.getId()){
	        			edges.remove(e);
	        		}
	        	}
	        }
	    }

	    public void execute(Vertex source) {
	        settledNodes = new HashSet<Vertex>();
	        unSettledNodes = new HashSet<Vertex>();
	        distance = new HashMap<Vertex, Integer>();
	        predecessors = new HashMap<Vertex, Vertex>();
	        distance.put(source, 0);
	        unSettledNodes.add(source);
	        while (unSettledNodes.size() > 0) {
	            Vertex node = getMinimum(unSettledNodes);
	            settledNodes.add(node);
	            unSettledNodes.remove(node);
	            findMinimalDistances(node);
	        }
	    }

	    private void findMinimalDistances(Vertex node) {
	        List<Vertex> adjacentNodes = getNeighbors(node);
	        for (Vertex target : adjacentNodes) {
	            if (getShortestDistance(target) > getShortestDistance(node)
	                    + getDistance(node, target)) {
	                distance.put(target, getShortestDistance(node)
	                        + getDistance(node, target));
	                predecessors.put(target, node);
	                unSettledNodes.add(target);
	            }
	        }

	    }

	    private int getDistance(Vertex node, Vertex target) {
	        for (Edge edge : edges) {
	            if (edge.getSource().equals(node)
	                    && edge.getDestination().equals(target)) {
	                return edge.getWeight();
	            }
	        }
	        throw new RuntimeException("Should not happen");
	    }

	    private List<Vertex> getNeighbors(Vertex node) {
	        List<Vertex> neighbors = new ArrayList<Vertex>();
	        for (Edge edge : edges) {
	            if (edge.getSource().equals(node)
	                    && !isSettled(edge.getDestination())) {
	                neighbors.add(edge.getDestination());
	            }
	        }
	        return neighbors;
	    }

	    private Vertex getMinimum(Set<Vertex> vertexes) {
	        Vertex minimum = null;
	        for (Vertex vertex : vertexes) {
	            if (minimum == null) {
	                minimum = vertex;
	            } else {
	                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
	                    minimum = vertex;
	                }
	            }
	        }
	        return minimum;
	    }

	    private boolean isSettled(Vertex vertex) {
	        return settledNodes.contains(vertex);
	    }

	    private int getShortestDistance(Vertex destination) {
	        Integer d = distance.get(destination);
	        if (d == null) {
	            return Integer.MAX_VALUE;
	        } else {
	            return d;
	        }
	    }

	    /*
	     * This method returns the path from the source to the selected target and
	     * NULL if no path exists
	     */
	    public LinkedList<Vertex> getPath(Vertex target) {
	        LinkedList<Vertex> path = new LinkedList<Vertex>();
	        Vertex step = target;
	        // check if a path exists
	        if (predecessors.get(step) == null) {
	            return null;
	        }
	        path.add(step);
	        while (predecessors.get(step) != null) {
	            step = predecessors.get(step);
	            path.add(step);
	        }
	        // Put it into the correct order
	        Collections.reverse(path);
	        return path;
	    }
	    
	    
	    public static Graph BFS(Graph g, Vertex v){
	    	LinkedList<Vertex>[] l = new LinkedList[g.getVertexes().size()*5];
	    	l[0] = new LinkedList<Vertex>();
	    	l[0].addLast(v);
	    	for (Vertex vertex:g.getVertexes()){
	    		vertex.setLabel("Unexplored");
	    	}
	    	for (Edge edge:g.getEdges()){
	    		edge.setLabel("Unexplored");
	    	}
	    	v.setLabel("Visited");
	    	
	    	ArrayList<Vertex> finalVertices = new ArrayList<Vertex>();
	    	ArrayList<Edge> finalEdges = new ArrayList<Edge>();
	    	finalVertices.add(v);
	    	int i = 0;
	    	while (!l[i].isEmpty()){
		    	l[i+1] = new LinkedList<Vertex>();
		    	for (Vertex vertex:l[i]){
		    		for (Edge e:g.getEdges()){
		    			boolean isSource = e.getSource()==vertex;
		    			boolean isDestination = e.getDestination()==vertex;
		    			if (isSource||isDestination ){ //Incident edges
		    				if (e.getLabel()=="Unexplored" && e.getWeight()!=-1){
		    					Vertex w = isSource ? e.getDestination() : e.getSource(); //gets opposite vertex
		    					if (w.getLabel()=="Unexplored"){
		    						e.setLabel("Discovery");
		    						w.setLabel("Visited");
		    						l[i+1].addLast(w);
		    						finalVertices.add(w);
		    						finalEdges.add(e);
		    					}
		    					else{
		    						e.setLabel("Cross");
		    						finalEdges.add(e);
		    					}
		    				}
		    			}
		    		}
		    	}
		    	++i;
	    	}
	    	return new Graph(finalVertices, finalEdges);
	    
	    }

}

