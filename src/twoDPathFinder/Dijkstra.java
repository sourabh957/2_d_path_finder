package twoDPathFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Dijkstra extends JFrame {

	private JPanel contentPane;
	private JLabel[] ll1;
	private JPanel j1;
	
	int n2;
	void setn(String s1) {
		n2 = Integer.parseInt(s1);
		ll1 = new JLabel[n2*n2];
	}
	int srcx;
	void setsrcx(String s2) { srcx = Integer.parseInt(s2); }
	int srcy;
	void setsrcy(String s3) { srcy = Integer.parseInt(s3); }
	int desx;
	void setdesx(String s4) { desx = Integer.parseInt(s4); }
	int desy;
	void setdesy(String s5) { desy = Integer.parseInt(s5); }
	

	String matrix[][];
	void setarr(String [][] mat)
	{
		matrix = mat;
	}
	
	Map<Integer, Set<Integer>> edgeTo= new HashMap<Integer, Set<Integer>>();
    boolean flag = false;
    public void addEdge(int v1, int v2) {
        add(v1, v2);
        add(v2, v1);
    }
    
    public void add(int from, int to) {
        if (!edgeTo.containsKey(from)) {
            Set<Integer> s = new HashSet<Integer>();
            s.add(to);
            edgeTo.put(from, s);
        } else {
            edgeTo.get(from).add(to);
        }
    }
    
    public Set<Integer> adj(int v) {
        return edgeTo.get(v);
    }
    
    
    public void createGrap() {     //CHANGED
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                
                if (!isFreeCell(matrix[r][c])) {
                    continue;
                }
                createNeighbor(r, c, matrix);
            }
        }
    }
    
    public void createNeighbor( int r, int c, String[][] matrix2){  //CHANGED
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                if ((row ==0 && col == 0) || (row == -1 && col == -1) || (row == 1 && col == 1) || (row == -1 && col == 1) || (row == 1 && col == -1)){
                    continue;
                }
                if ((0 > c + col) || (c + col >= matrix2[0].length) || (0 > r + row) || (r + row >= matrix2.length)) {
                    continue;
                }
                
                String value = matrix2[r+row][c+col];   //CHANGED
                if (!isFreeCell(value)){
                    continue;
                }
                int from = createUniqueId(r, c);
                int to = createUniqueId(row+r, col+c);
                this.add(from, to);
            }
        }
        
    }
    public boolean isFreeCell(String value) {
        return(value.equals("1"));
    }
    public int createUniqueId(int r, int c) {
        return r*25 + c;
    }
    public void print(){
        for (Integer num : edgeTo.keySet()) {
            System.out.print("Key : " + num + " Values:");
            for (Integer a : edgeTo.get(num)) {
                System.out.print(" " + a + " ");
            }
            System.out.println();
        }
    }
private static final int NO_PARENT = -1;
    
   
    public void dijkstra(int startVertex,int endVertex)
    {
        int nVertices = 700;
        
        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices+100];
        
        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices+100];//nVertices
        
        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
        
        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;
        
        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices+100];//nVertices
        
        // The starting vertex does not
        // have a parent
        
        
        parents[startVertex]=0;
        
        //parents[startVertex] = NO_PARENT;
        
        // Find shortest path for all
        // vertices
        //for (int i = 1; i < nVertices; i++)
            for (Integer i : this.edgeTo.keySet())   // ~~~~~~~~~ NEED TO CHANGE    //CHANGED
    {
            
            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = 0;
            int shortestDistance = Integer.MAX_VALUE;
            //for (int vertexIndex = 0;
            //     vertexIndex < nVertices;
            //     vertexIndex++)             // ~~~~~~~~~~~~~~ NEED TO BE CHANGED
            for (Integer vertexIndex : this.edgeTo.keySet())  //CHANGED
            {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            
            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;
            
            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            //for (int vertexIndex = 0;
            //    vertexIndex < nVertices;
            //     vertexIndex++)             // ~~~~~~~~~~~~~~~~~~ NEED TO BE CHANGED
            if(!edgeTo.isEmpty()) {
            	if(!edgeTo.get(nearestVertex).isEmpty()) {
            Set<Integer> j = (Set<Integer>)this.edgeTo.get(nearestVertex);    //CHANGED
            if(!j.isEmpty()) {
            for (Integer vertexIndex : j)
            {
                int edgeDistance = 1;//adjacencyMatrix[nearestVertex][vertexIndex];// TO BE CHANGED
                
                if (((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                    edgeDistance;
                }
            }
            }
            }
            }
        }
        
        printSolution(startVertex, shortestDistances, parents,endVertex);
    }
    
    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private void printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents,int endVertex)
    {
        int nVertices = distances.length;
        
        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            //if (vertexIndex != startVertex)
            if(vertexIndex == endVertex)
            {
                printPath(vertexIndex, parents);
            }
        }
    }
    
    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private  void printPath(int currentVertex,
                                  int[] parents)
    {
        if (currentVertex == 0)//NO_PARENT
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
        l2.add(currentVertex);
    }
    
    ArrayList<Integer> l2 = new ArrayList<>();
    
    void creategrid()
	{
		j1 = new JPanel();
		

		String arr[] = new String[n2*n2];

        int qp =0;
        for(int i=0;i<n2;i++)
        {
        	for(int j=0;j<n2;j++)
        	{
        		arr[qp]=matrix[i][j];
        		qp++;
        	}
        }
		GridLayout gd = new GridLayout(n2,n2);
		j1.setLayout(gd);
		
		for(int i=0;i<n2*n2;i++)
		{
			 ll1[i] = new JLabel(arr[i]);
			Border border = BorderFactory.createLineBorder(Color.gray, 1);

			ll1[i].setBorder(border);
			if(arr[i].equals("0"))
			{
				ll1[i].setBackground(Color.black);
				ll1[i].setOpaque(true);
				ll1[i].setText("");
			}
			else {
				ll1[i].setText("");
				ll1[i].setBackground(Color.white);
				ll1[i].setOpaque(true);
			}
			
			
			j1.add(ll1[i]);
			j1.setVisible(true);
			
		}
        add(j1,BorderLayout.CENTER);
	}
    
    void showPath()
    {
    	Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				
				int k =1;
				int m = srcx*n2+srcy;
				ll1[m].setBackground(Color.green);
		    	ll1[m].setOpaque(true);
		    	ll1[m].setText(0+"");
		    	ll1[m].setFont(new Font("Arial",Font.BOLD,20));
		    	ll1[m].setHorizontalAlignment(SwingConstants.CENTER);
		    	try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for(int j=0;j<l2.size();j++)
		    		
		    	{    
		    		int i1 = l2.get(j);
		    		int x1 = i1/25;
		    		int y1 = i1%25;
		    		int i = x1*n2 +y1;

		    		try {
			    		ll1[i].setBackground(Color.green);
				    	ll1[i].setOpaque(true);
				    	ll1[i].setText(j+1+"");
				    	ll1[i].setFont(new Font("Arial",Font.BOLD,20));
				    	ll1[i].setHorizontalAlignment(SwingConstants.CENTER);
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	}
				
			}
			
		});
		
		th.start();			
    }
    
	public Dijkstra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}