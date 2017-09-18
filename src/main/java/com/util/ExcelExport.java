package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 统计模块一键导出
 * @author <a href="mailto:wangxuhui@ssreader.cn">wangxuhui</a>
 * @version 2014-6-19
 */
public class ExcelExport {

	public final static String EXCEL_03 = "2003";

	public final static String EXCEL_07 = "2007";

	private String sheetName;

	private String verson_excel;

	private String encoding;

	private Workbook book = null;

	private Sheet sheet = null;

	private String name;// Excel名称

	public ExcelExport(String version) {

		if (EXCEL_07.equals(version)) {
			verson_excel = EXCEL_07;
		}
		else {
			verson_excel = EXCEL_03;
		}

		Properties prop = System.getProperties();
		this.encoding = prop.getProperty("file.encoding");

	}

	public ExcelExport() {

		verson_excel = EXCEL_07;

		Properties prop = System.getProperties();
		this.encoding = prop.getProperty("file.encoding");
	}

	private Workbook getWorkBook() {
		if (book == null) {
			if (EXCEL_07.equals(verson_excel)) {
				book = new XSSFWorkbook();
			}
			else {
				book = new HSSFWorkbook();
			}
		}
		return book;
	}

	public void addSheet(List<List<String>> datas, String sheetName) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();

		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置背景
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle cellStyle2 = workbook.createCellStyle();

