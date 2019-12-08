package com.androdevlinux.percy.aegean.models.changelly

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by percy on 15/11/2017.
 */

class GetMinAmountReponseBean {
    @SerializedName("jsonrpc")
    @Expose
    var jsonrpc: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("result")
    @Expose
    var result: String? = null
    @SerializedName("error")
    @Expose
    var error: ErrorBean? = null

}
