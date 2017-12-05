package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AbstractModel implements Serializable {

    @SerializedName("imdbID")
    private String id;
    @SerializedName("Response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
