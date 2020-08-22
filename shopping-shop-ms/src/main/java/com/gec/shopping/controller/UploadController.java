package com.gec.shopping.controller;

import com.gec.shopping.pojo.entity.RestBean;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadController {
	
	@Value("${minio.endpoint}")
	private String endpoint;     //http://192.168.1.5:9000 #MinIO服务所在地址
	@Value("${minio.bucketName}")
	private String bucketName;  //mall #存储桶名称
	@Value("${minio.accessKey}")
	private String accessKey;   //minioadmin #访问的key
	@Value("${minio.secretKey}")
	private String secretKey;   //minioadmin #访问的秘钥

	@PostMapping("/uploadFile")
	public RestBean uploadFile(MultipartFile file) {
		try {
			MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
			boolean isExist = minioClient.bucketExists(bucketName);
			if (!isExist) {
				minioClient.makeBucket(bucketName);
				// 定义文件规则
				minioClient.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
			}

			// 设置存储对象的名称
			String filename = file.getOriginalFilename();
			SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMdd");
			String objectName = sfd.format(new Date()) + "/" + filename;
			// 把存储对象放在存储桶中
			minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getContentType());
			System.out.println("上传文件成功");
			// 得到存储对象的URL
			String objectURL = minioClient.getObjectUrl(bucketName, objectName);
			System.out.println(objectURL);
			return new RestBean(true, objectURL);
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "文件上传失败");
		}
	}
	
//	@RequestMapping("/uploadFile")
//	public Result uploadFile(MultipartFile file){
//		try {
//			//设置虚拟的映射路径 ---> D:/file
//			String path="D:/file";
//			String url = "";
//			if (file!=null && file.getSize()>0) {
//				file.transferTo(new File(path, file.getOriginalFilename()));
//				url = "http://localhost:8887/upload/"+file.getOriginalFilename();
//			}	
//			return new Result(true, url);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return new Result(false, "上传失败");
//		}		
//	}
	
//	@Value("${FILE_SERVER_URL}")
//	private String file_server_url;
//	
//	@RequestMapping("/uploadFile")
//	public Result uploadFile(MultipartFile file){
//		
//		try {
//			// 获得文件名:
//			String fileName = file.getOriginalFilename();
//			// 获得文件的扩展名:
//			String extName = fileName.substring( fileName.lastIndexOf(".")+1 );
//			// 创建工具类
//			FastDFSClient client = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
//			
//			String path = client.uploadFile(file.getBytes(), extName); // group1/M00/
//			
//			String url = file_server_url + path;
//			
//			return new Result(true, url);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "上传失败！");
//		}
//		
//	}
	
}
