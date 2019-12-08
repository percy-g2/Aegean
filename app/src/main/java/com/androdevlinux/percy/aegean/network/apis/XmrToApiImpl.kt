package com.androdevlinux.percy.aegean.network.apis

import com.androdevlinux.percy.aegean.models.XmrToBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusResponse
import com.androdevlinux.percy.aegean.models.XmrToResponse
import com.androdevlinux.percy.aegean.utils.NativeUtils
import io.reactivex.Observable

class XmrToApiImpl private constructor() : AbstractBaseApi<XmrToAPI>() {
    private val xmrToAPI: XmrToAPI

    init {
        setBaseUrl(NativeUtils.xmrToBaseUrl)
        xmrToAPI = getClient(XmrToAPI::class.java)

    }

    fun createOrder(xmrToBody: XmrToBody): Observable<XmrToResponse> {
        return xmrToAPI.createOrder(xmrToBody)
    }

    fun getOrderStatus(xmrToOrderStatusBody: XmrToOrderStatusBody): Observable<XmrToOrderStatusResponse> {
        return xmrToAPI.getOrderStatus(xmrToOrderStatusBody)
    }

    companion object {

        private var xmrToAPIApiManager: XmrToApiImpl? = null

        val instance: XmrToApiImpl
            get() {
                if (xmrToAPIApiManager == null)
                    xmrToAPIApiManager = XmrToApiImpl()
                return xmrToAPIApiManager!!
            }
    }
}