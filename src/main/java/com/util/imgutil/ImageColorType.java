package com.util.imgutil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageColorType {
	/**
	 * 判断图片颜色类型
	 * @param imgagePath 图片路径
	 * @return true是Rgb否则是Cmyk
	 * @throws IOException
	 */
	public static boolean isRgbOrCmyk(String imgagePath) throws IOException {
		File file = new File(imgagePath);
		boolean isRgb = true;// true是Rgb否则是Cmyk
		// 创建输入流
		ImageInputStream input = ImageIO.createImageInputStream(file);
		Iterator readers = ImageIO.getImageReaders(input);
		if (readers == null || !readers.hasNext()) {
			throw new RuntimeException("No ImageReaders found");
		}
		ImageReader reader = (ImageReader) readers.next();
		reader.setInput(input);
		// 获取文件格式
		BufferedImage image;
		try {
			// 尝试读取图片 (包括颜色的转换).
			reader.read(0); // RGB
			isRgb = true;
		}
		catch (IIOException e) {
			// 读取Raster (没有颜色的转换).
			reader.readRaster(0, null);// CMYK
			isRgb = false;
		}
		return isRgb;
	}

	public static void main(String[] args) {
		try {
			System.out.println(ImageColorType.isRgbOrCmyk("/Users/lh/Downloads/cmky.jpg"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
