package stdviews;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import stdcommon.DBConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JPasswordField;

public class SignUpWindow extends JFrame {

	private JPanel contentPane;
	private JTextField stdid;
	private JTextField name;
	private JTextField age;
	private JTextField roll;
	private JTextField prc;
	private JButton cancel;
	private JButton signup;
	private JPasswordField pass;
	private JPasswordField cpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpWindow frame = new SignUpWindow();
					frame.getContentPane().setSize(new Dimension(500, 800));
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
	public SignUpWindow() {
		initComponents();
		createEvents();
		}

	

	private void initComponents() {
		// TODO Auto-generated method stub
		setFont(new Font("Dialog", Font.BOLD, 18));
		setTitle("Sign Up");
		setSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(500, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("StudentID");
		lblNewLabel.setBounds(37, 33, 95, 14);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 58, 115, 14);
		
		JLabel lblConfirmPassword = new JLabel("Confirm\r\nPassword");
		lblConfirmPassword.setBounds(37, 83, 142, 14);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(37, 108, 46, 14);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(37, 133, 33, 14);
		
		JLabel lblRollNo = new JLabel("Roll no.");
		lblRollNo.setBounds(37, 158, 95, 14);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(37, 183, 95, 14);
		contentPane.setLayout(null);
		contentPane.add(lblPassword);
		contentPane.add(lblConfirmPassword);
		contentPane.add(lblName);
		contentPane.add(lblAge);
		contentPane.add(lblRollNo);
		contentPane.add(lblPrice);
		contentPane.add(lblNewLabel);
		
		stdid = new JTextField();
		stdid.setBounds(189, 30, 203, 20);
		contentPane.add(stdid);
		stdid.setColumns(10);
		
		name = new JTextField();
		name.setBounds(189, 105, 203, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		age = new JTextField();
		age.setBounds(189, 130, 203, 20);
		contentPane.add(age);
		age.setColumns(10);
		
		roll = new JTextField();
		roll.setBounds(189, 155, 203, 20);
		contentPane.add(roll);
		roll.setColumns(10);
		
		prc = new JTextField();
		prc.setBounds(189, 180, 203, 20);
		contentPane.add(prc);
		prc.setColumns(10);
		
		signup = new JButton("Sign Up");
		
		signup.setBounds(94, 227, 89, 23);
		contentPane.add(signup);
		
		cancel = new JButton("Cancel");
		
		cancel.setBounds(244, 227, 89, 23);
		contentPane.add(cancel);
		
		pass = new JPasswordField();
		pass.setBounds(189, 55, 203, 20);
		contentPane.add(pass);
		
		cpass = new JPasswordField();
		cpass.setBounds(189, 80, 203, 20);
		contentPane.add(cpass);

		
	}
	private void createEvents() {
		// TODO Auto-generated method stub
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JOptionPane.showMessageDialog(null, "data not inserted");
			}
		});
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qry="insert into student values(?,?,?,?,?,?)";
				try{
					Connection con=DBConnection.getConnection();
					if(pass.getText().equals(cpass.getText())){
						PreparedStatement pstmt=con.prepareStatement(qry);
						pstmt.setString(1, stdid.getText());
						pstmt.setString(2, pass.getText());
						pstmt.setString(3, name.getText());
						pstmt.setInt(4,Integer.parseInt(age.getText()) );
						pstmt.setInt(5,Integer.parseInt(roll.getText()) );
						pstmt.setInt(6,Integer.parseInt(prc.getText()) );
						int status=pstmt.executeUpdate();
						if(status>0){
							dispose();
							JOptionPane.showMessageDialog(null, "successfully signed up");
							new MainFrame().setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "Failed");
					}
					else
						JOptionPane.showMessageDialog(null, "Password not matched");
						
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
				
			}
		});
	}
}
