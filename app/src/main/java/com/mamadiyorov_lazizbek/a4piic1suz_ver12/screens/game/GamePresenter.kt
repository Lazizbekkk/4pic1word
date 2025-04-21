@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.game

import android.util.Log
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.data.QuestionDate
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.utils.Constants.QUESTIONS_COUNT

import kotlin.random.Random

class GamePresenter(private val view: GameContract.GameView) : GameContract.GamePresenter {
    private var model: GameContract.GameModel = GameModel()
    private var isInitialized = false

    private var currentQuestionIndex: Int
    private var coinGame: Int

    private var _sbAnswer: StringBuilder = StringBuilder(model.getStringSbAnswer())
    private var _sbVariant: StringBuilder = StringBuilder(model.getStringSbVariants())


    private lateinit var _sbCopyVariant: StringBuilder
    private lateinit var answer: String
    private var clickCountsAns: Int = model.getClickCountAns()


    private var helpArrAns = model.getHelpArr()
    private var deleteArrVariants = model.getDelete()
    private var hideVariantsArray = model.getHideVariants()

    init {
        currentQuestionIndex = model.getCurrentQuestionIndex()
        coinGame = model.getCoin()

        showQuestion()
        setLevel()
        setCoins()
        showAnsAndVariants()

    }

    override fun nextLevel() {
        resetLevel()
        ++ currentQuestionIndex
        model.setCurrentQuestionIndex(currentQuestionIndex)
        showQuestion()
        setLevel()
        coinGame += 50
        setCoins()
        model.setCoin(coinGame)
    }

    override fun wowGameRestart() {
        resetLevel()
        currentQuestionIndex = 0
        coinGame = 100
        showQuestion()
        view.setLevel(currentQuestionIndex)
        model.setCurrentQuestionIndex(currentQuestionIndex)
        model.setCoin(coinGame)
    }


    override fun clickedVariantButton(index: Int) {
        val _indexOF: Int = _sbAnswer.indexOf("#")

        if (_indexOF != -1) {
            val c: Char = _sbVariant[index]
            _sbVariant.setCharAt(index, '*')
            _sbAnswer.setCharAt(_indexOF, c)

            // `model` ga yangi qiymatlarni saqlash
            model.setStringSbVariants(_sbVariant)
            model.setStringSbAnswer(_sbAnswer)

            Log.d("TAG_Laziz", "clickedVariantButton: -----------------------------")
            Log.d("TAG_Laziz", "clickedVariantButton: _sbVariant set:  $_sbVariant")
            Log.d("TAG_Laziz", "clickedVariantButton: _sbAnswer set:  $_sbAnswer")
            Log.d("TAG_Laziz", "clickedVariantButton: _sbVariant get:  ${model.getStringSbVariants()}")
            Log.d("TAG_Laziz", "clickedVariantButton: _sbAnswer get:  ${model.getStringSbAnswer()}")

            view.hideVariantButton(index)
            view.selectAnswerButton(c, _indexOF)

            if (isNextLevel() ) {
                Log.d("TTTTT", "currentQuestionIndex: $currentQuestionIndex   AND QUESTIONS_COUNT:  $QUESTIONS_COUNT")
                if (currentQuestionIndex < QUESTIONS_COUNT - 1) {

                    view.showWinDialog()
                } else {
                    view.showWowDialog()
                }
                return
            }

            clickCountsAns++
            model.setClickCountsAns(clickCountsAns)
            Log.d("KLKL", "clickedVariantButton: clickCountsAns: $clickCountsAns \t _sbAnswerLength: ${_sbAnswer.length} \t _sbAnswer: ${_sbAnswer}")

            if (clickCountsAns == _sbAnswer.length) {
                isLoseGame()
            }
        }
    }

