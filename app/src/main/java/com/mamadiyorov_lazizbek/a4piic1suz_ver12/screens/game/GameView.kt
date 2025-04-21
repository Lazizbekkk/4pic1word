package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.DeleteDialog
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.ExitDialog
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.HelpDialog
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.WinDialog
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.dialogs.WowDialog

class GameView : AppCompatActivity(R.layout.activity_game2), GameContract.GameView {

    private lateinit var presenter: GameContract.GamePresenter

    private lateinit var imageViewList: ArrayList<ShapeableImageView>
    private lateinit var answerButtons: ArrayList<AppCompatButton>
    private lateinit var variantButtons: ArrayList<AppCompatButton>

    private lateinit var imgGifGame: ImageView

    private lateinit var levelText: TextView
    private lateinit var coinText: TextView

    private lateinit var helpButton: ImageView
    private lateinit var deleteButton: ImageView

    private lateinit var helpDialog: HelpDialog
    private lateinit var deleteDialog: DeleteDialog

    private lateinit var exitDialog: ExitDialog

    private lateinit var wowDialog: WowDialog
    private lateinit var winDialog: WinDialog


    private lateinit var imgFrame: ShapeableImageView
    private var listImageID = ArrayList<Int>()

    private lateinit var backButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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




        loadViews()

        wowDialog = WowDialog(this)

        winDialog = WinDialog(this)

        helpDialog = HelpDialog(this)
        deleteDialog = DeleteDialog(this)
        exitDialog = ExitDialog(this)

        Glide.with(this)
            .asGif()
            .load(R.raw.bg_game_gif) // `raw` papkada saqlangan GIF fayli
            .into(imgGifGame)


        presenter = GamePresenter(this)

        helpDialog.setHelpBtnClicked {
            presenter.clickedHelpButton()
        }

        deleteDialog.setDeleteBtnClicked {
            presenter.clickedDeleteButton()
        }
        helpButton.setOnClickListener {
            Log.d("DIALOG", "helpButton.setOnClickListener  ")
            helpDialog.show()
        }
        deleteButton.setOnClickListener {
            deleteDialog.show()
        }

        exitDialog.setOpenMenuScreen {
            finish()
        }
        backButton.setOnClickListener {
            exitDialog.show()
        }

        wowDialog.setRestartClicked {
            presenter.wowGameRestart()
        }

        winDialog.setNextLevel {
            presenter.nextLevel()
        }





