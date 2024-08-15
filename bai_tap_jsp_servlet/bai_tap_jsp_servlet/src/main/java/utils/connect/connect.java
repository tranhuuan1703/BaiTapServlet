package utils.connect;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 


public class connect {

	// Initialize all the information regarding 
    // Database Connection 
    String dbDriver = "com.mysql.cj.jdbc.Driver"; 
    String dbURL = "jdbc:mysql://localhost:3306/BAI_TAP_JSP_SERVLETV1"; 
    // Database name to access 
    
    String dbUsername = "root"; 
    String dbPassword = "admin123"; 
    
    
    public Connection connection() {
    	
    	try {
    		Class.forName(dbDriver);
    		Connection con = DriverManager.getConnection(dbURL, 
									                    dbUsername,  
									                    dbPassword); 
    		return con; 
    	}catch(Exception e) {
    		e.fillInStackTrace();
    	}
		return null;
          
    }
    
}
