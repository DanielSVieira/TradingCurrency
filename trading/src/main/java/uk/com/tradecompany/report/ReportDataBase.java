package uk.com.tradecompany.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import uk.com.tradecompany.model.trade.TradingDomain;

public class ReportDataBase {

	private static ReportDataBase instance;
	private ArrayList<TradingDomain> tradingList = new ArrayList<>();
	private Map<LocalDate, BigDecimal> dailyIncoming = new HashMap<LocalDate, BigDecimal>();
	private Map<LocalDate, BigDecimal> dailyOutcoming = new HashMap<LocalDate, BigDecimal>();
	private Map<String, BigDecimal> incomingPerEntity = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> outcomingPerEntity = new HashMap<String, BigDecimal>();

	private ReportDataBase() {
	}

	public static ReportDataBase getInstance() {
		if (instance == null) {
			instance = new ReportDataBase();
		}
		return instance;
	}

	public void addTrading(TradingDomain tradingDomain) {
		this.tradingList.add(tradingDomain);
	}

	public ArrayList<TradingDomain> getTradingList() {
		return this.tradingList;
	}

	public void addDailyIncoming(LocalDate settlementDate, BigDecimal transactionAmount) {
		if (this.dailyOutcoming.containsKey(settlementDate)) {
			this.dailyOutcoming.put(settlementDate, this.dailyOutcoming.get(settlementDate).add(transactionAmount));
		} else {
			this.dailyOutcoming.put(settlementDate, transactionAmount);
		}
	}

	public void addDailyOutcoming(LocalDate settlementDate, BigDecimal transactionAmount) {
		if (this.dailyIncoming.containsKey(settlementDate)) {
			this.dailyIncoming.put(settlementDate, this.dailyIncoming.get(settlementDate).add(transactionAmount));
		} else {
			this.dailyIncoming.put(settlementDate, transactionAmount);
		}
	}

	public void addIncomingPerEntity(String entity, BigDecimal transactionAmount) {
		if (this.incomingPerEntity.containsKey(entity)) {
			this.incomingPerEntity.put(entity, this.incomingPerEntity.get(entity).add(transactionAmount));
		} else {
			this.incomingPerEntity.put(entity, transactionAmount);
		}

	}

	public void addOutcomingPerEntity(String entity, BigDecimal transactionAmount) {
		if (this.outcomingPerEntity.containsKey(entity)) {
			this.outcomingPerEntity.put(entity, this.outcomingPerEntity.get(entity).add(transactionAmount));
		} else {
			this.outcomingPerEntity.put(entity, transactionAmount);
		}
	}

	public void printEntries() {
		Map<String, BigDecimal> result2 = new LinkedHashMap<>();
		this.incomingPerEntity.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

		this.incomingPerEntity = this.incomingPerEntity.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		this.incomingPerEntity = orderByValue(incomingPerEntity);
		this.outcomingPerEntity = orderByValue(outcomingPerEntity);

		this.incomingPerEntity.forEach((entity, amount) -> {
			System.out.println("Entity : " + entity + " bought : " + amount);
		});

		this.outcomingPerEntity.forEach((entity, amount) -> {
			System.out.println("Entity : " + entity + " sold : " + amount);
		});

		this.dailyIncoming.forEach((localDate, amount) -> {
			System.out.println("At the day " + localDate + " there was " + amount + " USD incomig.");
		});

		this.dailyOutcoming.forEach((localDate, amount) -> {
			System.out.println("At the day " + localDate + " there was " + amount + " USD outcomig.");
		});
	}

	private Map<String, BigDecimal> orderByValue(Map<String, BigDecimal> unsortedMap) {
		return unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
	}

}