    override fun clickedAnswerButton(index: Int) {
        val _indexOF = _sbVariant.indexOf("*")
        if (_indexOF != -1 && !helpArrAns.contains(index)) {
            clickCountsAns--
            model.setClickCountsAns(clickCountsAns)
            Log.d("KLKL", "helpArrAns: helpArrAnsList: ${helpArrAns.toString()}")

            val c: Char = _sbAnswer[index]
            var indexCopy = 0

            _sbAnswer.setCharAt(index, '#')

            lastStateBackground()
            for (i in _indexOF until _sbCopyVariant.length) {
                if (_sbVariant[i] == '*' && _sbCopyVariant[i] == c) {
                    indexCopy = i
                    break
                }
            }

            _sbVariant.setCharAt(indexCopy, c)


            model.setStringSbVariants(_sbVariant)
            model.setStringSbAnswer(_sbAnswer)
            Log.d("TAG_Laziz", "clickedAnswerButton: -------------------------------------")
            Log.d("TAG_Laziz", "clickedAnswerButton: _sbVariant set:  $_sbVariant")
            Log.d("TAG_Laziz", "clickedAnswerButton: _sbAnswer set:  $_sbAnswer")
            Log.d("TAG_Laziz", "clickedAnswerButton: _sbVariant get:  ${model.getStringSbVariants()}")
            Log.d("TAG_Laziz", "clickedAnswerButton: _sbAnswer get:  ${model.getStringSbAnswer()}")


            view.showVariantIndex(c, indexCopy)
            view.unselectedAnswerButton(index)
            Log.d("KLKL", "clickedAnswerButton(): clickCountsAns: $clickCountsAns \t _sbAnswerLength: ${_sbAnswer.length} \t _sbAnswer: $_sbAnswer")
        }
    }





    override fun clickedHelpButton() {
        if (coinGame < 20) {
            view.showToast("Help ishlatish uchun kamida 20 coin kerak")
            return
        }



        val list = ArrayList<Int>()
        for (i in _sbAnswer.indices) {
            if (_sbAnswer[i] == '#') { // bo'sh joy bo'lsa, indeksni ro'yxatga qo'shish
                list.add(i)
            }
        }

        if (list.isNotEmpty()) {
            val randomIndex = Random.nextInt(list.size)
            val randomValue = list[randomIndex]

            var deleteIndex = -1
            for (i in _sbVariant.indices) {
                if (answer[randomValue] == _sbVariant[i]) {
                    deleteIndex = i
                    break
                }
            }


            if (deleteIndex != -1) {
                helpArrAns.add(randomValue)
                model.setHelpArr(helpArrAns)

                coinGame -= 20

                view.setCoin(coinGame)
                model.setCoin(coinGame)

                // Fond rangini o'zgartirish va harfni joylashtirish
                view.helpIndexSetBackground(randomValue)
                _sbAnswer.setCharAt(randomValue, answer[randomValue])
                model.setStringSbAnswer(_sbAnswer)

                // Variantni yashirish
                hideVariantsArray.add(deleteIndex)
                model.setHideVariants(hideVariantsArray)
                view.hideVariantButton(deleteIndex)
                view.selectAnswerButton(_sbAnswer[randomValue], randomValue)

                Log.d("LLLKKK", "help ishlagandan;   deleteIndex === $deleteIndex   randomValue === $randomValue")
                clickCountsAns++
                model.setClickCountsAns(clickCountsAns)

                Log.d("RANDOM", "randomIndex: $randomIndex \t randomValue: $randomValue")
                Log.d("KLKL", "clickedHelpButton():     clickCountsAns: $clickCountsAns   \t _sbAnswerLength: ${_sbAnswer.length} \t _sbAnswer: $_sbAnswer")
            }
        }



        if (isNextLevel()) {
            resetLevel()
            Log.d("TTTTT", "currentQuestionIndex: $currentQuestionIndex   AND QUESTIONS_COUNT:  $QUESTIONS_COUNT")
            if (currentQuestionIndex < QUESTIONS_COUNT - 1) {
                view.showWinDialog()
            } else {
               view.showWowDialog()
            }
            return
        }

        if (clickCountsAns == _sbAnswer.length) {
            isLoseGame()
        }
    }

    private fun resetLevel() {
        helpArrAns = ArrayList()
        deleteArrVariants = ArrayList()
        hideVariantsArray = ArrayList()
        model.setHelpArr(helpArrAns)
        model.setDeleteArr(deleteArrVariants)
        model.setHideVariants(hideVariantsArray)
        _sbVariant = StringBuilder("")
        _sbAnswer = StringBuilder("")

        model.setStringSbAnswer(_sbAnswer)
        model.setStringSbVariants(_sbVariant)
        clickCountsAns = 0
        model.setClickCountsAns(clickCountsAns)
        _sbCopyVariant = StringBuilder()
        view.setVisibleImageFrame(false)
        Log.d("TAG_Laziz", "resetLevel: ------------------------------------- \n")
        Log.d("TAG_Laziz", "resetLevel: _sbVariant set:  $_sbVariant")
        Log.d("TAG_Laziz", "resetLevel: _sbAnswer set:  $_sbAnswer")
        Log.d("TAG_Laziz", "resetLevel: _sbVariant get:  ${model.getStringSbVariants()}")
        Log.d("TAG_Laziz", "resetLevel: _sbAnswer get:  ${model.getStringSbAnswer()}")
    }


