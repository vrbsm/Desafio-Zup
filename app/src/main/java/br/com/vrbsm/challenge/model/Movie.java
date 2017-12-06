package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.List;

public class Movie extends SugarRecord<Movie> {

    @SerializedName("imdbID")
    private String imdbid;

    @SerializedName("Response")
    private String response;

    @SerializedName("Title")
    private String title;

    @SerializedName("Poster")
    private String urlImage;

    @SerializedName("Year")
    private String year;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;
    @SerializedName("Language")
    private String language;
    @SerializedName("Country")
    private String country;

    @SerializedName("Awards")
    private String awards;
    @SerializedName("Type")
    private String type;
    @SerializedName("DVD")
    private String DVD;


    @SerializedName("Production")
    private String production;
    @SerializedName("Website")
    private String website;

    @Ignore
    @SerializedName("Ratings")
    private List<Rating> ratings;

    private String rating;

    @Override
    public void save() {
        if (ratings != null)
            this.rating = getRatings();
        super.save();
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    private String getRatings() {
        String rating = new String();
        for (Rating r : ratings)
            rating += r.getSource() + ": " + r.getValue() + "  ";
        return rating;
    }


    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        if (rating != null)
            return rating;
        else {
            if (ratings != null)
                return getRatings();
            else
                return "";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return title + " ( " + year + " ) ";
    }
}
