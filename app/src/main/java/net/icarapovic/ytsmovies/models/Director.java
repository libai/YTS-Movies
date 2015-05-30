package net.icarapovic.ytsmovies.models;

public class Director {

    public static final String MEDIUM_IMAGE = "director_medium_image";
    public static final String NAME = "director_name";

    private String name;
    private String small_image;
    private String medium_image;

    public String getName() {
        return name;
    }

    public String getSmallImage() {
        return small_image;
    }

    public String getMediumImage() {
        return medium_image;
    }
}