        addClickEvents()

    }


    private fun loadViews() {
        imgGifGame = findViewById(R.id.img_view_gif)

        deleteButton = findViewById(R.id.deleteButton)
        helpButton = findViewById(R.id.helpButton)

        levelText = findViewById(R.id.levelNumber)
        coinText = findViewById(R.id.coin)

        imageViewList = ArrayList(4)


        imageViewList.add(findViewById(R.id.imgQuestion1))
        imageViewList.add(findViewById(R.id.imgQuestion2))
        imageViewList.add(findViewById(R.id.imgQuestion3))
        imageViewList.add(findViewById(R.id.imgQuestion4))
        listImageID
        imgFrame = findViewById(R.id.img_frame)

        backButton = findViewById(R.id.backButtonGame)





        answerButtons = ArrayList(6)
        val containerAns = findViewById<LinearLayout>(R.id.containerAnswers)
        for (i in 0..<containerAns.childCount) {
            answerButtons.add(containerAns.getChildAt(i) as AppCompatButton)
        }

        variantButtons = ArrayList(12)
        val containerVariants1 = findViewById<LinearLayout>(R.id.containerVariants1)
        for (i in 0 until containerVariants1.childCount) {
            variantButtons.add(containerVariants1.getChildAt(i) as AppCompatButton)
        }
        val containerVariants2 = findViewById<LinearLayout>(R.id.containerVariants2)
        for (i in 0 until containerVariants2.childCount) {
            variantButtons.add(containerVariants2.getChildAt(i) as AppCompatButton)
        }

        imageViewList[0].setOnClickListener {
            presenter.clickedImageID(0)
        }
        imageViewList[1].setOnClickListener {
            presenter.clickedImageID(1)
        }
        imageViewList[2].setOnClickListener {
            presenter.clickedImageID(2)
        }
        imageViewList[3].setOnClickListener {
            presenter.clickedImageID(3)
        }

        imgFrame.setOnClickListener {
            presenter.imageFrameClicked()
        }



    }

    override fun onBackPressed() {
        exitDialog.show()
    }

    private fun addClickEvents() {

        for (i in 0 until answerButtons.size) {
            answerButtons[i].tag = i
            answerButtons[i].setOnClickListener {
                presenter.clickedAnswerButton(it.tag as Int)
            }
        }

        for (i in 0 until variantButtons.size) {
            variantButtons[i].tag = i
            variantButtons[i].setOnClickListener {
                presenter.clickedVariantButton(it.tag as Int)
            }
        }


    }

    @SuppressLint("SetTextI18n")
    override fun setLevel(index: Int) {
        levelText.text = "${index + 1}"
    }

    override fun setCoin(coin: Int) {
        coinText.text = "${coin}"
    }

    override fun showQuestionImage(imgID1: Int, imgID2: Int, imgID3: Int, imgID4: Int) {
        imageViewList[0].setImageResource(imgID1)
        imageViewList[1].setImageResource(imgID2)
        imageViewList[2].setImageResource(imgID3)
        imageViewList[3].setImageResource(imgID4)
        listImageID.clear()
        listImageID.add(imgID1)
        listImageID.add(imgID2)
        listImageID.add(imgID3)
        listImageID.add(imgID4)
    }

    override fun showAnswerButtonByLength(length: Int) {
        for (i in answerButtons.indices) {
            if (i < length) {
                answerButtons[i].visibility = View.VISIBLE
                answerButtons[i].text = ""
                answerButtons[i].isEnabled = false
                answerButtons[i].setBackgroundResource(R.drawable.bg_answer_def)
                answerButtons[i].setTextColor(Color.WHITE)
            } else {
                answerButtons[i].visibility = View.GONE
            }
        }
    }

    override fun showVariants(variants: String) {
        visibleVariants()
        for (i in variants.indices) {
            variantButtons[i].text = variants[i].toString()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun visibleVariants() {
        for (i in 0 until variantButtons.size) {
            variantButtons[i].visibility = View.VISIBLE
            variantButtons[i].text = ""
        }
    }

    override fun selectAnswerButton(char: Char, index: Int) {
        answerButtons[index].isEnabled = true
        answerButtons[index].text = char.toString()
    }

    override fun unselectedAnswerButton(index: Int) {
        answerButtons[index].text = ""
        answerButtons[index].isEnabled = false
    }

    override fun hideVariantButton(index: Int) {
        variantButtons[index].visibility = View.INVISIBLE
    }

    override fun showVariantIndex(char: Char, index: Int) {
        variantButtons[index].text = char.toString()
        variantButtons[index].visibility = View.VISIBLE
    }

    override fun notHideVariant(index: Int) {
        variantButtons[index].visibility = View.VISIBLE
    }

    override fun setVisibleImageFrame(boolean: Boolean) {
        imgFrame.visibility = if (boolean) View.VISIBLE else View.GONE
    }

    override fun setImageRecImgFrame(id: Int) {
        imgFrame.setImageResource(listImageID[id])
    }

    override fun wrongIndexSetBackground(index: Int) {
        answerButtons[index].setBackgroundResource(R.drawable.bg_answer_wrong)
    }

    override fun helpIndexSetBackground(index: Int) {
        answerButtons[index].setBackgroundResource(R.drawable.bg_answer)
        setTextColorWhite(index)
    }

    override fun setTextColorGreen(index: Int) {
        answerButtons[index].setTextColor(Color.GREEN)
    }

    override fun setTextColorWhite(index: Int) {
        answerButtons[index].setTextColor(Color.WHITE)
    }

    override fun setTextAns(char: Char, index: Int) {
        answerButtons[index].text = char.toString()
    }

    override fun defSetBackgroundAnswer(index: Int) {
        answerButtons[index].setBackgroundResource(R.drawable.bg_answer_def)
        answerButtons[index].isEnabled = true
        setTextColorWhite(index)
    }

    override fun showWowDialog() {
        wowDialog.show()
    }

    override fun showWinDialog() {
        winDialog.show()
    }


}