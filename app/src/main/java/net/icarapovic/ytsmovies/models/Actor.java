package net.icarapovic.ytsmovies.models;

public class Actor {

    public static final String NAME = "name";
    public static final String MEDIUM_IMG = "m_img";

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
