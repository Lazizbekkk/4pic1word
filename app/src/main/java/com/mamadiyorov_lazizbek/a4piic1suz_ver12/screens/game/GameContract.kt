package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game

import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate

interface GameContract {
    interface GameModel {
        fun getQuestionByIndex(index: Int): QuestionDate
        fun getCurrentQuestionIndex(): Int
        fun setCurrentQuestionIndex(index: Int)
        fun getCoin(): Int
        fun setCoin(coin: Int)


        fun setStringSbAnswer(stringBuilder: StringBuilder)
        fun getStringSbAnswer(): String

        fun setStringSbVariants(stringBuilder: StringBuilder)
        fun getStringSbVariants(): String

        fun setHelpArr(help: ArrayList<Int>)
        fun setDeleteArr(help: ArrayList<Int>)
        fun setHideVariants(help: ArrayList<Int>)

        fun getHelpArr(): ArrayList<Int>
        fun getDelete(): ArrayList<Int>
        fun getHideVariants(): ArrayList<Int>

        fun setClickCountsAns(clickCountAns: Int)
        fun getClickCountAns(): Int

    }

    interface GameView {
        fun setLevel(index: Int)
        fun setCoin(coin: Int)

        fun showQuestionImage(imgID1: Int, imgID2: Int, imgID3: Int, imgID4: Int)
        fun showAnswerButtonByLength(length: Int)
        fun showVariants(variants: String)

        fun showToast(message: String)
        fun visibleVariants()

        fun selectAnswerButton(char: Char, index: Int)
        fun unselectedAnswerButton(index: Int)
        fun hideVariantButton(index: Int)

        fun showVariantIndex(char: Char, index: Int)

        fun notHideVariant(index: Int)

        fun setVisibleImageFrame(boolean: Boolean)
        fun setImageRecImgFrame(id: Int)

        fun wrongIndexSetBackground(index: Int)
        fun helpIndexSetBackground(index: Int)

        fun setTextColorGreen(index: Int)
        fun setTextColorWhite(index: Int)

        fun setTextAns(char: Char, index: Int)

        fun defSetBackgroundAnswer(index: Int)

        fun showWowDialog()
        fun showWinDialog()


    }

    interface GamePresenter {

        fun nextLevel()
        fun wowGameRestart()

        fun clickedVariantButton(index: Int)
        fun clickedAnswerButton(index: Int)
        fun clickedHelpButton()
        fun clickedDeleteButton()
        fun clickedImageID(id: Int)
        fun imageFrameClicked()
    }
}