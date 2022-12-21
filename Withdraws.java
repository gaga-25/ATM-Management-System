import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings({ "serial", "unused" })
public class Withdraws extends JFrame {

	private JPanel contentPane;
	private JTextField amtWithdrawn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraws frame = new Withdraws();
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
	public Withdraws() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnWithdraw = new JTextPane();
		txtpnWithdraw.setBackground(new Color(255, 255, 204));
		txtpnWithdraw.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnWithdraw.setForeground(new Color(0, 0, 0));
		txtpnWithdraw.setText("      Withdraw");
		txtpnWithdraw.setBounds(140, 24, 134, 24);
		panel.add(txtpnWithdraw);
		
		JTextPane txtpnYourBalance = new JTextPane();
		txtpnYourBalance.setFont(new Font("Arial", Font.BOLD, 13));
		txtpnYourBalance.setText("Your balance : ");
		txtpnYourBalance.setBackground(new Color(255, 255, 204));
		txtpnYourBalance.setBounds(54, 86, 111, 22);
		panel.add(txtpnYourBalance);
		
		JTextPane txtpnEnterAmount = new JTextPane();
		txtpnEnterAmount.setBackground(new Color(255, 255, 204));
		txtpnEnterAmount.setText("Enter amount : ");
		txtpnEnterAmount.setFont(new Font("Arial", Font.BOLD, 13));
		txtpnEnterAmount.setBounds(54, 130, 111, 22);
		panel.add(txtpnEnterAmount);
		
		amtWithdrawn = new JTextField();
		amtWithdrawn.setBackground(new Color(255, 255, 204));
		amtWithdrawn.setBounds(222, 133, 96, 19);
		panel.add(amtWithdrawn);
		amtWithdrawn.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(amtWithdrawn.getText().isEmpty() || Integer.valueOf(amtWithdrawn.getText()) == 0)
				{
					JOptionPane.showMessageDialog(Submit,"Enter a valid amount!");
				}
				else if(OldBalance < Integer.valueOf(amtWithdrawn.getText()))
				{
					JOptionPane.showMessageDialog(Submit, "Enough balance not available!");
				}
				else
				{
					try {
						String query = "update accounts_table set Balance = ? where AccNo = ?";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, OldBalance-Integer.valueOf(amtWithdrawn.getText()));
						ps.setInt(2, MyAccNo);
						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(Submit,"Collect your cash!");
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
		Submit.setFont(new Font("Arial", Font.BOLD, 14));
		Submit.setBackground(new Color(255, 255, 204));
		Submit.setBounds(144, 176, 85, 31);
		panel.add(Submit);
		
		JLabel BalanceLabel = new JLabel("New label");
		BalanceLabel.setForeground(new Color(255, 255, 204));
		BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		BalanceLabel.setBackground(new Color(255, 255, 204));
		BalanceLabel.setBounds(222, 86, 96, 22);
		panel.add(BalanceLabel);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BackBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new MainMenu().setVisible(true);
						dispose();
					}
				});
			}
		});
		BackBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BackBtn.setBounds(51, 176, 65, 31);
		panel.add(BackBtn);
		
		JButton LogOutBtn = new JButton("LogOut");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setBackground(new Color(255, 255, 204));
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 12));
		LogOutBtn.setBounds(144, 217, 85, 21);
		panel.add(LogOutBtn);
	}
	
	public Withdraws(int AccNo) {
		MyAccNo = AccNo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnWithdraw = new JTextPane();
		txtpnWithdraw.setBackground(new Color(255, 255, 204));
		txtpnWithdraw.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnWithdraw.setForeground(new Color(0, 0, 0));
		txtpnWithdraw.setText("      Withdraw");
		txtpnWithdraw.setBounds(140, 24, 134, 24);
		panel.add(txtpnWithdraw);
		
		JTextPane txtpnYourBalance = new JTextPane();
		txtpnYourBalance.setFont(new Font("Arial", Font.BOLD, 13));
		txtpnYourBalance.setText("Your balance : ");
		txtpnYourBalance.setBackground(new Color(255, 255, 204));
		txtpnYourBalance.setBounds(54, 86, 111, 22);
		panel.add(txtpnYourBalance);
		
		JTextPane txtpnEnterAmount = new JTextPane();
		txtpnEnterAmount.setBackground(new Color(255, 255, 204));
		txtpnEnterAmount.setText("Enter amount : ");
		txtpnEnterAmount.setFont(new Font("Arial", Font.BOLD, 13));
		txtpnEnterAmount.setBounds(54, 130, 111, 22);
		panel.add(txtpnEnterAmount);
		
		amtWithdrawn = new JTextField();
		amtWithdrawn.setBackground(new Color(255, 255, 204));
		amtWithdrawn.setBounds(222, 133, 96, 19);
		panel.add(amtWithdrawn);
		amtWithdrawn.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(amtWithdrawn.getText().isEmpty() || Integer.valueOf(amtWithdrawn.getText()) == 0)
				{
					JOptionPane.showMessageDialog(Submit,"Enter a valid amount!");
				}
				else if(OldBalance < Integer.valueOf(amtWithdrawn.getText()))
				{
					JOptionPane.showMessageDialog(Submit, "Enough balance not available!");
				}
				else
				{
					try {
						String query = "update accounts_table set Balance = ? where AccNo = ?";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, OldBalance-Integer.valueOf(amtWithdrawn.getText()));
						ps.setInt(2, MyAccNo);
						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(Submit,"Please collect your cash!");
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
		Submit.setFont(new Font("Arial", Font.BOLD, 14));
		Submit.setBackground(new Color(255, 255, 204));
		Submit.setBounds(144, 176, 85, 31);
		panel.add(Submit);
		
		JLabel BalanceLabel = new JLabel("New label");
		BalanceLabel.setForeground(new Color(255, 255, 204));
		BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		BalanceLabel.setBackground(new Color(255, 255, 204));
		BalanceLabel.setBounds(222, 86, 96, 22);
		panel.add(BalanceLabel);
		
		getBalance();
		BalanceLabel.setText(""+OldBalance);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BackBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new MainMenu(MyAccNo).setVisible(true);
						dispose();
					}
				});
			}
		});
		BackBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BackBtn.setBounds(51, 176, 65, 31);
		panel.add(BackBtn);
		
		JButton LogOutBtn = new JButton("LogOut");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setBackground(new Color(255, 255, 204));
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 12));
		LogOutBtn.setBounds(144, 217, 85, 21);
		panel.add(LogOutBtn);
		
	}
	
}
