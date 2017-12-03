package uk.com.tradecompany.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.com.tradecompany.exception.InvalidCurrencyValueException;
import uk.com.tradecompany.model.currency.ArabCurrency;
import uk.com.tradecompany.model.currency.RegularCurrency;

public class CurrencyFactoryTest {
	
	@Test
	public void createArabCurrencyInstance() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("AED") instanceof ArabCurrency );
	}
	
	@Test
	public void createRegularCurrencyInstance() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("BRL") instanceof RegularCurrency );
	}
	
	@Test
	public void createGBPCurrencyInstance() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("gbp") instanceof RegularCurrency );
	}
	
	@Test 
	public void createArabCurrencyWithLowerCase() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("aed") instanceof ArabCurrency);
	}
	
	@Test 
	public void createArabCurrencyMixinUpperAndLowerCase() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("aEd") instanceof ArabCurrency);
	}
	
	@Test 
	public void createArabCurrencyWithBlanckSpace() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("aEd ") instanceof ArabCurrency);
	}
	
	@Test 
	public void createArabCurrencyWithBlanckSpaces() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader(" aEd ") instanceof ArabCurrency);
	}
	
	@Test 
	public void createRegularCurrencyWithBlanckSpaces() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader(" IlS ") instanceof RegularCurrency);
	}
	
	@Test 
	public void createRegularIntanceWithLoweCase() throws InvalidCurrencyValueException {
		assertTrue(CurrencyFactory.createTrader("brl") instanceof RegularCurrency );
	}
	
	@Test (expected = InvalidCurrencyValueException.class)
	public void createInstanceWithNullValue() throws InvalidCurrencyValueException {
		CurrencyFactory.createTrader(null);
	}
	
	@Test (expected = InvalidCurrencyValueException.class)
	public void createInstanceWithEmptyValue() throws InvalidCurrencyValueException {
		CurrencyFactory.createTrader("");
	}
	
	@Test
	public void createInstanceWithInvalidValue() {
		try {
			CurrencyFactory.createTrader("@@@@###");
		} catch(Exception e) {
			assertTrue(e instanceof InvalidCurrencyValueException);
		}
	}
	
	@Test (expected = InvalidCurrencyValueException.class)  
	public void createInstanceWithInvalidValue2() throws InvalidCurrencyValueException {
		CurrencyFactory.createTrader("Dollar");
	}
	
	@Test
	public void createInstanceWithInvalidValue3() {
		try {
			CurrencyFactory.createTrader("123");
		} catch(Exception e) {
			assertTrue(e instanceof InvalidCurrencyValueException);
		}
	}
	
	@Test 
	public void createRegularIntanceWithLoweCaseM() throws InvalidCurrencyValueException {
		
		assertTrue(CurrencyFactory.createTrader("brl") instanceof RegularCurrency );
	}
		
}