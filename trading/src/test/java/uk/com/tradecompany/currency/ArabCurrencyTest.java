package uk.com.tradecompany.currency;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.factory.CurrencyFactory;
import uk.com.tradecompany.interfaces.CurrencyTrader;

public class ArabCurrencyTest {
	
	CurrencyTrader currencyTrader;
	
	@Before
	public void setup() throws InvalidCurrencyValueException {
		this.currencyTrader = CurrencyFactory.createTrader("AED");
	}
	
	@Test
	public void testMondayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 4);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 4);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testTuesdayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 5);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 5);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testWednesdayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 6);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 6);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	
	@Test
	public void testThursdayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 7);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 7);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testFridayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 8);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 10);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testSaturdayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 9);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 10);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testSundayArabDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 10);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.SEPTEMBER, 10);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testChristianHolidayDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.DECEMBER, 25);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.DECEMBER, 25);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}	
	
	@Test
	public void testUniversalHolidayDate() {
		LocalDate requestedDate = new LocalDate(2018, DateTimeConstants.JANUARY, 1);
		LocalDate nextWorkingDayDate = new LocalDate(2018, DateTimeConstants.JANUARY, 1);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}
	
	@Test
	public void testArabHolidayDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.JUNE, 27);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.JUNE, 27);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}	
	
	@Test
	public void testRamadanHolidayDate() {
		LocalDate requestedDate = new LocalDate(2017, DateTimeConstants.MAY, 25);
		LocalDate nextWorkingDayDate = new LocalDate(2017, DateTimeConstants.MAY, 25);
		assertEquals(currencyTrader.getNextAvailableDate(requestedDate), nextWorkingDayDate);
	}	

}
