package com.salama.test.ws.service;

import com.salama.service.core.net.RequestWrapper;
import com.salama.service.core.net.ResponseWrapper;
import com.salama.service.core.net.http.ContentTypeHelper;
import com.salama.service.core.net.http.MultipartFile;
import com.salama.service.core.net.http.MultipartRequestWrapper;
import com.salama.test.ws.util.ServiceUtil;
import org.apache.log4j.Logger;

import java.io.File;

public class UploadAndDownloadService {
	private final static Logger logger = Logger.getLogger(UploadAndDownloadService.class);
	
	private final static String UPLOAD_IMG_DIR = "upload_img";
	
	public static String upload1(RequestWrapper request) {
		try {
			logger.error(null);
			
			MultipartRequestWrapper multipartRequest = (MultipartRequestWrapper) request;
			
			MultipartFile multiFile1 = multipartRequest.getFile("file1");
			
			logger.debug("upload1() originalFileName:" + multiFile1.getOriginalFilename()
					+ " fileSize(byte):" + multiFile1.getSize());
			
			String fileId = ServiceUtil.newDataID();
			File fileSaveTo = getFilePathByFileId(request, fileId);
			multiFile1.transferTo(fileSaveTo);
			
			return fileId;
		} catch(Throwable e) {
			logger.error("", e);
			return null;
		}
	}
	
	public static void downloadAndShow(RequestWrapper request, ResponseWrapper response, String fileId) {
		try {
			File file = getFilePathByFileId(request, fileId);
			
			//output to response
			response.setDownloadFileName(request, response, fileId);
			response.writeFile(file);
		} catch(Throwable e) {
			logger.error("", e);
		}
	}
	
	public static void downloadAndSave(RequestWrapper request, ResponseWrapper response, String fileId) {
		try {
			File file = getFilePathByFileId(request, fileId);
			
			response.setContentType(ContentTypeHelper.ApplicationOctetStream);
			response.setDownloadFileName(request, response, fileId);
			
			response.writeFile(file);
		} catch(Throwable e) {
			logger.error("", e);
		}
	}
	
	private static File getFilePathByFileId(RequestWrapper request, String fileId) {
		//dir:WEB-INF/upload_img/
		String imgSaveToDirPath = request.getServletContext().getRealPath("/WEB-INF/temp/" + UPLOAD_IMG_DIR);
		File imgSaveToDir = new File(imgSaveToDirPath);
		if(!imgSaveToDir.exists()) {
			imgSaveToDir.mkdirs();
		}
		
		File file = new File(imgSaveToDir, fileId);
		
		return file;
	}
}
