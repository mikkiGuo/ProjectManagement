package com.example.mikki.projectmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.intentFor

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var t = Thread() {
            Thread.sleep(2500)

            startActivity(intentFor<MainActivity>())
            finish()
        }

        t.start()
    }
}
