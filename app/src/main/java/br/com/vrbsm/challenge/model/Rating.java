package br.com.vrbsm.challenge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vrbsm on 03/12/17.
 */

public class Rating extends AbstractModel {

    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;
}
