package com.web.app.upbit.run;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.web.app.upbit.vo.MarketVo;
import com.web.app.upbit.vo.MyBankVo;
import com.web.app.upbit.vo.OrderVo;
import com.web.app.upbit.vo.TickerVo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ApiHyun {
	public List<MyBankVo> accounts() {
		String accessKey = "NOLVx8ckNxW1wv7QzM7uGb4loWfmEIfZkFFQRupV";
        String secretKey = "Yuuld3VO2nFx7o0ctAxGGvTq9Is1IfZeP8qpOYDY";
        String serverUrl = "https://api.upbit.com";

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            return new Gson().fromJson(EntityUtils.toString(entity, "UTF-8"), new TypeToken<List<MyBankVo>>() {}.getType());
            
        } catch (IOException e) {
            return null;
        }
	}
	
	public List<MarketVo> markets() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.upbit.com/v1/market/all?isDetails=false")
		  .get()
		  .addHeader("accept", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		return new Gson().fromJson(response.body().string(), new TypeToken<List<MarketVo>>() {}.getType());
	}
	
	public List<TickerVo> ticker(String markets) throws IOException{
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.upbit.com/v1/ticker?markets="+markets)
		  .get()
		  .addHeader("accept", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		return new Gson().fromJson(response.body().string(), new TypeToken<List<TickerVo>>() {}.getType());
	}
	
	public void orders(OrderVo orderVo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String accessKey = "NOLVx8ckNxW1wv7QzM7uGb4loWfmEIfZkFFQRupV";
        String secretKey = "Yuuld3VO2nFx7o0ctAxGGvTq9Is1IfZeP8qpOYDY";
        String serverUrl = "https://api.upbit.com";
        
        HashMap<String, String> params = new HashMap<>();
        params.put("market", orderVo.getMarket());
        params.put("side", orderVo.getSide());
        params.put("volume", orderVo.getVolume());
        params.put("price", orderVo.getPrice());
        params.put("ord_type", orderVo.getOrd_type());
        
        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void cancel(String uuid) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String accessKey = "NOLVx8ckNxW1wv7QzM7uGb4loWfmEIfZkFFQRupV";
        String secretKey = "Yuuld3VO2nFx7o0ctAxGGvTq9Is1IfZeP8qpOYDY";
        String serverUrl = "https://api.upbit.com";
        
        HashMap<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpDelete request = new HttpDelete(serverUrl + "/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
