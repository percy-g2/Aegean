package com.androdevlinux.percy.aegean

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androdevlinux.percy.aegean.models.XmrToBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusBody
import com.androdevlinux.percy.aegean.models.XmrToOrderStatusResponse
import com.androdevlinux.percy.aegean.models.XmrToResponse
import com.androdevlinux.percy.aegean.models.changelly.GetMinAmountReponseBean
import com.androdevlinux.percy.aegean.models.changelly.MainBodyBean
import com.androdevlinux.percy.aegean.models.changelly.ParamsBean
import com.androdevlinux.percy.aegean.models.changelly.TransactionBean
import com.androdevlinux.percy.aegean.network.ApiManager
import com.androdevlinux.percy.aegean.utils.NativeUtils
import com.androdevlinux.percy.aegean.utils.Utils
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var apiManager: ApiManager? = null

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSubmit -> {
                if (edtDestinationBtcAddress.text.isNullOrEmpty() && edtBtcAmount.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Enter destination BTC address and BTC amount!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (edtDestinationBtcAddress.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Enter destination BTC address!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (edtBtcAmount.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Enter BTC amount!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    materialProgressBar.visibility = View.VISIBLE
                    val xmrToBody = XmrToBody()
                    xmrToBody.btcAmount = edtBtcAmount.text.toString().toFloat()
                    xmrToBody.btcDestAddress = edtDestinationBtcAddress.text.toString()
                    var xmrToResponse = XmrToResponse()
                    apiManager!!.createOrder(xmrToBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<XmrToResponse>() {
                            override fun onComplete() {
                                getOrderStatus(xmrToResponse)
                            }

                            override fun onNext(response: XmrToResponse) {
                                xmrToResponse = response
                                Toast.makeText(
                                    this@MainActivity,
                                    "state :" + xmrToResponse.state + "\n"
                                            + "btcAmount :" + xmrToResponse.btcAmount + "\n"
                                            + "btcDestAddress :" + xmrToResponse.btcDestAddress + "\n"
                                            + "uuid :" + xmrToResponse.uuid + "\n",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onStart() {
                                super.onStart()
                                Log.i(MainActivity::class.java.simpleName, "onStart")
                            }

                            override fun onError(e: Throwable) {
                                materialProgressBar.visibility = View.INVISIBLE
                                Log.i(MainActivity::class.java.simpleName, e.toString())
                            }
                        })
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getOrderStatus(xmrToResponse: XmrToResponse) {
        Thread.sleep(1000)
        val xmrToOrderStatusBody = XmrToOrderStatusBody()
        xmrToOrderStatusBody.uuid = xmrToResponse.uuid
        var xmrToOrderStatusResponse = XmrToOrderStatusResponse()
        apiManager!!.getOrderStatus(xmrToOrderStatusBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<XmrToOrderStatusResponse>() {
                override fun onComplete() {
                    Log.i("response", xmrToOrderStatusResponse.xmrAmountTotal.toString())
                    minAmount(xmrToOrderStatusResponse)
                }

                override fun onNext(response: XmrToOrderStatusResponse) {
                    xmrToOrderStatusResponse = response
                    Toast.makeText(
                        this@MainActivity,
                        "xmrReceivingIntegratedAddress :" + xmrToOrderStatusResponse.xmrReceivingIntegratedAddress + "\n"
                                + "expiresAt :" + xmrToOrderStatusResponse.expiresAt + "\n"
                                + "secondsTillTimeout :" + xmrToOrderStatusResponse.secondsTillTimeout + "\n",
                        Toast.LENGTH_SHORT
                    ).show()
                    //createTransaction(xmrToOrderStatusResponse)
                }

                override fun onStart() {
                    super.onStart()
                    Log.i("getOrderStatus", "onStart")
                }

                override fun onError(e: Throwable) {
                    Log.i("getOrderStatus", e.message.toString())
                    materialProgressBar.visibility = View.INVISIBLE
                }
            })
    }

    @SuppressLint("CheckResult")
    private fun minAmount(xmrToOrderStatusResponse: XmrToOrderStatusResponse) {
        val mainBodyBean = MainBodyBean()
        mainBodyBean.id = 1
        mainBodyBean.jsonrpc = "2.0"
        mainBodyBean.method = "getExchangeAmount"
        val params = ParamsBean()
        params.from = "xmr"
        params.to = "btc"
        params.amount = xmrToOrderStatusResponse.xmrAmountTotal.toString()
        mainBodyBean.params = params
        var sign: String? = null
        try {
            sign = Utils.hmacDigest(Gson().toJson(mainBodyBean), NativeUtils.changellySecretKey)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var getMinAmountReponseBean = GetMinAmountReponseBean()

        apiManager!!.getMinAmount(sign!!, mainBodyBean)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<GetMinAmountReponseBean>() {
                override fun onComplete() {
                    if (getMinAmountReponseBean.error == null) {
                        Log.i("test", getMinAmountReponseBean.result.toString())
                        createTransaction(xmrToOrderStatusResponse, getMinAmountReponseBean.result)
                    } else {
                        materialProgressBar.visibility = View.INVISIBLE
                        Snackbar.make(window.decorView.rootView, getMinAmountReponseBean.error!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }

                override fun onNext(response: GetMinAmountReponseBean) {
                    getMinAmountReponseBean = response
                }

                override fun onStart() {
                    super.onStart()
                    Log.i("getOrderStatus", "onStart")
                }

                override fun onError(e: Throwable) {
                    Log.i("getOrderStatus", e.message.toString())
                    materialProgressBar.visibility = View.INVISIBLE
                }
            })
    }

    @SuppressLint("CheckResult")
    private fun createTransaction(
        xmrToOrderStatusResponse: XmrToOrderStatusResponse,
        amount: String?
    ) {
        val mainBodyBean = MainBodyBean()
        mainBodyBean.id = 1
        mainBodyBean.jsonrpc = "2.0"
        mainBodyBean.method = "createTransaction"
        val params = ParamsBean()
        params.from = "btc"
        params.to = "xmr"
        params.amount = amount
        params.address = xmrToOrderStatusResponse.xmrReceivingAddress.toString()
        mainBodyBean.params = params
        var sign: String? = null
        try {
            sign = Utils.hmacDigest(Gson().toJson(mainBodyBean), NativeUtils.changellySecretKey)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var transactionBean = TransactionBean()

        apiManager!!.createTransaction(sign!!, mainBodyBean)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<TransactionBean>() {
                override fun onComplete() {
                    if (transactionBean.error == null) {
                        Log.i("response", transactionBean.result!!.status!!)
                        txtDepositAddress.visibility = View.VISIBLE
                        txtDepositAmount.visibility = View.VISIBLE
                        materialProgressBar.visibility = View.INVISIBLE
                        countDownView.visibility = View.VISIBLE
                        txtDepositAmount.text =
                            getString(R.string.deposit_amount) + "\n" + transactionBean.result!!.amountExpectedFrom.toString()
                        txtDepositAddress.text =
                            getString(R.string.deposit_address) + "\n" + transactionBean.result!!.payinAddress.toString()
                        countDownView.start(xmrToOrderStatusResponse.secondsTillTimeout.toLong() * 1000)
                    } else {
                        materialProgressBar.visibility = View.INVISIBLE
                        Snackbar.make(window.decorView.rootView, transactionBean.error!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }

                override fun onNext(response: TransactionBean) {
                    transactionBean = response
                }

                override fun onStart() {
                    super.onStart()
                    Log.i("getOrderStatus", "onStart")
                }

                override fun onError(e: Throwable) {
                    Log.i("getOrderStatus", e.message.toString())
                    materialProgressBar.visibility = View.INVISIBLE
                }
            })
    }

    /*@SuppressLint("CheckResult")
    fun onTrade(xmrToOrderStatusResponse: XmrToOrderStatusResponse) {
        val morphTokenBody = MorphTokenBody()
        val input = Input()
        input.asset = "BTC"
        input.refund = edtBtcRefundAddress.text.toString()
        val output = Output()
        output.address = xmrToOrderStatusResponse.xmrReceivingIntegratedAddress
        output.asset = "XMR"
        output.weight = 10000
        morphTokenBody.input = input
        morphTokenBody.output.add(output)
        apiManager!!.newTrade(morphTokenBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MorphTokenResponse>() {
                override fun onSuccess(morphTokenResponse: MorphTokenResponse) {
                    Toast.makeText(
                        this@MainActivity,
                        "createdAt :" + morphTokenResponse.createdAt + "\n"
                                + "id :" + morphTokenResponse.id + "\n"
                                + "state :" + morphTokenResponse.state + "\n"
                                + "depositAddress :" + morphTokenResponse.input.depositAddress + "\n",
                        Toast.LENGTH_SHORT
                    ).show()
                    dispose()
                    Log.i("onTrade", "onSuccess")
                }

                override fun onStart() {
                    super.onStart()
                    Log.i("onTrade", "onStart")
                }

                override fun onError(e: Throwable) {
                    Log.i("onTrade", e.toString())
                    dispose()
                }
            })

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btnSubmit.setOnClickListener(this)
        apiManager = ApiManager.instance
        txtDepositAddress.visibility = View.INVISIBLE
        txtDepositAmount.visibility = View.INVISIBLE
        countDownView.visibility = View.INVISIBLE
        materialProgressBar.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_refresh -> {
                edtBtcAmount.editableText.clear()
                edtBtcRefundAddress.editableText.clear()
                edtDestinationBtcAddress.editableText.clear()
                txtDepositAddress.visibility = View.INVISIBLE
                txtDepositAmount.visibility = View.INVISIBLE
                countDownView.visibility = View.INVISIBLE
                materialProgressBar.visibility = View.INVISIBLE
                edtDestinationBtcAddress.requestFocus()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
