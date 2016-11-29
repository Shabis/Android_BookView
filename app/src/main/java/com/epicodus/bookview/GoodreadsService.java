package com.epicodus.bookview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import oauth.signpost.http.HttpResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class GoodreadsService {
    public static final String TAG = GoodreadsService.class.getSimpleName();

    public static void searchBooks(String query, Callback callback) {
        String GOODREADS_KEY = Constants.GOODREADS_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOODREADS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GOODREADS_BOOK_QUERY_PARAMETER,query);
        String url = urlBuilder.build().toString();
        Log.d(TAG, url);


        Request request= new Request.Builder()
                .url(url)
                .build();
                Log.d(TAG, url);
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Book> processResults(Response response) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.v(TAG, jsonData);
            if (response.isSuccessful()) {
                JSONObject goodreadsJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = goodreadsJSON.getJSONArray("GoodreadsResponse");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject bookJSON = resultsJSON.getJSONObject(i);
                    String title = bookJSON.getJSONObject("best_book")
                            .getJSONObject("search")
                            .getJSONObject("results")
                            .getJSONObject("work")
                            .getString("title");
                    String author = bookJSON.getJSONObject("best_book")
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
                            .getJSONObject("best_book")
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
