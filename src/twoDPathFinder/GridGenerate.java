package twoDPathFinder;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class GridGenerate extends JFrame {

	private JPanel contentPane;
	int n;
	String arr2[][];
	JTextField txt1;
	JTextField txt2;
	JTextField txt3;
	JTextField txt4;


	void settext(String st) {
		n = Integer.parseInt(st);
	}
	public String[][] getarr2(){
		return arr2;
	}

	void creategrid()
	{
		JPanel j1 = new JPanel();

		String arr[] = new String[n*n];
		arr2 = new String [n][n];
        Random rand = new Random();
        int count0 = 0;
        for(int i=0;i<n*n;i++)
        {
    		int random = rand.nextInt(2);
    		if((random == 0) && (count0 == 0) ) { count0+=1; }
    		else { 
    		random =1;
    		count0+=1;
    		}
    		if(count0%3 == 0){ count0 = 0; }
         arr[i] = random+"";
        }
        int qp =0;
        for(int i=0;i<n;i++)
        {
        	for(int j=0;j<n;j++)
        	{
        		arr2[i][j]=arr[qp];
        		qp++;
        	}
        }
		GridLayout gd = new GridLayout(n,n);
		j1.setLayout(gd);
		
		for(int i=0;i<n*n;i++)
		{
			JLabel l1 = new JLabel(arr[i]);
			Border border = BorderFactory.createLineBorder(Color.gray, 1);

			l1.setBorder(border);
			if(arr[i].equals("0"))
			{
				l1.setBackground(Color.black);
				l1.setOpaque(true);
				l1.setText("");
			}
			else {
				l1.setText("");
				l1.setBackground(Color.white);
				l1.setOpaque(true);
			}
			
			
			j1.add(l1);
			j1.setVisible(true);
			
		}
        getContentPane().add(j1,BorderLayout.CENTER);
	}
	 
	public GridGenerate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 490);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

        
        JPanel j2 = new JPanel();
        JButton bt1= new JButton("DFS");
        bt1.setPreferredSize(new Dimension(130, 50));
        bt1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		DFS dfs = new DFS(n);
        		try {
		        		dfs.setn(n+"");
		        		dfs.setsrcx(txt1.getText());
		        		
		        		dfs.setsrcy(txt2.getText());
		        		
		        		dfs.setdesx(txt3.getText());
		        		
		        		dfs.setdesy(txt4.getText());
		        		if(Integer.parseInt(txt1.getText())>=n || Integer.parseInt(txt2.getText())>=n || Integer.parseInt(txt3.getText())>=n
		        				|| Integer.parseInt(txt4.getText())>=n)
		        		{
		        			JOptionPane.showMessageDialog(dfs,"Enter Source and Destination Properly"+"\n");
		        		}
		        		else {
			        		dfs.setarr(arr2);
			        		int x1=(Integer.parseInt(txt1.getText())*25)+Integer.parseInt(txt2.getText());
			        		int x2 =(Integer.parseInt(txt3.getText())*25)+Integer.parseInt(txt4.getText());
			        		dfs.creategrid();
			        		dfs.createGrap();
			        		dfs.printAllPaths(x1,x2);
			        		dfs.check();
			        		dfs.showPath();
			        		dfs.setVisible(true);
		        		}
        			}
        		catch(Exception abc) {
        			JOptionPane.showMessageDialog(dfs,"Enter Source and Destination Properly"+"\n");  
        		}
        		
        	}
        });
        j2.add(bt1);
        JButton bt2= new JButton("Dijkstra");
        bt2.setPreferredSize(new Dimension(130, 50));
        bt2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		Dijkstra dij = new Dijkstra();
        		try {
	        		dij.setn(n+"");
	        		dij.setsrcx(txt1.getText());
	        		
	        		dij.setsrcy(txt2.getText());
	        		
	        		dij.setdesx(txt3.getText());
	        		
	        		dij.setdesy(txt4.getText());
	        		if(Integer.parseInt(txt1.getText())>=n || Integer.parseInt(txt2.getText())>=n || Integer.parseInt(txt3.getText())>=n
	        				|| Integer.parseInt(txt4.getText())>=n)
	        		{
	        			JOptionPane.showMessageDialog(dij,"Enter Source and Destination Properly"+"\n");
	        		}
	        		else {
	        		dij.setarr(arr2);
	        		dij.creategrid();
	        		dij.createGrap();
	        		int x1=(Integer.parseInt(txt1.getText())*25)+Integer.parseInt(txt2.getText());
	        		int x2 =(Integer.parseInt(txt3.getText())*25)+Integer.parseInt(txt4.getText());
	        		dij.dijkstra(x1,x2);
	        		dij.showPath();
	        		dij.setVisible(true);
	        		}
        		}
        		catch(Exception abc) {
        			JOptionPane.showMessageDialog(dij,"Enter Source and Destination Properly"+"\n");
        		}
        	}
        });
        
        j2.add(bt2);
        JButton bt3= new JButton("BFS ");
        bt3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		BFS bfs = new BFS();
        		try {
		        		bfs.setn(n+"");
		        		bfs.setsrcx(txt1.getText());
		        		bfs.setsrcy(txt2.getText());
		        		bfs.setdesx(txt3.getText());
		        		bfs.setdesy(txt4.getText());
		        		if(Integer.parseInt(txt1.getText())>=n || Integer.parseInt(txt2.getText())>=n || Integer.parseInt(txt3.getText())>=n
		        				|| Integer.parseInt(txt4.getText())>=n)
		        		{
		        			JOptionPane.showMessageDialog(bfs,"Enter Source and Destination Properly"+"\n");
		        		}
		        		else {
		        		bfs.setarr(arr2);	
		        		bfs.matrixconvert();
		        		bfs.creategrid();
		        		BFS.SearchMazeBFS xyz1 = bfs.new SearchMazeBFS();
		        		BFS.Point src = bfs.new Point(Integer.parseInt(txt1.getText()),Integer.parseInt(txt2.getText()));
		        		BFS.Point dest = bfs.new Point(Integer.parseInt(txt3.getText()),Integer.parseInt(txt4.getText()));
		        		if ( xyz1.bfx(src, dest) ){
		        		    System.out.println("Path exists");
		        		    xyz1.printPath();
	        		}
	        		else System.out.println("Path doesn't exist");
	        	    
	        		
	        		bfs.showPath();
	        		bfs.setVisible(true);
		        		}
        		}
        		catch(Exception abc) {
        			JOptionPane.showMessageDialog(bfs,"Enter Source and Destination Properly"+"\n");
        		}
        	}
        });
        bt3.setPreferredSize(new Dimension(130, 50));
        
        j2.add(bt3);
        JButton bt4= new JButton("Recursion");
        bt4.setPreferredSize(new Dimension(130, 50));
        bt4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		Recursion rec = new Recursion();
        		try {
	        		rec.setn(n+"");
	        		rec.setsrcx(txt1.getText());
	        		
	        		rec.setsrcy(txt2.getText());
	        		
	        		rec.setdesx(txt3.getText());
	        		
	        		rec.setdesy(txt4.getText());
	        		
	        		rec.setarr(arr2);
	        		rec.call();
	        		rec.creategrid();
	        		rec.showPath();
	        		rec.setVisible(true);
        		}
        		catch(Exception abc) {
        			JOptionPane.showMessageDialog(rec,"Enter Source and Destination"+"\n");
        		}
        	}
        });
        j2.add(bt4);
        getContentPane().add(j2,BorderLayout.SOUTH);
        
        JPanel j3 = new JPanel();
        BoxLayout bx = new BoxLayout(j3,BoxLayout.Y_AXIS);
        
        j3.setLayout(bx);
        j3.add(Box.createRigidArea(new Dimension(30, 80)));
        JLabel lb1=new JLabel("");
        lb1.setText("Source");
        lb1.setPreferredSize(new Dimension(130, 50));
        j3.add(lb1);
        txt1 = new JTextField("X Coordinate");
        txt1.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent arg0) {
        		if(txt1.getText().equals("X Coordinate")){
        			txt1.setText("");
        		}
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(txt1.getText().equals("")){
        			txt1.setText("X Coordinate");
        		}
        	}
        });
        txt1.setPreferredSize(new Dimension( 50, 10 ));
        txt1.setHorizontalAlignment(JTextField.CENTER);
        j3.add(txt1);
        j3.add(Box.createRigidArea(new Dimension(30, 20)));
       
 
        txt2 = new JTextField("Y Coordinate");
        txt2.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txt2.getText().equals("Y Coordinate")){
        			txt2.setText("");
        		}
        	}
        	public void focusLost(FocusEvent e) {
        		if(txt2.getText().equals("")){
        			txt2.setText("Y Coordinate");
        		}
        	}
        	
        });
        txt2.setPreferredSize(new Dimension( 50, 10 ));
        txt2.setHorizontalAlignment(SwingConstants.CENTER);
        j3.add(txt2);
        
        j3.add(Box.createRigidArea(new Dimension(30, 50)));
        JLabel lb2=new JLabel();
        lb2.setText("Destination");
        lb2.setPreferredSize(new Dimension(130, 50));
        j3.add(lb2);
        txt3 = new JTextField("X Coordinate");
        txt3.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txt3.getText().equals("X Coordinate")){
        			txt3.setText("");
        		}
        	}
        	public void focusLost(FocusEvent e) {
        		if(txt3.getText().equals("")){
        			txt3.setText("X Coordinate");
        		}
        	}
        });
        txt3.setSize(130, 10);
        txt3.setHorizontalAlignment(SwingConstants.CENTER);
        j3.add(txt3);
        
        j3.add(Box.createRigidArea(new Dimension(30, 20)));
        txt4 = new JTextField("Y Coordinate");
        txt4.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txt4.getText().equals("Y Coordinate")){
        			txt4.setText("");
        		}
        	}
        	public void focusLost(FocusEvent e) {
        		if(txt4.getText().equals("")){
        			txt4.setText("Y Coordinate");
        		}
        	}
        });
        txt4.setSize(130, 10);
        txt4.setHorizontalAlignment(SwingConstants.CENTER);
        j3.add(txt4);
        j3.add(Box.createRigidArea(new Dimension(30, 80)));
        
        getContentPane().add(j3,BorderLayout.EAST);
		setSize(800,600);		
	}
}