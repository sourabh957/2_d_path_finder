package twoDPathFinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

public class MAIN extends JFrame {

	private JPanel contentPane;
	private JTextField text;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAIN frame = new MAIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MAIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 490);
		contentPane =  new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn = new JButton("Generate Grid");
		btn.setBackground(new Color(153, 153, 153));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(Integer.parseInt(text.getText())<=25 && Integer.parseInt(text.getText()) > 1)
					{
						GridGenerate gn=new GridGenerate();
						gn.settext(text.getText());
						gn.creategrid();
						gn.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Number limit should be in range 1 - 25"+"\n");
					}
					
				}
				catch(Exception abc) {
			
        			JOptionPane.showMessageDialog(null,"Enter number"+"\n");
				}
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn.setBounds(299, 280, 139, 52);
		getContentPane().add(btn);
		
		text = new JTextField();
	
		text.setBounds(424, 187, 78, 22);
		getContentPane().add(text);
		
		JLabel lblNewLabel = new JLabel("Enter n :-");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(245, 182, 103, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Path Finder");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(294, 49, 232, 82);
		getContentPane().add(lblNewLabel_1);
	}
}