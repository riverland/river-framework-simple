package org.river.base.db;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 时间工具类
 * @author river
 * @date 20120924
 */
public class DateUtils {
	public static final String YYYYMMDDHHMMSS0="yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	public static final String YYYYMMDDSSSSS="yyyyMMddHHmmssSSS";
	
	/**
	 * <p>
	 * 按照指定格式格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
}
