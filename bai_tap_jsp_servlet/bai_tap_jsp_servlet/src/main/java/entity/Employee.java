package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeeEmail;
	private Date employeeBirthDate;
	private int employeeAge;
	private String employeeGender;
	private String employeeAddress;
	private Position position;
	private Date dateOfJoiningTheCompany;
	private Admin Admin;
	

	public Employee(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public Employee(String employeeName, String employeeEmail, Date employeeBirthDate, int employeeAge,
			String employeeGender, String employeeAddress, Position position, Date dateOfJoiningTheCompany, Admin adminId) {
		super();
		
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeBirthDate = employeeBirthDate;
		this.employeeAge = employeeAge;
		this.employeeGender = employeeGender;
		this.employeeAddress = employeeAddress;
		this.position = position;
		this.dateOfJoiningTheCompany = dateOfJoiningTheCompany;
		this.Admin = adminId;
	}
	
	
	public Employee(int employeeId, String employeeName, String employeeEmail, Date employeeBirthDate, int employeeAge,
			String employeeGender, String employeeAddress, Position position, Date dateOfJoiningTheCompany) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeBirthDate = employeeBirthDate;
		this.employeeAge = employeeAge;
		this.employeeGender = employeeGender;
		this.employeeAddress = employeeAddress;
		this.position = position;
		this.dateOfJoiningTheCompany = dateOfJoiningTheCompany;
		
	}
	


	public Admin getAdmin() {
		return this.Admin;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
		
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Date getEmployeeBirthDate() {
		return employeeBirthDate;
	}

	public void setEmployeeBirthDate(Date employeeBirthDate) {
		this.employeeBirthDate = employeeBirthDate;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getDateOfJoiningTheCompany() {
        
		return dateOfJoiningTheCompany;
	}

	public void setDateOfJoiningTheCompany(Date dateOfJoiningTheCompany) {
		this.dateOfJoiningTheCompany = dateOfJoiningTheCompany;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeBirthDate=" + employeeBirthDate + ", employeeAge=" + employeeAge
				+ ", employeeGender=" + employeeGender + ", employeeAddress=" + employeeAddress + ", positionId="
				+ position + ", DateOfJoiningTheCompany=" + dateOfJoiningTheCompany + ", AdminId=" + Admin + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return employeeId == other.employeeId;
	}
	
	
	
}
