package com.epicodus.bookview.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class Book implements Serializable {
    String mTitle;
    ArrayList<String> mAuthors = new ArrayList<>();
    String mDescription;
    String mImageUrl;
    double mAverageRating;
    int mRatingCount;

    public Book() {
    }

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