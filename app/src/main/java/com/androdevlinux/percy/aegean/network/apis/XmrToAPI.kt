package com.androdevlinux.percy.aegean.network.apis

import com.androdevlinux.percy.aegean.models.XmrToBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusResponse
import com.androdevlinux.percy.aegean.models.XmrToResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface XmrToAPI {
    @POST("/api/v2/xmr2btc/order_create/")
    fun createOrder(@Body xmrToBody: XmrToBody): Observable<XmrToResponse>

    @POST("/api/v2/xmr2btc/order_status_query/")
    fun getOrderStatus(@Body xmrToOrderStatusBody: XmrToOrderStatusBody): Observable<XmrToOrderStatusResponse>

}
