package twoDPathFinder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Recursion extends JFrame {

	private JPanel contentPane;
	private JLabel[] ll1;
	JPanel j1;
	
	int n2;
	int b[][];
	void setn(String s1) {
		n2 = Integer.parseInt(s1);
		b = new int [n2][n2];
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
	

	String a[][];
	void setarr(String [][] mat)
	{
		a = mat;
	}
	boolean FindPath(int i,int j)
	{   if(i>-1 && j>-1 &&i<n2 &&j<n2) {
		
		if(b[i][j]==0)
	    {
	        b[i][j]=b[i][j]+1;
	        if((i == srcx) && (j== srcy))
	        {
	          
	            System.out.print("["+i+" "+j+"]");
	            return true;
	        }
		        
	        if(a[i][j].equals("0") || (i<0 && j<0))
	            return false;
		    if(FindPath(i+1,j)||FindPath(i,j+1)||FindPath(i-1,j)||FindPath(i,j-1))
	        {
	            
	            System.out.print("["+i+" "+j+"]");
	            l2.add(i*n2+j);
	            return true;
	        }
	        return false;
	    }
	    else
	    {
	        return false;
	    }
	              
	}
	return false;
	}
	ArrayList<Integer> l2 = new ArrayList<>();
	void call()
	{
		if(a[srcx][srcy].equals("0") || a[desx][desy].equals("0"))
		{
			System.out.println("Source or Destination cannot be blocked");
		}
	        
		else if(FindPath(desx,desy) == true)
		{
			System.out.println("Path exist");
		}
	        
		else
	    {
	    	System.out.println("Path does not exist");
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
        		arr[qp]=a[i][j];
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
		    		int i = l2.get(j);
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
	
	public Recursion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}