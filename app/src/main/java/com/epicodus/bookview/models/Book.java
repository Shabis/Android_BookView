package com.epicodus.bookview.models;


import org.parceler.Parcel;
import java.util.ArrayList;

/**
 * Created by Shelby Clayton on 11/30/2016.
 */
@Parcel
public class Book {
    String title;
    ArrayList<String> authors = new ArrayList<>();
    String description;
    String imageUrl;
    double averageRating;
    int ratingCount;
    String website;
    private String pushId;

    public Book() {}

    public Book(String title, ArrayList<String> authors, String description, String imageUrl, double averageRating, int ratingCount, String website) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.website = website;
        this.imageUrl = getLargeImageUrl(imageUrl);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getWebsite() {
        return website;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}