public class Edge  {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;
    private String label;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    public String getLabel(){
    	return label;
    }
    
    public void setLabel(String l){
    	label = l;
    }
    
    public String getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}