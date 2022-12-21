import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
@SuppressWarnings({ "serial", "unused" })
public class ATM_design extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM_design frame = new ATM_design();
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
	public ATM_design() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 204), 5, true));
		panel.setBackground(new Color(153, 51, 102));
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane maintext = new JTextPane();
		maintext.setFont(new Font("Arial", Font.BOLD, 20));
		maintext.setBackground(new Color(255, 255, 204));
		maintext.setText("Welcome to Peoples's Choice Bank");
		maintext.setBounds(91, 52, 215, 78);
		panel.add(maintext);
		
		JButton BeginBtn = new JButton("Click here to begin your transaction");
		BeginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				Statement st = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","Serampore@2023");
				new Login().setVisible(true);
				dispose();
				}
				catch(Exception E)
				{
					JOptionPane.showMessageDialog(BeginBtn, E);
				}
			}
		});
		BeginBtn.setBackground(new Color(255, 255, 204));
		BeginBtn.setFont(new Font("Arial", Font.BOLD, 12));
		BeginBtn.setBounds(135, 156, 246, 54);
		panel.add(BeginBtn);
	}
}
