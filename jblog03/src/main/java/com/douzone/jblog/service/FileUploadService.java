package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.CustomFileUploadException;
import com.douzone.jblog.vo.BlogVo;

@Service
public class FileUploadService {
	// private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	private static final String SAVE_PATH = "/upload-jblog";
	private static final String URL_BASE = "/assets/upload";

	@Autowired
	private BlogService blogService;
	
	public String restore(MultipartFile file,String id) {
		String url = null;

		try {
			if(file.isEmpty()) {
				BlogVo vo = new BlogVo();
				vo = blogService.getBlog(id);
				return url=vo.getLogo();
			}

			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
			String saveFilename = generateSaveFilename(extName);
			long fileSize = file.getSize();

			// LOGGER.info("############ " + originFilename);
			// LOGGER.info("############ " + fileSize);
			// LOGGER.info("############ " + saveFilename);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();

			url = URL_BASE + "/" + saveFilename;
		} catch (IOException e) {
			throw new CustomFileUploadException("file upload error:" + e);
		}

		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();

		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}
}
