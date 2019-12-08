package com.androdevlinux.percy.aegean.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XmrToOrderStatusResponse {

    @SerializedName("xmr_price_btc")
    @Expose
    private Double xmrPriceBtc;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("btc_amount")
    @Expose
    private Double btcAmount;
    @SerializedName("btc_dest_address")
    @Expose
    private String btcDestAddress;
    @SerializedName("xmr_required_amount")
    @Expose
    private Double xmrRequiredAmount;
    @SerializedName("xmr_receiving_address")
    @Expose
    private String xmrReceivingAddress;
    @SerializedName("xmr_receiving_integrated_address")
    @Expose
    private String xmrReceivingIntegratedAddress;
    @SerializedName("xmr_required_payment_id_long")
    @Expose
    private String xmrRequiredPaymentIdLong;
    @SerializedName("xmr_required_payment_id_short")
    @Expose
    private String xmrRequiredPaymentIdShort;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;
    @SerializedName("seconds_till_timeout")
    @Expose
    private Integer secondsTillTimeout;
    @SerializedName("xmr_amount_total")
    @Expose
    private Double xmrAmountTotal;
    @SerializedName("xmr_amount_remaining")
    @Expose
    private Double xmrAmountRemaining;
    @SerializedName("xmr_num_confirmations_remaining")
    @Expose
    private Integer xmrNumConfirmationsRemaining;
    @SerializedName("xmr_recommended_mixin")
    @Expose
    private Integer xmrRecommendedMixin;
    @SerializedName("btc_num_confirmations_before_purge")
    @Expose
    private Integer btcNumConfirmationsBeforePurge;
    @SerializedName("btc_num_confirmations")
    @Expose
    private Integer btcNumConfirmations;
    @SerializedName("btc_transaction_id")
    @Expose
    private String btcTransactionId;

    public Double getXmrPriceBtc() {
        return xmrPriceBtc;
    }

    public void setXmrPriceBtc(Double xmrPriceBtc) {
        this.xmrPriceBtc = xmrPriceBtc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getBtcAmount() {
        return btcAmount;
    }

    public void setBtcAmount(Double btcAmount) {
        this.btcAmount = btcAmount;
    }

    public String getBtcDestAddress() {
        return btcDestAddress;
    }

    public void setBtcDestAddress(String btcDestAddress) {
        this.btcDestAddress = btcDestAddress;
    }

    public Double getXmrRequiredAmount() {
        return xmrRequiredAmount;
    }

    public void setXmrRequiredAmount(Double xmrRequiredAmount) {
        this.xmrRequiredAmount = xmrRequiredAmount;
    }

    public String getXmrReceivingAddress() {
        return xmrReceivingAddress;
    }

    public void setXmrReceivingAddress(String xmrReceivingAddress) {
        this.xmrReceivingAddress = xmrReceivingAddress;
    }

    public String getXmrReceivingIntegratedAddress() {
        return xmrReceivingIntegratedAddress;
    }

    public void setXmrReceivingIntegratedAddress(String xmrReceivingIntegratedAddress) {
        this.xmrReceivingIntegratedAddress = xmrReceivingIntegratedAddress;
    }

    public String getXmrRequiredPaymentIdLong() {
        return xmrRequiredPaymentIdLong;
    }

    public void setXmrRequiredPaymentIdLong(String xmrRequiredPaymentIdLong) {
        this.xmrRequiredPaymentIdLong = xmrRequiredPaymentIdLong;
    }

    public String getXmrRequiredPaymentIdShort() {
        return xmrRequiredPaymentIdShort;
    }

    public void setXmrRequiredPaymentIdShort(String xmrRequiredPaymentIdShort) {
        this.xmrRequiredPaymentIdShort = xmrRequiredPaymentIdShort;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Integer getSecondsTillTimeout() {
        return secondsTillTimeout;
    }

    public void setSecondsTillTimeout(Integer secondsTillTimeout) {
        this.secondsTillTimeout = secondsTillTimeout;
    }

    public Double getXmrAmountTotal() {
        return xmrAmountTotal;
    }

    public void setXmrAmountTotal(Double xmrAmountTotal) {
        this.xmrAmountTotal = xmrAmountTotal;
    }

    public Double getXmrAmountRemaining() {
        return xmrAmountRemaining;
    }

    public void setXmrAmountRemaining(Double xmrAmountRemaining) {
        this.xmrAmountRemaining = xmrAmountRemaining;
    }

    public Integer getXmrNumConfirmationsRemaining() {
        return xmrNumConfirmationsRemaining;
    }

    public void setXmrNumConfirmationsRemaining(Integer xmrNumConfirmationsRemaining) {
        this.xmrNumConfirmationsRemaining = xmrNumConfirmationsRemaining;
    }

    public Integer getXmrRecommendedMixin() {
        return xmrRecommendedMixin;
    }

    public void setXmrRecommendedMixin(Integer xmrRecommendedMixin) {
        this.xmrRecommendedMixin = xmrRecommendedMixin;
    }

    public Integer getBtcNumConfirmationsBeforePurge() {
        return btcNumConfirmationsBeforePurge;
    }

    public void setBtcNumConfirmationsBeforePurge(Integer btcNumConfirmationsBeforePurge) {
        this.btcNumConfirmationsBeforePurge = btcNumConfirmationsBeforePurge;
    }

    public Integer getBtcNumConfirmations() {
        return btcNumConfirmations;
    }

    public void setBtcNumConfirmations(Integer btcNumConfirmations) {
        this.btcNumConfirmations = btcNumConfirmations;
    }

    public String getBtcTransactionId() {
        return btcTransactionId;
    }

    public void setBtcTransactionId(String btcTransactionId) {
        this.btcTransactionId = btcTransactionId;
    }

}
