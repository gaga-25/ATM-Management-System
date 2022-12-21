import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings({ "serial", "unused" })
public class Balance extends JFrame {

	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Balance frame = new Balance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Balance() {
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(93, 89, -57, 19);
		panel.add(textPane);
		
		JTextPane txtpnYourBalanceIs = new JTextPane();
		txtpnYourBalanceIs.setText("Your balance is : ");
		txtpnYourBalanceIs.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnYourBalanceIs.setBackground(new Color(255, 255, 204));
		txtpnYourBalanceIs.setBounds(39, 120, 138, 40);
		panel.add(txtpnYourBalanceIs);
		
		JTextPane txtpnAccountNumber = new JTextPane();
		txtpnAccountNumber.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnAccountNumber.setText("Account number : ");
		txtpnAccountNumber.setBackground(new Color(255, 255, 204));
		txtpnAccountNumber.setBounds(39, 58, 138, 40);
		panel.add(txtpnAccountNumber);
		
		JLabel AccNoLabel = new JLabel("New label");
		AccNoLabel.setForeground(new Color(255, 255, 204));
		AccNoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		AccNoLabel.setBackground(new Color(255, 255, 204));
		AccNoLabel.setBounds(230, 58, 125, 40);
		panel.add(AccNoLabel);
		
		JLabel BalanceLabel = new JLabel("New label");
		BalanceLabel.setForeground(new Color(255, 255, 204));
		BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		BalanceLabel.setBounds(230, 120, 99, 40);
		panel.add(BalanceLabel);
		
		JButton BackBtn = new JButton("BACK");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setFont(new Font("Arial", Font.BOLD, 14));
		BackBtn.setBounds(163, 200, 85, 33);
		panel.add(BackBtn);
	}
	int MyAccNo;
	int OldBalance;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	
	public Balance(int AccNo)
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 426, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(93, 89, -57, 19);
		panel.add(textPane);
		
		JTextPane txtpnYourBalanceIs = new JTextPane();
		txtpnYourBalanceIs.setText("Your balance is : ");
		txtpnYourBalanceIs.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnYourBalanceIs.setBackground(new Color(255, 255, 204));
		txtpnYourBalanceIs.setBounds(39, 120, 125, 32);
		panel.add(txtpnYourBalanceIs);
		
		JTextPane txtpnAccountNumber = new JTextPane();
		txtpnAccountNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnAccountNumber.setText("Account number : ");
		txtpnAccountNumber.setBackground(new Color(255, 255, 204));
		txtpnAccountNumber.setBounds(39, 58, 125, 32);
		panel.add(txtpnAccountNumber);
		
		JLabel AccNoLabel = new JLabel("New label");
		AccNoLabel.setForeground(new Color(255, 255, 204));
		AccNoLabel.setBackground(new Color(255, 255, 204));
		AccNoLabel.setBounds(230, 58, 125, 32);
		panel.add(AccNoLabel);
		
		
		JLabel BalanceLabel = new JLabel("New label");
		BalanceLabel.setForeground(new Color(255, 255, 204));
		BalanceLabel.setBackground(new Color(255, 255, 204));
		BalanceLabel.setBounds(230, 58, 125, 32);
		BalanceLabel.setBounds(230, 120, 99, 32);
		panel.add(BalanceLabel);
		
		
		
		MyAccNo = AccNo;
		AccNoLabel.setText(""+MyAccNo);
		getBalance();
		BalanceLabel.setText(""+OldBalance);
		
		JButton BackBtn = new JButton("BACK");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu(MyAccNo).setVisible(true);
				dispose();
			}
		});
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BackBtn.setBounds(163, 200, 85, 33);
		panel.add(BackBtn);
		
	
			
	}
	private void getBalance()
	{
		 try {
			 String query = "select *from accounts_table where AccNo = '"+MyAccNo+"' ";
				
			    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
			    st = con.createStatement();
			    rs1=st.executeQuery(query);
			    if(rs1.next())
			    {
			    	
				OldBalance = rs1.getInt(6);
				
				
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
}