    override fun clickedDeleteButton() {
        if (coinGame < 10) {
            view.showToast("delete ishlatish coin 10 tadan kup bulishi kerak")
            return
        }
        val list = ArrayList<Int>()
        for (i in _sbVariant.indices) {
            if (_sbVariant[i] != '*' && !answer.contains(_sbVariant[i])) { // bo'sh joy bo'lsa, indeksni ro'yxatga qo'shish
                list.add(i)
            }
        }

        if (list.isNotEmpty()) {
            coinGame -= 10
            view.setCoin(coinGame)
            model.setCoin(coinGame)
            val randomIndex: Int = Random.nextInt(list.size)
            val randomValue: Int = list[randomIndex]
            deleteArrVariants.add(randomValue)
            model.setDeleteArr(deleteArrVariants)

            view.hideVariantButton(randomValue)

            _sbVariant.setCharAt(randomValue, '*')
            model.setStringSbVariants(_sbVariant)

            Log.d("TAG_Laziz", "clickedDeleteButton: -----------------------------")
            Log.d("TAG_Laziz", "clickedDeleteButton: _sbVariant set:  $_sbVariant")
            Log.d("TAG_Laziz", "clickedDeleteButton: _sbVariant get:  ${model.getStringSbVariants()}")





        }
    }

    override fun clickedImageID(id: Int) {
        view.setImageRecImgFrame(id)
        view.setVisibleImageFrame(true)
    }


    override fun imageFrameClicked() {
        view.setVisibleImageFrame(false)
    }


    private fun setLevel() {
        view.setLevel(currentQuestionIndex)
    }

    private fun showQuestion() {
        clickCountsAns = model.getClickCountAns()
        _sbAnswer = StringBuilder(model.getStringSbAnswer())
        _sbVariant = StringBuilder(model.getStringSbVariants())

//
//        if (currentQuestionIndex < (QUESTIONS_COUNT - 1)) {
            if (model.getClickCountAns() == 0) {
                initializeQuestionState()
                isInitialized = true
            }

            val data = model.getQuestionByIndex(currentQuestionIndex)
            view.showQuestionImage(data.imgID1, data.imgID2, data.imgID3, data.imgID4)
            view.showAnswerButtonByLength(data.answer.length)
            view.showVariants(data.variant)
            answer = data.answer
            _sbCopyVariant = StringBuilder(data.variant)


            Log.d("TAG_Laziz", "showQuestion()  --------------------------------------------------------------------")
            Log.d("TAG_Laziz", "showQuestion()  _sbAnswer:  $_sbAnswer  \t  modeldan keldi: ${model.getStringSbAnswer()}")
            Log.d("TAG_Laziz", "showQuestion()  _sbVariant:  $_sbVariant \t  modeldan keldi: ${model.getStringSbVariants()}")
            Log.d("TAG_Laziz", "showQuestion()   clickCountsAns:  $clickCountsAns \t  modeldan keldi: ${model.getClickCountAns()}")
            showAnsAndVariants()

            if(clickCountsAns == answer.length){
                Log.d("TAG_Laziz", "showQuestion() clickCountsAns == answer.length  ifga tushdi")
                isLoseGame()
            }

        //}/* else {
//            currentQuestionIndex = 0
//            model.setCurrentQuestionIndex(currentQuestionIndex)
//            val data = model.getQuestionByIndex(currentQuestionIndex)
//            answer = data.answer
//            _sbCopyVariant = StringBuilder(data.variant)
//            view.showQuestionImage(data.imgID1, data.imgID2, data.imgID3, data.imgID4)
//            view.showAnswerButtonByLength(data.answer.length)
//            view.showVariants(data.variant)
//            Log.d("TAG_Laziz", "--------------------------------------------------------------------")
//            Log.d("TAG_Laziz", "_sbAnswer:  $_sbAnswer  \t  modeldan keldi: ${model.getStringSbAnswer()}")
//            Log.d("TAG_Laziz", "_sbVariant:  $_sbVariant \t  modeldan keldi: ${model.getStringSbVariants()}")
//            Log.d("TAG_Laziz", "clickCountsAns:  $_sbVariant \t  modeldan keldi: ${model.getClickCountAns()}")
//            showAnsAndVariants()
//
//
//            if(clickCountsAns == answer.length){
//                Log.d("TAG_Laziz", "showQuestion() clickCountsAns == answer.length  ifga tushdi")
//                isLoseGame()
//            }
//
//
//
//        }*/
    }

