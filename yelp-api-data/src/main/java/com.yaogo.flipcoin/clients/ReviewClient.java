package com.yaogo.flipcoin.clients;

import com.yaogo.flipcoin.models.Review;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewClient {

    private CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

    public void close() throws IOException {
        closeableHttpClient.close();
    }

    public List<Review> sendGet(String businessId) throws Exception {
        URIBuilder builder = new URIBuilder("https://api.yelp.com/v3/businesses/" + businessId + "/reviews");
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.addHeader("Authorization", Constants.TOKEN);

        try (CloseableHttpResponse response = closeableHttpClient.execute(httpGet)) {
            System.out.println(response.getStatusLine().toString());
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) {
                List<Review> reviews = new ArrayList<>();

                String raw = EntityUtils.toString(httpEntity);

                JSONObject root = new JSONObject(raw);

                JSONArray reviewsJSONArray = root.getJSONArray("reviews");

                for (int i = 0; i < reviewsJSONArray.length(); i ++) {
                    JSONObject jsonObject = reviewsJSONArray.getJSONObject(i);
                    Review review = reviewJSON2review(jsonObject);
                    reviews.add(review);
                }
                return reviews;
            }
        }
        return new ArrayList<Review>();
    }

    private Review reviewJSON2review(JSONObject jsonObject) {

        Review review = new Review();

        String id = jsonObject.getString("id");
        Double rating = jsonObject.getDouble("rating");
        String text = jsonObject.getString("text");

        review.setId(id);
        review.setRating(rating);
        review.setText(text);

        return review;
    }


}
