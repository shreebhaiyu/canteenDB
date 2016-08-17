package stdcommon;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost:3306/collage";
    static String user="root";
    static String password="system";
    
    public static Connection getConnection() {
		// TODO Auto-generated method stub
    	Connection con=null;
    	try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
		} 
    	catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println();
    	
		return con;
	}

}

