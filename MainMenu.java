import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


@SuppressWarnings({ "serial", "unused" })

public class MainMenu extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=-19,404
	 */
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		
		textField.setColumns(10);
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
		
		JTextPane txtpnPlease = new JTextPane();
		txtpnPlease.setBackground(new Color(255, 255, 204));
		txtpnPlease.setForeground(new Color(0, 0, 0));
		txtpnPlease.setFont(new Font("Arial", Font.BOLD, 13));
		txtpnPlease.setText("Please select your transaction type");
		txtpnPlease.setBounds(100, 65, 214, 38);
		panel.add(txtpnPlease);
		
		JButton withdrawBtn = new JButton("Withdraw");
		
		
		withdrawBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new Withdraws(MyAccNo).setVisible(true);
				SwingUtilities.windowForComponent(withdrawBtn).dispose();
				
				
				
			}
		});
		withdrawBtn.setBackground(new Color(255, 255, 204));
		withdrawBtn.setFont(new Font("Arial", Font.BOLD, 12));
		withdrawBtn.setBounds(33, 132, 115, 28);
		panel.add(withdrawBtn);
		
		JButton DepositBtn = new JButton("Deposit");
		DepositBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Deposits(MyAccNo).setVisible(true);
			}
		});
		DepositBtn.setBackground(new Color(255, 255, 204));
		DepositBtn.setFont(new Font("Arial", Font.BOLD, 12));
		DepositBtn.setForeground(new Color(0, 0, 0));
		DepositBtn.setBounds(257, 132, 130, 28);
		panel.add(DepositBtn);
		
		JButton ChangePinBtn = new JButton("Change PIN");
		ChangePinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Change_PIN(MyAccNo).setVisible(true);
				
			}
		});
		ChangePinBtn.setFont(new Font("Arial", Font.BOLD, 12));
		ChangePinBtn.setBackground(new Color(255, 255, 204));
		ChangePinBtn.setBounds(33, 188, 115, 28);
		panel.add(ChangePinBtn);
		
		JButton CheckBalanceBtn = new JButton("Check Balance");
		
		
		CheckBalanceBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
	
					new Balance(MyAccNo).setVisible(true);
					JButton CheckBalanceBtn = (JButton)e.getSource();
					SwingUtilities.windowForComponent(withdrawBtn).dispose();
				
			}
		});
		CheckBalanceBtn.setBackground(new Color(255, 255, 204));
		CheckBalanceBtn.setFont(new Font("Arial", Font.BOLD, 12));
		CheckBalanceBtn.setBounds(257, 188, 130, 28);
		panel.add(CheckBalanceBtn);
		
		JTextPane txtpnYourAccountNumber = new JTextPane();
		txtpnYourAccountNumber.setBackground(new Color(255, 255, 204));
		txtpnYourAccountNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnYourAccountNumber.setText("Your account number : ");
		txtpnYourAccountNumber.setBounds(33, 22, 164, 28);
		panel.add(txtpnYourAccountNumber);
		
		JLabel AccNoLabel = new JLabel("New label");
		AccNoLabel.setForeground(new Color(255, 255, 204));
		AccNoLabel.setBackground(new Color(255, 255, 204));
		AccNoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		AccNoLabel.setBounds(229, 22, 123, 28);
		panel.add(AccNoLabel);
	}
	public MainMenu(int AccNo) {
		// TODO Auto-generated constructor stub
		MyAccNo=AccNo;
		
		
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(10, 10, 416, 243);
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JTextPane txtpnPlease = new JTextPane();
		txtpnPlease.setBackground(new Color(255, 255, 204));
		txtpnPlease.setForeground(new Color(0, 0, 0));
		txtpnPlease.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnPlease.setText("Please select your transaction type");
		txtpnPlease.setBounds(100, 65, 214, 28);
		panel.add(txtpnPlease);
		
		JButton withdrawBtn = new JButton("Withdraw");
		
		
		withdrawBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new Withdraws(MyAccNo).setVisible(true);
				dispose();
				
			}
		});
		withdrawBtn.setBackground(new Color(255, 255, 204));
		withdrawBtn.setFont(new Font("Arial", Font.BOLD, 10));
		withdrawBtn.setBounds(33, 116, 115, 21);
		panel.add(withdrawBtn);
		
		JButton DepositBtn = new JButton("Deposit");
		DepositBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Deposits(MyAccNo).setVisible(true);
				dispose();
				
			}
		});
		DepositBtn.setBackground(new Color(255, 255, 204));
		DepositBtn.setFont(new Font("Arial", Font.BOLD, 10));
		DepositBtn.setForeground(new Color(0, 0, 0));
		DepositBtn.setBounds(257, 116, 115, 21);
		panel.add(DepositBtn);
		
		JButton ChangePinBtn = new JButton("Change PIN");
		ChangePinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Change_PIN(MyAccNo).setVisible(true);
				dispose();
			}
		});
		ChangePinBtn.setFont(new Font("Arial", Font.BOLD, 10));
		ChangePinBtn.setBackground(new Color(255, 255, 204));
		ChangePinBtn.setBounds(33, 188, 115, 21);
		panel.add(ChangePinBtn);
		
		JButton CheckBalanceBtn = new JButton("Check Balance");
		
		
		CheckBalanceBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
	
					new Balance(MyAccNo).setVisible(true);
					dispose();
				
			}
		});
		CheckBalanceBtn.setBackground(new Color(255, 255, 204));
		CheckBalanceBtn.setFont(new Font("Arial", Font.BOLD, 10));
		CheckBalanceBtn.setBounds(257, 188, 115, 21);
		panel.add(CheckBalanceBtn);
		
		
		
		JTextPane txtpnYourAccountNumber = new JTextPane();
		txtpnYourAccountNumber.setBackground(new Color(255, 255, 204));
		txtpnYourAccountNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnYourAccountNumber.setText("Your account number : ");
		txtpnYourAccountNumber.setBounds(33, 22, 164, 28);
		panel.add(txtpnYourAccountNumber);
		
		JLabel AccNoLabel = new JLabel("New label");
		AccNoLabel.setBounds(229, 22, 123, 28);
		AccNoLabel.setForeground(new Color(255, 255, 204));
		AccNoLabel.setBackground(new Color(255, 255, 204));
		AccNoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(AccNoLabel);
		AccNoLabel.setText(""+MyAccNo);
		
		
	}
}
