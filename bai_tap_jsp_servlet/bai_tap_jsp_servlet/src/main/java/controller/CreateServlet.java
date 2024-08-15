package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import entity.Admin;
import entity.Employee;
import entity.Position;
import utils.validatedData;

/**
 * Servlet implementation class employeeController
 */
@WebServlet("/employee")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
				
		// lay ten de hien thi ra man hinh
		Admin admin = (Admin)session.getAttribute("accountAdmin");
		request.setAttribute("nameAdmin", admin.getFullName()); // dung de hien thi fullName ra man hinh
				
		RequestDispatcher requestDispatcher;
		
        requestDispatcher=request.getRequestDispatcher("/views/Add.jsp");
        requestDispatcher.forward(request, response);
	        
	        

	}

	
	
	private java.sql.Date convertStringToDate(String employeeBirthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = dateFormat.parse(employeeBirthDate);
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    // tao ra cac method co the thong bao neu sai thì gui cac thong bao loi
    private HashMap<String, String> isValidate(String UserName, String email, String gender, String position, String DateJoiningCompany, String employeeAge, String employeeBirthDate) {
    	
    	if (employeeBirthDate.equals(null)) {
    		employeeBirthDate = "";
    	}
    	
    	//validatedData validate = new validatedData();
    	HashMap<String, String> hashMap = new HashMap<>();
    	// lay gia tri dung sai cua validte
    	boolean validateUseName = validatedData.isUserNameRegex(UserName);
    	boolean validateEmail = validatedData.isEmailRegex(email);
    	boolean validateGender = validatedData.isGenderRegex(gender);
    	boolean validatePosition = validatedData.isPositionRegex(position);
    	boolean validateDateJoiningCompany = validatedData.isDateRegex(DateJoiningCompany);
    	boolean validateBirthDate = validatedData.isDateRegex(employeeBirthDate);
    	// su dung dieu kien if de dua ra thong bao
    	if (!validateUseName) {
    		hashMap.put("errorUserName", "Tên bị sai format bạn không được để trống và không chứa số");
    	}else {
    		hashMap.put("errorUserName", "");
    	}
    	
    	if (!validateEmail) {
    		hashMap.put("errorEmail", "Email bị sai format bạn không đượcc để trống và chứa @ có dấu chấm vd '.com, .vn'");
    	}else {
    		hashMap.put("errorEmail", "");
    	}
    	
    	if (!validateGender) {
    		hashMap.put("errorGender", "Gender không được để trống và chỉ có ba giá trị male, female hoac orther");
    	}else {
    		hashMap.put("errorGender", "");
    	}
    	
    	if (!validatePosition) {
    		hashMap.put("errorPosition", "Position không được để trống và chỉ có hai giá trị employee va boss");
    	}else {
    		hashMap.put("errorPosition", "");
    	}
    	
    	if (!validateDateJoiningCompany) {
    		hashMap.put("errorDateJoiningCompany", "Ngày không được quá ngày hiện tại và không được để trống");
    	}else {
    		hashMap.put("errorDateJoiningCompany", "");
    	}
    	
    	if (validateBirthDate) {
    		int currentYear = LocalDate.now().getYear();
        	int yearBirhDay = Integer.parseInt(employeeBirthDate.substring(6, 10));
        	if (Integer.parseInt(employeeAge) != (currentYear - yearBirhDay) ) {
        		hashMap.put("errorEmployeeAge", "Tuổi bị sai");
        	}else {
        		hashMap.put("errorEmployeeAge", "");
        	}
        	hashMap.put("errorBirthDate", "");
        	
    	}else {
    		hashMap.put("errorBirthDate", "Ngày tháng năm sinh không được trống");
    		hashMap.put("errorEmployeeAge", "Tuổi bị sai");
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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		// lay thong tin tu form
		String employeeName = request.getParameter("nameEmployee"); // lay thong tin ten
		String employeeBirthDate = request.getParameter("birthDayEmployee"); // lay thong tin ngay sinh
		String employeeEmail = request.getParameter("emailEmployee"); // lay thong tin email
		String employeeAge = request.getParameter("ageEmployee"); // lay thong tin tuoi
		String employeeGender = request.getParameter("inlineRadioOptions"); // ly thong tin option cua gioi tinh
		String employeePosition = request.getParameter("position"); // lay thong tin cua vi tri
		String employeeAddress = request.getParameter("addressEmployee"); // lay thong tin dia chi
		String employeeDateOfJoiningTheCompany = request.getParameter("dateJoiningCompany"); // lay thong tin ngay vao nhan vien
		
		// xu ly validated o server
		HashMap<String, String> hashMap = isValidate(employeeName, employeeEmail, employeeGender, employeePosition, employeeDateOfJoiningTheCompany, employeeAge, employeeBirthDate);
		if (hashMap != null) {
			request.setAttribute("msgUserName", hashMap.get("errorUserName"));
			request.setAttribute("msgEmail", hashMap.get("errorEmail"));
			request.setAttribute("msgGender", hashMap.get("errorGender"));
			request.setAttribute("msgPosition", hashMap.get("errorPosition"));
			request.setAttribute("msgDateJoinCompany", hashMap.get("errorDateJoiningCompany"));
			request.setAttribute("msgEmployeeAge", hashMap.get("errorEmployeeAge"));
			request.setAttribute("msgEmployeeBirthDate", hashMap.get("errorBirthDate"));
			// forward
			RequestDispatcher requestDispatcher;
	        requestDispatcher=request.getRequestDispatcher("/views/Add.jsp");
	        requestDispatcher.forward(request, response);
		}else {
			// convert string to date
			java.sql.Date SqemployeeBirthDate = convertStringToDate(employeeBirthDate);
			java.sql.Date sqemployeeDateOfJoiningTheCompany = convertStringToDate(employeeDateOfJoiningTheCompany);
			
			int sqemployeeAge = 0;
			sqemployeeAge = Integer.parseInt(employeeAge);
			
			
			// tao cac object
			EmployeeDao insertEmployee = new EmployeeDao();
			Admin admin = new Admin();
			Position position = new Position();
			position.setPosition(employeePosition);
					
					
			// tao mot object employee
			Employee employee = new Employee(employeeName, employeeEmail, 
											SqemployeeBirthDate, sqemployeeAge,
											employeeGender, employeeAddress, 
											position, sqemployeeDateOfJoiningTheCompany, admin);
			// insert		
			insertEmployee.insert(employee.getEmployeeName(), 
								employee.getEmployeeEmail(),
								employee.getEmployeeBirthDate(),
								employee.getEmployeeAge(),
								employee.getEmployeeGender(),
								employee.getEmployeeAddress(),
								employee.getPosition(),
								employee.getDateOfJoiningTheCompany(),
								employee.getAdmin());

			
			response.sendRedirect("home?page=1");
			System.out.println("Successfully!");
		}
	}

}
