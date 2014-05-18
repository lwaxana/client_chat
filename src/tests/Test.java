package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		
		
		//String regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
		//String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$%^&+=]){0,}(?=\\S+$).{8,25}$";
		//String regex = "^[a-zA-Z0-9]{2,}[a-zA-Z0-9@$-_]{0,}$";
		String regex = "^[a-zA-Z]{1,}([a-zA-Z0-9@\\-_]){0,}";
		
		String str = "aaaa";
		
		
	    
		
		
		
		System.out.println(str.length());
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
		
		
		
		
		
	}

}
