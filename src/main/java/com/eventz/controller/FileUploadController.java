package com.eventz.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://kittbook.com")
//Max uploaded file size (here it is 20 MB)
@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
public class FileUploadController {
	
	private static final String serverPath = "/home/kittbook/tomcat/webapps/kittbook.com/images/";
	
	@RequestMapping(value = "/upload")
	public String uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		String dateString = dateFormat.format(new Date());
		
		int randomNumber = 100000 + new Random().nextInt(900000);
		
		String fileName = dateString + "_" + randomNumber;
		String path = serverPath + fileName;

		// create the output file on the server.
		File outputFile = new File(path);
		try {
			uploadedFileRef.transferTo(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}