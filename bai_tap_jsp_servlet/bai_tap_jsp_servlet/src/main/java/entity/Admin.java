package entity;

public class Admin {
	private int AdminId = 1;
	private String fullName;
	private String email;
	private String passWord;
	
	public Admin() {
		
	}
	
	public Admin(String fullName, String email, String passWord) {
		super();
		
		
		this.fullName = fullName;
		this.email = email;
		this.passWord = passWord;
	}
	
	public int getAdminId() {
		return AdminId;
	}
	
	public Admin(String email, String passWord) {
		this.email = email;
		this.passWord = passWord;
	}
	public int getId() {
		return AdminId;
	}
	public void setId(int id) {
		this.AdminId = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Admin [AdminId=" + AdminId + ", fullName=" + fullName + ", email=" + email + ", passWord=" + passWord
				+ "]";
	}
	
	
	
}
