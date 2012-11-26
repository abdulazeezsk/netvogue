package org.netvogue.server.aws.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.netvogue.server.aws.common.Constants;
import org.netvogue.server.common.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.Upload;

public class UploadManager {
	
	@Autowired
	private FileManager transferManager;
	
	public static final String FILE_ID = "fileId";
	public static final String FILE_NAME = "fileName";
	public static final String QUERY_STRING = "queryString";
	public static final String UPLOAD_REFERENCE = "uploadReference";
	

	public UploadManager() {
	}

	public List<Map<String, Object>> processUpload(LinkedList<MultipartFile> files,ImageType imageType, String username) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for ( MultipartFile file : files ) {
			list.add(processUpload(file, imageType, username));
		}
		return list;
	}
	
	public Map<String, Object> processUpload(MultipartFile file,ImageType imageType, String username) {
        Map<String, Object> map = null;
		try {
			String uniqueId = UUID.randomUUID().toString();
	
			ObjectMetadata metaData = new ObjectMetadata();
	        metaData.addUserMetadata("fileName", file.getOriginalFilename());
	        metaData.setContentType(file.getContentType());
	        metaData.setContentLength(file.getSize());
	        
	        String bucketName = Constants.bucketname;
	        System.out.println("Bucket Name: " + bucketName);
//			ProgressListener progressListener = new ProgressListener() {
//          public void progressChanged(ProgressEvent progressEvent) {
//              
//              switch (progressEvent.getEventCode()) {
//              case ProgressEvent.COMPLETED_EVENT_CODE:
//                  
//                  break;
//              case ProgressEvent.FAILED_EVENT_CODE:
//                  
//                  break;
//              }
//          }
//      };

	        
	        Upload upload = transferManager.upload(bucketName, uniqueId, file.getBytes(), metaData, imageType, username);
	        String key = bucketName+"/"+ username + "/" + imageType.getKey();
	        String RESTlink = transferManager.getQueryString(key, uniqueId);
	        
//	        String RESTlink = transferManager.getQueryString(bucketName+"/"+ imageType.getKey() , uniqueId + "-" + Size.GThumb.toString());
	        
	        System.out.println(RESTlink);
	        map = new HashMap<String, Object>();
	        map.put(FILE_ID, uniqueId);
	        map.put(FILE_NAME, file.getOriginalFilename());
	        map.put(QUERY_STRING, RESTlink);
	        map.put(UPLOAD_REFERENCE, upload);
	        
		} catch (Exception e) {
			System.out.println("There was an error in transfer manager while uploading" +  " - " + e.toString());
			e.printStackTrace();
		}
        
        return map;
	}
	
	public String getQueryString(String key, ImageType imageType, String username) {
		String RESTLink = null;
        String bucketName = Constants.bucketname;
		RESTLink = transferManager.getQueryString(bucketName + "/" + username + "/" + imageType.getKey(), key);
		return RESTLink;
	}
	
	public String getQueryString(String key, ImageType imageType, Size imageSize, String username) {
		String RESTLink = null;
		String bucketName = Constants.bucketname;
		RESTLink = transferManager.getQueryString(bucketName+ "/" + username + "/" + imageType.getKey(), 
													key + "-" + imageSize.toString());
		return RESTLink;
	}
	
	public ResultStatus deletePhotosById(String photoId, ImageType imageType,
			String userName) {
		String bucketName = Constants.bucketname;
		return transferManager.deletePhotosById(bucketName,
				userName + "/" + imageType.getKey() + "/" + photoId);
	}
	
	public ResultStatus deletePhotosList(List<String> photoIdsList, ImageType imageType,
			String userName) {
		String bucketName = Constants.bucketname;
		return transferManager.deletePhotosList(photoIdsList, bucketName,
				userName + "/" + imageType.getKey() + "/");
	}
	
}
