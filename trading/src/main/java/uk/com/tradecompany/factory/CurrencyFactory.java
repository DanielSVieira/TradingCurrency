package uk.com.tradecompany.factory;

import java.util.function.Supplier;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.interfaces.CurrencyTrader;
import uk.com.tradecompany.utils.CurrencyShortNames;
import uk.com.tradecompany.utils.StringUtils;

public class CurrencyFactory {

	public static CurrencyTrader createTrader(String currency) throws InvalidCurrencyValueException {

		if(StringUtils.isNullOrBlank(currency)) {
			throw new InvalidCurrencyValueException("Null or empty values are unacceptable.");
		}
		
		Supplier<CurrencyTrader> currencyTrader = CurrencyShortNames.map.get(StringUtils.upperCaseWithNoBlankSpaces(currency));

		if (currencyTrader != null) {
			return currencyTrader.get();
		}

		throw new InvalidCurrencyValueException("Sorry, we do not trade " + StringUtils.upperCaseWithNoBlankSpaces(currency) + " currency yet.");
	}

}
