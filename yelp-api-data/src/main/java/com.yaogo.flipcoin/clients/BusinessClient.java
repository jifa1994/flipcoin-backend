package com.yaogo.flipcoin.clients;


import com.yaogo.flipcoin.models.Business;
import com.yaogo.flipcoin.models.Location;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessClient {

    private CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

    public void close() throws IOException {
        closeableHttpClient.close();
    }

    public List<Business> sendGet(String[] strings) throws Exception {
        URIBuilder builder = new URIBuilder("https://api.yelp.com/v3/businesses/search");
        if (strings.length > 0) {
            for (String pair : strings) {
                String[] pair_array = pair.split(",");
                String key = pair_array[0];
                String value = pair_array[1];
                builder.setParameter(key, value);
            }
        }
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.addHeader("Authorization", Constants.TOKEN);

        try (CloseableHttpResponse response = closeableHttpClient.execute(httpGet)) {
            System.out.println(response.getStatusLine().toString());
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                List<Business> businessesEntity = new ArrayList<>();


                String raw = EntityUtils.toString(httpEntity);

                JSONObject root = new JSONObject(raw);

                JSONArray businessesJSONArray = root.getJSONArray("businesses");

                for (int i = 0; i < businessesJSONArray.length(); i++) {
                    JSONObject jsonObject = businessesJSONArray.getJSONObject(i);
                    Business businessEntity = businessJSON2businessClass(jsonObject);

                    businessesEntity.add(businessEntity);
                }

                return businessesEntity;
            }

        }
        return new ArrayList<Business>();
    }
    
    private Business businessJSON2businessClass(JSONObject jsonObject) throws MalformedURLException {
        Business businessEntity = new Business();
        Location locationEntity = new Location();

        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String url =  jsonObject.getString("url");
        String image_url = jsonObject.getString("image_url");
        Integer reviewCount = jsonObject.getInt("review_count");
        Double rating = jsonObject.getDouble("rating");
        String price = "Unknown";
        if (jsonObject.has("price")){
            price = jsonObject.getString("price");
        }

        JSONObject locationJSON = jsonObject.getJSONObject("location");

        locationEntity.setAddress1(locationJSON.getString("address1"));

        businessEntity.setId(id);
        businessEntity.setName(name);
        businessEntity.setUrl(url);
        businessEntity.setImageUrl(image_url);
        businessEntity.setReviewCount(reviewCount);
        businessEntity.setRating(rating);
        businessEntity.setPrice(price);

        businessEntity.setLocation(locationEntity);

        return businessEntity;
    }

}