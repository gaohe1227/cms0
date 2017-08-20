package com.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**\
 * 
 * @author 高鹤
 *
 * 2016年5月30日
 *
 * 作用:图片工具类
 */
public class ImageUtil {
	/**
	 * 裁剪图片
	 * @param srcPath:图片源路径
	 * @param targetPath:目标路径
	 * @param x:轴位置
	 * @param y:y轴位置
	 * @param width：宽
	 * @param height：高
	 * @return
	 * @throws IOException 
	 */
  public static boolean  cutImage(String srcPath,String targetPath,Integer x,Integer y,Integer width,Integer height) throws IOException{

		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			// 读取图片文件
			fis = new FileInputStream(srcPath);
			// 创建图片文件流迭代器
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader imageReader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(fis);
			// 将图片流放入图片文件流中
			imageReader.setInput(iis, true);
			ImageReadParam param = imageReader.getDefaultReadParam();
			/**
			 * 定义裁剪区域
			 */
			Rectangle rect = new Rectangle(x, y, width, height);// 图像处理的2D类
			param.setSourceRegion(rect);
			BufferedImage bImage = imageReader.read(0, param);
			return ImageIO.write(bImage, "jpg", new File(targetPath));
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (iis != null) {
				iis.close();

			}
		}
		 
	 
  }
}
