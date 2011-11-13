public class Node{
		
		 public Node leftNode,rightNode,upNode,downNode,parent; // the nodes
		 public int i,j;
		 public boolean Gold,Wampus,visited,fullyExpanded;
	public Node(){
		
	}
	public Node(boolean G,boolean W,boolean V,int i,int j){
		this.Wampus=W;
		this.Gold=G;
		this.visited=V;
		this.i=i;
		this.j=j;
		
	}
	public void setFullyExpanded(){
	this.fullyExpanded=true;
	}
	}