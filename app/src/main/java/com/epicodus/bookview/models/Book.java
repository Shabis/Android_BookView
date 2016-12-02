package com.epicodus.bookview.models;


import org.parceler.Parcel;
import java.util.ArrayList;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */
@Parcel
public class Book {
    String mTitle;
    ArrayList<String> mAuthors = new ArrayList<>();
    String mDescription;
    String mImageUrl;
    double mAverageRating;
    int mRatingCount;
    String mWebsite;



    public Book() {}

    public Book(String title, ArrayList<String> authors, String description, String imageUrl, double averageRating, int ratingCount, String website) {
        this.mTitle = title;
        this.mAuthors = authors;
        this.mDescription = description;
        this.mImageUrl = imageUrl;
        this.mAverageRating = averageRating;
        this.mRatingCount = ratingCount;
        this.mWebsite = website;
        mImageUrl = getLargeImageUrl(imageUrl);
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

    public String getWebsite() {
        return mWebsite;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }
}