package com.epicodus.bookview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class GoogleBooksService {

    public static void findBooks(String book, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOOGLE_BOOKS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GOOGLE_BOOKS_QUERY_PARAMETER, book);
        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .header(Constants.GOOGLE_BOOKS_KEY, "")
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Book> processResults(Response response) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject googleJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = googleJSON.getJSONArray("items");
                for (int i = 0; i < itemsJSON.length(); i++) {
                    JSONObject bookJSON = itemsJSON.getJSONObject(i);
                    String title = bookJSON.getJSONObject("volumeInfo").getString("title");
                    String author = bookJSON.getJSONObject("volumeInfo").getString("author");
                    String link = bookJSON.getString("link");
                    String description = bookJSON.getString("description");
                    String imageUrl = bookJSON.getJSONObject("imageLinks").getString("imageUrl");
                    double averageRating = bookJSON.getDouble("averageRating");
                    int ratingCount = bookJSON.getInt("ratingCount");

                    Book book = new Book(title, author, link, description, imageUrl, averageRating, ratingCount);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}
