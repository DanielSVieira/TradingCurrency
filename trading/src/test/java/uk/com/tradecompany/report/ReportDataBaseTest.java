package uk.com.tradecompany.report;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.exception.InvalidDateException;
import uk.com.tradecompany.exception.InvalidOperationException;
import uk.com.tradecompany.exception.InvalidTradingValueException;
import uk.com.tradecompany.service.CurrencyTraderService;

public class ReportDataBaseTest {

	private ReportDataBase reportDataBase = ReportDataBase.getInstance();
	private CurrencyTraderService currencyTraderService;
	
	@Before
	public void config() {
		currencyTraderService = new CurrencyTraderService();
	}
	
	@Test
	public void addTradesToReportViaService() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		Double agreedFx = 0.50;
		String instructionDate = "01 Jan 2016";
		String settlementDate = "02 Jan 2016";
		Long quantity = new Long(200);
		Double priceUnity = 100.25;
		String operation = "B";
		String currency = "SGP";
		String entity = "foo";

		currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
			
		agreedFx = 0.22;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(450);
		priceUnity = 150.5;
		operation = "S";
		currency = "AED";
		entity = "bar";

		currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);

		agreedFx = 0.0;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(150);
		priceUnity = 150.5;
		operation = "S";
		currency = "AED";
		entity = "bar";

		currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);

		agreedFx = 2.0;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(100);
		priceUnity = 2.0;
		operation = "S";
		currency = "AED";
		entity = "bar";

		currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);

		agreedFx = 0.13;
		instructionDate = "01 Dec 2017";
		settlementDate = "01 Dec 2017";
		quantity = new Long(450);
		priceUnity = 150.5;
		operation = "B";
		currency = "AED";
		entity = "Daniel";
		
		assertEquals(reportDataBase.getTradingList().size(), 4);

	}
	
	
	@Test (expected = InvalidDateException.class)
	public void addInvalidTradeViaService() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		Double agreedFx = 0.50;
		String instructionDate = "03 Jan 2016";
		String settlementDate = "02 Jan 2016";
		Long quantity = new Long(200);
		Double priceUnity = 100.25;
		String operation = "B";
		String currency = "SGP";
		String entity = "foo";

		currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	
	@Test (expected = InvalidDateException.class)
	public void add3ValidTradesAnd1InvalidTradeViaService()  {
		Double agreedFx = 0.50;
		String instructionDate = "03 Jan 2016";
		String settlementDate = "02 Jan 2016";
		Long quantity = new Long(200);
		Double priceUnity = 100.25;
		String operation = "B";
		String currency = "SGP";
		String entity = "foo";

		try {
			currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		agreedFx = 0.22;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(450);
		priceUnity = 150.5;
		operation = "S";
		currency = "AED";
		entity = "bar";

		try {
			currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		agreedFx = 0.0;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(150);
		priceUnity = 150.5;
		operation = "S";
		currency = "AED";
		entity = "bar";

		try {
			currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		agreedFx = 2.0;
		instructionDate = "05 Jan 2016";
		settlementDate = "07 Jan 2016";
		quantity = new Long(100);
		priceUnity = 2.0;
		operation = "S";
		currency = "AED";
		entity = "bar";

		try {
			currencyTraderService.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		agreedFx = 0.13;
		instructionDate = "01 Dec 2017";
		settlementDate = "01 Dec 2017";
		quantity = new Long(450);
		priceUnity = 150.5;
		operation = "B";
		currency = "AED";
		entity = "Daniel";
		
		assertEquals(reportDataBase.getTradingList().size(), 3);
	}
	
}
