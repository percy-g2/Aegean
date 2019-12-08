package com.androdevlinux.percy.aegean.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XmrToBody {

    @SerializedName("btc_amount")
    @Expose
    private Float btcAmount;
    @SerializedName("btc_dest_address")
    @Expose
    private String btcDestAddress;

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

}
