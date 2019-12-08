package com.androdevlinux.percy.aegean.network.apis

import com.androdevlinux.percy.aegean.models.changelly.GetCurrenciesResponseBean
import com.androdevlinux.percy.aegean.models.changelly.GetMinAmountReponseBean
import com.androdevlinux.percy.aegean.models.changelly.MainBodyBean
import com.androdevlinux.percy.aegean.models.changelly.TransactionBean
import com.androdevlinux.percy.aegean.utils.NativeUtils
import io.reactivex.Observable


class ChangellyApiImpl private constructor() : AbstractBaseApi<ChangellyAPI>() {
    private val changellyAPI: ChangellyAPI

    init {
        setBaseUrl(NativeUtils.changellyBaseUrl)
        changellyAPI = getClient(ChangellyAPI::class.java)
    }

    fun getCurrencies(sign: String, body: MainBodyBean):Observable<GetCurrenciesResponseBean> {
        return changellyAPI.getCurrencies(contentType, NativeUtils.changellyApiKey, sign, body)
    }

    fun getMinAmount(sign: String, body: MainBodyBean): Observable<GetMinAmountReponseBean> {
        return changellyAPI.getMinAmount(contentType, NativeUtils.changellyApiKey, sign, body)
    }

    fun createTransaction(sign: String, body: MainBodyBean): Observable<TransactionBean> {
        return changellyAPI.createTransaction(contentType, NativeUtils.changellyApiKey, sign, body)
    }

    companion object {
        private var changellyApiImpl: ChangellyApiImpl? = null

        val instance: ChangellyApiImpl
            get() {
                if (changellyApiImpl == null) {
                    changellyApiImpl = ChangellyApiImpl()
                }
                return changellyApiImpl!!
            }
    }
}

