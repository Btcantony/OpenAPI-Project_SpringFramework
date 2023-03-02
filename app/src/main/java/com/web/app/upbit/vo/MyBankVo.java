package com.web.app.upbit.vo;

public class MyBankVo {
	private String currency;
	private String balance;
	private String locked;
	private String avg_buy_price;
	private boolean avg_buy_price_modified;
	private String unit_currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getAvg_buy_price() {
		return avg_buy_price;
	}

	public void setAvg_buy_price(String avg_buy_price) {
		this.avg_buy_price = avg_buy_price;
	}

	public boolean isAvg_buy_price_modified() {
		return avg_buy_price_modified;
	}

	public void setAvg_buy_price_modified(boolean avg_buy_price_modified) {
		this.avg_buy_price_modified = avg_buy_price_modified;
	}

	public String getUnit_currency() {
		return unit_currency;
	}

	public void setUnit_currency(String unit_currency) {
		this.unit_currency = unit_currency;
	}

}
