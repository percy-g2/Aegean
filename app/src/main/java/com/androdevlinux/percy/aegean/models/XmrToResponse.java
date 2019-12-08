package com.androdevlinux.percy.aegean.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XmrToResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("btc_amount")
    @Expose
    private Float btcAmount;
    @SerializedName("btc_dest_address")
    @Expose
    private String btcDestAddress;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getBtcAmount() {
        return btcAmount;
    }

    public void setBtcAmount(Float btcAmount) {
        this.btcAmount = btcAmount;
    }

    public String getBtcDestAddress() {
        return btcDestAddress;
    }

    public void setBtcDestAddress(String btcDestAddress) {
        this.btcDestAddress = btcDestAddress;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}