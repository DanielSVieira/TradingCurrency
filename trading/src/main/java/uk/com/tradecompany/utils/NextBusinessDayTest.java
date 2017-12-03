package uk.com.tradecompany.utils;

import java.util.HashSet;


import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

import org.joda.time.LocalDate;


public class NextBusinessDayTest {

    private static DateCalculator<LocalDate> dateCalculator;
    private static final LocalDate startDate = new LocalDate(2017, 12, 01);

    public static void main(String[] args) {
		setUp();
		testNextBusinessDay();
	}

    public static void setUp() {
        HashSet<LocalDate> holidays = new HashSet<LocalDate>();
        holidays.add(new LocalDate(2012, 2, 10));  // Friday
        holidays.add(new LocalDate(2012, 2, 14));  // Tuesday

        DefaultHolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>(holidays);

        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("holidays", holidayCalendar);
        dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("holidays", HolidayHandlerType.FORWARD);
        LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("holidays", HolidayHandlerType.FORWARD);
        JodaWorkingWeek workingWeek = JodaWorkingWeek.ARAB_DEFAULT; 
        dateCalculator.setWorkingWeek(workingWeek);
    }


    public static void testNextBusinessDay() {
        dateCalculator.setStartDate(startDate);
        System.out.println(startDate);

        dateCalculator.setStartDate(startDate);
        System.out.println(dateCalculator.getCurrentBusinessDate());
        
//        Assert.assertEquals(dateCalculator.moveByBusinessDays(2).getCurrentBusinessDate(),
//        new LocalDate(2012, 2, 15)); // also skips the 14th (holiday)
    }
}