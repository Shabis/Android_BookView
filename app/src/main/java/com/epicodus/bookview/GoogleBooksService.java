package com.epicodus.bookview;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class GoogleBooksService {
    public static void findBooks(String book, Callback callback) {
        OkHttpClient client = new OkHttpClient();

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
}
