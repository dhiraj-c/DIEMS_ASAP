package com.dietms.diemsasap.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.dietms.diemsasap.R

class SplashActivity : AppCompatActivity() {

//    lateinit var imgAppLogo: ImageView
//
//    lateinit var uptodown: Animation
//
//    lateinit var downtoup: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        imgAppLogo = findViewById(R.id.imgAppLogo)
//
//        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown)
//
//        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup)
//
//        imgAppLogo.startAnimation(downtoup)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)


    }
}
