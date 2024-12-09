package com.northstar.book_network_shop.file;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.io.File.separator;

import com.northstar.book_network_shop.book.Book;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileStorageService {

	@Value("${application.file.upload.photos-output-path}")
	
	private String fileUploadPath;
	
	public String saveFile(
			    @Nonnull MultipartFile sourceFile, 
			    @Nonnull Book book, 
			    @Nonnull Integer userId)
	{
		final String fileUploadSubPath = "users" + File.separator + userId;
		return uploadFile(sourceFile,fileUploadSubPath);
	}

	private String uploadFile(
			@Nonnull MultipartFile sourceFile, 
			@Nonnull String fileUploadSubPath) {
		final String finalUploadPath = fileUploadPath + separator +fileUploadSubPath;
		File targetFolder = new File(fileUploadPath);
		if(!targetFolder.exists()) {
			boolean folderCreated = targetFolder.mkdirs();
			if(!folderCreated) {
				log.warn("Failed to create the target folder");
				return null;
			}
		}
		
		final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
		return null;
	}

	private String getFileExtension(String originalFilename) {
		
		return null;
	}

}
