package home;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.parser.JSONParser;

public class Encrypt {
	
	
	public static void processCityForPolyline(String city) {
		///
				JSONParser parser = new JSONParser();
				
				String key = "HereItIsHidenKey"; // 128 bit key
		        String initVector = "HereItIsRandomIV"; // 16 bytes IV
//				encrypt(key, initVector, "Hello World");
				
				try {
		            String pathPrefix = "D:\\WSS\\bus-tracker-lines\\src\\main\\java\\home\\isu\\";
					BufferedWriter writer = new BufferedWriter(new FileWriter(pathPrefix+city+"\\allLines.cr"));
					Object obj = parser.parse(new FileReader(
		                    pathPrefix+city+"\\allLinesMin.json"));
					
					writer.write( new String(Encrypt.encrypt(key, initVector, obj.toString()).getBytes("UTF-8")));
		            
		            writer.close();
					
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
	}
	
	 public static String encrypt(String key, String initVector, String value) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());
	            System.out.println("encrypted string: "
	                    + Base64.getEncoder().encodeToString(encrypted));

	            return Base64.getEncoder().encodeToString(encrypted);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return null;
	    }

	    public static String decrypt(String key, String initVector, String encrypted) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

	            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

	            return new String(original);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return null;
	    }

}
