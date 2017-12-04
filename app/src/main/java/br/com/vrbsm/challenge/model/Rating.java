package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;


public class Rating extends AbstractModel {

    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;
}
