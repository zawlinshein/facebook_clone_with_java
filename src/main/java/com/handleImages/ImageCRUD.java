package com.handleImages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.servlet.http.Part;

public class ImageCRUD {
//	photo save
	public static void savephoto(String imageName, Part file, String path) throws Exception {
		
		try {
			FileOutputStream fos = new FileOutputStream(path + imageName);
			InputStream is = file.getInputStream();
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	public static void deletePhoto(String imagePath, String imageName) throws Exception {
		try {
			File image = new File(imagePath + imageName);
			if(image.exists()) {
				if(!imageName.equals("dummy.png")) {
					if(image.delete()) {
						System.out.println("image deleted successfully!");
					} else {
						System.out.println("image fail to delete!");
					}
				} else {
						System.out.println("image name is dummy.png");
				}
			} else {
				System.out.println("image does not exist!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	public static String getHashedImageName(String imageName) throws Exception {
		String fileExtension = imageName.substring(imageName.indexOf("."));
		String hashValue = generateHashValue(imageName);
		String hashedImageName = hashValue + fileExtension;
		return hashedImageName;
	}
	
	private static String generateHashValue(String input) throws Exception {
	    String uniqueInput = input + System.currentTimeMillis(); // Concatenate input with current timestamp
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    byte[] hashBytes = md.digest(uniqueInput.getBytes("UTF-8"));

	    StringBuilder hashValue = new StringBuilder();
	    for (byte b : hashBytes) {
	        hashValue.append(String.format("%02x", b));
	    }

	    return hashValue.toString();
	}
}
