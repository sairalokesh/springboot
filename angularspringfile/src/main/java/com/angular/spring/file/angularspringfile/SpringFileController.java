package com.angular.spring.file.angularspringfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringFileController {
	
	@Autowired private ServletContext context;
	
	@GetMapping(value="/getImages")
	@CrossOrigin
	public ResponseEntity<List<String>> getImages() {
		List<String> images = new ArrayList<String>();
		String filePath = context.getRealPath("/images");
        File fileFolder = new File(filePath);
		if(fileFolder!=null){
			for (final File file : fileFolder.listFiles()) {
		        if (!file.isDirectory()) {
		        	String encodedBase64 = null;
		            try {
		            	String extension = FilenameUtils.getExtension(file.getName());
		                FileInputStream fileInputStreamReader = new FileInputStream(file);
		                byte[] bytes = new byte[(int)file.length()];
		                fileInputStreamReader.read(bytes);
		                encodedBase64 = Base64.getEncoder().encodeToString(bytes);
		                images.add("data:image/"+extension+";base64,"+encodedBase64);
		                fileInputStreamReader.close();
		            } catch (FileNotFoundException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}

}
