package com.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
/**
 * 更改图片为缩略图 210*150
 * 
 *
 * @author <a href="mailto:614046330@qq.com">shiran</a>
 * @version 2016年12月22日
 */
public class ImageChange{
	public  void resizeImage(InputStream is, OutputStream os, String format,int width,int height) throws IOException {  
	    BufferedImage prevImage = ImageIO.read(is);  
	    int newWidth = width;  
	    int newHeight = height;  
	    BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);  
	    Graphics graphics = image.createGraphics();  
	    graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);  
	    ImageIO.write(image, format, os);  
	    os.flush();  
	    is.close();  
	    os.close();  
	}
	public  void resizeImage(File file, File ofile, String format,int width,int height) throws IOException {  
	    FileInputStream is =new FileInputStream(file);
		BufferedImage prevImage = ImageIO.read(is);  
	    int newWidth = width;  
	    int newHeight = height;  
	    BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);  
	    Graphics graphics = image.createGraphics();  
	    graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);  
	    FileOutputStream os=new FileOutputStream(ofile);
	    ImageIO.write(image, format, os);  
	    os.flush();  
	    is.close();  
	    os.close();  
	}
	public  void resizeImage(String file, String ofile, String format,int width,int height) throws IOException {  
	    FileInputStream is =new FileInputStream(file);
		BufferedImage prevImage = ImageIO.read(is);  
	    int newWidth = width;  
	    int newHeight = height;  
	    BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);  
	    Graphics graphics = image.createGraphics();  
	    graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);  
	    FileOutputStream os=new FileOutputStream(ofile);
	    ImageIO.write(image, format, os);  
	    os.flush();  
	    is.close();  
	    os.close();  
	}
}
