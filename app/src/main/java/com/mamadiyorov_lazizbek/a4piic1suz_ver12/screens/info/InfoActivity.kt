package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.info

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R

class InfoActivity: AppCompatActivity() {

    private lateinit var backBtn: ImageView
    private lateinit var btnGita: ImageView
    private lateinit var btnTelegram: ImageView
    private lateinit var btnInstagram: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.inf_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Glide.with(this)
            .asGif()
            .load(R.raw.bg_game_gif) // `raw` papkada saqlangan GIF fayli
            .into(findViewById(R.id.img_view_gif))

        loadView()


        backBtn.setOnClickListener {
            finish()
        }
        btnTelegram.setOnClickListener {
            val telegramIntent = Intent(Intent.ACTION_VIEW)
            telegramIntent.data = Uri.parse("https://t.me/gitauz")  // Telegram profilingiz linki
            telegramIntent.setPackage("org.telegram.messenger")

            try {
                startActivity(telegramIntent)  // Ilova o'rnatilgan bo'lsa
            } catch (e: ActivityNotFoundException) {
                // Ilova o'rnatilmagan bo'lsa, brauzerda ochadi
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/gitauz")))
            }
        }
        btnInstagram.setOnClickListener {
            val instagramIntent = Intent(Intent.ACTION_VIEW)
            instagramIntent.data = Uri.parse("http://www.instagram.com/gita.uz")  // Instagram profilingiz linki
            instagramIntent.setPackage("com.instagram.android")

            try {
                startActivity(instagramIntent)  // Ilova o'rnatilgan bo'lsa
            } catch (e: ActivityNotFoundException) {
                // Ilova o'rnatilmagan bo'lsa, brauzerda ochadi
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/gita.uz")))
            }
        }
        btnGita.setOnClickListener {
            val gitaUrl = "https://www.google.com/search?q=gita+dasturchilar+akademiyasi+&sca_esv=33073fab614d76a8&udm=2&biw=1280&bih=619&sxsrf=ADLYWIKM_iq2JBuW3fxqyLWCuKw3w3jMlA%3A1728891897076&ei=-csMZ_-hBOazwPAP1d_v6Qo&ved=0ahUKEwi_goWbsI2JAxXmGRAIHdXvO60Q4dUDCBA&uact=5&oq=gita+dasturchilar+akademiyasi+&gs_lp=Egxnd3Mtd2l6LXNlcnAiHmdpdGEgZGFzdHVyY2hpbGFyIGFrYWRlbWl5YXNpIDIEECMYJzIEEAAYHkjoCFDQBljQBnACeACQAQCYAXKgAXKqAQMwLjG4AQPIAQD4AQGYAgOgAnmYAwCIBgGSBwMyLjGgB9sC&sclient=gws-wiz-serp"  // O'zingizning Gita haqidagi sayt linkini kiriting
            val gitaIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gitaUrl))

            try {
                startActivity(gitaIntent)  // Saytni brauzerda ochadi
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Brauzer topilmadi", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadView(){
        backBtn = findViewById(R.id.btn_back)
        btnTelegram = findViewById(R.id.btn_telegram)
        btnInstagram = findViewById(R.id.btn_instagram)
        btnGita = findViewById(R.id.btn_gita)
    }
}