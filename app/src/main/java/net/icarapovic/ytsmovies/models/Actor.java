package net.icarapovic.ytsmovies.models;

public class Actor {

    public static final String NAME = "actor_name";
    public static final String MEDIUM_IMG = "actor_medium_image";

    private String name;
    private String character_name;
    private String small_image;
    private String medium_image;

    public String getName() {
        return name;
    }

    public String getCharacterName() {
        return character_name;
    }

    public String getSmallImage() {
        return small_image;
    }

    public String getMediumImage() {
        return medium_image;
    }
}
