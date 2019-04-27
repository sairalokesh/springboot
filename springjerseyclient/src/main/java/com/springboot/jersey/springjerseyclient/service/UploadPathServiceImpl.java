package com.springboot.jersey.springjerseyclient.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadPathServiceImpl implements UploadPathService {

	@Autowired protected ServletContext context;
	
	@Override
	public File getFilesPath(String fileName,String path) {
		boolean exists = (new File(context.getRealPath("/"+path+"/"))).exists();
		if (!exists) {
			new File(context.getRealPath("/"+path+"/")).mkdir();
		}
		String modifiedFilePath =context.getRealPath("/"+path+"/"+File.separator+fileName);
		File file = new File(modifiedFilePath);
		return file;
	}

}
