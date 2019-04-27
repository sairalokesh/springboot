package com.springboot.file.springbootfile.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.file.springbootfile.model.User;
import com.springboot.file.springbootfile.model.UserFiles;
import com.springboot.file.springbootfile.repository.UserFileRepository;
import com.springboot.file.springbootfile.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private UserFileRepository userFileRepository;
	@Autowired private UploadPathService uploadPathService;
	@Autowired private ServletContext context;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User save(User user) {
		user.setCreatedDate(new Date());
		User dbUser = userRepository.save(user);
		if(dbUser!=null && user.getFiles()!=null && user.getFiles().size()>0) {
			for(MultipartFile file : user.getFiles()) {
				if(file!=null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
					File storeFile = uploadPathService.getFilesPath(modifiedFileName, "images");
					if(storeFile!=null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					UserFiles files = new UserFiles();
					files.setFileName(fileName);
					files.setFileExtension(FilenameUtils.getExtension(fileName));
					files.setModifiedFileName(modifiedFileName);
					files.setUser(dbUser);
					userFileRepository.save(files);
				}
			}
			
		}
		return dbUser;
	}

	@Override
	public User findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	@Override
	public List<UserFiles> findFilesByUserId(Long userId) {
		return userFileRepository.findFilesByUserId(userId);
	}
	

	@Override
	public User update(User user) {
		user.setUpdatedDate(new Date());
		User dbUser = userRepository.save(user);
		if(user!=null && user.getRemoveImages()!=null && user.getRemoveImages().size()>0) {
			userFileRepository.deleteFilesByUserIdAndImageName(user.getId(), user.getRemoveImages());
			for(String file : user.getRemoveImages()) {
				File dbFile = new File(context.getRealPath("/images/"+File.separator+file));
				if(dbFile.exists()) {
					dbFile.delete();
				}
			}
		}
		
		if(dbUser!=null && user.getFiles()!=null && user.getFiles().size()>0) {
			for(MultipartFile file : user.getFiles()) {
				if(file!=null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
					File storeFile = uploadPathService.getFilesPath(modifiedFileName, "images");
					if(storeFile!=null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					UserFiles files = new UserFiles();
					files.setFileName(fileName);
					files.setFileExtension(FilenameUtils.getExtension(fileName));
					files.setModifiedFileName(modifiedFileName);
					files.setUser(dbUser);
					userFileRepository.save(files);
				}
			}
			
		}
		
		return dbUser;
	}

	@Override
	public void deleteuser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void deleteFilesByUserId(Long userId) {
		List<UserFiles> files = userFileRepository.findFilesByUserId(userId);
		if(files!=null && files.size()>0) {
			for(UserFiles dbFile : files) {
				File dbImageFile = new File(context.getRealPath("/images/"+File.separator+dbFile.getModifiedFileName()));
				if(dbImageFile.exists()) {
					dbImageFile.delete();
				}
			}
			userFileRepository.deleteFilesByUserId(userId);
		}
	}
}
