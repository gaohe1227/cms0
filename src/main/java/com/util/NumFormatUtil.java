/**
 * 
 */
package com.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author <a href="mailto:yalei@chaoxing.com">yalei</a>
 * 2015年11月9日
 */
public class NumFormatUtil {
	private static final DecimalFormat decimal0 = new DecimalFormat("0");
	
	private static final DecimalFormat decimal = new DecimalFormat("0.#");
	
	private static final DecimalFormat decimal2 = new DecimalFormat("0.##");
	
	public static String fDouble(double d) {
		return decimal.format(d);
	}

	public static String fDouble2(double d) {
		return decimal2.format(d);
	}
    public static double getDoubleValue(int w,double objectiveScore ){
    	 BigDecimal bd = new BigDecimal(objectiveScore);   
 		 bd = bd.setScale(w,BigDecimal.ROUND_HALF_UP); 
 		 return bd.doubleValue();
    }

	public static void main(String args[]){
		double d = 111.11;
		System.out.println(fDouble0(d));
	
	}
	
	
	public static String fDouble0(double d) {
		return decimal0.format(d);
	}
}
