package com.epicodus.bookview;

import java.util.ArrayList;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class Book {
    private String mTitle;
    private ArrayList<String> mAuthors = new ArrayList<>();
    private String mDescription;
    private String mImageUrl;
    private double mAverageRating;
    private int mRatingCount;


    public Book(String title, ArrayList<String> authors, String description, String imageUrl, double averageRating, int ratingCount) {
        this.mTitle = title;
        this.mAuthors = authors;
        this.mDescription = description;
        this.mImageUrl = imageUrl;
        this.mAverageRating = averageRating;
        this.mRatingCount = ratingCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<String> getAuthors() {
        return mAuthors;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getAverageRating() {
        return mAverageRating;
    }

    public int getRatingCount() {
        return mRatingCount;
    }
}