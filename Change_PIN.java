import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
@SuppressWarnings({ "serial", "unused" })
public class Change_PIN extends JFrame {

	private JPanel contentPane;
	private JTextField NewPinLabel;
	private JTextField ConfirmPinLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change_PIN frame = new Change_PIN();
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
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null, rs1 = null;
	Statement st = null;
	public Change_PIN() {
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
		
		JTextPane txtpnChangePin = new JTextPane();
		txtpnChangePin.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnChangePin.setBackground(new Color(255, 255, 204));
		txtpnChangePin.setText("   Change PIN");
		txtpnChangePin.setBounds(152, 32, 123, 28);
		panel.add(txtpnChangePin);
		
		JTextPane txtpnNewPin = new JTextPane();
		txtpnNewPin.setBackground(new Color(255, 255, 204));
		txtpnNewPin.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnNewPin.setText("       New PIN");
		txtpnNewPin.setBounds(89, 78, 116, 28);
		panel.add(txtpnNewPin);
		
		NewPinLabel = new JTextField();
		NewPinLabel.setBackground(new Color(255, 255, 204));
		NewPinLabel.setBounds(243, 78, 96, 28);
		panel.add(NewPinLabel);
		NewPinLabel.setColumns(10);
		
		JTextPane txtpnConfirmPin = new JTextPane();
		txtpnConfirmPin.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnConfirmPin.setBackground(new Color(255, 255, 204));
		txtpnConfirmPin.setText("    Confirm PIN");
		txtpnConfirmPin.setBounds(89, 137, 116, 28);
		panel.add(txtpnConfirmPin);
		
		ConfirmPinLabel = new JTextField();
		ConfirmPinLabel.setBackground(new Color(255, 255, 204));
		ConfirmPinLabel.setBounds(243, 137, 96, 28);
		panel.add(ConfirmPinLabel);
		ConfirmPinLabel.setColumns(10);
		
		JButton ChangeBtn = new JButton("Change");
		ChangeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(NewPinLabel.getText().isEmpty() || ConfirmPinLabel.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(ChangeBtn, "Please fill up all the fields!");
				}
				else if(!NewPinLabel.getText().equals(ConfirmPinLabel.getText()))
				{
					JOptionPane.showMessageDialog(ChangeBtn, "The PINs are not equal! Please confirm again!");
				}
				else
				{
					try {
						String query = "update accounts_table set pin = ? where AccNo = '"+MyAccNo+"'";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, Integer.valueOf(NewPinLabel.getText()));

						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(ChangeBtn,"PIN updated!");
						}
						else
						{
							JOptionPane.showMessageDialog(ChangeBtn, "Missing Information!");
						}
					}
					catch(Exception E)
					{
						JOptionPane.showMessageDialog(ChangeBtn, E);
					}
				}
			}
		});
		ChangeBtn.setBackground(new Color(255, 255, 204));
		ChangeBtn.setFont(new Font("Arial", Font.BOLD, 14));
		ChangeBtn.setBounds(179, 190, 96, 28);
		panel.add(ChangeBtn);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(220, 234, -45, 19);
		panel.add(textPane);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		BackBtn.setFont(new Font("Arial", Font.BOLD, 14));
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setBounds(42, 190, 75, 28);
		panel.add(BackBtn);
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		LogOutBtn.setBounds(315, 190, 85, 26);
		panel.add(LogOutBtn);
	}
	int MyAccNo;
	public Change_PIN(int AccNo) {
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
		
		JTextPane txtpnChangePin = new JTextPane();
		txtpnChangePin.setFont(new Font("Arial", Font.BOLD, 15));
		txtpnChangePin.setBackground(new Color(255, 255, 204));
		txtpnChangePin.setText("   Change PIN");
		txtpnChangePin.setBounds(152, 32, 123, 28);
		panel.add(txtpnChangePin);
		
		JTextPane txtpnNewPin = new JTextPane();
		txtpnNewPin.setBackground(new Color(255, 255, 204));
		txtpnNewPin.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnNewPin.setText("       New PIN");
		txtpnNewPin.setBounds(89, 78, 116, 28);
		panel.add(txtpnNewPin);
		
		NewPinLabel = new JTextField();
		NewPinLabel.setBackground(new Color(255, 255, 204));
		NewPinLabel.setBounds(243, 78, 96, 28);
		panel.add(NewPinLabel);
		NewPinLabel.setColumns(10);
		
		JTextPane txtpnConfirmPin = new JTextPane();
		txtpnConfirmPin.setFont(new Font("Arial", Font.BOLD, 14));
		txtpnConfirmPin.setBackground(new Color(255, 255, 204));
		txtpnConfirmPin.setText("    Confirm PIN");
		txtpnConfirmPin.setBounds(89, 137, 116, 28);
		panel.add(txtpnConfirmPin);
		
		ConfirmPinLabel = new JTextField();
		ConfirmPinLabel.setBackground(new Color(255, 255, 204));
		ConfirmPinLabel.setBounds(243, 137, 96, 28);
		panel.add(ConfirmPinLabel);
		ConfirmPinLabel.setColumns(10);
		
		JButton ChangeBtn = new JButton("Change");
		ChangeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(NewPinLabel.getText().isEmpty() || ConfirmPinLabel.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(ChangeBtn, "Please fill up all the fields!");
				}
				else if(!NewPinLabel.getText().equals(ConfirmPinLabel.getText()))
				{
					JOptionPane.showMessageDialog(ChangeBtn, "The PINs are not equal! Please confirm again!");
				}
				else
				{
					try {
						String query = "update accounts_table set pin = ? where AccNo ='"+MyAccNo+"'";
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, Integer.valueOf(NewPinLabel.getText()));
						
						if(ps.executeUpdate() == 1)
						{
							JOptionPane.showMessageDialog(ChangeBtn,"PIN updated!");
						}
						else
						{
							JOptionPane.showMessageDialog(ChangeBtn, "Missing Information!");
						}
					}
					catch(Exception E)
					{
						JOptionPane.showMessageDialog(ChangeBtn, E);
					}
				}
			}
		});
		ChangeBtn.setBackground(new Color(255, 255, 204));
		ChangeBtn.setFont(new Font("Arial", Font.BOLD, 14));
		ChangeBtn.setBounds(179, 190, 96, 28);
		panel.add(ChangeBtn);
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(220, 234, -45, 19);
		panel.add(textPane);
		
		JButton BackBtn = new JButton("Back");
		
		
		BackBtn.setFont(new Font("Arial", Font.BOLD, 14));
		BackBtn.setBackground(new Color(255, 255, 204));
		BackBtn.setBounds(42, 190, 75, 28);
		panel.add(BackBtn);
		
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainMenu(MyAccNo).setVisible(true);
				dispose();
			}
		});
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ATM_design().setVisible(true);
				dispose();
			}
		});
		LogOutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		LogOutBtn.setBounds(315, 190, 85, 26);
		panel.add(LogOutBtn);
		
	}
}
