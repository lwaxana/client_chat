package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsValidation {

	/**
	 * 
	 * @param str
	 * @return Validation prénom - Accepte caractères alpha ( min 2 ) sans caractères spéciaux, ni tirets ( <= 50 caracteres )
	 */
	public static boolean checkPrenom(String str){
		String prenom = str.trim();
		if(prenom.length() <= 50 ){
			String regex = "(^\\p{Alpha}{0,2})(\\p{Alpha}{2,48}$)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			if ( matcher.matches() ){
				return true;
			}
			else{
				return false;
			}			
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param str
	 * @return Validation nom - Accepte caracteres spéciaux alpha ( min 2 ) sans caractres spéciaux ni tirets <= 100 caracteres
	 */
	public static boolean checkNom(String str){
		String nom = str.trim();
		if(nom.length() <= 100 ){
			String regex = "(^\\p{Alpha}{0,2})(\\p{Alpha}{2,98}$)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			if ( matcher.matches() ){
				return true;
			}
			else{
				return false;
			}			
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param str
	 * @return Mot de passe - 8 à 25 caractres - Min 1 majuscule, 1 minuscule, 1 chiffre accepte caracteres spéciaux pas d'espace
	 */
	public static boolean checkPwd(String str){
		String regex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([@#$%^&+=]){0,}(?=\\S+$).{8,25}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if ( matcher.matches()){
			return true;
		}
		else{
			return false;
		}
		
		
		
	}
	
	/**
	 * 
	 * @param str
	 * @return Accepte nick ( 4 - 20 caractres ) commence par lettre. Accepte A-Z a-z 0-9 @-_
	 */
	public static boolean checkNick(String str){
		String nick = str.trim();
		if ( !nick.equals("")){
			if ( nick.length() >= 4 && nick.length() <= 20){
				String regex = "^[a-zA-Z]{1,}([a-zA-Z0-9@\\-_]){0,}";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(str);
				if ( matcher.matches()){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public static boolean checkRue(String str){
		return true;
	}
	
	public static boolean checkNumero(String str){
		return true;
	}
	
	public static boolean checkVille(String str){
		return true;
	}
	
	public static boolean checkZip(String str){
		return true;
	}
	
	/**
	 * 
	 * @param str
	 * @return Accepte email xxxx@xxxx.xx(x) ou xxx.xxxx@xxxx.xx(x)  < = 100
	 */
	public static boolean checkUserName(String str){
		String nom = str.trim();
		if(nom.length() <= 100 ){
			String regex = "^[a-zA-Z0-9]{1,}(\\.){0,1}[a-zA-Z0-9]{1,}(\\@){1}([a-zA-Z0-9]){1,}(\\.){1}([a-zA-Z]{2,3}$)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			if ( matcher.matches() ){
				return true;
			}
			else{
				return false;
			}			
		}
		else{
			return false;
		}
		
		
		
	}
	
	public static boolean checkMessage(String str){
		return ((str.trim()).equals("") ? false : true) && ((str.length() <= 250)? true : false );
	}
}

