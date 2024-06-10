package controller_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.WifiVari;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIService {


	private final String Url  = "http://openapi.seoul.go.kr:8088/414c4b526d73616e3732424d446467/json/TbPublicWifiInfo/";
	private final OkHttpClient client = new OkHttpClient();
	
	
	
public List<WifiVari> loadAPI() throws IOException {
		
		int totalCount = getTotalCount();
		List<WifiVari> list = new ArrayList<>();
		for(int i=1; i<= totalCount/1000 +1; i++) {
			
			String apiUrl = Url + (i * 1000 - 999) + "/" + (i * 1000);
			Request.Builder builder = new Request.Builder().url(apiUrl).get();
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			JsonArray row = null;
			
			
			if(response.isSuccessful()) {
				row = getArray(response.body().string());
			}
			
			for(int j=0; j<row.size(); j++) {
				Gson gson = new Gson();
				WifiVari data = gson.fromJson(row.get(j), WifiVari.class);
				list.add(data);
			}
			
			
		}
		
		return list;
		
	}
	
	
	public JsonArray getArray(String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		
		return result.get("row").getAsJsonArray();
	}
	
	
	
	public int getTotalCount() throws IOException {
		String apiUrl = Url + "1/1";
		Request.Builder builder = new Request.Builder().url(apiUrl).get();
		Request request = builder.build();
		Response response = client.newCall(request).execute();
		
		int total = 0;
		
		 if(response.isSuccessful()) {
		        String responseBody = response.body().string();
		        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject(); // json -> jsonelement -> jsonobject
		        JsonObject wifiTotal = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		        total = wifiTotal.getAsJsonPrimitive("list_total_count").getAsInt();
		    }
		    
		    return total;
	}
	
	
	
	
	
}
