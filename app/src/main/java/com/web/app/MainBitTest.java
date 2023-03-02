package com.web.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.api.ApiTestResponse;
import com.web.app.upbit.run.Api;
import com.web.app.upbit.util.NumberHelper;
import com.web.app.upbit.vo.CashMemory;
import com.web.app.upbit.vo.MarketVo;
import com.web.app.upbit.vo.MyBankVo;
import com.web.app.upbit.vo.OrderVo;
import com.web.app.upbit.vo.TickerVo;

public class MainBitTest {
	public static void main(String[] args)
			throws IOException, InterruptedException, NoSuchAlgorithmException, SlackApiException {
		/*
		try {
			String urlStr = "https://slack.com/api/chat.postMessage?";
			urlStr += "token=123456&";
			urlStr += "channel=123456";
			urlStr += "text=" + URLEncoder.encode("안녕하세요", "UTF-8");

			HttpURLConnection conn = null;
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("GET");
			conn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		    String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response.toString());
			in.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}*/

		// Cmd 생성
		//double d = 32905.06115571;
		//System.out.print(Math.floor(d * 1000)/1000);
		Api api = new Api();
		List<MyBankVo> accounts = api.accounts();
		double krw_money = 0;
		double all_money = 0;
		for (MyBankVo account : accounts) {
			if(account.getCurrency().equals("KRW")) krw_money = Double.parseDouble(account.getBalance());
			else all_money += Double.parseDouble(account.getBalance()) * Double.parseDouble(account.getAvg_buy_price());
		}
		System.out.println("보유 KRW : " + new NumberHelper().doubleDotdouble(krw_money, 3) + "  총 보유자산 : " + Math.floor((krw_money+all_money) * 1000)/1000);
		// api.cancel("05d476ec-4b42-43d0-b669-dce158d33a6c");
		// 주문 객체 생성
		/*
		 * OrderVo orderVo = new OrderVo(); orderVo.setMarket("KRW-GAS");
		 * orderVo.setOrd_type("limit"); orderVo.setPrice("50000");
		 * orderVo.setSide("ask"); orderVo.setVolume("20");
		 * 
		 * // 주문 실행 api.orders(orderVo);
		 */

		// 모든 마켓 정보 수신
		// List<MarketVo> listMarketVo = api.markets();

		// 모든 마켓 실시간 수신
		/*
		 * while (true) { for (MarketVo v : listMarketVo) {
		 * if(v.getMarket().substring(0, 3).equals("KRW")) {
		 * if(v.getMarket().equals("KRW-BTC")) { Thread.sleep(100); List<TickerVo>
		 * listTickerVo = api.ticker(v.getMarket()); //System.out.println(new
		 * Gson().toJson(listTickerVo)); // 저장 하는걸 } } } }
		 */

		/*
		 * Api api = new Api(); while (true) { Thread.sleep(100); List<TickerVo>
		 * listTickerVo = api.ticker("KRW-ORBS"); System.out.println(new
		 * Gson().toJson(listTickerVo)); }
		 */
		// List<MyBankVo> listMyBankVo = api.accounts();
		/*
		 * String markets = ""; int i = 0; List<MarketVo> listMarketVo = api.markets();
		 * for (MarketVo v : listMarketVo) { if(v.getMarket().substring(0,
		 * 3).equals("KRW")) {
		 * //System.out.println("["+v.getMarket()+"]["+v.getEnglish_name()+"]["+v.
		 * getKorean_name()+"]"); if(i==0) markets += v.getMarket(); else markets +=
		 * ","+v.getMarket(); i++; } }
		 */
		// System.out.println(new Gson().toJson(listMyBankVo));
		// List<TickerVo> listTickerVo = api.ticker(markets);
		// for (TickerVo v : listTickerVo) {
		// System.out.println("["+v.getMarket()+"]["+v.getTrade_date()+"]["+v.getTrade_price()+"]");
		// }
	}
}
