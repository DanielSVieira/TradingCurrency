package uk.com.tradecompany.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import uk.com.tradecompany.interfaces.CurrencyTrader;
import uk.com.tradecompany.model.currency.ArabCurrency;
import uk.com.tradecompany.model.currency.RegularCurrency;

public final class CurrencyShortNames {

	public final static Map<String, Supplier<CurrencyTrader>> map = new HashMap<>();

	static {

		map.put("AED", ArabCurrency::new);
		map.put("SAR", ArabCurrency::new);
		map.put("USD", RegularCurrency::new);
		map.put("EUR", RegularCurrency::new);
		map.put("BRL", RegularCurrency::new);
		map.put("PLN", RegularCurrency::new);
		map.put("GBP", RegularCurrency::new);
		map.put("ILS", RegularCurrency::new);
		map.put("SGP", RegularCurrency::new);

	}

}
