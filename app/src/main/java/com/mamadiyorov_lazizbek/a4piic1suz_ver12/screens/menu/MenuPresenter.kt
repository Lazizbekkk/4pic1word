package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.menu

import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate

class MenuPresenter(private val view: MenuContract.View) : MenuContract.Presenter {
    private val model: MenuContract.Model = MenuModel()

    init {
        if(model.getCurrentQuestionIndex() == 0 && model.getClickedAnsCount() == 0){
            view.playButtonSetText("Play")
        }
        else {
            view.playButtonSetText("Continue")
        }
    }

    override fun openGameScreen() {
        view.openGameScreen()
        view.playButtonSetText("Continue")
    }

    override fun getQuestion(): QuestionDate = model.getQuestionByIndex()

    override fun getLevel(): Int = model.getCurrentQuestionIndex()
    override fun showQuestion() {
        val questionData = model.getQuestionByIndex()
        view.showQuestionImage(
            questionData.imgID1,
            questionData.imgID2,
            questionData.imgID3,
            questionData.imgID4
        )
    }
}