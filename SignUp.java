import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
@SuppressWarnings({ "serial", "unused" })
public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField DebitCardNo;
	private JTextField AccNo;
	private JTextField Fname;
	private JTextField Lname;
	private JTextField Phone;
	private JTextField pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void Clear()
	{
		DebitCardNo.setText("");
		AccNo.setText("");
		Fname.setText("");
		Lname.setText("");
		Phone.setText("");
		pin.setText("");
		}
	

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 446, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnSignUp = new JTextPane();
		txtpnSignUp.setBackground(new Color(255, 255, 204));
		txtpnSignUp.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnSignUp.setText("     Sign Up");
		txtpnSignUp.setBounds(169, 10, 113, 29);
		panel.add(txtpnSignUp);
		
		JTextPane txtpnDebitCardNumber = new JTextPane();
		txtpnDebitCardNumber.setBackground(new Color(255, 255, 204));
		txtpnDebitCardNumber.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnDebitCardNumber.setText("Debit card number");
		txtpnDebitCardNumber.setBounds(38, 51, 99, 19);
		panel.add(txtpnDebitCardNumber);
		
		JTextPane txtpnAccountNumber = new JTextPane();
		txtpnAccountNumber.setBackground(new Color(255, 255, 204));
		txtpnAccountNumber.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnAccountNumber.setText("Account number");
		txtpnAccountNumber.setBounds(38, 82, 99, 19);
		panel.add(txtpnAccountNumber);
		
		DebitCardNo = new JTextField();
		DebitCardNo.setBackground(new Color(255, 255, 204));
		DebitCardNo.setBounds(182, 51, 96, 19);
		panel.add(DebitCardNo);
		DebitCardNo.setColumns(10);
		
		AccNo = new JTextField();
		AccNo.setBackground(new Color(255, 255, 204));
		AccNo.setBounds(182, 82, 96, 19);
		panel.add(AccNo);
		AccNo.setColumns(10);
		
		JTextPane txtpnFirstName = new JTextPane();
		txtpnFirstName.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnFirstName.setBackground(new Color(255, 255, 204));
		txtpnFirstName.setText("First name");
		txtpnFirstName.setBounds(38, 111, 99, 19);
		panel.add(txtpnFirstName);
		
		Fname = new JTextField();
		Fname.setBackground(new Color(255, 255, 204));
		Fname.setBounds(182, 111, 96, 19);
		panel.add(Fname);
		Fname.setColumns(10);
		
		JTextPane txtpnLastName = new JTextPane();
		txtpnLastName.setBackground(new Color(255, 255, 204));
		txtpnLastName.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnLastName.setText("Last name");
		txtpnLastName.setBounds(38, 140, 99, 19);
		panel.add(txtpnLastName);
		
		Lname = new JTextField();
		Lname.setBackground(new Color(255, 255, 204));
		Lname.setBounds(182, 140, 96, 19);
		panel.add(Lname);
		Lname.setColumns(10);
		
		JTextPane txtpnMobileNumber = new JTextPane();
		txtpnMobileNumber.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnMobileNumber.setBackground(new Color(255, 255, 204));
		txtpnMobileNumber.setText("Mobile number");
		txtpnMobileNumber.setBounds(38, 169, 99, 19);
		panel.add(txtpnMobileNumber);
		
		Phone = new JTextField();
		Phone.setBackground(new Color(255, 255, 204));
		Phone.setBounds(182, 169, 96, 19);
		panel.add(Phone);
		Phone.setColumns(10);
		
		JTextPane txtpnPin = new JTextPane();
		txtpnPin.setBackground(new Color(255, 255, 204));
		txtpnPin.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnPin.setText("           PIN");
		txtpnPin.setBounds(38, 198, 99, 19);
		panel.add(txtpnPin);
		
		pin = new JTextField();
		pin.setBackground(new Color(255, 255, 204));
		pin.setBounds(182, 198, 96, 19);
		panel.add(pin);
		pin.setColumns(10);
		
		
		
		
		
		JButton Submit = new JButton("Submit");
		Submit.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(DebitCardNo.getText().isEmpty() || AccNo.getText().isEmpty() || Fname.getText().isEmpty() || Lname.getText().isEmpty() || Phone.getText().isEmpty() || pin.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(Submit, "Please fill up all the fields!");
				}
				else
				{
					try {
						Connection con = null;
						PreparedStatement pst = null;
						@SuppressWarnings("unused")
						ResultSet rs = null;
						Statement st = null;
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement Add = con.prepareStatement("insert into accounts_table(DebitCardno, AccNo, Fname, Lname, Phone, pin) values(?,?,?,?,?,?)");		
						Add.setInt(1, Integer.valueOf(DebitCardNo.getText()));
						Add.setInt(2,Integer.valueOf(AccNo.getText()));
						Add.setString(3, Fname.getText());
						Add.setString(4, Lname.getText());
						Add.setString(5,  Phone.getText());
						Add.setInt(6, Integer.valueOf(pin.getText()));
						
						int row = Add.executeUpdate();
						JOptionPane.showMessageDialog(Submit, "Signed up successfully!");
						con.close();
						Clear();
						dispose();
					}
					catch(Exception E){
						JOptionPane.showMessageDialog(Submit, E);
					}
				}
			}
		});
		Submit.setBounds(322, 211, 85, 29);
		panel.add(Submit);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
			}
		});
		BackBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BackBtn.setBounds(38, 227, 99, 21);
		panel.add(BackBtn);
		
		
	}
}
