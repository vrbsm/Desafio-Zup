package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Search {

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Response")
    private String response;

    @SerializedName("Error")
    private String error;

    @SerializedName("Search")
    private List<Movie> search;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
