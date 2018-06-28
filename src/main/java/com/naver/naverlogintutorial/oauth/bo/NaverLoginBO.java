package com.naver.naverlogintutorial.oauth.bo;

import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.modoo.cg.dao.Dao;
import com.naver.naverlogintutorial.oauth.model.NaverLoginApi;

public class NaverLoginBO {
	private final static String CLIENT_ID = "milVbgYr_ma3HoV3QO0k";
	private final static String CLIENT_SECRET = "KgH0E11Mcr";
	private final static String REDIRECT_URI = "http://127.0.0.1/cg/callback";
	private final static String SESSION_STATE = "oauth_state";
	private final static String ACCESS_TOKEN = "Atoken";
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	private final static String TOKEN_DEL = "https://nid.naver.com/oauth2.0/token?grant_type=delete";
	
	
	/* 네아로 인증  URL 생성  Method */
	public String getAuthorizationUrl(HttpSession session) {

		/* 세션 유효성 검증을 위하여 난수를 생성 */
		String state = generateRandomString();
		/* 생성한 난수 값을 session에 저장 */
		setSession(session,state);

		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI)
				.state(state) //앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(NaverLoginApi.instance());

		return oauthService.getAuthorizationUrl();
	}

	/* 네아로 Callback 처리 및  AccessToken 획득 Method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
		
		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI)
					.state(state)
					.build(NaverLoginApi.instance());
					
			/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);

			
			String get_Atoken = accessToken.getAccessToken();
			//setSession(ACCESS_TOKEN,get_Atoken);
			
			setTokenSession(session,get_Atoken);
			
			return accessToken;
		}
		return null;
	}
public OAuth2AccessToken getReflashToken(HttpSession session, String code, String state) throws IOException{
		
		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)){
		
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					
					
					.build(NaverLoginApi.instance());
					
			/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);

			
			String get_Atoken = accessToken.getAccessToken();
			//setSession(ACCESS_TOKEN,get_Atoken);
			
			setTokenSession(session,get_Atoken);
			
			return accessToken;
		}
		return null;
	}
	
	
	
	
	/* 세션 유효성 검증을 위한 난수 생성기 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	
	/* http session에 데이터 저장 */
	private void setSession(HttpSession session,String state){
		session.setAttribute(SESSION_STATE, state);		
	}
	
	/* http session에서 데이터 가져오기 */	
	private String getSession(HttpSession session){
		return (String) session.getAttribute(SESSION_STATE);
	}
	
	
	/*네아로 토큰 관리*/
	/*http session에 토큰  데이터 저장*/
	
	private void setTokenSession(HttpSession session,String get_Atoken){
		session.setAttribute(ACCESS_TOKEN, get_Atoken);		
	}
	
	/*http session에 토큰  데이터 가져오기*/
	private String getTokenSession(HttpSession session){
		return (String) session.getAttribute(ACCESS_TOKEN);	
	}	
	
	
	
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{

		OAuth20Service oauthService =new ServiceBuilder()
    			.apiKey(CLIENT_ID)
    			.apiSecret(CLIENT_SECRET)
    			.callback(REDIRECT_URI).build(NaverLoginApi.instance());
    	
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
	
	public String logout(HttpSession session) {
			
		String token= getTokenSession(session);
		System.out.println("토큰"+token);
		try {
			String encodeResult = URLEncoder.encode(token,"utf-8");
			String apiURL = TOKEN_DEL +"&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&access_token="+encodeResult+"&service_provider=NAVER";
	
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestProperty("Content-type", "text/xml; charset=UTF-8");

			con.connect();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
				String inputLine;
				StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				
				response.append(inputLine);
				
			}
				br.close();
				System.out.println(response.toString());
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
			

			
		
		return null;
	}
	public void refresh(HttpSession session) {
		
		String token= getTokenSession(session);
		
		

		
		try {
			String encodeResult = URLEncoder.encode(token,"utf-8");
			String refresh ="https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&client_id="+CLIENT_ID +"&client_secret="+CLIENT_SECRET+"&refresh_token="+encodeResult;
			System.out.println(refresh);
		
			URL url = new URL(refresh);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");

			con.connect();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
				String inputLine;
				StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				
				response.append(inputLine);
				
			}
				br.close();
				System.out.println(response.toString() + "갱신");
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
		
	}
	
	public void naverprofile(HttpSession session) {
		System.out.println("naverprofile()");
		
		String token = getTokenSession(session);
		
		String header = "Bearer "+token;
		
		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			String ab =response.toString();
			System.out.println(ab);
			JSONParser  jsonParser= new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(ab);
			JSONObject dataObject = (JSONObject) jsonObject.get("response");
			String name=(String) dataObject.get("nickname");
			String email=(String) dataObject.get("email");
			System.out.println(name +"   "+ email);
			Dao Dao=new Dao();
			Dao.register(null, name, email);
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
