package net.icarapovic.ytsmovies.models;

public class Torrent {

    private String date_uploaded;
    private String size_bytes;
    private String hash;
    private String date_uploaded_unix;
    private String peers;
    private String seeds;
    private String url;
    private String size;
    private String quality;
    private String resolution;
    private String framerate;
    private String download_count;

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public String getSize_bytes() {
        return size_bytes;
    }

    public String getHash() {
        return hash;
    }

    public String getDate_uploaded_unix() {
        return date_uploaded_unix;
    }

    public String getPeers() {
        return peers;
    }

    public String getSeeds() {
        return seeds;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

    public String getQuality() {
        return quality;
    }

    public String getResolution() {
        return resolution;
    }

    public String getFramerate() {
        return framerate;
    }

    public String getDownload_count() {
        return download_count;
    }
}
