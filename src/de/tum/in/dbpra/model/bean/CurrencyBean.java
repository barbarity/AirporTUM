package de.tum.in.dbpra.model.bean;
import java.math.BigDecimal;

public class CurrencyBean {

	private String currencyCode;
	private String name;
	private String symbol;
	private BigDecimal priceInDollar;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode=currencyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public BigDecimal getPriceInDollar() {
		return priceInDollar;
	}

	public void setPriceInDollar(BigDecimal priceInDollar) {
		this.priceInDollar = priceInDollar;
	}
}

