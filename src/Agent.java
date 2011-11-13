import java.io.*;
import java.util.*;
import java.util.Vector.*;


import org.rlcommunity.rlglue.codec.AgentInterface;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;
import org.rlcommunity.rlglue.codec.util.AgentLoader;
import org.w3c.dom.NodeList;

public class Agent
implements AgentInterface
{

	/**
	 * @param args
	 */
	
	 public Agent()
	 
	    {
	        goldCollected = false;
	        randGenerator = new Random();
	    }

	    public void agent_init(String s)
	    {
	    }
/**
 *This method generates the Eagle Action intArray Indexes
 * 
 */
	    public int[] Icontents(){
	    
	    	int [] contents = new int [289];
	    	contents[0]=144;
	    	int k = 1;
	    	for (int i =0 ;i<12;i++)
	    	{
	    	for (int j=0;j<12;j++){
	    		contents[k]=i;
	    		contents[k+1]=j;
	    		k+=2;
	    		
	    	}
	    	}
	    //	System.out.println(Arrays.toString(contents));
	    	return contents;
	    	
	    }
	   
	    public String [][]TheGrid(int[] observation){
	    	 String [][] TheGrid =new String[12][12] ;
	    	int k=0;
	    	for(int i=0;i<12;i++){
	    		for(int j=0;j<12;j++){
	    		
	    			TheGrid[i][j]="";
	    		
	    			TheGrid[i][j]=(observation[k]==1)?TheGrid[i][j].concat("W"):TheGrid[i][j].concat("Q");
	    			TheGrid[i][j]=(observation[k+1]==1)?TheGrid[i][j].concat("P"):TheGrid[i][j].concat("Q");
	    			TheGrid[i][j]=(observation[k+2]==1)?TheGrid[i][j].concat("G"):TheGrid[i][j].concat("Q");
	    			k+=3;
	    			
	    		}
	    		
	    			}
	    	
	    	return TheGrid;
	    }
	    
	    public void DFSOld (String [][] Grid,Node root,int i,int j){
	    	
	    	if(i<11&&j<11&&Grid[i+1][j].contains("P")!=true&&Grid[i][j].contains("P")!=true){
	    		System.out.println(i);
	    		Tree.insertLeft(root, Grid[i+1][j].contains("G"), Grid[i+1][j].contains("W"),false,i,j);
	    		 if(Grid[i+1][j].contains("G")==true)return;
	    		DFSOld(Grid,root.leftNode,i+1,j);
	    		
	    	}
	    	if(j<11&&i<11&&Grid[i][j+1].contains("P")!=true&&Grid[i][j].contains("P")!=true){
	    		Tree.insertRight(root, Grid[i][j+1].contains("G"), Grid[i][j+1].contains("W"),false,i,j);
	    		 if(Grid[i][j+1].contains("G")==true)System.out.println("Gold @" +i+""+j+1);
	    		DFSOld(Grid,root.rightNode,i,j+1);
	    		
	    	}
	    	
	    		
	    	
	    	
	    }
	    
	    public Tree TreeGrid(String[][] Grid){
	    	Tree Tree=new Tree();
	    	boolean W,G,P;
	    	for(int i=0; i<12;i++)
		    	 for (int j=0;j<12;j++){
		    		
		    		 if(Grid[i][j].contains("W")==true)W=true;
		    		 if(Grid[i][j].contains("P")==true)P=true;
		    		 if(Grid[i][j].contains("G")==true)G=true;
		    		  
		    		 
		    		 
		    	 }			
	    	
	    	
	    	return Tree;
	    	
	    }
	    public Action agent_start(Observation observation)
	    {
	      agenda=EAGLE;
	        if(agenda==EAGLE)
	        	System.out.println("EagleMode");
	        	Action EagleAction= new Action(3,0,1);
	        	EagleAction.intArray=new int [289];
	        	EagleAction.intArray=Icontents();
	        	EagleAction.charArray=(new char[] {'q'});
	        	Tree=new Tree();
	        	return EagleAction;
	        	
	     
	    }

	    public Action agent_step(double reward, Observation observation)
	    {
	    	
	    	
	    		    	
	    	
	    	 grid= TheGrid(observation.intArray);
	    	 
	    	 DFS(Tree.getRoot(), 0, 0);
	    	 
	    	 
	    	
	 /*   	for(int i=0; i<12;i++)
	    	 for (int j=0;j<12;j++){
	    		 if(Grid[i][j].contains("W")==true)System.out.println("Wampus @" +i+j);
	    		 if(Grid[i][j].contains("P")==true)System.out.println("Pit @" +i+j);
	    		 if(Grid[i][j].contains("G")==true)System.out.println("Gold @" +i+j);
	    	 }
	    		
	    	
	   */ 	 
	    	
	    	
	    	// System.out.println(Arrays.toString(observation.intArray));
	    	//System.out.println(observation.intArray.length);
	    	
	        Action returnAction = new Action();
	       
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        try
	        {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            char c = st.nextToken().charAt(0);
	            returnAction.charArray = (new char[] {
	                c
	            });
	            if(c == 'q')
	            {
	                int n = Integer.parseInt(st.nextToken());
	                returnAction.intArray = new int[2 * n + 1];
	                returnAction.intArray[0] = n;
	                for(int i = 1; i < 2 * n + 1; i++)
	                    returnAction.intArray[i] = Integer.parseInt(st.nextToken());

	            }
	        }
	        catch(IOException e)
	        {
	            e.printStackTrace();
	        }
	        return returnAction;
	    }

	    public boolean addUp(Node root,int j,int i){ //Was DFSRight
	    	int q=j;
	    	int w=i+1;
	    	
	    if(i<11&&!(Tree.checkinPath(root, q, w))&&grid[q][w].contains("P")!=true){
	    	System.out.println("addUp"+j+i);
	    		    	Tree.insertUp(root,grid[q][w].contains("G"),grid[q][w].contains("W"),true,q,w);
	    		    	List<Node> nodes = new ArrayList();
	    		    	if(grid[q][w].contains("G"))printPath(Tree.getGoalPath(root, nodes));
	    		    	DFS(root.upNode,q,w);
	    		    	
	    		    	
	    }
	    	return false;
	    }
	
	    public boolean addDown(Node root,int j,int i){ //was DFSLeft
	    	int q=j;
	    	int w=i-1;
	    	
		    if(i>0&&!(Tree.checkinPath(root, q, w))&&grid[q][w].contains("P")!=true){
		    	System.out.println("addDown"+j+i);
		    		    	Tree.insertDown(root,grid[q][w].contains("G"),grid[q][w].contains("W"),true,q,w);
		    		    	List<Node> nodes = new ArrayList();
		    		    	if(grid[q][w].contains("G"))printPath(Tree.getGoalPath(root, nodes));
		    		    	DFS(root.downNode,q,w);
		    		    	
		    }
		    	return false;
		    }
	    
	    public boolean addRight (Node root,int j ,int i){
	    	int q = j+1;
	    	int w= i;
	    	
	    	   if(j<11&&!(Tree.checkinPath(root, q, w))&&grid[q][w].contains("P")!=true){
	    		   System.out.println("addRight"+j+i);
		    	Tree.insertRight(root,grid[q][w].contains("G"),grid[q][w].contains("W"),true,q,w);
		    	//List<Node> nodes = new ArrayList();
		    	//if(grid[q][w].contains("G"))printPath(Tree.getGoalPath(root, nodes));
		    	DFS(root.rightNode,q,w);
		    	
	    	   }
	    	   return false;
	    }
	    
	    public boolean addLeft (Node root,int j ,int i){
	    	int q = j-1;
	    	int w= i;
	    	
	    	   if(j>0&&!(Tree.checkinPath(root, q, w))&&grid[q][w].contains("P")!=true){
	    		   System.out.println("addLeft"+j+i);
		    	Tree.insertLeft(root,grid[q][w].contains("G"),grid[q][w].contains("W"),true,q,w);
		    	List<Node> nodes = new ArrayList();
		    	if(grid[q][w].contains("G"))printPath(Tree.getGoalPath(root, nodes));
		    	DFS(root.leftNode,q,w);
		    	
	    	   }
	    	   return false;
	    }
	    
	    public void agent_end(double reward)
	    {
	        goldCollected = false;
	    }

	    public void printPath(List<Node> nodes){
	    	
	    	Iterator i = nodes.iterator();
	    	while(i.hasNext()){
	    		Node n = (Node)i.next();
	    		System.out.println("the i is" + n.i + "the j is" + n.j);
	    		
	    	}
	  
	    }
	    
	    public void DFS(Node root,int i,int j)
	    {
	    
	     if(addRight( root, i, j)==false)
	   		 if(addUp(root, i, j)==false)
	    		 if(addLeft(root,i,j)==false)
	    			 	addDown(root,i,j);
	     
	    
	    }
	   
	    public void agent_cleanup()
	    {
	    }

	    public String agent_message(String message)
	    {
	        if(message.toLowerCase().equals("what is your name?"))
	            return "My name is Wumpus World Random Agent, GUC MET";
	        else
	            return "WumpusWorldRandomAgent (Java) does not understand your message.";
	    }

	    public static void main(String args[])
	    {
	        AgentLoader theLoader = new AgentLoader(new Agent());
	        theLoader.run();
	    }

	    private boolean goldCollected;
	    private Random randGenerator;
	    final Integer EAGLE = 1; //For eagle-mode
	    final Integer AGENT = 2; //For agent-mode
	    static Integer agenda = 0; //Keeps track of the mode
	    final String [] strategies = {"DFS","BFS","IDS","UCS","ASS"};
	    //The different search strategies
	    static Integer strategyIndex = -1;
	    //An index into the strategies array
	    static Queue q=new LinkedList(); //The search queue
	    static NodeList partialStateNodes;
	    //Nodes to be updated by an observation
	    static String [] pathToGoal;
	    //Sequence of actions constructed by the eagle
	    static Integer pathToGoalIndex;
	    //An index into the pathToGoal array
	    Tree Tree= new Tree();
	    String [][] grid= new String[12][12];
}
