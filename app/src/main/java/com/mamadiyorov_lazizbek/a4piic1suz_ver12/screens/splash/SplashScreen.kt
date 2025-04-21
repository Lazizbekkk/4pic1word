package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game.GameView
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.menu.MenuScreen

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity(R.layout.screen_splash) {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_splash)

        this.enableEdgeToEdge()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.systemUiVisibility = uiOptions


// GIF-ni yuklash va yuklanish holatini kuzatish
        Glide.with(this)
            .asGif()
            .load(R.raw.bg_game_gif) // Bu yerda GIF faylingizni yuklaysiz
            .diskCacheStrategy(DiskCacheStrategy.NONE) // Cache qilmaslik uchun
            .listener(object : RequestListener<GifDrawable> {
                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // GIF to'liq yuklandi, bu yerda tasvirlarni ko'rsatish mumkin
                    findViewById<ImageView>(R.id.imageView1SPlash).visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.imageView2Splash).visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.imageView3SPlash).visibility = View.VISIBLE
                    findViewById<ImageTextView>(R.id.text_splash).visibility = View.VISIBLE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Yuklanish xatoga uchraganida ishlaydi (agar kerak bo'lsa)
                    return false
                }
            })
            .into(findViewById(R.id.img_view_gif)) // GIF joylashadigan ImageView



        val handler = Handler()

        val imageTextView = findViewById<ImageTextView>(R.id.text_splash)
        imageTextView.text = "4 picture \n1 word"

        handler.postDelayed({ this.openInfoScreen() }, 3000)


        //Objects.requireNonNull(Looper.myLooper())
    }


    private fun openInfoScreen() {
        Log.d("LLL", "OpenInfoScreen")
        val intent = Intent(this@SplashScreen, MenuScreen::class.java)
        startActivity(intent)
    }
}
