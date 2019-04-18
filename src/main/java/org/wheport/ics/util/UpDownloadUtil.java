package org.wheport.ics.util;

import org.springframework.web.multipart.MultipartFile;
import org.wheport.ics.config.ConfigurationConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传工具类
 */
public class UpDownloadUtil {
	//验证上传图片的格式和类型
	 public static boolean validateImageFileType(MultipartFile formFile) {
	  
		 if (formFile != null && formFile.getSize() > 0) {
			  // 获取上传文件的后缀
			  String ext = formFile.getOriginalFilename().substring(
			  formFile.getOriginalFilename().lastIndexOf('.') + 1).toLowerCase();
			  
			  //允许上传的文件类型
			  List<String> arrowType = Arrays.asList("image/png","image/jpg","image/jpeg","image/pjpeg");
			  //允许上传的文件后缀
			  List<String> arrowExtension = Arrays.asList("jpg","png","JPG");
			  
			  return arrowType.contains(formFile.getContentType().toLowerCase())&&arrowExtension.contains(ext);
		  }
		  
		  return false;
	 }
	 /**获取上传 除后缀 的文件名*/
	 public static String getFileNameNotExt(MultipartFile formfile){
		 
		 return formfile.getOriginalFilename().substring(0,formfile.getOriginalFilename().lastIndexOf('.')).toLowerCase();
	 }
	 /**获取上传文件的后缀*/
	 public static String getExt(MultipartFile formfile){
		 
		 return formfile.getOriginalFilename().substring(formfile.getOriginalFilename().lastIndexOf('.')+1).toLowerCase();
	 }
	 public static String createOriginaUrl(String paramName){
		  String fileUrlDir = ConfigurationConfig.getProperty(paramName);
		  return fileUrlDir;
	}
	public static String createAccessPath(String url){
		String imgPrefix = ConfigurationConfig.getProperty("imgPrefix");
		if(url != null && !url.startsWith("http")){
			return imgPrefix + url;
		}
		return url;
	}
	 /*
	 public static String createNewUrl(){
	      PropertyReader propertyReader=new  PropertyReader();
	      propertyReader.setRealPath("/app-run.properties");
		  String fileUrlDir = propertyReader.getPropertyInfoMap().get("newFile");
		  return fileUrlDir;
	}
	public static String createNewFileSrc(){
			PropertyReader propertyReader=new  PropertyReader();
		      propertyReader.setRealPath("/app-run.properties");
		      String fileUrlDir = propertyReader.getPropertyInfoMap().get("newFile");
			  return fileUrlDir;
	} 
	public static String createOriginaUrl(){
		      PropertyReader propertyReader=new  PropertyReader();
		      propertyReader.setRealPath("/app-run.properties");
			  String fileUrlDir = propertyReader.getPropertyInfoMap().get("originalFile");
			  return fileUrlDir;
	}
	public static String createOriginalFileSrc(){
		PropertyReader propertyReader=new  PropertyReader();
	      propertyReader.setRealPath("/app-run.properties");
		  String fileUrlDir = propertyReader.getPropertyInfoMap().get("originalFile");
		  return fileUrlDir;
	 }
		*/
	 public static File saveFile(File savedir, String fileName, byte[] data) throws Exception{
		  if(!savedir.exists())
		  {
			  System.out.println("---------------------------");
			  savedir.mkdirs();//如果目录不存在就创建
			  
		  }
		  File file = new File(savedir, fileName);
		  
		  FileOutputStream fileoutstream = new FileOutputStream(file);
		  
		  fileoutstream.write(data);
		  
		  fileoutstream.close();
		  
		  return file;
		  
	 }
	 /*
	 public static String createfileUrl(HttpServletRequest request){
		  ServletContext context = request.getSession().getServletContext();
		 
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/");
	      
	      PropertyReader propertyReader=new  PropertyReader();
	      propertyReader.setRealPath("/app-run.properties");
	      
	      String fileUrlDir =propertyReader.getPropertyInfoMap().get("url")+"/"+ sdf.format(new Date());
	      //得到图片保存目录的真实路径
	      String fileUrl  = context.getRealPath(fileUrlDir);
	      
		  return fileUrl;
	 }
	 public static String createMobileUrl(HttpServletRequest request){
		  ServletContext context = request.getSession().getServletContext();
		 
	      //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/");
	      
	      PropertyReader propertyReader=new  PropertyReader();
	      propertyReader.setRealPath("/app-run.properties");
	      
	     // String fileUrlDir =propertyReader.getPropertyInfoMap().get("imgMobileUrl")+"/"+ sdf.format(new Date());
	      String fileUrlDir =propertyReader.getPropertyInfoMap().get("imgMobileUrl");
	      //得到图片保存目录的真实路径
	      String fileUrl  = context.getRealPath(fileUrlDir);
	      
		  return fileUrl;
	 }
	 public static String createWebUrl(HttpServletRequest request){
		  ServletContext context = request.getSession().getServletContext();
		 
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/");
	      
	      PropertyReader propertyReader=new  PropertyReader();
	      propertyReader.setRealPath("/app-run.properties");
	      
	    // String fileUrlDir =propertyReader.getPropertyInfoMap().get("imgWebUrl")+"/"+ sdf.format(new Date());
	      String fileUrlDir =propertyReader.getPropertyInfoMap().get("imgWebUrl");
	      //得到图片保存目录的真实路径
	      String fileUrl  = context.getRealPath(fileUrlDir);
	     // String fileUrl  = request.getContextPath()+propertyReader.getPropertyInfoMap().get("imgWebUrl");
	     // System.out.println("-----------"+request.getContextPath());
	      
		  return fileUrl;
	 }
	 */
	 //构建文件名称
	 public static String createFileName(String fileName,String ext){
		 
		 String imagename = fileName+"."+ ext;//构建文件名称
		
		 return imagename;
	 }
	

}
