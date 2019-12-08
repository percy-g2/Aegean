package com.androdevlinux.percy.aegean.network


import com.androdevlinux.percy.aegean.models.XmrToBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusResponse
import com.androdevlinux.percy.aegean.models.XmrToResponse
import com.androdevlinux.percy.aegean.models.changelly.GetMinAmountReponseBean
import com.androdevlinux.percy.aegean.models.changelly.MainBodyBean
import com.androdevlinux.percy.aegean.models.changelly.TransactionBean
import com.androdevlinux.percy.aegean.network.apis.ChangellyApiImpl
import com.androdevlinux.percy.aegean.network.apis.XmrToApiImpl
import io.reactivex.Observable

class ApiManager private constructor() {

    private val changellyApiImpl: ChangellyApiImpl = ChangellyApiImpl.instance
    private val xmrToApiImpl: XmrToApiImpl = XmrToApiImpl.instance

    fun createOrder(xmrToBody: XmrToBody): Observable<XmrToResponse> {
        return xmrToApiImpl.createOrder(xmrToBody)
    }

    fun createTransaction(sign: String, body: MainBodyBean): Observable<TransactionBean> {
        return changellyApiImpl.createTransaction(sign, body)
    }

    fun getMinAmount(sign: String, body: MainBodyBean): Observable<GetMinAmountReponseBean> {
        return changellyApiImpl.getMinAmount(sign, body)
    }

    fun getOrderStatus(xmrToOrderStatusBody: XmrToOrderStatusBody): Observable<XmrToOrderStatusResponse> {
        return xmrToApiImpl.getOrderStatus(xmrToOrderStatusBody)
    }

    companion object {

        private var apiManager: ApiManager? = null

        val instance: ApiManager
            get() {
                if (apiManager == null) {
                    apiManager = ApiManager()
                }
                return apiManager!!
            }
    }
}
