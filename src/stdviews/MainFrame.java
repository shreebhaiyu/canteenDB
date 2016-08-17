package stdviews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Window.Type;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;

import stdcommon.DBConnection;
import stdcommon.Studentpozo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton submit;
	private JTextField user;
	private JPasswordField pass;
	private JButton cancel;
	private JLabel signup;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setType(Type.POPUP);
		setFont(new Font("Elephant", Font.BOLD, 18));
		setTitle("LOG IN");
		initComponents();
		createEvents();
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		user = new JTextField();
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		signup = new JLabel("Sign Up");
		
		signup.setHorizontalAlignment(SwingConstants.CENTER);
		signup.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		submit = new JButton("Submit");
		
		
		cancel = new JButton("Cancel");
		
		lblNewLabel_1 = new JLabel("New User?");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(user, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(pass, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(144)
					.addComponent(submit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(cancel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(282)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(signup, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(user, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(pass, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(submit)
						.addComponent(cancel))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(signup)
						.addComponent(lblNewLabel_1))
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	private void createEvents() {
		// TODO Auto-generated method stub
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usr=user.getText();
				@SuppressWarnings("deprecation")
				String password=pass.getText();
				try {
					Connection con=DBConnection.getConnection();
					PreparedStatement ps=con.prepareStatement("select * from student where stdid='"+usr+"' and stdpass='"+password+"'");
					ResultSet rs=ps.executeQuery();
					 if(rs.next())
			            {
			                Studentpozo.setUser(usr);
			                Studentpozo.setPass(password);
			                
						 	JOptionPane.showMessageDialog(null,"Welcome "+usr);
						 	new MainWindow().setVisible(true);
						 	dispose();
			            }
			            else
			                 JOptionPane.showMessageDialog(null,"Invalid userid/password ");
			        
						
			        } 
			        catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
					
					
				} 
			}
		);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		signup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SignUpWindow su=new SignUpWindow();
				su.setVisible(true);
				dispose();
			}
		});
		
	}
}
