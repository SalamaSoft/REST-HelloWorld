package com.salama.test.ws.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.w3c.tools.codec.Base64Encoder;

import MetoXML.Cast.BaseTypesMapping;

import com.salama.service.core.net.RequestWrapper;
import com.salama.service.core.net.ResponseWrapper;
import com.salama.service.core.net.http.ContentTypeHelper;
import com.salama.service.core.net.http.HttpResponseWrapper;
import com.salama.service.core.net.http.MultipartFile;
import com.salama.service.core.net.http.MultipartRequestWrapper;
import com.salama.test.ws.util.ServiceUtil;
import com.salama.util.easyimage.ImageUtil;
import com.salama.util.easyimage.ImageUtil.ImageType;

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
	
	public static void download1(RequestWrapper request, ResponseWrapper response, String fileId) {
		try {
			File file = getFilePathByFileId(request, fileId);
			
			//output to response
			ImageUtil.ImageType imgType = ImageUtil.getImageType(file);
			
			if(imgType == ImageType.PNG) {
				response.setContentType(ContentTypeHelper.ImagePng);
			} else if(imgType == ImageType.JPEG) {
				response.setContentType(ContentTypeHelper.ImageJpeg);
			} else if(imgType == ImageType.GIF) {
				response.setContentType(ContentTypeHelper.ImageGif);
			} else {
				response.setContentType(ContentTypeHelper.ImageJpeg);
			}

			response.setContentType(ContentTypeHelper.ApplicationOctetStream);
			response.setDownloadFileName("test中文", "utf-8");
			response.writeFile(file);
		} catch(Throwable e) {
			logger.error("", e);
		}
	}
	
	public static void download2(RequestWrapper request, ResponseWrapper response, String fileId) {
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
		String imgSaveToDirPath = request.getServletContext().getRealPath("/WEB-INF/" + UPLOAD_IMG_DIR);
		File imgSaveToDir = new File(imgSaveToDirPath);
		if(!imgSaveToDir.exists()) {
			imgSaveToDir.mkdirs();
		}
		
		File file = new File(imgSaveToDir, fileId);
		
		return file;
	}
}
