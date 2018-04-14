package com.eko.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class TimeUtil {

	private TimeUtil() {

	}

	public static Date getDateYMD() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getZeroDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		final TimeZone tz = TimeZone.getTimeZone("GMT+0");
		sdf.setTimeZone(tz);
		Date dt = new Date();
		return stringToFullDate(sdf.format(dt));
	}

	public static Date getCurrDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dt = new Date();
		return stringToFullDate(sdf.format(dt));
	}

	public static Date stringToFullDate(String dateStr) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getCurrYear() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}

	public static String getCurrshortYear() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yy");
		return formatter.format(date);
	}

	public static String getDateYMDDHSS(Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return formatter.format(date);
	}

	public static String getDateYMDHM(Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}

	public static String getAllShortCurrDate() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String getAllShortCurrDateTime() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return formatter.format(date);
	}

	public static String getAllShortCurrTime() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("HHmmssSSS");
		return formatter.format(date);
	}

	public static String getAllShortTime() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("HHmmss");
		return formatter.format(date);
	}

	public static Date getYMDHSDate() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			
			return null;
		}
	}

	public static Long convDateToLongByFormat(String nowdatetime, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		final TimeZone tz = TimeZone.getTimeZone("GMT+0");
		sdf.setTimeZone(tz);
		try {
			Date d1 = sdf.parse(nowdatetime);
			long diff = d1.getTime();
			return diff;
		} catch (ParseException e) {
			
			return null;
		}
	}

	public static Long convDateToZeroLong(String nowdatetime) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final TimeZone tz = TimeZone.getTimeZone("GMT+0");
		sdf.setTimeZone(tz);
		try {
			Date d1 = sdf.parse(nowdatetime);
			long diff = d1.getTime();
			return diff;
		} catch (ParseException e) {
			
			return null;
		}
	}

	public static String stringToDateThr(String dateStr) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			return formatter.format(formatter.parse(dateStr));
			// return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTodayDate() {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	public static long getDateDiff(String startDay, String endDay) {
		long diff = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDay);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDay);

			diff = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0
					? (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
					: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
		}
		return diff;
	}

}
