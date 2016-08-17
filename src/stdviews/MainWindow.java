package stdviews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import stdcommon.DBConnection;
import stdcommon.Studentpozo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField paid;
	public Border ourBorder=BorderFactory.createLineBorder(Color.black);
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton save;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
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
		
		JLabel name = new JLabel("Name");
		
		JLabel roll = new JLabel("Roll no.");
		
		JLabel age = new JLabel("Age");
		
		JLabel amnt = new JLabel("Paid");
		
		label = new JLabel("");
		label.setBorder(ourBorder);
		label_1 = new JLabel("");
		label_1.setBorder(ourBorder);
		label_2 = new JLabel("");
		label_2.setBackground(Color.LIGHT_GRAY);
		label_2.setBorder(ourBorder);
		paid = new JTextField();
		paid.setColumns(10);
		
		save = new JButton("Save\r\n");
		
		
		cancel = new JButton("Cancel");
		
		btnPrint = new JButton("Print");
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.RED);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(name)
								.addComponent(roll)
								.addComponent(age)
								.addComponent(amnt)
								.addComponent(save, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(36)
									.addComponent(cancel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(paid, 198, 198, 198))
							.addContainerGap(44, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(name))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(roll)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(age)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amnt)
						.addComponent(paid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(save)
						.addComponent(btnPrint)
						.addComponent(cancel))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	String usr=Studentpozo.getUser();
	String pass=Studentpozo.getPass();
	int pay=0;
	int price=0;
	Connection con=null;
	private JButton cancel;
	private JButton btnPrint;
	
	private void createEvents() {
		// TODO Auto-generated method stub
		
		try {

			con=DBConnection.getConnection();
			PreparedStatement stmt=con.prepareStatement("select * from student where stdid='"+usr+"' and stdpass='"+pass+"'");
		//	PreparedStatement stmt1=con.prepareStatement("select * from student where stdid='"+usr+"' and stdpass='"+pass+"'");
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
			    label.setText(rs.getString("name"));
				label_1.setText(Integer.toString(rs.getInt("rollno")));
				label_2.setText(Integer.toString(rs.getInt("age")));
				price=rs.getInt("price");
			}
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    pay=Integer.parseInt(paid.getText());
				
				try {
					// Connection con1=DBConnection.getConnection();
				/*	 PreparedStatement stmt=con1.prepareStatement("select * from student where stdid='"+usr+"' and stdpass='"+pass+"'");
					 ResultSet rs1=stmt.executeQuery();
					if(rs1.next()){
					    price = rs1.getInt("price");
						}
				*/	int price=0;
					PreparedStatement stmt=con.prepareStatement("select * from student where stdid='"+usr+"' and stdpass='"+pass+"'");
					ResultSet rs=stmt.executeQuery();
					if(rs.next()){
						price=rs.getInt("price");
					}
					String qry="update student set price="+(price+pay)+" where stdid='"+usr+"' and stdpass='"+pass+"'";
					Statement stmt1=con.createStatement();
					int status=stmt1.executeUpdate(qry);
					if(status>0)
						JOptionPane.showMessageDialog(rootPane, "record updated");
					else
						JOptionPane.showMessageDialog(rootPane, "ERROR");
					dispose();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String qry="select * from student";
				Connection con=DBConnection.getConnection();
				try{
				Map<String, Object[]> data = new TreeMap<String, Object[]>();
				XSSFWorkbook workbook = new XSSFWorkbook(); 
				int rownum = 0;
				Set<String> keyset = data.keySet();
				//Create a blank sheet
				XSSFSheet sheet = workbook.createSheet("price");
				data.put("1", new Object[] {"STDID","STDNAME", "STDAGE","ROLLNO","AMOUNT"});
				for (String key : keyset)
				{
				    Row row = sheet.createRow(rownum++);
				    Object [] objArr = data.get(key);
				    int cellnum = 0;
				    for (Object obj : objArr)
				    {
				       Cell cell = row.createCell(cellnum++);
				       if(obj instanceof String)
				            cell.setCellValue((String)obj);
				        else if(obj instanceof Integer)
				            cell.setCellValue((Integer)obj);
				    }
				}
				try {
					PreparedStatement stmt=con.prepareStatement(qry);
					ResultSet rs=stmt.executeQuery();
					int i=1;
					
					while(rs.next()){
					i++;	
						  //Blank workbook
		
		 
		//This data needs to be written (Object[])
		
		data.put(Integer.toString(i), new Object[] {rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
		
		 
		//Iterate over data and write to sheet
		
		
		for (String key : keyset)
		{
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr)
		    {
		       Cell cell = row.createCell(cellnum++);
		       if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Integer)
		            cell.setCellValue((Integer)obj);
		    }
		}
		try 
		{
			//Write the workbook in file system
		    FileOutputStream out = new FileOutputStream(new File("print_demo.xlsx"));
		    workbook.write(out);
		    out.close();
		    

		     
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
						
					}
				} catch (Exception e2) {
					// TODO
					e2.printStackTrace();
				}
				}
				catch(Exception e3){
					e3.printStackTrace();
				}
			}
		});
	}
}
