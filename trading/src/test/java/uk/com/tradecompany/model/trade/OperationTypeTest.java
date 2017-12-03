package uk.com.tradecompany.model.trade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperationTypeTest {
	
	@Test
	public void getSellOperationType() {
		assertEquals(OperationType.getEnum("S"), OperationType.Sell);
	}
	
	@Test
	public void getBuyOperationType() {
		assertEquals(OperationType.getEnum("B"), OperationType.Buy);
	}
	
	@Test
	public void getBuyOperationTypeLowerCase() {
		assertEquals(OperationType.getEnum("b"), OperationType.Buy);
	}
	
	@Test
	public void getSellOperationTypeLowerCase() {
		assertEquals(OperationType.getEnum("s"), OperationType.Sell);
	}
	
	@Test
	public void getUnkownOperation() {
		assertEquals(OperationType.getEnum("Sell"), null);
	}
	
	@Test
	public void getUnkownOperation2() {
		assertEquals(OperationType.getEnum("Buy"), null);
	}
	
	@Test
	public void getBuyOperationWithNullValue() {
		assertEquals(OperationType.getEnum(null), null);
	}
	
	@Test
	public void getBuyOperationWithEmotylValue() {
		assertEquals(OperationType.getEnum(""), null);
	}

}
