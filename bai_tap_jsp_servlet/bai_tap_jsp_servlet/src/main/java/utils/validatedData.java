package utils;

import java.util.regex.Pattern;


public class validatedData {
	
	
	// format regex
	private static final String userNameRegex = "^[^\\d]{1,20}$";
	private static final String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
	private static final String genderRegex = "^(male|female|orther)$";
	private static final String positionRegex = "^(boss|employee)$";
	private static final String dateRegex = "^(([0-2][0-9]|3[01])/0[1-9]|1[0-2])/[0-9]{4}$";
	private static final String regexDate = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
		
	// kiem tra format userName xem dung hay sai
	public static boolean isUserNameRegex(String userName) {
		return Pattern.matches(userNameRegex, userName);
	}
	
	// Kiem tra format emaile xem dung hay sai
	public static boolean isEmailRegex(String email) {
		return Pattern.matches(emailRegex, email);
	}
	
	// kiem tra format gender xem dung hay sai
	public static boolean isGenderRegex(String gender) {
		return Pattern.matches(genderRegex, gender);
	}
	
	
	// kiem tra format position xem dung hay sai
	public static boolean isPositionRegex(String position) {
		return Pattern.matches(positionRegex, position);
	}
	
	
	// kiem tra format date xem dung hay sai
	public static boolean isDateRegex(String date) {
		return Pattern.matches(dateRegex, date);
	}
	
	public static boolean isDateRegex1(String date) {
		return Pattern.matches(regexDate, date);
	}
}
