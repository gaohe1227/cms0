package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelUtil {
	/**
	 * 导出Excel数据
	 * @param titles:标题头
	 * @param fileName:文件名
	 * @param lists:数据列表
	 * @param keys:字段
	 * @param inv
	 */
	public static void exportExcel(String[] titles, String fileName, List<Map<String, Object>> lists, String[] keys, HttpServletRequest request,HttpServletResponse response) {
		List<List<String>> newlist = new ArrayList<List<String>>();
		ExcelExport excelExport = new ExcelExport();
		List<String> listTitle = new ArrayList<String>();
		if (titles != null && titles.length > 0) {
			for (String title : titles) {
				listTitle.add(title);
			}
			newlist.add(listTitle);
		}
		if (null != lists) {
			int size = lists.size();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					List<String> list = new ArrayList<String>();
					Map<String, Object> map = lists.get(i);
					for (String key : keys) {
						if (map.get(key) != null) {
							list.add(map.get(key).toString());
						}
						else {
							list.add("");
						}

					}
					newlist.add(list);
				}

			}

		}
		excelExport.addYstjSheet(newlist, fileName);
	 	
		excelExport.write(response, request, fileName);

	}
}
