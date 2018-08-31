package com.example.user.neveralone2;

import java.util.zip.DataFormatException;

public class Feed {
    private String feedTitle;
    private String feedText;
    private String feedLocation;
    private String feedLatitude;
    private String feedLongitude;

    public Feed(String feedTitle, String feedText) {
        this.feedTitle = feedTitle;
        this.feedText = feedText;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public String getFeedText() {
        return feedText;
    }

    public String getFeedLocation() {
        return feedLocation;
    }

    public String getFeedLatitude() {
        return feedLatitude;
    }

    public String getFeedLongitude() {
        return feedLongitude;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }


    public void setFeedLocation(String feedLocation) {
        this.feedLocation = feedLocation;
    }

    public void setFeedLatitude(String feedLatitude) {
        this.feedLatitude = feedLatitude;
    }

    public void setFeedLongitude(String feedLongitude) {
        this.feedLongitude = feedLongitude;
    }
}
