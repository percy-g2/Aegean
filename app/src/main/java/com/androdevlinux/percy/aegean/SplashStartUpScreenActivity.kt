package com.androdevlinux.percy.aegean

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashStartUpScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTimeOut = 3000
        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashStartUpScreenActivity, MainActivity::class.java)
            startActivity(i)
            // close this activity
            //finish()
        }, splashTimeOut.toLong())
    }
}
