package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
/**
 * Exce帮助类
 * Description:
 * @author:高鹤
 * @date:2017年9月7日
 */

public class ExcelImport {
	/**
	 * 获取Excel的所有sheet的名称
	 * 
	 * @param file
	 * @return 所有sheet名称的list
	 * @throws IOException
	 */
	public static List<String> getSheets(MultipartFile file) throws IOException {
		Workbook book = null;
		try {
			book = new XSSFWorkbook(file.getInputStream());
		}
		catch (Exception ex) {
			book = new HSSFWorkbook(file.getInputStream());
		}
		List<String> sheetList = new ArrayList<String>();
		for (int sheetIndex = 0; sheetIndex < book.getNumberOfSheets(); sheetIndex++) {
			Sheet st = book.getSheetAt(sheetIndex);
			sheetList.add(st.getSheetName());
		}
		return sheetList;
	}

	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param MultipartFile  file读取数据的源Excel
	 * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @param sheetNum 要读取第几个sheet，从0开始
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[][] getData(MultipartFile file, int ignoreRows, int sheetNum) throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		Workbook book = null;
		try {
			book = new XSSFWorkbook(file.getInputStream());
		}
		catch (Exception ex) {
			book = new HSSFWorkbook(file.getInputStream());
		}
		Cell cell = null;
		Sheet st = book.getSheetAt(sheetNum);
		// 第一行为标题，不取
		for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd").format(date);
							}
							else {
								value = "";
							}
						}
						else {
							value = new DecimalFormat("0.#").format(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						try {
							 value = String.valueOf(cell.getNumericCellValue());
						} catch (IllegalStateException e) {
							 value = String.valueOf(cell.getRichStringCellValue());
						}
//						if (!cell.getStringCellValue().equals("")) {
//							value = cell.getStringCellValue();
//						}
//						else {
//							value = cell.getNumericCellValue() + "";
//						}
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						value = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
				}
				// if (columnIndex == 0 && value.trim().equals("")) {
				// break;
				// }
				values[columnIndex] = rightTrim(value);
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}
		}
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file 读取数据的源Excel
	 * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @param sheetNum 要读取第几个sheet，从0开始
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[][] getData(File file, int ignoreRows, int sheetNum) throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		Workbook book = null;
		InputStream is = new FileInputStream(file);
		try {
			book = new XSSFWorkbook(is);
		}
		catch (Exception ex) {
			book = new HSSFWorkbook(is);
		}
		Cell cell = null;
		Sheet st = book.getSheetAt(sheetNum);
		// 第一行为标题，不取
		for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd").format(date);
							}
							else {
								value = "";
							}
						}
						else {
							value = new DecimalFormat("0.#").format(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						}
						else {
							value = cell.getNumericCellValue() + "";
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						value = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
				}
				// if (columnIndex == 0 && value.trim().equals("")) {
				// break;
				// }
				values[columnIndex] = rightTrim(value);
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}
		}
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str 要处理的字符串
	 * @return 处理后的字符串
	 */
	private static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

}
