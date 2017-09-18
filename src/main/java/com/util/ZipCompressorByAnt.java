package com.util;

import java.io.File;
import java.util.List;
import java.util.Map;

 
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipCompressorByAnt {

	public static Logger logger = LoggerFactory.getLogger(ZipCompressorByAnt.class);

	private File zipFile;

	public ZipCompressorByAnt(String pathName) {
		zipFile = new File(pathName);
	}

	public void compress(String srcPathName, List<Map<Object, Object>> lists) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);

		int ct = 0;
		// 遍历list，得到zpljmc属性
		for (Map<Object, Object> maps : lists) {
			String zpljmc = (String) maps.get("zpljmc");
			String zpljslt = (String) maps.get("zpljslt");

			if (zpljmc != null) {
				ct++;
				logger.debug(zpljmc);
				zpljmc = zpljmc.replace("\\", File.separator);
				zpljmc = zpljmc.substring(zpljmc.lastIndexOf("/") + 1);
				fileSet.setIncludes("**/" + zpljmc);// 包括哪些文件或文件夹
			}
			if (zpljslt != null) {
				ct++;
				logger.debug(zpljslt);
				zpljslt = zpljslt.replace("\\", File.separator);
				zpljslt = zpljslt.substring(zpljslt.lastIndexOf("/") + 1);
				fileSet.setIncludes("**/" + zpljslt);// 包括哪些文件或文件夹
			}
		}
		fileSet.setIncludes("*.xls");
		zip.addFileset(fileSet);
		logger.warn("ZipCompressorByAnt-->" + ct + "文件开始打包");
		zip.execute();
		logger.warn("ZipCompressorByAnt-->" + ct + "文件打包完成");
	}

	/**
	 * 删除目录/文件
	 * @param srcPathName
	 */
	public void delDir(String srcPathName) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		Project prj = new Project();
		Delete delete = new Delete();
		delete.setProject(prj);
		if (srcdir.isDirectory()) {
			delete.setDir(srcdir);
		}
		else {
			delete.setFile(srcdir);
		}
		delete.execute();
	}
}
