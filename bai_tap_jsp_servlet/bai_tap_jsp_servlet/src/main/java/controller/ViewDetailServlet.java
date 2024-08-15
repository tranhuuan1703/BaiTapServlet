package controller;

import java.io.IOException;
import java.util.HashMap;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import entity.Employee;
import com.google.gson.*;
/**
 * Servlet implementation class findByIdController
 */
@WebServlet("/viewDetail")
public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String employeeId = request.getParameter("employyId");
		int employeeIdInt = Integer.parseInt(employeeId);
		System.out.println(employeeIdInt);
		EmployeeDao employee = new EmployeeDao();
		HashMap<String, String> hashMap = employee.queryEmployee(employeeIdInt);
		
		
		Gson gson = new Gson();
		
		OutputStream outputStream = response.getOutputStream();
		
		outputStream.write(gson.toJson(hashMap).getBytes());
		outputStream.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
