import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings({ "serial", "unused" })
public class Deposits extends JFrame {

	private JPanel contentPane;
	private JTextField amtDeposited;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposits frame = new Deposits();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	int MyAccNo;
	int OldBalance;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	
	private void getBalance()
	{
		 try {
			 String query = "select *from accounts_table where AccNo = '"+MyAccNo+"' ";
				
			    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
			    st = con.createStatement();
			    rs1=st.executeQuery(query);
			    if(rs1.next())
			    {
			    	
				OldBalance = rs1.getInt(7);
			    }
			    else
			    {
			    	//JOptionPane.showMessageDialog(this, "Wrong debit card number or pin!");
			    }
				
		 }
		 catch(Exception E) {
		 
		 
			 JOptionPane.showMessageDialog(this, E);
		 }
	}
	public Deposits(int AccNo) {
		MyAccNo = AccNo;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnDeposits = new JTextPane();
		txtpnDeposits.setBackground(new Color(255, 255, 204));
		txtpnDeposits.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnDeposits.setText("   DEPOSITS");
		txtpnDeposits.setBounds(157, 27, 103, 24);
		panel.add(txtpnDeposits);
		
		JTextPane txtpnEnterAmount = new JTextPane();
		txtpnEnterAmount.setBackground(new Color(255, 255, 204));
		txtpnEnterAmount.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnEnterAmount.setText("Enter amount : ");
		txtpnEnterAmount.setBounds(38, 91, 130, 24);
		panel.add(txtpnEnterAmount);
		
		amtDeposited = new JTextField();
		amtDeposited.setFont(new Font("Arial", Font.PLAIN, 14));
		amtDeposited.setBackground(new Color(255, 255, 204));
		amtDeposited.setBounds(232, 91, 130, 24);
		panel.add(amtDeposited);
		amtDeposited.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(amtDeposited.getText().isEmpty() || Integer.valueOf(amtDeposited.getText()) == 0)
				{
					JOptionPane.showMessageDialog(Submit,"Enter a valid amount!");
				}
				else
				{
					try {
						String query = "update accounts_table set Balance = ? where AccNo = ?";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, OldBalance+Integer.valueOf(amtDeposited.getText()));
						ps.setInt(2, MyAccNo);
						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(Submit,"Balance updated!");
						}
						else
						{
							JOptionPane.showMessageDialog(Submit, "Missing Information!");
						}
					}
					catch(Exception E)
					{
						JOptionPane.showMessageDialog(Submit, E);
					}
				}
			}
		});
		Submit.setBackground(new Color(255, 255, 204));
		Submit.setFont(new Font("Arial", Font.BOLD, 12));
		Submit.setBounds(175, 162, 85, 31);
		
		panel.add(Submit);
		getBalance();
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu(MyAccNo).setVisible(true);
				dispose();
			}
		});
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BackBtn.setBounds(49, 203, 76, 31);
		panel.add(BackBtn);
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setBackground(new Color(255, 255, 204));
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		LogOutBtn.setBounds(175, 203, 85, 31);
		panel.add(LogOutBtn);
		
		
		
	}
	

	/**
	 * Create the frame.
	 */
	public Deposits() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnDeposits = new JTextPane();
		txtpnDeposits.setBackground(new Color(255, 255, 204));
		txtpnDeposits.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnDeposits.setText("   DEPOSITS");
		txtpnDeposits.setBounds(157, 27, 103, 24);
		panel.add(txtpnDeposits);
		
		JTextPane txtpnEnterAmount = new JTextPane();
		txtpnEnterAmount.setBackground(new Color(255, 255, 204));
		txtpnEnterAmount.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnEnterAmount.setText("Enter amount : ");
		txtpnEnterAmount.setBounds(38, 91, 130, 24);
		panel.add(txtpnEnterAmount);
		
		amtDeposited = new JTextField();
		amtDeposited.setFont(new Font("Arial", Font.PLAIN, 14));
		amtDeposited.setBackground(new Color(255, 255, 204));
		amtDeposited.setBounds(232, 91, 130, 24);
		panel.add(amtDeposited);
		amtDeposited.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(amtDeposited.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(Submit,"Enter a valid amount!");
				}
				else
				{
					try {
						String query = "update accounts_table set Balance = ? where AccNo = '"+MyAccNo+"'";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, OldBalance+Integer.valueOf(amtDeposited.getText()));
						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(Submit,"Balance updated!");
						}
						else
						{
							JOptionPane.showMessageDialog(Submit, "Missing Information!");
						}
					}
					catch(Exception E)
					{
						JOptionPane.showMessageDialog(Submit, E);
					}
				}
			}
		});
		Submit.setBackground(new Color(255, 255, 204));
		Submit.setFont(new Font("Arial", Font.BOLD, 13));
		Submit.setBounds(175, 162, 85, 31);
		panel.add(Submit);
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setBackground(new Color(255, 255, 204));
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		LogOutBtn.setBounds(175, 203, 85, 31);
		panel.add(LogOutBtn);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setFont(new Font("Arial", Font.BOLD, 13));
		BackBtn.setBounds(49, 203, 76, 31);
		panel.add(BackBtn);
	}
	
}