    private fun setCoins() {
        view.setCoin(coinGame)
    }




    private fun isLoseGame() {
        //   showQuestion()

        for (i in _sbAnswer.indices) {

            if (helpArrAns.contains(i)) {
                view.wrongIndexSetBackground(i)
                view.setTextColorGreen(index = i)
            } else {
                view.wrongIndexSetBackground(i)
                view.setTextColorWhite(i)
            }
        }
        for (i in _sbVariant.indices) {
            if (deleteArrVariants.contains(i)) {
                view.hideVariantButton(i)
            }
        }

    }


    private fun isNextLevel(): Boolean {
        val data = model.getQuestionByIndex(currentQuestionIndex)
        //    Log.d("TTT","isNextLevel: " + currentQuestionIndex + "\t user ans: " + _sbAnswer + "\t quizAns: " + data.getAnswer())
        return _sbAnswer.toString().equals(data.answer, ignoreCase = true)
    }

    fun lastStateBackground() {
        for (i in _sbAnswer.indices) {
            if (i in helpArrAns) {
                view.helpIndexSetBackground(i) // Agar indeks `helpArrAns` da mavjud bo'lsa, maxsus fon rangi
            } else {
                view.defSetBackgroundAnswer(i) // Aks holda, standart fon rangi
            }
        }
    }
    private fun showAnsAndVariants() {
        // Log yozuvi (diagnostika uchun)
        Log.d("MY_LISTS", "showAnsAndVariants: -----------------------------")
        Log.d("MY_LISTS", "helpArrAns: $helpArrAns")
        Log.d("MY_LISTS", "deleteArrVariants: $deleteArrVariants")
        Log.d("MY_LISTS", "hideVariantsArray: $hideVariantsArray")
        Log.d("MY_LISTS", "hideVariantsArray: $hideVariantsArray")
        Log.d("MY_LISTS", "_sbAnswer: $_sbAnswer")
        Log.d("MY_LISTS", "_sbVariant: $_sbVariant")
        Log.d("MY_LISTS", "_sbCopyVariant: $_sbCopyVariant")

        Log.d("TAG_Laziz", "-------------------------------------------------")
        Log.d("TAG_Laziz", "showAnsAndVariants: _sbVariant get:  ${model.getStringSbVariants()}")
        Log.d("TAG_Laziz", "showAnsAndVariants: _sbAnswer get:  ${model.getStringSbAnswer()}")


        // `_sbAnswer` indekslari uchun fon ranglarini va variantlarni yashirishni boshqarish
        for (i in _sbAnswer.indices) {
            if (i in helpArrAns) {
                view.setTextAns(answer[i], i)
                view.helpIndexSetBackground(i)
            // `helpArrAns` ichidagi indekslar uchun maxsus fon rangi
            } else {
                if(_sbAnswer[i] != '#'){
                    view.setTextAns(_sbAnswer[i], i)
                }
                view.defSetBackgroundAnswer(i)
            }
        }

        // `hideVariantsArray` ichidagi indekslar uchun variant tugmalarini yashirish
        for (i in hideVariantsArray.indices){
            view.hideVariantButton(hideVariantsArray[i])
        }
       for (i in _sbVariant.indices){
           if(_sbVariant[i] == '*'){
               view.hideVariantButton(i)
           }
       }



    }

    private fun initializeQuestionState() {
        // Only initialize _sbAnswer and _sbVariant here
        val data = model.getQuestionByIndex(currentQuestionIndex)
        _sbAnswer = StringBuilder().apply { repeat(data.answer.length) { append('#') } }
        _sbVariant = StringBuilder(data.variant)
        _sbCopyVariant = StringBuilder(data.variant)
        model.setStringSbAnswer(_sbAnswer)
        model.setStringSbVariants(_sbVariant)
    }

}