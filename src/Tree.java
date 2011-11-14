import java.io.IOException;
import java.util.List;


public class Tree {
	
	 private Node roott;
	 
	 
	 public Tree(){
		roott =new Node(false,false,true,0,0);
		roott.parent=new Node(false,false,false,20,20);
		
		
	 }
	 
	 public void insertLeft(Node root,boolean G,boolean W,boolean V,int i,int j){
		 
		 root.leftNode=new Node(G,W,V,i,j);
		 root.leftNode.parent=new Node(false,false,true,root.i,root.j);
		 root.leftNode.parent=root;
		 
	 }
	 public void insertRight(Node root,boolean G,boolean W,boolean V,int i,int j){
		 root.rightNode=new Node(G,W,V,i,j);
		 root.rightNode.parent=new Node(false,false,true,root.i,root.j);
		 root.rightNode.parent=root;
	 }
	 public void insertUp(Node root,boolean G,boolean W,boolean V,int i,int j){
		 root.upNode=new Node(G,W,V,i,j);
		 root.upNode.parent = new Node(false,false,true,root.i,root.j);
		 root.upNode.parent=root;
	 }
	 public void insertDown(Node root,boolean G,boolean W,boolean V,int i,int j){
		 root.downNode=new Node(G,W,V,i,j);
		 root.downNode.parent = new Node(false,false,true,root.i,root.j);
		 root.downNode.parent=root;
		 
	 }
	public Node getRoot(){
		
		return this.roott;
	}
	
	public boolean checkinPath(Node root,int i , int j){
		
	
		//System.out.println("checkinpath "+i+""+j+"with"+root.j+""+root.i);
		if(root.parent.i==20)return false;
		else if(root.j==j&&root.i==i)return true;
		return checkinPath(root.parent, i, j);
		
		
			
			
		}
		
	
	
	public List<Node> getGoalPath(Node root,List<Node> nodes){
		
		if(root.parent==null){
			return nodes;
		}
		else{
		nodes.add(root);
		return getGoalPath(root.parent, nodes);
		}
		
		
	}
	
	public void printTree(Node root){
		//sif()
		System.out.println("Gold"+root.Gold+"Wampus"+root.Wampus+"___");
		System.out.println("/"+"   \\");
		printTree(root.leftNode);
		printTree(root.rightNode);
	}
	 }