		cellStyle2.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4500);
		sheet.setColumnWidth(4, 4500);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 4500);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			for (int index = 0; index < str.size(); index++) {
				try {
					/**
					 * 存放值
					 */
					Cell cell = row.createCell(index);

					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (i == 0) {
						cell.setCellStyle(cellStyle);
					}
					else {
						cell.setCellStyle(cellStyle2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}

					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;

	}

	/**
	 * 印刷统计专用
	 * @param datas
	 * @param sheetName
	 */
	public void addYstjSheet(List<List<String>> datas, String sheetName) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();

		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置背景
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle cellStyle2 = workbook.createCellStyle();
		// 设置字体居中
		cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle2.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4500);
		sheet.setColumnWidth(4, 8000);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 4500);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			for (int index = 0; index < str.size(); index++) {
				try {
					/**
					 * 存放值
					 */
					Cell cell = row.createCell(index);

					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (i == 0) {
						cell.setCellStyle(cellStyle);
					}
					else {
						cell.setCellStyle(cellStyle2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}

					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;

	}

	/**
	 * 为学位外语免试审核（中心）添加导出上报学位外语审核表功能
	 */
	public void addSheet2(List<List<String>> datas, String sheetName, String pcmc, String xxzx) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();
		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置背景
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle cellStyle2 = workbook.createCellStyle();

		cellStyle2.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);

		Row ztRow1 = sheet.getRow(0);
		if (ztRow1 == null) {
			ztRow1 = sheet.createRow(0);
		}
		Cell cell1 = ztRow1.getCell(0);
		if (cell1 == null) {
			cell1 = ztRow1.createCell(0);
		}
		cell1.setCellValue("学位外语免试审核表");
		cell1.setCellStyle(cellStyle); // 样式应用到该单元格上

		// 第一行合并
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, 10);
		sheet.addMergedRegion(region);

		Row ztRow2 = sheet.getRow(1);
		if (ztRow2 == null) {
			ztRow2 = sheet.createRow(1);
		}
		Cell cell2 = ztRow2.getCell(0);
		if (cell2 == null) {
			cell2 = ztRow2.createCell(0);
		}
		cell2.setCellValue("学位外语批次：      " + pcmc);
		cell2.setCellStyle(cellStyle); // 样式应用到该单元格上
		// 第二行合并
		CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 10);
		sheet.addMergedRegion(region2);

		Row ztRow3 = sheet.getRow(2);
		if (ztRow3 == null) {
			ztRow3 = sheet.createRow(2);
		}
		Cell cell3 = ztRow3.getCell(0);
		if (cell3 == null) {
			cell3 = ztRow3.createCell(0);
		}
		cell3.setCellValue("学习中心、函授站名称（盖章）：" + xxzx + "     负责人签字：        年    月    日");
		// 第三行合并
		CellRangeAddress region3 = new CellRangeAddress(2, 2, 0, 10);
		sheet.addMergedRegion(region3);

		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i + 3);
			for (int index = 0; index < str.size(); index++) {
				try {
					/**
					 * 存放值
					 */
					Cell cell = row.createCell(index);

					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (i == 0) {
						cell.setCellStyle(cellStyle);
					}
					else {
						cell.setCellStyle(cellStyle2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;

	}

	// 导出签到表
	public void addSheetQd(List<List<String>> datas, String sheetName, String[] names, String[] values) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();
		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle cellStyle2 = workbook.createCellStyle();

		cellStyle2.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4500);
		sheet.setColumnWidth(4, 4500);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 4500);

		Row row1 = sheet.createRow(0);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue(names[0]);
		cell1.setCellStyle(cellStyle1);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		// 依次表示起始行，截至行，起始列， 截至列

		Row row2 = sheet.createRow(1);
		Cell cell2 = row2.createCell(0);
		cell2.setCellValue(names[1]);
		Cell cell3 = row2.createCell(5);
		cell3.setCellValue(names[2]);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 8));
		Cell cell22 = row2.createCell(1);
		cell22.setCellValue(values[1]);// 学习中心
		Cell cell33 = row2.createCell(6);
		cell33.setCellValue(values[2]);// 考区

		Row row3 = sheet.createRow(2);
		Cell cell4 = row3.createCell(0);
		cell4.setCellValue(names[3]);
		Cell cell5 = row3.createCell(5);
		cell5.setCellValue(names[4]);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 8));
		Cell cell44 = row3.createCell(1);
		cell44.setCellValue(values[3]);// 设置考试时间

		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i + 3);
			for (int index = 0; index < str.size(); index++) {
				try {
					Cell cell = row.createCell(index);

					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					cell.setCellStyle(cellStyle2);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;

	}

	public void addSheet1(List<List<String>> datas, String sheetName) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();

		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		// 设置背景
		// cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		// cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		// cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle cellStyle2 = workbook.createCellStyle();

		cellStyle2.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4500);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			for (int index = 0; index < str.size(); index++) {
				try {
					/**
					 * 存放值
					 */
					Cell cell = row.createCell(index);

					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (i == 0) {
						cell.setCellStyle(cellStyle);
					}
					else {
						cell.setCellStyle(cellStyle2);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;

	}

	public void write(HttpServletResponse response, HttpServletRequest request, String name) {
		OutputStream out = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			boolean isMSIE = (StringUtils.isNotBlank(agent) && agent.indexOf("MSIE") != -1);
			if (isMSIE || agent.indexOf("rv:11") > 0) {
				name = URLEncoder.encode(name, "UTF-8");
			}
			else {
				name = new String(name.getBytes("UTF-8"), "ISO-8859-1");
			}
			String fileName = null;
			if (name == null) {
				UUID uuid = UUID.randomUUID();
				fileName = verson_excel.equals("2003") ? uuid + ".xls" : uuid + ".xlsx";
			}
			else {
				fileName = verson_excel.equals("2003") ? name + ".xls" : name + ".xlsx";
			}
			fileName = StringUtil.trimAll(fileName);
			response.setContentType("application/ms-excel ;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			out = response.getOutputStream();
			getWorkBook().write(out);
			return;

		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writelocal(String excelfiledir, String name) {
		try {
			String fileName = null;

			if (name == null) {
				UUID uuid = UUID.randomUUID();
				fileName = verson_excel.equals("2003") ? uuid + ".xls" : uuid + ".xlsx";
			}
			else {
				fileName = verson_excel.equals("2003") ? name + ".xls" : name + ".xlsx";
			}
			fileName = StringUtil.trimAll(fileName);
			File file = new File(excelfiledir + File.separator + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			getWorkBook().write(out);
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zipExcels(HttpServletResponse response, String filedir, String name) {
		try {
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + name);
			ZipOutputStream zipout = new ZipOutputStream(response.getOutputStream());
			File excelfiles = new File(filedir);
			zip(zipout, excelfiles, "");
			zipout.flush();
			zipout.close();
			File[] fl = excelfiles.listFiles();
			for (int i = 0; i < fl.length; i++) {
				fl[i].delete();
			}
			return;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		}
		else {
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	/**
	 * 压缩打包
	 * @param sourceFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean fileToZip(HttpServletResponse response, String sourceFilePath, String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ZipOutputStream zos = null;
		if (sourceFile.exists() == false) {
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在");
		}
		else {
			try {
				File[] sourceFiles = sourceFile.listFiles();
				if (null == sourceFiles || sourceFiles.length < 1) {
					System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩");
				}
				else {
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
					zos = new ZipOutputStream(response.getOutputStream());
					byte[] bufs = new byte[1024 * 10];
					filezip(sourceFiles, "", fis, bis, bufs, zos);
					flag = true;
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			finally {
				// 关闭流
				try {
					if (null != bis) {
						bis.close();
					}
					if (null != fis) {
						fis.close();
					}
					if (null != zos) {
						zos.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}

	/**
	 * 文件、文件夹打压缩包
	 * @param sourceFiles
	 * @param base
	 * @param fis
	 * @param bis
	 * @param bufs
	 * @param zos
	 */
	private static void filezip(File[] sourceFiles, String base, FileInputStream fis, BufferedInputStream bis, byte[] bufs, ZipOutputStream zos) {
		try {
			for (int i = 0; i < sourceFiles.length; i++) {
				File sourceFile = sourceFiles[i];
				String filename = sourceFile.getName();
				if (sourceFile.isDirectory()) {
					File[] sourceSonFiles = sourceFile.listFiles();
					// base = base + filename + "/";
					filezip(sourceSonFiles, base + filename + "/", fis, bis, bufs, zos);
				}
				else {
					String filetype = filename.substring(filename.lastIndexOf("."), filename.length());
					// 下载临时文件不进行打包
					if (!".position".equals(filetype)) {
						// System.out.println("压缩文件：" + base + filename);
						// 创建ZIP实体，并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(base + filename);// 服务器UTF-8
						// ZipEntry zipEntry = new ZipEntry(new String((base +
						// filename).getBytes(), "GBK"));// 本地GBK
						zipEntry.setUnixMode(644);
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(sourceFile);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
						if (null != bis) {
							bis.close();
						}
						if (null != fis) {
							fis.close();
						}
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getVerson_excel() {
		return verson_excel;
	}

	public void setVerson_excel(String verson_excel) {
		this.verson_excel = verson_excel;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 试卷袋标签带样式Excel
	 * @param datas
	 * @param sheetName
	 */
	public void addSjdbq(List<List<String>> datas, String sheetName) {
		Workbook workbook = getWorkBook();
		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();

		// 设置单元格内容水平对其方式
		// XSSFCellStyle.ALIGN_CENTER 居中对齐
		// XSSFCellStyle.ALIGN_LEFT 左对齐
		// XSSFCellStyle.ALIGN_RIGHT 右对齐
		// 设置单元格内容垂直对其方式
		// XSSFCellStyle.VERTICAL_TOP 上对齐
		// XSSFCellStyle.VERTICAL_CENTER 中对齐
		// XSSFCellStyle.VERTICAL_BOTTOM 下对齐
		// 设置背景
		// cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		// cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		// cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		/**
		 * 设置单元格为加粗居中样式
		 */
		CellStyle alignBoldCellStyle = workbook.createCellStyle();
		alignBoldCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		alignBoldCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 创建字体对象
		Font ztFont = workbook.createFont();
		ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 字体加粗
		ztFont.setFontHeightInPoints((short) 14); // 将字体大小设置为22px
		alignBoldCellStyle.setFont(ztFont);
		alignBoldCellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));

		CellStyle rightCellStyle = workbook.createCellStyle();
		rightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		rightCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		rightCellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));

		// 创建一个新的表
		Sheet sheet = StringUtils.isBlank(sheetName) ? workbook.createSheet() : workbook.createSheet(sheetName);
		sheet.setColumnWidth(0, 2500);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(3, 2500);
		sheet.setColumnWidth(4, 8000);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			for (int index = 0; index < str.size(); index++) {
				try {
					/**
					 * 存放值
					 */
					Cell cell = row.createCell(index);
					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (index % 2 == 0) {
						cell.setCellStyle(rightCellStyle);
					}
					else {
						cell.setCellStyle(alignBoldCellStyle);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;
	}

	private Sheet getSheet() {
		if (sheet == null) {
			Workbook wbModule = getWorkBook();
			sheet = wbModule.createSheet("sheet1");
		}
		return sheet;
	}

	public void setCellStyle(int row, String value, String value1) throws Exception {

		Workbook wbModule = getWorkBook();
		Sheet sheet = getSheet();

		CellStyle style = wbModule.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		Row ztRow = sheet.getRow(row);
		if (ztRow == null) {
			ztRow = sheet.createRow(row);
		}
		Cell cell = ztRow.getCell(0);
		if (cell == null) {
			cell = ztRow.createCell(0);
		}
		cell.setCellValue(value);
		Font ztFont = wbModule.createFont();
		// ztFont.setItalic(true); // 设置字体为斜体字
		ztFont.setColor(Font.COLOR_RED); // 将字体设置为“红色”
		ztFont.setFontHeightInPoints((short) 16); // 将字体大小设置为16px
		ztFont.setFontName("宋体"); // 将“华文行楷”字体应用到当前单元格上
		// ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD); //
		// 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）

		style.setFont(ztFont); // 将字体应用到样式上面
		cell.setCellStyle(style); // 样式应用到该单元格上
		// 第一行合并
		CellRangeAddress region = new CellRangeAddress(row, row, 0, 9);
		sheet.addMergedRegion(region);

		Row ztRow2 = sheet.getRow(row + 1);
		if (ztRow2 == null) {
			ztRow2 = sheet.createRow(row + 1);
		}
		Cell cell2 = ztRow2.getCell(0);
		if (cell2 == null) {
			cell2 = ztRow2.createCell(0);
		}
		cell2.setCellValue(value1);
		cell2.setCellStyle(style); // 样式应用到该单元格上

		// 第2行合并
		CellRangeAddress region2 = new CellRangeAddress(row + 1, row + 1, 0, 9);
		sheet.addMergedRegion(region2);
	}

	public void addSheetForKsjs(List<List<String>> datas, String sheetName, List<Integer> rowList, List<Integer> rowList2) {
		Workbook workbook = getWorkBook();
		Sheet sheet = getSheet();
		workbook.setSheetName(0, sheetName);
		Font ztFont1 = workbook.createFont();
		ztFont1.setFontName("宋体");
		ztFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont1.setFontHeightInPoints((short) 18);
		Font ztFont2 = workbook.createFont();
		ztFont2.setFontName("宋体");
		ztFont2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont2.setColor(Font.COLOR_RED);
		ztFont2.setFontHeightInPoints((short) 11);
		Font ztFont3 = workbook.createFont();
		ztFont3.setFontName("宋体");
		ztFont3.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont3.setFontHeightInPoints((short) 12);

		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();
		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFont(ztFont1);

		CellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle2.setFont(ztFont2);
		cellStyle2.setWrapText(true);

		CellStyle cellStyle3 = workbook.createCellStyle();
		cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle3.setFont(ztFont3);
		cellStyle3.setDataFormat(workbook.createDataFormat().getFormat("@"));

		CellStyle cellStyle4 = workbook.createCellStyle();
		cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle4.setFont(ztFont3);
		cellStyle4.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 设置边框
		cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 7000);
		sheet.setColumnWidth(5, 7000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 5000);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			if (rowList2.contains(i)) {
				row.setHeight((short) 600);
			}
			else {
				row.setHeight((short) 450);
			}
			for (int index = 0; index < str.size(); index++) {
				try {
					Cell cell = row.createCell(index);
					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (rowList.contains(i)) {
						cell.setCellStyle(cellStyle);
					}
					else if (rowList2.contains(i)) {
						cell.setCellStyle(cellStyle2);
					}
					else {
						cell.setCellStyle(cellStyle3);
						if (StringUtils.isNotBlank(value)
								|| (StringUtils.isBlank(value) && index % 9 == 0 && index > 0 && StringUtils.isNotBlank(str.get(index - 1)))) {
							cell.setCellStyle(cellStyle4);
						}
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;
	}

	public void addSheetForKsjs11111(List<List<String>> datas, String sheetName, List<Integer> rowList, List<Integer> rowList2) {
		Workbook workbook = getWorkBook();
		Sheet sheet = getSheet();
		XSSFPrintSetup ps = (XSSFPrintSetup) sheet.getPrintSetup();
		ps.setLandscape(false); // 打印方向，true：横向，false：纵向(默认)
		ps.setVResolution((short) 600);
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 纸张类型
		sheet.setMargin(HSSFSheet.TopMargin, (double) 0.2); // 上边距
		sheet.setMargin(HSSFSheet.BottomMargin, (double) 0.2); // 下边距
		sheet.setMargin(HSSFSheet.LeftMargin, (double) 0.2); // 左边距
		sheet.setMargin(HSSFSheet.RightMargin, (double) 0.2); // 右边距

		workbook.setSheetName(0, sheetName);
		Font ztFont1 = workbook.createFont();
		ztFont1.setFontName("宋体");
		ztFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont1.setFontHeightInPoints((short) 13);
		Font ztFont2 = workbook.createFont();
		ztFont2.setFontName("宋体");
		ztFont2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont2.setColor(Font.COLOR_RED);
		ztFont2.setFontHeightInPoints((short) 11);
		Font ztFont3 = workbook.createFont();
		ztFont3.setFontName("宋体");
		ztFont3.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ztFont3.setFontHeightInPoints((short) 10);

		// 设置样式
		CellStyle cellStyle = workbook.createCellStyle();
		// 设置字体居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFont(ztFont1);

		CellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle2.setFont(ztFont2);
		cellStyle2.setWrapText(true);

		CellStyle cellStyle3 = workbook.createCellStyle();
		cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle3.setFont(ztFont3);
		cellStyle3.setDataFormat(workbook.createDataFormat().getFormat("@"));
		cellStyle3.setWrapText(true);

		CellStyle cellStyle4 = workbook.createCellStyle();
		cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle4.setFont(ztFont3);
		cellStyle4.setDataFormat(workbook.createDataFormat().getFormat("@"));
		// 设置边框
		cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		cellStyle4.setWrapText(true);

		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 1000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 2000);
		sheet.setColumnWidth(9, 2500);
		// 遍历数据
		for (int i = 0; i < datas.size(); i++) {
			List<String> str = datas.get(i);
			// 定义列（列名称）
			Row row = sheet.createRow(i);
			if (rowList2.contains(i)) {
				row.setHeight((short) 900);
			}
			else {
				row.setHeight((short) 600);
			}
			for (int index = 0; index < str.size(); index++) {
				try {
					Cell cell = row.createCell(index);
					String value = str.get(index);
					if (StringUtils.isNotBlank(value)) {
						value = new String(value.getBytes(), encoding);
					}
					if (rowList.contains(i)) {
						cell.setCellStyle(cellStyle);
					}
					else if (rowList2.contains(i)) {
						cell.setCellStyle(cellStyle2);
					}
					else {
						cell.setCellStyle(cellStyle3);
						if (StringUtils.isNotBlank(value)
								|| (StringUtils.isBlank(value) && index % 9 == 0 && index > 0 && StringUtils.isNotBlank(str.get(index - 1)))) {
							cell.setCellStyle(cellStyle4);
						}
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					}
					cell.setCellValue(value);
				}
				catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		}
		book = workbook;
	}

	public void setCellStyle2(File exlFile, int row, String value, String value1) throws Exception {

		FileInputStream fis = new FileInputStream(exlFile);

		HSSFWorkbook wbModule = new HSSFWorkbook(fis);

		Sheet sheet = wbModule.getSheetAt(0);

		CellStyle style = wbModule.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		Row ztRow = sheet.getRow(row);
		if (ztRow == null) {
			ztRow = sheet.createRow(row);
		}
		Cell cell = ztRow.getCell(0);
		if (cell == null) {
			cell = ztRow.createCell(0);
		}
		cell.setCellValue(value);
		Font ztFont = wbModule.createFont();
		// ztFont.setItalic(true); // 设置字体为斜体字

		ztFont.setFontHeightInPoints((short) 16); // 将字体大小设置为16px
		ztFont.setFontName("宋体"); // 将“华文行楷”字体应用到当前单元格上
		// ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD); //
		// 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
		ztFont.setColor(Font.COLOR_NORMAL);
		style.setFont(ztFont); // 将字体应用到样式上面
		cell.setCellStyle(style); // 样式应用到该单元格上
		// 第一行合并
		CellRangeAddress region = new CellRangeAddress(row, row, 0, 7);
		sheet.addMergedRegion(region);

		Row ztRow2 = sheet.getRow(row + 1);
		if (ztRow2 == null) {
			ztRow2 = sheet.createRow(row + 1);
		}
		Cell cell2 = ztRow2.getCell(0);
		if (cell2 == null) {
			cell2 = ztRow2.createCell(0);
		}
		cell2.setCellValue(value1);
		ztFont.setFontHeightInPoints((short) 12); // 将字体大小设置为12px
		ztFont.setColor(Font.COLOR_RED); // 将字体设置为“红色”
		style.setFont(ztFont); // 将字体应用到样式上面
		cell2.setCellStyle(style); // 样式应用到该单元格上

		// 第2行合并
		CellRangeAddress region2 = new CellRangeAddress(row + 1, row + 1, 0, 7);
		sheet.addMergedRegion(region2);
	}

}
