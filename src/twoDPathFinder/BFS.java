package twoDPathFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BFS extends JFrame {

	private JPanel contentPane;
	private JLabel[] ll1;
	JPanel j1;


	int n2;
	void setn(String s1) {
		n2 = Integer.parseInt(s1);
		ll1 = new JLabel[n2*n2];
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
	

	String mat[][];
	void setarr(String [][] matrix)
	{
		mat = matrix;
		mat_int =new int[n2+1][n2+1];
		
	}
	int mat_int[][];
	
	void matrixconvert()
	{
		for(int i=0;i<n2;i++)
		{
			for(int j=0;j<n2;j++)
			{
				mat_int[i][j] = Integer.parseInt(mat[i][j]);
			}
		}
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
        		arr[qp]=mat[i][j];
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
	class Point {
		public Point(int row, int col) {
		    x = row;
		    y = col;
		}
		public int x;
		public int y;
	}

	class SearchMazeBFS {
		private HashMap<Point, Point> hmap;
	    public static final int ROW = 25;
	    public static final int COL = 25;
	    int[] rowNum; 
	    int[] colNum; 
	    boolean[][] visited;
	    Point source, dest;
	    SearchMazeBFS(){
	    	hmap = new HashMap<Point, Point>();
	    	rowNum = new int[] { -1, 0, 0, 1 };
	    	colNum = new int[] { 0, -1, 1, 0 };
	    	visited = new boolean[ROW][COL];
	    }

	    boolean isValid(int row, int col) {
		return (((row >= 0) && (row < ROW)) && ((col >= 0) && (col < COL)));
	    }

	    boolean bfx(Point src, Point destination) {
	    	source = new Point(src.x, src.y);
	    	dest = new Point(destination.x, destination.y);
			if ((mat_int[source.x][source.y] == 0) || (mat_int[dest.x][dest.y] == 0))
		   		return false;

			visited[source.x][source.y] = true;
			Queue<Point> q = new ArrayDeque<Point>();
			q.add(source);

			while (!q.isEmpty()) {
			    Point curr = q.peek();
			    if (curr.x == dest.x && curr.y == dest.y){
			    	return true;
			    }
			    q.poll();
			    for (int i = 0; i < 4; i++) {
					int row = curr.x + rowNum[i];
					int col = curr.y + colNum[i];
					if ((isValid(row, col) && mat_int[row][col] == 1)
						&& !visited[row][col]){    
					    visited[row][col] = true;
					    Point adjCell = new Point(row,col);
					    if(adjCell.x == dest.x && adjCell.y == dest.y)
					    	adjCell = dest;
					    q.add(adjCell);
					    if (hmap.get(adjCell) == null) {
			            	hmap.put(adjCell, curr); 
			           }
					}
			    }
			}
			return false;
	    }
	    
	    void printPath(){
		    ArrayList<Point> arr = new ArrayList<Point>();
		    arr.add(dest);
		    Point temp = dest;
		    while(temp.x != source.x || temp.y != source.y){
		        if (hmap.containsKey(temp)){ 
		            Point temp1 = hmap.get(temp);
		            arr.add(temp1);
		            temp = temp1;
		        }
		    }
		    for(int i = arr.size()-1;i >= 0; i--){
		        System.out.println(arr.get(i).x + " " + arr.get(i).y);
		        l2.add((arr.get(i).x)*n2+(arr.get(i).y));
		    }
	    }

	    void printHashMap(){
	    	for(Map.Entry<Point, Point> entry : hmap.entrySet() ){
	    		System.out.println("child x : " + entry.getKey().x +
	    						   " child y : " + entry.getKey().y +
	    						   " parent x : " + entry.getValue().x +
	    						   " parent y : " + entry.getValue().y );
	    	}
	    }
	}
	ArrayList<Integer> l2 = new ArrayList<>();
	
	void showPath()
    {
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {

				for(int j=0;j<l2.size();j++)
		    		
		    	{	
		    		int i = l2.get(j);
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
	
	public BFS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}