package uk.com.tradecompany.interfaces;

import java.math.BigDecimal;

import org.joda.time.LocalDate;


@FunctionalInterface
public interface CurrencyTrader {

	LocalDate getNextAvailableDate(LocalDate localDate);
	
	
	default BigDecimal calculateCost(Long quantity, BigDecimal priceUnity, BigDecimal agreedFx) {
        return priceUnity.multiply(new BigDecimal(quantity)).multiply(agreedFx);
	}
	
//	default BigDecimal calculateCost(TradingDomain tradingDomain) {
//	    BigDecimal itemCost  = BigDecimal.ZERO;
//	    BigDecimal totalCost = BigDecimal.ZERO;
//	    
//        itemCost  = tradingDomain.getPriceUnity().multiply(new BigDecimal(tradingDomain.getQuantity()));
//        totalCost = totalCost.add(itemCost);
//        
//        return totalCost;
//	}
	
}
