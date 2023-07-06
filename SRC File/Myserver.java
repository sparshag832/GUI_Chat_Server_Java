import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.List;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Myserver extends JFrame {
	private JTextField textENC;
	private JTextField textSND;
	public List list;
	 ServerSocket ss;
	  Socket s;


	  public static void main(String[] args) {
			
		  Myserver frame = new Myserver();		
		}
	
	public Myserver() {
		getContentPane().setBackground(new Color(245, 222, 179));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setLayout(null);
		panel.setBounds(0, 0, 634, 26);
		getContentPane().add(panel);
		
		JLabel lblSERVER = new JLabel("SERVER");
		lblSERVER.setHorizontalAlignment(SwingConstants.CENTER);
		lblSERVER.setFont(new Font("Sitka Small", Font.BOLD, 12));
		lblSERVER.setForeground(new Color(255, 250, 205));
		lblSERVER.setBounds(270, 11, 78, 14);
		panel.add(lblSERVER);
		
		textENC = new JTextField();
		textENC.setColumns(10);
		textENC.setBounds(334, 129, 290, 32);
		getContentPane().add(textENC);
		
		textSND = new JTextField();
		textSND.setColumns(10);
		textSND.setBounds(334, 250, 290, 32);
		getContentPane().add(textSND);
		
		
		JLabel lblNewLabel_2 = new JLabel("RECEIVED TEXT");
		lblNewLabel_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(427, 97, 154, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SENDED TEXT");
		lblNewLabel_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(436, 227, 118, 14);
		getContentPane().add(lblNewLabel_3);
		
		List listMSG = new List();
		listMSG.setBounds(10, 62, 290, 290);
		getContentPane().add(listMSG);
		
		JButton btnNewButton = new JButton("SEND TEXT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
		 		{
		 		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		 			
		 		String str=textSND.getText();

		 			int s_c=8;
		 			String encrypt="";
		 			String value=str;
		 			for(int i=0;i<value.length();i++)
		 			{
		 				char ch=value.charAt(i);
		 				ch+=s_c;
		 				encrypt=encrypt + ch;
		 			}
		 			
		 			listMSG.add("Me:-"+str);
			 		oos.writeObject(encrypt);
		 		
		 			
		 		}
		 		catch(Exception ex)
		 		{
		 			System.out.println(ex.getMessage());
		 		}
		 			
			}
		});
		btnNewButton.setBounds(436, 309, 109, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Decrypted Text:");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(95, 32, 154, 24);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Encrypted Message");
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(334, 159, 154, 24);
		getContentPane().add(lblNewLabel_2_1_1);
		
//  Receive Message		
		
		this.setVisible(true);
		this.setTitle("Server");
		
		try {
			
			ss=new ServerSocket(2005);
			s=ss.accept();
			while(true)
			{
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				int s_c=8;
				String decrypt="";
				String str1=ois.readObject().toString();
				textENC.setText(str1);
				for(int i=0;i<str1.length();i++) 
				{
					char ch=str1.charAt(i);
					ch-=s_c;
					decrypt=decrypt + ch;
				}
					
				listMSG.add("Friend:-"+decrypt);
			}
			
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			
			
		}
		
	
		
	}
}
