package com.wlopera.employments.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utilities {

	public static String saveFile(MultipartFile multiPart, String path) {
		String filename = multiPart.getOriginalFilename();
		try {
			File file = new File(path + filename);
			System.out.println("Archivo a descargar: " + file.getAbsolutePath());

			multiPart.transferTo(file);
			return filename;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
