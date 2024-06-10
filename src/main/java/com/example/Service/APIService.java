package com.example.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.WifiDTO;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIService {

    private final String Url = "http://openapi.seoul.go.kr:8088/414c4b526d73616e3732424d446467/json/TbPublicWifiInfo/";
    private final OkHttpClient client = new OkHttpClient();

    public int getTotalCount() throws IOException {
        String apiUrl = Url + "1/1";
        Request.Builder builder = new Request.Builder().url(apiUrl).get();
        Request request = builder.build();
        Response response = client.newCall(request).execute();

        int total = 0;

        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
            total = result.get("list_total_count").getAsInt();
        } else {
            System.out.println("호출 실패");
        }


        return total;
    }



    public List<WifiDTO> loadAPI() throws IOException {
        int totalCount = getTotalCount();
        List<WifiDTO> list = new ArrayList<>();


        for (int i = 1; i <= totalCount / 1000 + 1; i++) {
            String apiUrl = Url + (i * 1000 - 999) + "/" + (i * 1000);
            Request.Builder builder = new Request.Builder().url(apiUrl).get();
            Request request = builder.build();
            Response response = client.newCall(request).execute();

            JsonArray row = null;

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");

                row = result.get("row").getAsJsonArray();


                for (int j = 0; j < row.size(); j++) {
                    Gson gson = new Gson();
                    WifiDTO data = gson.fromJson(row.get(j), WifiDTO.class);
                    list.add(data);
                }
            }
        }
        return list;

    }







}
