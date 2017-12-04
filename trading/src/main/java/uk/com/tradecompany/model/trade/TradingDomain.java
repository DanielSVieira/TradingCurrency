package uk.com.tradecompany.model.trade;

import java.math.BigDecimal;
import java.text.ParseException;

import org.joda.time.LocalDate;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.exception.InvalidDateException;
import uk.com.tradecompany.exception.InvalidOperationException;
import uk.com.tradecompany.exception.InvalidTradingValueException;
import uk.com.tradecompany.factory.CurrencyFactory;
import uk.com.tradecompany.interfaces.CurrencyTrader;
import uk.com.tradecompany.utils.DateUtils;

public class TradingDomain  {
	
	private BigDecimal agreedFx;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Long quantity;
	private BigDecimal priceUnity;
	private OperationType operation;
	private String currency;
	private String entity;
	private CurrencyTrader currencyTrader;
	private BigDecimal transactionAmount;
	
	public TradingDomain(Double agreedFxRaw, String instructionDateRaw, String settlementDateRaw, Long quantity, Double priceUnityRaw, String operationRaw, String currency, String entity) throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		LocalDate instructionDate = DateUtils.stringToLocalDate(instructionDateRaw);
		LocalDate settlementDate = DateUtils.stringToLocalDate(settlementDateRaw);
		if(settlementDate.isBefore(instructionDate)) {
			throw new InvalidDateException("Settlement date cannot happen before instruction date");
		}
		if(OperationType.getEnum(operationRaw) == null) {
			throw new InvalidOperationException("Unknown operation informed.");
		}
		if(agreedFxRaw == null || quantity == null || priceUnityRaw == null) {
			throw new InvalidTradingValueException("At least one needed value was not provided to calculate the amount of this transaction");
		}
		
		this.currencyTrader = CurrencyFactory.createTrader(currency);
		this.agreedFx = new BigDecimal(agreedFxRaw);
		this.instructionDate = instructionDate;
		this.settlementDate = this.currencyTrader.getNextAvailableDate(settlementDate);
		this.quantity = quantity;
		this.priceUnity = new BigDecimal(priceUnityRaw);
		this.operation = OperationType.getEnum(operationRaw);
		this.currency = currency;
		this.entity = entity;
		this.transactionAmount = this.currencyTrader.calculateCost(this.quantity, this.priceUnity, this.agreedFx);
	}
	
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceUnity() {
		return priceUnity;
	}
	public void setPriceUnity(BigDecimal priceUnity) {
		this.priceUnity = priceUnity;
	}
	public OperationType getOperation() {
		return operation;
	}
	public void setOperation(OperationType operation) {
		this.operation = operation;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public CurrencyTrader getCurrencyTrader() {
		return currencyTrader;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
