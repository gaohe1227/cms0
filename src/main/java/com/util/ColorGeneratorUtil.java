package com.util;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.LoggerFactory;

public class ColorGeneratorUtil {
	public static org.slf4j.Logger logger = LoggerFactory
			.getLogger(ColorGeneratorUtil.class);
	private static Properties prop = new Properties();
	static {
		try {
			InputStream in = ColorGeneratorUtil.class.getClassLoader().getResourceAsStream("JfreeChartsColorConf.properties");
			prop.load(in);
		} catch (IOException e) {
			logger.error("解析JfreeCharts颜色配置文件出错...");
			e.printStackTrace();
		}
	}

	/**
	 * 生成课程任务点类型分布环形图表颜色;
	 * 
	 * @return
	 */
	public static Map<String, Color> getCourseTaskDistributionColor() {
		Map<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("视频", String2Color(prop.getProperty("CTDC_AUDIO")));
		colorMap.put("图书", String2Color(prop.getProperty("CTDC_BOOK")));
		colorMap.put("章节测验", String2Color(prop.getProperty("CTDC_WORKJOB")));
		colorMap.put("文档", String2Color(prop.getProperty("CTDC_DOCUMENT")));
		colorMap.put("音频", String2Color(prop.getProperty("CTDC_VIDEO")));
		return colorMap;
	}

	/**
	 * 生成学生综合成绩分布环形图表颜色;
	 * 
	 * @return
	 */
	public static Map<String, Color> getStudentGradeDistributionColor() {
		Map<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("20分以下", String2Color(prop.getProperty("SGDC_LESS_20")));
		colorMap.put("20-40分", String2Color(prop.getProperty("SGDC_20_40")));
		colorMap.put("40-60分", String2Color(prop.getProperty("SGDC_40_60")));
		colorMap.put("60-80分", String2Color(prop.getProperty("SGDC_60_80")));
		colorMap.put("80-100分", String2Color(prop.getProperty("SGDC_80_100")));
		colorMap.put("100分", String2Color(prop.getProperty("SGDC_100")));
		return colorMap;
	}
	
	public static Map<String, String> getStudentGradeDistributionRGBColor() {
		Map<String, String> colorMap = new LinkedHashMap<String, String>();
		colorMap.put("40以下", prop.getProperty("P_SGDC_20_40"));
		colorMap.put("40-59", prop.getProperty("P_SGDC_40_60"));
		colorMap.put("60-79", prop.getProperty("P_SGDC_60_80"));
		colorMap.put("80-99", prop.getProperty("P_SGDC_80_100"));
		colorMap.put("100分", prop.getProperty("P_SGDC_100"));
		return colorMap;
	}
	
	public static Map<String, Color> getStudentGradeDistributionColorPhone() {
		Map<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("40分以下", String2Color(prop.getProperty("P_SGDC_20_40")));
		colorMap.put("40-59", String2Color(prop.getProperty("P_SGDC_40_60")));
		colorMap.put("60-79", String2Color(prop.getProperty("P_SGDC_60_80")));
		colorMap.put("80-99", String2Color(prop.getProperty("P_SGDC_80_100")));
		colorMap.put("100分", String2Color(prop.getProperty("P_SGDC_100")));
		return colorMap;
	}
	
	
	/**
	 * 生成学生综合成绩分布环形图表颜色;
	 * 
	 * @return
	 */
	public static Map<String, Color> getdeviceTypeDistributionColor() {
		Map<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("移动客户端", String2Color(prop.getProperty("DTSC_MDPVCOUNT")));
		colorMap.put("电脑网页版", String2Color(prop.getProperty("DTSC_PCPVCOUNT")));
		return colorMap;
	}

	/**
	 * 生成学生访问次数折线图曲线颜色;
	 * 
	 * @return
	 */
	public static Color getStudentVisitedStatisticColor() {
		return String2Color(prop.getProperty("SVSC_SERIESPAINT"));
	}

	/**
	 * 生成作业测试客观题答案分布;
	 * @return
	 */
	public static  Map<String,Color> getChapterWorkAnswerDistributionColor(){
		Map<String,Color> colorMap = new HashMap<String,Color>();
		colorMap.put("A", String2Color(prop.getProperty("A")));
		colorMap.put("B", String2Color(prop.getProperty("B")));
		colorMap.put("C", String2Color(prop.getProperty("C")));
		colorMap.put("D", String2Color(prop.getProperty("D")));
		colorMap.put("E", String2Color(prop.getProperty("E")));
		colorMap.put("F", String2Color(prop.getProperty("F")));
		colorMap.put("G", String2Color(prop.getProperty("G")));
		return colorMap;
	}
	
	
	public static Color String2Color(String str) {
		int i = Integer.parseInt(str.substring(1), 16);
		return new Color(i);
	}

	public static void main(String[] args) {
		/*Map<String, Color> color = getCourseTaskDistributionColor();
		Set<Entry<String, Color>> set = color.entrySet();
		for (Entry<String, Color> entry : set) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}*/
	}
}
