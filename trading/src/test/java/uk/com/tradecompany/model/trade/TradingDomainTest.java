package uk.com.tradecompany.model.trade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.exception.InvalidDateException;
import uk.com.tradecompany.exception.InvalidOperationException;
import uk.com.tradecompany.exception.InvalidTradingValueException;
import uk.com.tradecompany.model.currency.ArabCurrency;
import uk.com.tradecompany.model.currency.RegularCurrency;
import uk.com.tradecompany.model.trade.TradingDomain;

public class TradingDomainTest {
	
	private TradingDomain tradingDomain;
	private Double agreedFx; 
	private String instructionDate;
	private String settlementDate;
	private Long quantity;
	private Double priceUnity;
	private String operation;
	private String currency;
	private String entity;
	
	@Before
	public void prepare() {
		this.agreedFx = 0.50;
		this.instructionDate = "01 Jan 2016";
		this.settlementDate = "02 Jan 2016";
		this.quantity = new Long(200);
		this.priceUnity = 100.25;
		this.operation = "S";
		this.currency = "SGP";
		this.entity = "foo";
	}
	
	@Test
	public void constructorTest() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertTrue(tradingDomain instanceof TradingDomain);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInvalidDate() throws ParseException, InvalidCurrencyValueException, InvalidOperationException,InvalidTradingValueException {
		this.instructionDate = "";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test
	public void constructorTestSameDate() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "02 Jan 2016";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertTrue(tradingDomain instanceof TradingDomain);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInstructionDateBeforeSettlementDate() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "01 Jan 2016";
		this.settlementDate = "02 Jan 2015";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInstructionDateBeforeSettlementDate2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "29 Jul 2016";
		this.settlementDate = "28 jul 2016";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInstructionDateBeforeSettlementDate3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "29 Jul 2016";
		this.settlementDate = "28 jul 2016";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInstructionDateBeforeSettlementDate4() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "01 may 2016";
		this.settlementDate = "30 apr 2016";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidDateException.class)
	public void constructorTestInstructionDateBeforeSettlementDate5() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.instructionDate = "01 may 2016";
		this.settlementDate = "";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidOperationException.class)
	public void constructorTestUnkownOperation() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "T";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	
	@Test (expected = InvalidOperationException.class)
	public void constructorTestUnkownOperation2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = null;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidOperationException.class)
	public void constructorTestUnkownOperation3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "Sell";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}	
	
	@Test
	public void constructorTestValidOperation() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "S";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertEquals(tradingDomain.getOperation(), OperationType.Sell);
	}
	
	@Test
	public void constructorTestValidOperation2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "B";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertEquals(tradingDomain.getOperation(), OperationType.Buy);
	}
	
	@Test
	public void constructorTestValidOperation3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "b";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertEquals(tradingDomain.getOperation(), OperationType.Buy);
	}
	
	@Test
	public void constructorTestValidOperation4() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		operation = "s";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertEquals(tradingDomain.getOperation(), OperationType.Sell);
	}
	
	@Test (expected = InvalidCurrencyValueException.class)
	public void constructorTestInvalidCurrency() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		currency = "Dollar";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidCurrencyValueException.class)
	public void constructorTestInvalidCurrency2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		currency = "";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidTradingValueException.class)
	public void constructorNullValue1() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.agreedFx = null;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidTradingValueException.class)
	public void constructorNullValue2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.priceUnity = null;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test (expected = InvalidTradingValueException.class)
	public void constructorNullValue3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.priceUnity = null;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}	
	
	@Test (expected = InvalidCurrencyValueException.class)
	public void constructorTestInvalidCurrency3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		currency = null;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	}
	
	@Test 
	public void constructorTestRegularCurrency() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		currency = "USD";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertTrue(tradingDomain.getCurrencyTrader() instanceof RegularCurrency);
	}
	
	@Test 
	public void constructorTestArabCurrency() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		currency = "SAR";
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		assertTrue(tradingDomain.getCurrencyTrader() instanceof ArabCurrency);
	}

}
