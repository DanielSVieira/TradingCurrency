package uk.com.tradecompany.interfaces;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.exception.InvalidOperationException;
import uk.com.tradecompany.exception.InvalidTradingValueException;
import uk.com.tradecompany.model.trade.TradingDomain;

public class ICurrencyTraderTest {
	
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
	public void setup() {
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
	public void testTotalCost() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx());
		assertTrue(new BigDecimal(10025).compareTo( tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx()).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0);
	}
	
	@Test
	public void testTotalCost2() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.quantity = new Long(5000);
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx());
		assertTrue(new BigDecimal(250625).compareTo( tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx()).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0);		
	}
	
	@Test
	public void testTotalCost3() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.quantity = new Long(5000);
		this.agreedFx = 0.32;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx());
				
		assertTrue(new BigDecimal(160400).compareTo( tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx()).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0);		
	}
	
	@Test (expected = InvalidTradingValueException.class)
	public void testTotalCost4() throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.quantity = null;
		this.agreedFx = 0.32;
		tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
	
		assertTrue(new BigDecimal(160400).compareTo( tradingDomain.getCurrencyTrader().calculateCost(tradingDomain.getQuantity(), tradingDomain.getPriceUnity(), tradingDomain.getAgreedFx()).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0);		
	}

}
