package uk.com.tradecompany.model.currency;


import java.util.HashSet;

import org.joda.time.LocalDate;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;
import uk.com.tradecompany.interfaces.CurrencyTrader;

public class RegularCurrency implements CurrencyTrader{
	
	private DateCalculator<LocalDate> dateCalculator;

	public LocalDate getNextAvailableDate(LocalDate localDate) {
        HashSet<LocalDate> holidays = new HashSet<LocalDate>();


        DefaultHolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>(holidays);

        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("holidays", holidayCalendar);
        dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("holidays", HolidayHandlerType.FORWARD);
        LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("holidays", HolidayHandlerType.FORWARD);
        JodaWorkingWeek workingWeek = JodaWorkingWeek.DEFAULT; 
        dateCalculator.setWorkingWeek(workingWeek);
        
        dateCalculator.setStartDate(localDate);
        return dateCalculator.getCurrentBusinessDate();
	}



}
