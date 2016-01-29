package com.zhihui.meb.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(df.parse("9999-12-31").getTime());

			Date d = new Date(253402185600000L);
			System.out.println(d);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}