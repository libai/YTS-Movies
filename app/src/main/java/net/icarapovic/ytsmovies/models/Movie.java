package net.icarapovic.ytsmovies.models;

public class Movie {

    public static final String ID = "movie_id";
    public static final String TITLE = "movie_title";
    public static final String CHARACTERS = "movie_caracters";
    public static final String POSTER = "movie_poster";
    public static final String GENRES = "movie_genres";
    public static final String RUNTIME = "movie_runtime";
    public static final String YEAR = "movie_year";
    public static final String DESCRIPTION = "movie_desc";
    public static final String IMDB_RATING = "movie_imdb_rating";
    public static final String MPA_RATING = "movie_mpa_rating";
    public static final String RT_RATING = "movie_rt_rating";
    public static final String TRAILER = "movie_trailer_youtube_code";


    private String small_cover_image;
    private String[] genres;
    private String medium_cover_image;
    private String background_image;
    private String state;
    private String runtime;
    private String mpa_rating;
    private String url;
    private String date_uploaded;
    private Torrent[] torrents;
    private int id;
    private String title;
    private String date_uploaded_unix;
    private String title_long;
    private String slug;
    private String year;
    private String imdb_code;
    private String rating;
    private String language;

    public String getSmall_cover_image() {
        return small_cover_image;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getMedium_cover_image() {
        return medium_cover_image;
    }

    public String getState() {
        return state;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public String getUrl() {
        return url;
    }

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public Torrent[] getTorrents() {
        return torrents;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate_uploaded_unix() {
        return date_uploaded_unix;
    }

    public String getTitle_long() {
        return title_long;
    }

    public String getSlug() {
        return slug;
    }

    public String getYear() {
        return year;
    }

    public String getImdb_code() {
        return imdb_code;
    }

    public String getRating() {
        return rating;
    }

    public String getLanguage() {
        return language;
    }

    public String getBackground_image() {
        return background_image;
    }
}
