package com.epicodus.bookview.services;

import android.util.Log;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class GoogleBooksService {
    public static final String TAG = GoogleBooksService.class.getSimpleName();

    public static void findBooks(String book, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOOGLE_BOOKS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GOOGLE_BOOKS_QUERY_PARAMETER, book);
        urlBuilder.addQueryParameter(Constants.GOOGLE_BOOKS_KEY_PARAMETER, Constants.GOOGLE_BOOKS_KEY);
        String url = urlBuilder.build().toString();
        Log.v(TAG, url);


        Request request = new Request.Builder()
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
                Log.v(TAG, "Beginning");
                JSONObject googleJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = googleJSON.getJSONArray("items");
                for (int i = 0; i < itemsJSON.length(); i++) {
                    JSONObject bookJSON = itemsJSON.getJSONObject(i);

                    String title = "";
                    if(bookJSON.getJSONObject("volumeInfo").has("title")) {
                        title = bookJSON.getJSONObject("volumeInfo").getString("title");
                    } else {
                        title = "no title available";
                    }

                    ArrayList<String> authors = new ArrayList<>();
                    JSONArray authorJSON = bookJSON.getJSONObject("volumeInfo").getJSONArray("authors");
                    for (int y = 0; y < authorJSON.length(); y++) {
                        authors.add(authorJSON.get(y).toString());
                    }

                    String description = "";
                    if (bookJSON.getJSONObject("volumeInfo").has("description")) {
                        description = bookJSON.getJSONObject("volumeInfo").getString("description");
                    } else {
                        description = "no description available";
                    }

                    String imageUrl = "";
                    if (bookJSON.getJSONObject("volumeInfo").getJSONObject("imageLinks").has("thumbnail")) {
                        imageUrl = bookJSON.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                    } else {
                        imageUrl = "No Preview Available";
                    }

                    double averageRating = 0;
                    if (bookJSON.getJSONObject("volumeInfo").has("averageRating")) {
                        averageRating = bookJSON.getJSONObject("volumeInfo").getDouble("averageRating");
                    } else {
                        averageRating = 0;
                    }

                    int ratingCount = 0;
                    if (bookJSON.getJSONObject("volumeInfo").has("ratingsCount")) {
                        ratingCount = bookJSON.getJSONObject("volumeInfo").getInt("ratingsCount");
                    } else {
                        ratingCount = 0;
                    }

                    String website = "";
                    if (bookJSON.getJSONObject("volumeInfo").has("previewLink")) {
                        website = bookJSON.getJSONObject("volumeInfo").getString("previewLink");
                    } else {
                        website = "Preview Not Available";
                    }

                    Book book = new Book(title, authors, description, imageUrl, averageRating, ratingCount, website);
                    books.add(book);
                    Log.v(TAG, "end");
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
