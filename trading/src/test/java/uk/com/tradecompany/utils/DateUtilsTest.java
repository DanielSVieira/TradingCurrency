package uk.com.tradecompany.utils;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

import uk.com.tradecompany.exception.InvalidDateException;

public class DateUtilsTest {
	
	@Test
	public void testParserDate() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("01 Jan 2016").equals(new LocalDate(2016, DateTimeConstants.JANUARY, 01)));
	}
	
	@Test
	public void testParserDate2() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("07 Jan 2016").equals(new LocalDate(2016, DateTimeConstants.JANUARY, 7)));
	}
	
	@Test (expected = InvalidDateException.class)
	public void testParserDate3() throws ParseException {
		DateUtils.stringToLocalDate("07 Jan 06");
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate4() throws ParseException {
		DateUtils.stringToLocalDate("07 01 2016");
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate5() throws ParseException {
		DateUtils.stringToLocalDate("07-01-2016");
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate6() throws ParseException {
		DateUtils.stringToLocalDate("07/01/2016");
	}
	
	@Test (expected = InvalidDateException.class)
	public void testParserDate7() throws ParseException {
		DateUtils.stringToLocalDate("07 Jan 1899");
	}
	
	@Test
	public void testParserDate8() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("07 Jan 1900").equals(new LocalDate(1900, DateTimeConstants.JANUARY, 7)));
	}
	
	@Test
	public void testParserDate9() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("20 Mar 1901").equals(new LocalDate(1901, DateTimeConstants.MARCH, 20)));
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate10() throws ParseException {
		DateUtils.stringToLocalDate("anything");
	}
	
	@Test (expected = InvalidDateException.class)
	public void testParserDate11() throws ParseException {
		DateUtils.stringToLocalDate(null);
	}
	
	@Test (expected = InvalidDateException.class)
	public void testParserDate12() throws ParseException {
		DateUtils.stringToLocalDate(" ");
	}
	
	@Test
	public void testParserDate13() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("23 Feb 2017").equals(new LocalDate(2017, DateTimeConstants.FEBRUARY, 23)));
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate14() throws ParseException {
		DateUtils.stringToLocalDate("23 Fev 2017");
	}	
	
	@Test
	public void testParserDate15() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("23 dec 2017").equals(new LocalDate(2017, DateTimeConstants.DECEMBER, 23)));
	}
	
	@Test
	public void testParserDate16() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("23 DEC 2017").equals(new LocalDate(2017, DateTimeConstants.DECEMBER, 23)));
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate17() throws ParseException {
		DateUtils.stringToLocalDate("33 DEC 2017");
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate18() throws ParseException {
		DateUtils.stringToLocalDate("29 feb 2017");
	}
	
	@Test (expected = ParseException.class)
	public void testParserDate19() throws ParseException {
		DateUtils.stringToLocalDate("-29 feb 2017");
	}
	
	@Test
	public void testParserDate20() throws ParseException {
		assertTrue(DateUtils.stringToLocalDate("28 feb 2022").equals(new LocalDate(2022, DateTimeConstants.FEBRUARY, 28)));
	}

}
