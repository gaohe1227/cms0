/**
 * 
 */
package com.util;

/**
 * 替换Excel中某一行某一列的值
 *  @author <a href="mailto:xuwenjie@chaoxing.com">XuWenjie</a>
 *  @version 2016-12-29
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChangeCell {

	// @SuppressWarnings("deprecation")
	// public static void main(String[] args) throws Exception {
	// File file = new File("D:\\test.xls");
	//
	// // 下面尝试更改第一行第一列的单元格的值
	//
	// ChangeCell.updateExcel(file, 0, 0, 0, "更改测试");
	// }

	public static void updateExcel(File exlFile, int sheetIndex, int col, int row, String value) throws Exception {

		FileInputStream fis = new FileInputStream(exlFile);

		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		// workbook.

		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

		HSSFRow r = sheet.getRow(row);

		HSSFCell cell = r.getCell(col);

		// int type=cell.getCellType();

		String str1 = cell.getStringCellValue();

		// 这里假设对应单元格原来的类型也是String类型

		cell.setCellValue(value);

		System.out.println("单元格原来值为" + str1);

		System.out.println("单元格值被更新为" + value);

		fis.close();// 关闭文件输入流

		FileOutputStream fos = new FileOutputStream(exlFile);

		workbook.write(fos);

		fos.close();// 关闭文件输出流

	}

	private String getCellValue(HSSFCell cell) {

		String cellValue = "";

		DecimalFormat df = new DecimalFormat("#");

		switch (cell.getCellType()) {

		case XSSFCell.CELL_TYPE_STRING:

			cellValue = cell.getRichStringCellValue().getString().trim();

			break;

		case XSSFCell.CELL_TYPE_NUMERIC:

			cellValue = df.format(cell.getNumericCellValue()).toString();

			break;

		case XSSFCell.CELL_TYPE_BOOLEAN:

			cellValue = String.valueOf(cell.getBooleanCellValue()).trim();

			break;

		case XSSFCell.CELL_TYPE_FORMULA:

			cellValue = cell.getCellFormula();

			break;

		default:

			cellValue = "";

		}

		return cellValue;

	}
	
}
