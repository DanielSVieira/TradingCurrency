package uk.com.tradecompany.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;

import uk.com.tradecompany.exception.InvalidDateException;

public class DateUtils {

	public static LocalDate stringToLocalDate(String s) throws ParseException {
		if(StringUtils.isNullOrBlank(s)) {
			throw new InvalidDateException("Date not provided.");
		}
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
		formatter.setLenient(false);
		Date date = (Date) formatter.parse(s);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.YEAR) < 1900) {
			throw new InvalidDateException("We only accept dates from 1900's year ahead.");
		}

		LocalDate localDate = new LocalDate(date);
		
 		return localDate;
	}
}
