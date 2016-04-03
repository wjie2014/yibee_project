package net.yibee.core.service.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.yibee.core.service.UploadFileService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImpl implements UploadFileService {
	
	private Logger logger = Logger.getLogger(this.getClass()); 

	@Override
	public boolean uploadFile(String destinationDir, MultipartFile file, String filename)
			throws Exception {
		 logger.info("文件长度: " + file.getSize()); 
         logger.info("文件类型: " + file.getContentType()); 
         logger.info("文件名称: " + file.getName()); 
         logger.info("文件原名: " + file.getOriginalFilename()); 
         logger.info("========================================"); 
         //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的 
//       FileUtils.copyInputStreamToFile(commentInfo.getAudioFile().getInputStream(), 
//      		 new File(realPath, commentInfo.getAudioFile().getOriginalFilename())); 
         try {   
             SaveFileFromInputStream(file.getInputStream(), destinationDir, filename);   
         } catch (IOException e) {   
             logger.info(e.getMessage());   
             return false;   
         }   
		return true;
	}
	
	 /**保存文件  
    * @param stream  
    * @param path  
    * @param filename  
    * @throws java.io.IOException
    */  
   private void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException   
   {         
//	   path = "E:/test";   //test
       FileOutputStream outputStream = new FileOutputStream( path + "/"+ filename);   
//       byte[] buffer =new byte[1024*1024];   
//       int byteread = 0;    
//       while ((byteread=stream.read(buffer))!=-1)   
//       {   
//			byteread += byteread;   
//			outputStream.write(buffer,0,byteread);   
//			outputStream.flush();   
//       }    
	   int byteCount = 0;
	   byte[] bytes = new byte[1024];
	   while ((byteCount = stream.read(bytes)) != -1){
	        outputStream.write(bytes, 0, byteCount);
	   }
	   outputStream.close();   
	   stream.close();  
   } 

	@Override
	public boolean uploadHandoutImg(String destinationDir, MultipartFile file,
			String filename, int widthTarget) throws Exception {
		 logger.info("文件长度: " + file.getSize()); 
	     logger.info("文件类型: " + file.getContentType()); 
	     logger.info("文件名称: " + file.getName()); 
	     logger.info("文件原名: " + file.getOriginalFilename()); 
	     logger.info("========================================"); 
	     //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的 
	//   FileUtils.copyInputStreamToFile(commentInfo.getAudioFile().getInputStream(), 
	//  		 new File(realPath, commentInfo.getAudioFile().getOriginalFilename())); 
	     try {   
	    	 SaveImgFromInputStream(file.getInputStream(), destinationDir, filename, widthTarget);   
	     } catch (IOException e) {   
	         logger.info(e.getMessage());   
	         return false;   
	     }   
		return true;
	} 
	 
   /**
    * 缩放图像
    * @param scale        缩放比例
    * @param flag         缩放选择:true 放大; false 缩小;
    */
    private void SaveImgFromInputStream(InputStream stream, String path, String filename, int widthTarget) throws IOException{         
//		   path = "E:/test";   //test
	   FileOutputStream outputStream = new FileOutputStream( path + "/"+ filename);   
	   BufferedImage src = ImageIO.read(stream);
	   int width = src.getWidth(); // 得到源图宽
	   int height = src.getHeight(); // 得到源图长
	   double scale = Double.parseDouble(new java.text.DecimalFormat("#.0000").format((double)width/(double)widthTarget));
	   if (scale > 1){
		   // 缩小
		   width = widthTarget;
		   height = (int) ((double)height / scale);
	   }else{
		   // 放大
	//			   width = width * scale;
	//			   height = height * scale;
	   }   
	   Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	   BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	   Graphics g = tag.getGraphics();
	   g.drawImage(image, 0, 0, null); // 绘制缩小后的图
	   g.dispose();
	   ImageIO.write(tag, "JPEG", new File(path + "/"+ filename));// 输出到文件流
	   outputStream.close();   
	   stream.close();  
    }

}
