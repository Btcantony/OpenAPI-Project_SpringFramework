package com.web.app.upbit.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.web.app.upbit.run.Api;
import com.web.app.upbit.service.UpbitService;
import com.web.app.upbit.util.NumberHelper;
import com.web.app.upbit.vo.CashMemory;
import com.web.app.upbit.vo.MarketVo;
import com.web.app.upbit.vo.MyBankVo;
import com.web.app.upbit.vo.TickerVo;

@Component
public class Task {		
	
	@Autowired private UpbitService upbitService;
	
	private static int running_cnt = 0;
	private static List<MarketVo> listMarketVo;
	private static Api api;
	private static boolean is_running = false;
	private String upbit_access_key = "";
	private String upbit_token_key = "";
	private String slack_access_key = "";
	private String slack_token_key = "";
	
	@Scheduled(fixedDelay = 100, initialDelay = 0)
	public void upbitMarket() throws IOException {		
		try {
			if(!is_running) {
				is_running = true;
				if(running_cnt == 0) {
					// Cmd 생성
					api = new Api();
					
					// 모든 마켓 정보 수신
					listMarketVo =  api.markets();
				}
				
				// 모든 마켓 실시간 수신
 				List<TickerVo> listTickerVo = new ArrayList<TickerVo>();
				for (MarketVo v : listMarketVo) {
					if(v.getMarket().substring(0, 3).equals("KRW")) {
						Thread.sleep(100);
						List<TickerVo> upbitlistTickerVo = api.ticker(v.getMarket());
						TickerVo tickerVo = upbitlistTickerVo.get(0);
					
						// Save
						//upbitService.insert_ticker(tickerVo);
						listTickerVo.add(tickerVo);
					}
				}
				CashMemory.CashMemoryListTickerVo = listTickerVo;
				System.out.println(new Gson().toJson(CashMemory.CashMemoryListTickerVo));
				// 나의 계좌 실시간 수신
				List<MyBankVo> accounts = api.accounts();
				double krw_money = 0;
				double market_money = 0;
				for (MyBankVo account : accounts) {
					Thread.sleep(100);
					if(account.getCurrency().equals("KRW")) krw_money = Double.parseDouble(account.getBalance());
					else market_money += Double.parseDouble(account.getBalance()) * Double.parseDouble(account.getAvg_buy_price());
					System.out.println(account.getAvg_buy_price());
				}
				CashMemory.krw_money = new NumberHelper().doubleDotdouble(krw_money,3);
				CashMemory.all_money = new NumberHelper().doubleDotdouble(market_money + krw_money,3);
				//System.out.println(CashMemory.krw_money + " : " + CashMemory.all_money);
				// 위 코인 정보를 슬랙 API 로 채팅창으로 보냄 
				//this.send_slack(CashMemory.CashMemoryListTickerVo);
				running_cnt++;
				is_running = false;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}					
	}

//	private void send_slack(List<TickerVo> cashMemoryListTickerVo) {
//		for (TickerVo tickerVo : cashMemoryListTickerVo) {
//			// "현재 비트코인은 500원입니다."
//			// "나의 금액이 100원 올랐습니다."
//		}
//		
//	} 
	
	// 토큰 : xoxb-4268999864340-4266486958467-A1y5ttjSOdMizw5ZcyJ61tgV
	// 채널 ID : C047X06F6G4
	
	//준현 슬랙 
	public void sendSlack() throws Exception  {
		String urlStr = "https://slack.com/api/chat.postMessage?";
		urlStr += "xoxb-4268999864340-4266486958467-A1y5ttjSOdMizw5ZcyJ61tgV&";
		urlStr += "C047X06F6G4";
		urlStr += "text="+URLEncoder.encode("{하이}", "UTF-8");

		HttpURLConnection conn = null;
		URL url = new URL(urlStr);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		conn.connect();
		new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	}
}
