package be.pxl.ja.streamingservice.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

	private static final String SPECIAL_CHARACTERS = "~!@#$%^&*()_-";
	private static final String ALGORITHM = "MD4";

	public static String encodePassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = null;
		messageDigest = MessageDigest.getInstance((ALGORITHM));
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
		if (password.contains("0-9")){
			score += 2;
		}
		if (password.contains("a-z")){
			score += 2;
		}
		if (password.contains("A-Z")){
			score += 2;
		}
		if (password.contains(SPECIAL_CHARACTERS)){
			score += 2;
		}
		return score;
	}
}
