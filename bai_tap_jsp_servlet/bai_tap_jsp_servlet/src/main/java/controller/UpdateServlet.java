package controller;


import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import entity.Admin;
import entity.Employee;
import entity.Position;
import utils.validatedData;
/**
 * Servlet implementation class EditController
 */
@WebServlet("/EditEmployee")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
		
		HttpSession session = request.getSession();
		
		// lay ten de hien thi ra man hinh
		Admin admin = (Admin)session.getAttribute("accountAdmin");
		request.setAttribute("nameAdmin", admin.getFullName()); // dung de hien thi fullName ra man hinh
		String id = request.getParameter("id");
		
		
		
		// chuyen id kieu string sang int
		int employeeId = 0;
		employeeId = Integer.parseInt(id);
		
		// khoi tao lop dao va query lop dao
		//Position position = new Position();
		EmployeeDao employee = new EmployeeDao();
		HashMap<String, String> hashMap = employee.queryEmployee(employeeId);
		
		if (hashMap != null) {
			
			// luu info session
			session.setAttribute("employeeId", employeeId);
			session.setAttribute("userName", hashMap.get("employeeName"));
			session.setAttribute("employeeAge", hashMap.get("employeeAge"));
			
			
			request.setAttribute("employeeInfo", hashMap);
			// forward den trang edit
			RequestDispatcher requestDispatcher;
			
	       requestDispatcher=request.getRequestDispatcher("/views/Edit.jsp");
	       requestDispatcher.forward(request, response);
		}else {
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Source is not found");
			return;
		}
		
	
	}
	
	
	
	 private java.sql.Date convertStringToDate(String employeeBirthDate) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date parsedDate = dateFormat.parse(employeeBirthDate);
	            return new java.sql.Date(parsedDate.getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
    private java.sql.Date convertStringToDateCompany(String employeeDateOfJoiningTheCompany) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = dateFormat.parse(employeeDateOfJoiningTheCompany);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // validated
    private HashMap<String, String> isValidate(int employeeSessionId, int employeeId, String userNameSession, String userName, 
    											String employeeSessionAge, String employeeAge, String dateOfJoiningCompany, 
    											String employeeBirthDate, String email, String position, String gender){
    	
    	
    	HashMap<String, String> hashMap = new HashMap<>();
    	boolean validateEmail = validatedData.isEmailRegex(email);
    	boolean validatePosition = validatedData.isPositionRegex(position);
    	boolean validateGender = validatedData.isGenderRegex(gender);
    	boolean validatejoiningCompany = validatedData.isDateRegex1(dateOfJoiningCompany);
    	boolean validateBirthDate = validatedData.isDateRegex1(employeeBirthDate);
    	if (employeeSessionId != employeeId) {
    		hashMap.put("errorId", "ID bị sai");
    	}else {
    		hashMap.put("errorId", "");
    	}
    	
    	if (!userNameSession.equals(userName)) {
    		hashMap.put("errorUserName", "Tên bị sai");
    	}else {
    		hashMap.put("errorUserName", "");
    	}
    	if (!employeeBirthDate.isEmpty()) {
    		if (!validateBirthDate) {
    			hashMap.put("errorEmployeeBirthDate", "Bạn cần nhập ngày giờ, ngày tháng năm phải theo format");
    			hashMap.put("errorEmployeeAge", "Tuổi bị sai");
    		}else {
    			int currentYear = LocalDate.now().getYear();
            	int yearBirhDay = Integer.parseInt(employeeBirthDate.substring(0, 4));
            	if (Integer.parseInt(employeeAge) != (currentYear - yearBirhDay) ) {
            		hashMap.put("errorEmployeeAge", "Tuổi bị sai");
            	}else {
            		hashMap.put("errorEmployeeAge", "");
            	}
    		}
    		
        	
    	}else {
    		hashMap.put("errorEmployeeBirthDate", "Bạn cần nhập ngày giờ, ngày tháng năm phải theo format");
    		hashMap.put("errorEmployeeAge", "Tuổi bị sai");
    	}
    	
    	
    	if (!dateOfJoiningCompany.isEmpty()) {
    		if (!validatejoiningCompany) {
    			hashMap.put("errorDate", "Ngày không được quá ngày hiện tại và không được để trống");
    		}else {
    			System.out.println(dateOfJoiningCompany);
            	// Định dạng chuỗi đầu vào
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                // Chuyển chuỗi thành đối tượng LocalDate
                LocalDate inputDate = LocalDate.parse(dateOfJoiningCompany, formatter);
                
                // Lấy ngày hiện tại
                LocalDate currentDate = LocalDate.now();
                System.out.println(currentDate);
            	if (inputDate.isAfter(currentDate)) {
            		hashMap.put("errorDate", "Ngày không được quá ngày hiện tại và không được để trống");
            	}else {
            		hashMap.put("errorDate", "");
            	}
    		}
    		
    	}else {
    		hashMap.put("errorDate", "Ngày không được quá ngày hiện tại và không được để trống");
    	}
    	
    	
    	
    	// sửa email
    	if (!validateEmail) {
    		hashMap.put("errorEmail", "Email bị sai format bạn không được để trống và chứa @ có dấu chấm vd '.com, .vn'");
    	}else {
    		hashMap.put("errorEmail", "");
    	}
    	
    	// kiem tra validate position
    	if (!validatePosition) {
    		hashMap.put("errorPosition", "Position không được để trống và chỉ có hai giá trị employee va boss");
    	}else {
    		hashMap.put("errorPosition", "");
    	}
    	
    	
    	// kiem ta validate gender
    	if (!validateGender) {
    		hashMap.put("errorGender", "Gender không được để trống và chỉ có ba giá trị male, female hoac orther");
    	}else {
    		hashMap.put("errorGender", "");
    	}
    	
    	// kiem tra neu dictionary co gia tri thi reutrn ve hashmap
    	for (String key : hashMap.keySet()) {
    		if (!hashMap.get(key).isEmpty()) {
        		return hashMap;
        	}
    	}
    	return null;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// lay parameters
		String employeeId = request.getParameter("Id");
		String employeeName = request.getParameter("nameEmployee"); // lay thong tin ten
		String employeeBirthDate = request.getParameter("birthDayEmployee"); // lay thong tin ngay sinh
		String employeeEmail = request.getParameter("emailEmployee"); // lay thong tin email
		String employeeAge = request.getParameter("ageEmployee"); // lay thong tin tuoi
		String employeeGender = request.getParameter("inlineRadioOptions"); // ly thong tin option cua gioi tinh
		String employeePosition = request.getParameter("position"); // lay thong tin cua vi tri
		String employeeAddress = request.getParameter("addressEmployee"); // lay thong tin dia chi
		String employeeDateOfJoiningTheCompany = request.getParameter("dateJoiningCompany"); // lay thong tin ngay vao nhan vien
		System.out.println(employeeDateOfJoiningTheCompany);
		// lay session employeeId
		int employeeSessionId = (int) session.getAttribute("employeeId");
		String employeeSessioName = (String) session.getAttribute("userName");
		String employeeSessionAge = (String) session.getAttribute("employeeAge");
		HashMap<String, String> hashMapValidated = isValidate(employeeSessionId, Integer.parseInt(employeeId), employeeSessioName, employeeName, 
																employeeSessionAge, employeeAge, employeeDateOfJoiningTheCompany, employeeBirthDate, employeeEmail,
																employeePosition, employeeGender);
		// chuyen doi kieu du lieu
		//java.sql.Date SqemployeeBirthDate = convertStringToDate(employeeBirthDate);
		//java.sql.Date sqemployeeDateOfJoiningTheCompany = convertStringToDate(employeeDateOfJoiningTheCompany);
		if (hashMapValidated == null) {
			int sqemployeeAge = 0;
			sqemployeeAge = Integer.parseInt(employeeAge);
			
			int sqemployeeId = 0;
			sqemployeeId = Integer.parseInt(employeeId);
			// khoi tao dao de update
			EmployeeDao  employee = new EmployeeDao();
			Position position = new Position();
			position.setPosition(employeePosition);
			
			try {
				// update
				employee.update(sqemployeeId, employeeName, employeeEmail, employeeBirthDate, sqemployeeAge, employeeGender,
								employeeAddress, position, employeeDateOfJoiningTheCompany);
				
				response.sendRedirect("home");
				System.out.println("Successfully!");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
			
			request.setAttribute("msgEmployeeId", hashMapValidated.get("errorId"));
			request.setAttribute("msgEmployeeName", hashMapValidated.get("errorUserName"));
			request.setAttribute("msgEmployeeAge", hashMapValidated.get("errorEmployeeAge"));
			request.setAttribute("msgEmployeeDateJoiningCompany", hashMapValidated.get("errorDate"));
			request.setAttribute("msgEmail", hashMapValidated.get("errorEmail"));
			request.setAttribute("msgPosition", hashMapValidated.get("errorPosition"));
			request.setAttribute("msgGender", hashMapValidated.get("errorGender"));
			request.setAttribute("msgErrorBithDate", hashMapValidated.get("errorEmployeeBirthDate"));
			// forward
			RequestDispatcher requestDispatcher;
	        requestDispatcher=request.getRequestDispatcher("/views/Edit.jsp");
	        requestDispatcher.forward(request, response);
		}	
	}

}
