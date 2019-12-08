package com.androdevlinux.percy.aegean.network.apis

import com.androdevlinux.percy.aegean.models.changelly.GetCurrenciesResponseBean
import com.androdevlinux.percy.aegean.models.changelly.GetMinAmountReponseBean
import com.androdevlinux.percy.aegean.models.changelly.MainBodyBean
import com.androdevlinux.percy.aegean.models.changelly.TransactionBean
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by percy on 22/11/17.
 */

interface ChangellyAPI {

    @POST("/")
    fun getCurrencies(@Header("Content-Type") content_type: String, @Header("api-key") api_key: String, @Header("sign") sign: String, @Body p: MainBodyBean): Observable<GetCurrenciesResponseBean>

    @POST("/")
    fun getMinAmount(@Header("Content-Type") content_type: String, @Header("api-key") api_key: String, @Header("sign") sign: String, @Body p: MainBodyBean): Observable<GetMinAmountReponseBean>

    @POST("/")
    fun createTransaction(@Header("Content-Type") content_type: String, @Header("api-key") api_key: String, @Header("sign") sign: String, @Body p: MainBodyBean): Observable<TransactionBean>
}

