package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 */
public class ZipUtils {

	public static void doCompress(String srcFile, String zipFile) throws Exception {
		doCompress(new File(srcFile), new File(zipFile));
	}

	/**
	 * 文件压缩
	 * @param srcFile 目录或者单个文件
	 * @param destFile 压缩后的ZIP文件
	 */
	public static void doCompress(File srcFile, File destFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			for (File file : files) {
				doCompress(file, null, out);
			}
		}
		else {
			doCompress(srcFile, null, out);
		}
	}

	public static void doCompress(String pathname, String fileName, ZipOutputStream out) throws IOException {
		doCompress(new File(pathname), fileName, out);
	}

	public static void doCompress(File file, String fileName, ZipOutputStream out) throws IOException {
		if (file.exists()) {
			byte[] buffer = new byte[1024];
			FileInputStream fis = new FileInputStream(file);
			String name = file.getName();
			int lastIndexOf = name.lastIndexOf(".");
			if (lastIndexOf >= 0) {

				String suffix = name.substring(lastIndexOf, name.length());
				if (fileName != null) {
					out.putNextEntry(new ZipEntry(fileName + suffix));
				}
				else {
					out.putNextEntry(new ZipEntry(file.getName()));
				}
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}

				out.setEncoding("gbk");
				out.flush();
				out.closeEntry();
				fis.close();
			}
		}
	}

}