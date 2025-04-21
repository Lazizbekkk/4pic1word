package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game

import android.content.SharedPreferences
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain.AppRepository
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain.SharedPref
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.utils.Constants

class GameModel : GameContract.GameModel {

    private var repository: AppRepository = AppRepository()
    private var sharedPreferences: SharedPreferences = SharedPref.getSharedPref()

    override fun getQuestionByIndex(index: Int) = repository.getQuestionByIndex(index)

    override fun getCurrentQuestionIndex(): Int {
        return sharedPreferences.getInt(Constants.CURRENT_QUESTION_INDEX, 0)
    }

    override fun setCurrentQuestionIndex(index: Int) {
        sharedPreferences.edit().putInt(Constants.CURRENT_QUESTION_INDEX, index).apply()
    }

    override fun getCoin(): Int = sharedPreferences.getInt(Constants.COIN, 100)

    override fun setCoin(coin: Int) {
        sharedPreferences.edit().putInt(Constants.COIN, coin).apply()
    }

    override fun setStringSbAnswer(stringBuilder: StringBuilder) {
        sharedPreferences.edit().putString(Constants.SB_ANSWER, stringBuilder.toString()).apply()
    }

    override fun getStringSbAnswer(): String = sharedPreferences.getString(Constants.SB_ANSWER, "").toString()

    override fun setStringSbVariants(stringBuilder: StringBuilder) {
        sharedPreferences.edit().putString(Constants.SB_VARIANTS, stringBuilder.toString()).apply()
    }

    override fun getStringSbVariants(): String = sharedPreferences.getString(Constants.SB_VARIANTS, "").toString()

    private fun saveArrayListToPrefs(key: String, list: ArrayList<Int>) {
        val stringBuilder = StringBuilder()
        if (list.isNotEmpty()) {
            for (index in list) {
                stringBuilder.append(index).append(")")
            }
        }
        sharedPreferences.edit().putString(key, stringBuilder.toString()).apply()
    }

    override fun setHelpArr(help: ArrayList<Int>) {
        saveArrayListToPrefs(Constants.HELP_ARR, help)
    }

    override fun setDeleteArr(help: ArrayList<Int>) {
        saveArrayListToPrefs(Constants.DELETE_ARR, help)
    }

    override fun setHideVariants(help: ArrayList<Int>) {
        saveArrayListToPrefs(Constants.HIDE_VAR_ARR, help)
    }

    private fun getArrayListFromPrefs(key: String, defaultString: String): ArrayList<Int> {
        val savedString = sharedPreferences.getString(key, defaultString)
        if (savedString == defaultString) {
            return ArrayList()
        }

        val strArr = savedString?.split(")")?.filter { it.isNotEmpty() }
        val resultList = ArrayList<Int>()
        strArr?.forEach { index ->
            resultList.add(index.toInt())
        }
        return resultList
    }

    override fun getHelpArr(): ArrayList<Int> {
        return getArrayListFromPrefs(Constants.HELP_ARR, "Lazizbek")
    }

    override fun getDelete(): ArrayList<Int> {
        return getArrayListFromPrefs(Constants.DELETE_ARR, "Lazizbek_DElete")
    }

    override fun getHideVariants(): ArrayList<Int> {
        return getArrayListFromPrefs(Constants.HIDE_VAR_ARR, "Lazizbek_Hide")
    }

    override fun setClickCountsAns(clickCountAns: Int) {
        sharedPreferences.edit().putInt(Constants.CLICK_COUNT_ANS, clickCountAns).apply()
    }

    override fun getClickCountAns(): Int  = sharedPreferences.getInt(Constants.CLICK_COUNT_ANS, 0)
}
