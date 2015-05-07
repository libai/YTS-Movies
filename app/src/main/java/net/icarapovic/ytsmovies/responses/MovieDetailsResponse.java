package net.icarapovic.ytsmovies.responses;

import net.icarapovic.ytsmovies.models.Actor;
import net.icarapovic.ytsmovies.models.Director;
import net.icarapovic.ytsmovies.models.MoviePoster;
import net.icarapovic.ytsmovies.models.Torrent;

public class MovieDetailsResponse {

    private String id;
    private String url;
    private String imdb_code;
    private String title;
    private String title_long;
    private String slug;
    private String year;
    private String rating;
    private String runtime;
    private String language;
    private String mpa_rating;
    private String download_count;
    private String like_count;
    private String rt_critics_score;
    private String rt_critics_rating;
    private String rt_audience_score;
    private String rt_audience_rating;
    private String description_intro;
    private String description_full;
    private String yt_trailer_code;
    private String date_uploaded;
    private String date_uploaded_unix;
    private String[] genres;
    private MoviePoster images;
    private Torrent[] torrents;
    private Director[] directors;
    private Actor[] actors;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImdb_code() {
        return imdb_code;
    }

    public String getTitle() {
        return title;
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

    public String getRating() {
        return rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getLanguage() {
        return language;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public String getDownload_count() {
        return download_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public String getRt_critics_score() {
        return rt_critics_score;
    }

    public String getRt_critics_rating() {
        return rt_critics_rating;
    }

    public String getRt_audience_score() {
        return rt_audience_score;
    }

    public String getRt_audience_rating() {
        return rt_audience_rating;
    }

    public String getDescription_intro() {
        return description_intro;
    }

    public String getDescription_full() {
        return description_full;
    }

    public String getYt_trailer_code() {
        return yt_trailer_code;
    }

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public String getDate_uploaded_unix() {
        return date_uploaded_unix;
    }

    public String[] getGenres() {
        return genres;
    }

    public MoviePoster getImages() {
        return images;
    }

    public Torrent[] getTorrents() {
        return torrents;
    }

    public Director[] getDirectors() {
        return directors;
    }

    public Actor[] getActors() {
        return actors;
    }
}
