package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.menu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.databinding.ScreenMenuBinding
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.ExitDialog2
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game.GameView
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.info.InfoActivity

class MenuScreen : AppCompatActivity(R.layout.screen_menu), MenuContract.View {
    private lateinit var presenter: MenuContract.Presenter
    private lateinit var binding: ScreenMenuBinding
    private lateinit var exitDialog: ExitDialog2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exitDialog = ExitDialog2(this)



        window.statusBarColor = Color.BLACK
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            window.decorView.systemUiVisibility = 0
        }



        Glide.with(this)
            .asGif()
            .load(R.raw.bg_game_gif)
            .into(binding.imgViewGif)
        presenter = MenuPresenter(this)


        binding.btnContinue.setOnClickListener {
            presenter.openGameScreen()
        }
        exitDialog.setFinishGame {
            finishAffinity()
        }
        binding.lottieAnimationInfo.setOnClickListener {
            startActivity(Intent(this@MenuScreen, InfoActivity::class.java))
        }


    }

    override fun onBackPressed() {
        exitDialog.show()
    }

    override fun onResume() {
        super.onResume()
        setLevel(presenter.getLevel())
        showQuestionImage(
            presenter.getQuestion().imgID1,
            presenter.getQuestion().imgID2,
            presenter.getQuestion().imgID3,
            presenter.getQuestion().imgID4
        )
    }

    @SuppressLint("SetTextI18n")
    override fun setLevel(index: Int) {
        binding.levelNumber.text = "${index + 1}"
    }

    override fun showQuestionImage(imgID1: Int, imgID2: Int, imgID3: Int, imgID4: Int) {
        binding.imgQuestion1.setImageResource(imgID1)
        binding.imgQuestion2.setImageResource(imgID2)
        binding.imgQuestion3.setImageResource(imgID3)
        binding.imgQuestion4.setImageResource(imgID4)
    }

    override fun openGameScreen() {
        startActivity(Intent(this@MenuScreen, GameView::class.java))
    }

    override fun playButtonSetText(name: String) {
        binding.btnContinue.text = name
    }
}