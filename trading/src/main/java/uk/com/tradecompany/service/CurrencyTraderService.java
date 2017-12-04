package uk.com.tradecompany.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.exception.InvalidOperationException;
import uk.com.tradecompany.exception.InvalidTradingValueException;
import uk.com.tradecompany.model.trade.OperationType;
import uk.com.tradecompany.model.trade.TradingDomain;
import uk.com.tradecompany.report.ReportDataBase;

public class CurrencyTraderService {

	private ReportDataBase reportDataBase;

	public void startTrading(Double agreedFx, String instructionDate, String settlementDate, Long quantity, Double priceUnity, String operation, String currency, String entity) throws ParseException, InvalidCurrencyValueException, InvalidOperationException, InvalidTradingValueException {
		this.reportDataBase = ReportDataBase.getInstance();
		TradingDomain tradingDomain = new TradingDomain(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation, currency, entity);
		this.reportDataBase.addTrading(tradingDomain);
	}

	public static void main(String[] args) {
		CurrencyTraderService currencyTradeService = new CurrencyTraderService();
		currencyTradeService.reportDataBase = ReportDataBase.getInstance();

		initializeMockDate(currencyTradeService);
		ArrayList<TradingDomain> tradingList = currencyTradeService.reportDataBase.getTradingList();
		
		currencyTradeService.insertDataIntoReportDataBase(tradingList);
		
		currencyTradeService.reportDataBase.printEntries();
	}

	public void insertDataIntoReportDataBase(ArrayList<TradingDomain> tradingList ) {
		Set<String> entities = this.distinctEntities(tradingList);
		Set<LocalDate> localDates = this.distinctDates(tradingList);
		this.groupDataByEntity(entities, tradingList);
		this.groupDataBySettlementDate(localDates, tradingList);
	}

	private Set<LocalDate> distinctDates(ArrayList<TradingDomain> tradingList) {
		Set<LocalDate> localDates = new HashSet<>();
		tradingList.forEach(trade -> localDates.add(trade.getSettlementDate()));
		return localDates;
	}

	private Set<String> distinctEntities(ArrayList<TradingDomain> tradingList) {
		Set<String> entities = new HashSet<>();
		tradingList.forEach(trade -> entities.add(trade.getEntity()));
		return entities;
	}

	private void groupDataBySettlementDate(Set<LocalDate> localDates, ArrayList<TradingDomain> tradingList) {
		for (LocalDate distinctSettlementDate : localDates) {
			
			tradingList.stream().filter(trade -> trade.getSettlementDate().equals(distinctSettlementDate))
					.filter(trade -> trade.getOperation().equals(OperationType.Buy))
					.forEach(trade -> this.reportDataBase.addDailyIncoming(distinctSettlementDate,
							trade.getTransactionAmount()));
			
			tradingList.stream().filter(trade -> trade.getSettlementDate().equals(distinctSettlementDate))
			.filter(trade -> trade.getOperation().equals(OperationType.Sell))
			.forEach(trade -> this.reportDataBase.addDailyOutcoming(distinctSettlementDate,
					trade.getTransactionAmount()));
		}
	}

	private void groupDataByEntity(Set<String> entities, ArrayList<TradingDomain> tradingList ) {
		for (String distinctEntity : entities) {
			tradingList.stream().filter(trade -> trade.getEntity().equals(distinctEntity))
					.filter(trade -> trade.getOperation().equals(OperationType.Buy)).forEach(trade -> this.reportDataBase
							.addIncomingPerEntity(distinctEntity, trade.getTransactionAmount()));
			
			tradingList.stream().filter(trade -> trade.getEntity().equals(distinctEntity))
			.filter(trade -> trade.getOperation().equals(OperationType.Sell))
			.forEach(trade -> this.reportDataBase.addOutcomingPerEntity(distinctEntity, trade.getTransactionAmount()));
		}
	}
	
	private static void initializeMockDate(CurrencyTraderService cts) {
		Double agreedFx = 0.50;
		String instructionDate = "01 Jan 2016";
		String settlementDate = "02 Jan 2016";
		Long quantity = new Long(200);
		Double priceUnity = 100.25;
		String operation = "B";
		String currency = "SGP";
		String entity = "foo";

		try {
			cts.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation,
					currency, entity);
			
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
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
			cts.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation,
					currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
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
			cts.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation,
					currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
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
			cts.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation,
					currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
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

		try {
			cts.startTrading(agreedFx, instructionDate, settlementDate, quantity, priceUnity, operation,	currency, entity);
		} catch (ParseException | InvalidCurrencyValueException | InvalidOperationException | InvalidTradingValueException e) {
			e.printStackTrace();
		}
	}

}
