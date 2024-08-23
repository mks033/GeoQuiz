package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia,true)
    )




    private val cheatedQuestions = BooleanArray(questionBank.size)
    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val isCheater: Boolean
        get() = cheatedQuestions[currentIndex]

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex > 0) currentIndex - 1 else questionBank.size - 1
    }
    fun setCheaterStatus(cheated: Boolean) {
        cheatedQuestions[currentIndex] = cheated
    }
}
