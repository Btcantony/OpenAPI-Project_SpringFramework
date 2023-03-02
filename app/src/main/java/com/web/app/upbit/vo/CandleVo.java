package com.web.app.upbit.vo;

public class CandleVo {
	private String market;
	private String candle_date_time_utc;
	private String candle_date_time_kst;
	private Double opening_price;
	private Double high_price;
	private Double low_price;
	private Double trade_price;
	private Long timestamp;
	private Double candle_acc_trade_price;
	private Double candle_acc_trade_volume;
	private int unit;
	
	
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCandle_date_time_utc() {
		return candle_date_time_utc;
	}
	public void setCandle_date_time_utc(String candle_date_time_utc) {
		this.candle_date_time_utc = candle_date_time_utc;
	}
	public String getCandle_date_time_kst() {
		return candle_date_time_kst;
	}
	public void setCandle_date_time_kst(String candle_date_time_kst) {
		this.candle_date_time_kst = candle_date_time_kst;
	}
	public Double getOpening_price() {
		return opening_price;
	}
	public void setOpening_price(Double opening_price) {
		this.opening_price = opening_price;
	}
	public Double getHigh_price() {
		return high_price;
	}
	public void setHigh_price(Double high_price) {
		this.high_price = high_price;
	}
	public Double getLow_price() {
		return low_price;
	}
	public void setLow_price(Double low_price) {
		this.low_price = low_price;
	}
	public Double getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Double getCandle_acc_trade_price() {
		return candle_acc_trade_price;
	}
	public void setCandle_acc_trade_price(Double candle_acc_trade_price) {
		this.candle_acc_trade_price = candle_acc_trade_price;
	}
	public Double getCandle_acc_trade_volume() {
		return candle_acc_trade_volume;
	}
	public void setCandle_acc_trade_volume(Double candle_acc_trade_volume) {
		this.candle_acc_trade_volume = candle_acc_trade_volume;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	

	

}
