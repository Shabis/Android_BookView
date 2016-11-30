package com.epicodus.bookview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Book> processResults(Response response) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject jsonObj = null;
            try {
                jsonObj = XML.toJSONObject(jsonData);
            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }

            Log.d("XML", jsonData);

            Log.d("JSON", jsonObj.toString());
            Log.v(TAG, jsonData);
            if (response.isSuccessful()) {
                Log.v(TAG, "beginning json 1");
                JSONObject goodreadsJSON = new JSONObject(jsonData);
                Log.v(TAG, "beginning json 2");
                JSONArray resultsJSON = goodreadsJSON.getJSONArray("Results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    Log.v(TAG, "beginning json");
                    JSONObject bookJSON = resultsJSON.getJSONObject(i);
                    String title = bookJSON.getJSONObject("best_book")
                            .getJSONObject("work")
                            .getJSONObject("best_book type=\"Book\"")
                            .getJSONObject("work")
                            .getString("title");
                    Log.v(TAG, "end json");
                    Book book = new Book(title);
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
