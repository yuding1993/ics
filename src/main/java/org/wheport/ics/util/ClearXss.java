package org.wheport.ics.util;

public class ClearXss {
	
	/**
	 * 最简单的屏蔽xss关键字
	 * @param value 要被处理的字符串
	 * @return
	 */
	public static String cleanXSS(String value) {
		if(value == null){
			return null;
		}
		//You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
	
}
