package com.epicodus.bookview;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mLink;
    private String mDescription;
    private String mImageUrl;
    private double mAverageRating;
    private int mRatingCount;


    public Book(String title, String author, String link, String description, String imageUrl, double averageRating, int ratingCount) {
        this.mTitle = title;
        this.mAuthor = author;
        this.mLink = link;
        this.mDescription = description;
        this.mImageUrl = imageUrl;
        this.mAverageRating = averageRating;
        this.mRatingCount = ratingCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getLink() {
        return mLink;
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