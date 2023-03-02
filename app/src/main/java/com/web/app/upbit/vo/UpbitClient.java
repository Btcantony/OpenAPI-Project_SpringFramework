package com.web.app.upbit.vo;

import java.util.List;

public class UpbitClient {
	private double krw_money;
	private double all_money;
	private List<TickerVo> CashMemoryListTickerVo;

	public double getKrw_money() {
		return krw_money;
	}

	public void setKrw_money(double krw_money) {
		this.krw_money = krw_money;
	}

	public double getAll_money() {
		return all_money;
	}

	public void setAll_money(double all_money) {
		this.all_money = all_money;
	}

	public List<TickerVo> getCashMemoryListTickerVo() {
		return CashMemoryListTickerVo;
	}

	public void setCashMemoryListTickerVo(List<TickerVo> cashMemoryListTickerVo) {
		CashMemoryListTickerVo = cashMemoryListTickerVo;
	}

}
