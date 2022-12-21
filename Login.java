import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
@SuppressWarnings({ "serial", "unused" })
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField DebitCardNo;
	private JPasswordField pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void clear()
	{
		DebitCardNo.setText("");
		pin.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 10, 426, 253);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setFont(new Font("Arial", Font.BOLD, 18));
		txtpnLogin.setBackground(new Color(255, 255, 204));
		txtpnLogin.setForeground(new Color(0, 0, 0));
		txtpnLogin.setText("  Login");
		txtpnLogin.setBounds(253, 34, 142, 33);
		panel.add(txtpnLogin);
		
		DebitCardNo = new JTextField();
		DebitCardNo.setBackground(new Color(255, 255, 204));
		DebitCardNo.setBounds(253, 93, 142, 19);
		panel.add(DebitCardNo);
		DebitCardNo.setColumns(10);
		
		pin = new JPasswordField();
		pin.setBackground(new Color(255, 255, 204));
		pin.setBounds(253, 133, 142, 19);
		panel.add(pin);
		
		JTextPane txtpnDebitCardNumber = new JTextPane();
		txtpnDebitCardNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnDebitCardNumber.setText("Debit card number");
		txtpnDebitCardNumber.setBackground(new Color(255, 255, 204));
		txtpnDebitCardNumber.setBounds(99, 93, 120, 19);
		panel.add(txtpnDebitCardNumber);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBackground(new Color(255, 255, 204));
		txtpnPassword.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnPassword.setText("PIN");
		txtpnPassword.setBounds(99, 133, 120, 21);
		panel.add(txtpnPassword);
		
		JButton loginbtn = new JButton("Login");
		
		loginbtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(DebitCardNo.getText().isEmpty() || pin.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(loginbtn, "Please fill up all the fields!");
				}
				else
				{
					String query = "select *from accounts_table where DebitCardNo = '"+DebitCardNo.getText()+"' and pin = '"+pin.getText()+"' ";
				 try {
					    Connection con = null;
						PreparedStatement pst = null;
						ResultSet rs = null;
						Statement st = null;
					    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
					    st=con.createStatement();
					    rs=st.executeQuery(query);
					 if(rs.next())
					 {
						 new MainMenu(rs.getInt(2)).setVisible(true);
						 clear();
						 dispose();
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(loginbtn, "Incorrect debit card number or PIN!");
					 }
					 
				 }
				 catch(Exception E) {
				 
				 
					 JOptionPane.showMessageDialog(loginbtn, E);
				 }
				}
				
			}
		
		});
		loginbtn.setFont(new Font("Arial", Font.BOLD, 14));
		loginbtn.setBackground(new Color(255, 255, 204));
		loginbtn.setBounds(281, 176, 85, 21);
		panel.add(loginbtn);
		
		JButton signupbtn = new JButton("Signup");
		
		
		signupbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUp().setVisible(true);
				dispose();
			}
		});
		signupbtn.setBackground(new Color(255, 255, 204));
		signupbtn.setFont(new Font("Arial", Font.BOLD, 14));
		signupbtn.setBounds(281, 219, 85, 21);
		panel.add(signupbtn);
	}
}
