package twoDPathFinder;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class DFS extends JFrame implements Runnable{

	private JPanel contentPane;
	private JLabel[] ll1;
	private JPanel j1;
	private int n=1;

	int n2;
	
	public void setn(String s1) {
		n2 = Integer.parseInt(s1);
	}
	int srcx;
	void setsrcx(String s2) {
		srcx = Integer.parseInt(s2);	
	}
	int srcy;
	void setsrcy(String s3) {
		srcy = Integer.parseInt(s3);
	}
	int desx;
	void setdesx(String s4) {
		desx = Integer.parseInt(s4);
	}
	int desy;
	void setdesy(String s5) {
		desy = Integer.parseInt(s5);
	}
	

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
    
    public void createGrap() {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                
                if (!isFreeCell(matrix[r][c])) {
                    continue;
                }
                createNeighbor(r, c, matrix);
            }
        }
    }
    
    public void createNeighbor( int r, int c, String[][] matrix2){
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                if ((row ==0 && col == 0) || (row == -1 && col == -1) || (row == 1 && col == 1) || (row == -1 && col == 1) || (row == 1 && col == -1)){
                    continue;
                }
                if ((0 > c + col) || (c + col >= matrix2[0].length) || (0 > r + row) || (r + row >= matrix2.length)) {
                    continue;
                }
                
                String value = matrix2[r+row][c+col];
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
    
    public void printAllPaths(int s,int d)
    {
        boolean[] isVisited = new boolean[1000];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        for(int i=0;i<1000;i++)isVisited[i] = false;
        printAllPathsUtil(s, d, isVisited, pathList);
    }
    
    int flags =0;
    int tt=0;
    ArrayList<Integer> l2 = new ArrayList<>();
    public void printAllPathsUtil(Integer u, Integer d,boolean[] isVisited,ArrayList<Integer> localPathList) {
        
        isVisited[u] = true;

        if (u.equals(d) && tt==0)
        {
        	System.out.println(localPathList);
        	l2 = new ArrayList<Integer>(localPathList);
        	showPath();
            flags = 1;
            tt++;
        }
        else{
        	if(!edgeTo.isEmpty()) 
        	{
	        	Set<Integer> j = (Set<Integer>)edgeTo.get(u);
	        	if(!j.isEmpty()) 
	        	{
	            	for (Integer a : j) 
	            	{
	                	
	                	if (!isVisited[a])
	                    {
	                		localPathList.add(a);
	                        printAllPathsUtil(a, d, isVisited, localPathList);
	                        localPathList.remove(a);
	                    }
	                }
	        	}
        	}	
        }
        isVisited[u] = false;
    }
    
    public void check(){
    	if(flags == 0)
    		JOptionPane.showMessageDialog(this,"Path doesn't Exist");    		
    }
	
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

					for(int j=0;j<l2.size();j++)
			    		
			    	{
			    		int i1 = l2.get(j);
			    		int x1 = i1/25;
			    		int y1 = i1%25;
			    		int i = x1*n2 +y1;
			    		try {
				    		ll1[i].setBackground(Color.green);
					    	ll1[i].setOpaque(true);
					    	ll1[i].setText(j+"");
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
	
	public DFS(int n2) {
		ll1 = new JLabel[n2*n2];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
	}

	@Override
	public void run() {
	}

}