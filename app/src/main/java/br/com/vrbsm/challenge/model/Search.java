package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search extends AbstractModel{

    @SerializedName("Search")
    private List<Movie> search;

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
