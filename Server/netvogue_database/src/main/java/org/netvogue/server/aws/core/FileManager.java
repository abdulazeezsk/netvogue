package org.netvogue.server.aws.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class FileManager extends TransferManager {
	
	private FileManager(AWSCredentials credentials) {
		super(credentials);
	}

	private static String accesskey = "AKIAJPHEXCL7WIP7ITSQ";
	private static FileManager transferManager;
	private static SecretKeySpec signingKey = null;
	private static Mac mac = null;
	private static String secureKey = "yWbD67M+VidV+4G/6oMdfiSzg0ouVo2kD58+9yqV";
	
	public static FileManager getSharedInstance() throws Exception {
		
		if ( transferManager == null ) {
			//				PropertiesCredentials credentials = new PropertiesCredentials(FileManager.class.getResourceAsStream("AwsCredentials.properties"));
			AWSCredentials credentials = new BasicAWSCredentials(accesskey,secureKey);
			transferManager = new FileManager(credentials);
			setKey(secureKey);
			
		}
		
		return transferManager;
	}
	
	// This method converts AWSSecretKey into crypto instance.
	public static void setKey(String AWSSecretKey) throws Exception {
		  mac = Mac.getInstance("HmacSHA1");
		  byte[] keyBytes = AWSSecretKey.getBytes("UTF8");
		  signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		  mac.init(signingKey);
	}

	// This method creates S3 signature for a given String.
	private String sign(String data) throws Exception
	{
		// Signed String must be BASE64 encoded.
		byte[] signBytes = mac.doFinal(data.getBytes("UTF8"));
		String signBytesString = new String(signBytes);
		
		//byte[] signature = org.springframework.security.crypto.codec.Base64.encode(signBytes);
		byte[] signature = Base64.encodeBase64(signBytes);
		String sig = new String(signature);
		return sig;
	}
	
	public String getQueryString(String bucket,String key) {
        QueryStringAuthGenerator generator =
                new QueryStringAuthGenerator(accesskey, secureKey, true, Utils.DEFAULT_HOST, CallingFormat.getPathCallingFormat());

        return generator.get(bucket, key, null);
	}


	public String signForGet(String link) {	
		String signature = null;
		
		// S3 timestamp pattern.
		String fmt = "EEE, dd MMM yyyy HH:mm:ss ";
		SimpleDateFormat df = new SimpleDateFormat(fmt, Locale.US);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		  
		String method = "GET";
		String contentMD5 = "";
		String contentType = "";
		//String date = df.format(new Date()) + "GMT";
		Date date = new Date();
		Long time = date.getTime();
		time += 20 * 60 * 1000;
		time = time/1000;
		String expires = time.toString();

		// Generate signature
		StringBuffer buf = new StringBuffer();
		buf.append(method).append("\n");
		buf.append(contentMD5).append("\n");
		buf.append(contentType).append("\n");
		buf.append(expires).append("\n");
		buf.append(link);
		try {
			signature = sign(buf.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer reqAuthenticationString = new StringBuffer();
		//reqAuthenticationString.append(method).append(" ");
		reqAuthenticationString.append(link).append("?");
		reqAuthenticationString.append("AWSAccessKeyId=").append(accesskey);
		reqAuthenticationString.append("&Signature=").append(signature);
		reqAuthenticationString.append("&Expires=").append(expires);
		return reqAuthenticationString.toString();
	}
	
	public ObjectMetadata getMetaData(MultipartFile file) {
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.addUserMetadata("fileName", file.getOriginalFilename());
        metaData.setContentType(file.getContentType());
        metaData.setContentLength(file.getSize());
        return metaData;
	}
	
	public Upload upload(String bucketName,String fileName,byte[] buffer, ObjectMetadata metadata,
								ImageType imageType) {
		
		String imageKey = imageType.getKey() + "/" + fileName;
		Size[] sizes = imageType.getSizes();
		
		
		try {
			for ( Size size : sizes ) {
				Upload temp = upload(bucketName,imageKey+ "-" + size.toString(), buffer, metadata, size);
				System.out.println("input" + buffer.toString() + " : "+ temp.getDescription());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Upload upload = null;
		try {
			upload = upload(bucketName, imageKey, buffer, metadata);
			System.out.println("input" + buffer.toString() + " : " + upload.getDescription());
		} catch (Exception e) {
			
		}
		return upload;
	}
	
	public Upload upload(String bucketName,String key,byte[] input,ObjectMetadata metaData,Size size) {

		try {
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(input));
			BufferedImage ResizedImage 	= Scalr.resize(originalImage, Method.QUALITY, Mode.AUTOMATIC, size.getWidth(), size.getHeight());
			String fileExtension = metaData.getUserMetadata().get("fileName");
			fileExtension = fileExtension.substring(fileExtension.indexOf(".")+1);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(ResizedImage,fileExtension,output);
			return upload(bucketName, key, output.toByteArray(), metaData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Upload upload(String bucketName,String key,byte[] bytes) {
		Upload upload = upload(bucketName, key, bytes, new ObjectMetadata());
		return upload;
	}

	public Upload upload(String bucketName,String key,byte[] bytes,ObjectMetadata metadata) {
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		metadata.setContentLength(bytes.length);
		Upload upload = upload(bucketName, key, input, metadata);
		return upload;
	}

	public Upload upload(String bucketName,String key,InputStream input) {
		Upload upload = upload(bucketName, key, input, new ObjectMetadata());
		return upload;
	}

}