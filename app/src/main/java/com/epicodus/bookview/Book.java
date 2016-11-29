package com.epicodus.bookview;

public class Book {
    private String mTitle;
    private String mAuthor;
    private double mRating;
    private String mImageUrl;

    public Book(String title, String author, double rating, String imageUrl) {
        this.mTitle = title;
        this.mAuthor = author;
        this.mRating = rating;
        this.mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public double getRating() {
        return mRating;
    }

    public String getImageUrl(){
        return mImageUrl;
    }
}
