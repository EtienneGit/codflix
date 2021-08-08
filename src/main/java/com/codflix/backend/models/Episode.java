package com.codflix.backend.models;

public class Episode {
    private int id;
    private int serieId;
    private String title;
    private String releaseDate;
    private String summary;
    private String trailerUrl;
    private int season;



    public Episode(int id, int serieId, String title, String releaseDate, String summary, String trailerUrl, int season) {
        this.id = id;
        this.serieId = serieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.trailerUrl = trailerUrl;
        this.season = season;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", serieId=" + serieId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", season='" + season + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int seriesId) {
        this.serieId = seriesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTrailerUrl() { return trailerUrl; }

    public void setTrailerUrl(String trailer_url) { this.trailerUrl = trailer_url; }

    public int getSeason() { return season; }

    public void setSeason(int season) { this.season = season; }

}
