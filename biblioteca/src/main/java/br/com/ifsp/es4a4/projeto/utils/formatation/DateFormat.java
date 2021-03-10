package br.com.ifsp.es4a4.projeto.utils.formatation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateFormat {
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		
		return cal.getTime();
	}
	
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		
		return cal.getTime();
	}

}
