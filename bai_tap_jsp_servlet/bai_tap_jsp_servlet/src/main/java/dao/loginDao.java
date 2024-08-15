package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import entity.*;
import utils.*;
import utils.connect.connect;
public class loginDao {
	
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public ResultSet login(String userName, String passWord) {
    	
    	String query = "select * from admin where emailAdress=? or userPassword=?;";
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(query);
    		ps.setString(1, userName);
    		ps.setString(2, passWord);
    		
    		rs = ps.executeQuery();
    		return rs;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    } 
    
    
	
}
