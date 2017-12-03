package uk.com.tradecompany.model.trade;

import java.util.HashMap;
import java.util.Map;

import uk.com.tradecompany.utils.StringUtils;

public enum OperationType {
	Sell("S"), Buy("B");

	private String shortDescription;

	private static Map<String, OperationType> map = new HashMap<String, OperationType>();

	static {
		for (OperationType operation : OperationType.values()) {
			map.put(operation.shortDescription, operation);
		}
	}

	private OperationType(final String operation) {
		this.shortDescription = operation;
	}

	public static OperationType getEnum(String operation) {
		return map.get(StringUtils.uperCase(operation));
	}

}
