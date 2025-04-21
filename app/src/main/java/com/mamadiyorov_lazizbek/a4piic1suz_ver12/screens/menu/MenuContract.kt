package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.menu

import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate

interface MenuContract {
    interface Model{
        fun getQuestionByIndex(): QuestionDate
        fun getCurrentQuestionIndex(): Int
        fun getClickedAnsCount(): Int
    }
    interface View{
        fun setLevel(index: Int)
        fun showQuestionImage(imgID1: Int, imgID2: Int, imgID3: Int, imgID4: Int)
        fun openGameScreen()
        fun playButtonSetText(name: String)

    }
    interface Presenter{
        fun openGameScreen()
        fun getQuestion(): QuestionDate
        fun getLevel(): Int
        fun showQuestion()
    }
}