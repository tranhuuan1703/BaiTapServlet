package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;

import entity.*;
import utils.connect.connect;

public class EmployeeDao {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    // them mot nhan vien
    public void insert(String employeeName, String employeeEmail, Date employeeBirthDate, int employeeAge, String employeeGender,
    						String employeeAddress, Position position, Date DateJoiningTheCompany, Admin admin) {
    	
    	String query = "Insert Into employee(employeeName, employeeEmail, employeeBirthDate, employeeAge, employeeGender, employeeAddress, positionId, DateOfJoiningTheCompany, adminId)"
    			+ "value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(query);
    		
    		ps.setString(1, employeeName);
    		ps.setString(2, employeeEmail);
    		ps.setDate(3, (java.sql.Date) employeeBirthDate);
    		ps.setInt(4, employeeAge);
    		ps.setString(5, employeeGender);
    		ps.setString(6, employeeAddress);
    		ps.setInt(7, position.getPositionId());
    		ps.setDate(8, (java.sql.Date) DateJoiningTheCompany);
    		ps.setInt(9,  admin.getAdminId());
    		
    		ps.executeUpdate();
    		conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    // updated mot nhan vien
    public void update(int employeeId, String employeeName, String employeeEmail, String employeeBirthDate, int employeeAge, String employeeGender,
    						String employeeAddress, Position position, String DateJoiningTheCompany) {
    	String sql = "UPDATE employee\r\n"
    			+ "SET employeeName  = ?, employeeEmail  = ?, employeeBirthDate  = ?, employeeAge  = ?, employeeAddress = ?, employeeGender  = ?, positionId  = ?, DateOfJoiningTheCompany = ?\r\n"
    			+ "WHERE employeeId = ?;";
    	
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, employeeName);
    		ps.setString(2, employeeEmail);
    		ps.setString(3, employeeBirthDate);
    		ps.setInt(4, employeeAge);
    		ps.setString(5,  employeeAddress);
    		ps.setString(6, employeeGender);
    		ps.setInt(7, position.getPositionId());
    		ps.setString(8, DateJoiningTheCompany);
    		ps.setInt(9,  employeeId);
    		
    		ps.executeUpdate();
    		conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    // tim kiem nhan vien theo id
    public HashMap<String, String> queryEmployee(int employeeId) {
    	String sql = "select * from employee where employeeId = ?";
    	Position position = new Position();
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(sql);
    		
    		ps.setInt(1, employeeId);
    		
    		
    		rs = ps.executeQuery();

			if (rs.next()) {
				
				// lay gia tri trong bang
				int Id = rs.getInt("employeeId");
				String nameEmployee = rs.getString("employeeName");
				String emailEmployee = rs.getString("employeeEmail");
				Date employeBirthDay = rs.getDate("employeeBirthDate");
				int employeeAge = rs.getInt("employeeAge");
				String employeeGender = rs.getString("employeeGender");
				String employeeAddress = rs.getString("employeeAddress");
				int employeePositionId = rs.getInt("positionId");
				Date employeeDateOfJoiningTheCompany = rs.getDate("DateOfJoiningTheCompany");
				
				// lay gia tri cua position
				position.setPositionId(employeePositionId);
				String role = position.getPosition();
				
				
				// set Attribute
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put("employeeId", Integer.toString(Id));
				hashMap.put("employeeName", nameEmployee);
				hashMap.put("emailEmployee", emailEmployee);
				hashMap.put("employeBirthDay", employeBirthDay.toString());
				hashMap.put("employeeAge", Integer.toString(employeeAge));
				hashMap.put("employeeGender", employeeGender);
				hashMap.put("employeeAddress", employeeAddress);
				hashMap.put("position", role);
				hashMap.put("employeeDateOfJoiningTheCompany", employeeDateOfJoiningTheCompany.toString());
				
				// dong connect
				conn.close();
				return hashMap;
				
			}
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    // truy van tat ca nhan vien
    public List<Employee> queryAllEmployee() {
    	String sql = "select * from employee";
    	Position position = new Position();
    	Admin admin = new Admin();
    	List<Employee> list = new ArrayList<>();
    	
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		
    		

    		while (rs.next()) {
    			
    			position.setPositionId(rs.getInt("positionId"));
    			
    			Employee employee = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"), rs.getString("employeeEmail"), 
						rs.getDate("employeeBirthDate"), rs.getInt("employeeAge"), rs.getString("employeeGender"),
						rs.getString("employeeAddress"), position, rs.getDate("DateOfJoiningTheCompany"));
    			
    			
    			list.add(employee);
    			
    		}
    		conn.close();
    		return list;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    // delete row
    public void deleteEmployee(int employeeId) {
    	
    	String sql = "DELETE FROM employee WHERE employeeId = ?;";
    	
    	try {
    		conn = new connect().connection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, employeeId);
    		
    		ps.executeUpdate();
    		conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    // pagination page
    
    public int CountData(String text) {
    	if (text == null) {
    		text = "";
    	}
    	
    	String sql = "select count(employeeId) from employee where employeeName LIKE ? or positionId like ? or employeeAddress like ?";
    	
    	try {
    		Position position = new Position();
    		int count = 0;
    		conn = new connect().connection();
    		
    		ps = conn.prepareStatement(sql);
    		
    		if (text.equals("boss") || text.equals("employee")){
	    		position.setPosition(text);
	    		
	    		ps = conn.prepareStatement(sql);
    	    	
    	    	ps.setString(1, "%" + position.getPositionId() + "%");
        		ps.setString(2, "%" + position.getPositionId() + "%");
        		ps.setString(3, "%" + position.getPositionId() + "%");
	    	}else {
    			ps = conn.prepareStatement(sql);
    	    	
    	    	ps.setString(1, "%" + text + "%");
        		ps.setString(2, "%" + text + "%");
        		ps.setString(3, "%" + text + "%");
	    	}
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			count = rs.getInt(1);
    		}
    		conn.close();
    		return count;
    	}catch(Exception e) {
    		e.printStackTrace();
    		}
    	
    	return 0;
    }
    public List<Employee> paginationPage(int page, String text) {
    	
    	if (text == null) {
    		text = "";
    	}
    	String sql = null;
    	// tao mot list de them cac employee vao
    	List<Employee> list = new ArrayList<>();
    	
    	
    	try {
    		conn = new connect().connection();
//    		if (text != null) {
    			Position position = new Position();
    			
    	    
    	    	sql = "select * from employee where employeeName LIKE ? or positionId like ? or employeeAddress like ?"
    	    			+ " order by employeeId desc LIMIT 10 OFFSET ?";
    	    	
    	    	// set object position
    	    	if (text.equals("boss") || text.equals("employee")){
    	    		position.setPosition(text);
    	    		
    	    		ps = conn.prepareStatement(sql);
        	    	
        	    	ps.setString(1, "%" + position.getPositionId() + "%");
            		ps.setString(2, "%" + position.getPositionId() + "%");
            		ps.setString(3, "%" + position.getPositionId() + "%");
    	    	}else {
	    			ps = conn.prepareStatement(sql);
        	    	
        	    	ps.setString(1, "%" + text + "%");
            		ps.setString(2, "%" + text + "%");
            		ps.setString(3, "%" + text + "%");
    	    	}
    	    	        		

        		int number = page;
        		
        		number = 10* (number - 1);
        		
        		
        		ps.setInt(4, number);
        		rs = ps.executeQuery();
    		    		
			while (rs.next()) {
			    
				
    			Position nposition = new Position();
    			nposition.setPositionId(rs.getInt("positionId"));
    			
    			// System.out.println(position.getPositionId() + " " + position.getPosition());
    			Employee employee = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"), rs.getString("employeeEmail"), 
						rs.getDate("employeeBirthDate"), rs.getInt("employeeAge"), rs.getString("employeeGender"),
						rs.getString("employeeAddress"), nposition, rs.getDate("DateOfJoiningTheCompany"));
    			   			
    			list.add(employee);
    			
    		}
			conn.close();
    		return list;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		}
    	
    	return null;
    }
    
    
}
