package br.com.takeaguide.takeaguide.dtos.guide_listing;

import java.util.List;

public class ChangeGuideListingRequest {

    private String title;
    private String description;
    private String location;
    private List<String> tours;
    private List<String> categories;


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<String> getTours() { return tours; }
    public void setTours(List<String> tours) { this.tours = tours; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
}
