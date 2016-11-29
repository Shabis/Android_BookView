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
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class GoodreadsService {

    public static void searchBooks(String book, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.GOODREADS_KEY, Constants.GOODREADS_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOODREADS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GOODREADS_BOOK_QUERY_PARAMETER, book);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
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
                JSONObject goodreadsJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = goodreadsJSON.getJSONArray("GoodreadsResponse");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject bookJSON = resultsJSON.getJSONObject(i);
                    String title = bookJSON.getJSONObject("best_book type='Book'")
                            .getJSONObject("search")
                            .getJSONObject("results")
                            .getJSONObject("work")
                            .getString("title");
                    String author = bookJSON.getJSONObject("best_book type='Book'")
                            .getJSONObject("search")
                            .getJSONObject("results")
                            .getJSONObject("work")
                            .getJSONObject("author")
                            .getString("name");
                    double rating = bookJSON.getJSONObject("search")
                            .getJSONObject("results")
                            .getJSONObject("work")
                            .getDouble("rating");
                    String imageUrl = bookJSON.getJSONObject("search")
                            .getJSONObject("results")
                            .getJSONObject("work")
                            .getJSONObject("best_book type='Book'")
                            .getString("image_url");

                    Book book = new Book(title, author, rating, imageUrl);
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
