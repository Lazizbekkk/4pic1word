package com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain

import android.util.Log
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate

class AppRepository {
    private var questionDateList: ArrayList<QuestionDate> = ArrayList()

       init {
           loadQuestion()
       }

    private fun loadQuestion(){
        questionDateList.add(QuestionDate(R.drawable.ic_wolf1,R.drawable.ic_wolf2,R.drawable.ic_wolf3,R.drawable.ic_wolf4,"wodlssdkfkja","wolf"))
        questionDateList.add(QuestionDate(R.drawable.ic_bear,R.drawable.ic_bear,R.drawable.ic_bear,R.drawable.ic_bear,"jablsedkfrpp","bear"))
        questionDateList.add(QuestionDate(R.drawable.ic_cat1,R.drawable.ic_cat2,R.drawable.ic_cat3,R.drawable.ic_cat4,"jabltedkfrcp","cat"))
        questionDateList.add(QuestionDate(R.drawable.ic_parrot1,R.drawable.ic_parrot2,R.drawable.ic_parrot3,R.drawable.ic_parrot4,"fprgteakfrpo","parrot"))
    }
    fun getQuestionByIndex(index: Int) = questionDateList[index]
}