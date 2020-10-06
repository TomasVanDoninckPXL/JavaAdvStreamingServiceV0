package be.pxl.ja.streamingservice.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

	private static final String SPECIAL_CHARACTERS = "~!@#$%^&*()_-";
	private static final String ALGORITHM = "MD4";

	public static String encodePassword(String password){
		MessageDigest messageDigest = null;
		try{
			messageDigest = MessageDigest.getInstance((ALGORITHM));
		}catch (NoSuchAlgorithmException e){
			return null;
		}
		messageDigest.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}

	public static boolean isValid(String providedPassword, String securedPassword) throws NoSuchAlgorithmException{
		return securedPassword.equals(encodePassword(providedPassword));
	}

	public static int calculateStrength(String password) {
		int score = 0;
		if (password.length() < 6){
			return 0;
		}else if (password.length() < 11){
			score += 1;
		}else{
			score += 2;
		}
		char[] pwChars = password.toCharArray();
		boolean upperCase = false, lowerCase = false, digit = false, specialChar = false;
		for (char a : pwChars){
			if (!digit && Character.isDigit(a)){
				digit = true;
			}
			else if (!upperCase && Character.isUpperCase(a)){
				upperCase = true;
			}
			else if (!lowerCase && Character.isLowerCase(a)){
				lowerCase = true;
			}
			else if (!specialChar && SPECIAL_CHARACTERS.indexOf(a) >= 0){
				specialChar = true;
			}
		}
		if (digit){
			score += 2;
		}
		if (lowerCase){
			score += 2;
		}
		if (upperCase){
			score += 2;
		}
		if (specialChar){
			score += 2;
		}
		return score;
	}
}
