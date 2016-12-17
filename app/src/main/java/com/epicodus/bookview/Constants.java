package com.epicodus.bookview;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class Constants {
    public static final String GOOGLE_BOOKS_KEY = BuildConfig.GOOGLE_BOOKS_KEY;
    public static final String GOOGLE_BOOKS_KEY_PARAMETER = "key";
    public static final String GOOGLE_BOOKS_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    public static final String GOOGLE_BOOKS_QUERY_PARAMETER = "q";
    public static final String SEARCH_PREFERENCE_KEY = "book";
    public static final String FIREBASE_CHILD_BOOK_SEARCH = "searchedBooks";
    public static final String FIREBASE_CHILD_WISHLIST = "wishlist";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_BOOKS = "books";
}
