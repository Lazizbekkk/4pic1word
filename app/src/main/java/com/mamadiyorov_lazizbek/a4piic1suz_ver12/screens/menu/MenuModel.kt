package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.menu

import android.content.SharedPreferences
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain.AppRepository
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain.SharedPref
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.utils.Constants

class MenuModel: MenuContract.Model {
    private var repository: AppRepository = AppRepository()
    private var sharedPreferences: SharedPreferences = SharedPref.getSharedPref()

    override fun getQuestionByIndex(): QuestionDate  = repository.getQuestionByIndex(sharedPreferences.getInt(Constants.CURRENT_QUESTION_INDEX, 0))


    override fun getCurrentQuestionIndex(): Int  =  sharedPreferences.getInt(Constants.CURRENT_QUESTION_INDEX, 0)
    override fun getClickedAnsCount(): Int  = sharedPreferences.getInt(Constants.CLICK_COUNT_ANS, 0)
}