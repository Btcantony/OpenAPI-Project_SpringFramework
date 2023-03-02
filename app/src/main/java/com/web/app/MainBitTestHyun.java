package com.web.app;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.web.app.upbit.run.Api;
import com.web.app.upbit.run.ApiHyun;
import com.web.app.upbit.vo.MarketVo;
import com.web.app.upbit.vo.MyBankVo;
import com.web.app.upbit.vo.OrderVo;
import com.web.app.upbit.vo.TickerVo;

public class MainBitTestHyun {
	public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
		
		// Cmd 생성
		ApiHyun api = new ApiHyun();
		//api.cancel("05d476ec-4b42-43d0-b669-dce158d33a6c");
		// 주문 객체 생성
		OrderVo orderVo = new OrderVo();
		orderVo.setMarket("KRW-BTC");
		orderVo.setOrd_type("limit");
		orderVo.setPrice("5000");
		orderVo.setSide("bid");
		orderVo.setVolume("1");
		
		// 주문 실행
		api.orders(orderVo);
		
		
		// 모든 마켓 정보 수신
		/*List<MarketVo> listMarketVo =  api.markets();*/
		
		// 모든 마켓 실시간 수신
		/*while (true) {
			for (MarketVo v : listMarketVo) {
				if(v.getMarket().substring(0, 3).equals("KRW")) {
					if(v.getMarket().equals("KRW-VET")) {
						Thread.sleep(100);
						List<TickerVo> listTickerVo = api.ticker(v.getMarket());
						System.out.println(new Gson().toJson(listTickerVo));
						// 저장 하는걸 
					}
				}
			}
		}*/

		/*Api api = new Api();
		while (true) {
			Thread.sleep(100);
			List<TickerVo> listTickerVo = api.ticker("KRW-VET");
			System.out.println(new Gson().toJson(listTickerVo));
		}*/
		//List<MyBankVo> listMyBankVo = api.accounts();
		/*String markets = "";
		int i = 0;
		List<MarketVo> listMarketVo =  api.markets();
		for (MarketVo v : listMarketVo) {
			if(v.getMarket().substring(0, 3).equals("KRW")) {
				//System.out.println("["+v.getMarket()+"]["+v.getEnglish_name()+"]["+v.getKorean_name()+"]");
				if(i==0) markets += v.getMarket();
				else markets += ","+v.getMarket();
				i++;
			}
		}*/
		//System.out.println(new Gson().toJson(listMyBankVo));
		//List<TickerVo> listTickerVo = api.ticker(markets);
		//for (TickerVo v : listTickerVo) {
		//	System.out.println("["+v.getMarket()+"]["+v.getTrade_date()+"]["+v.getTrade_price()+"]");
		//}
	}
}
