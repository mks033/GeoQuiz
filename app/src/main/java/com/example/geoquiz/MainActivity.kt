package com.example.geoquiz


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge




class MainActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel = QuizViewModel()

    private var questionTextView : TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val trueButton: Button = findViewById(R.id.true_button)
        val falseButton: Button = findViewById(R.id.false_button)
        val nextButton: Button = findViewById(R.id.next_button)
        val prevButton: Button = findViewById(R.id.prev_button)
        val cheatButton: Button = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_View)

        updateQuestion()

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        cheatButton.setOnClickListener {
            val intent = CheatActivity.newIntent(this@MainActivity, quizViewModel.currentQuestionAnswer)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            val isCheater = data?.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false) ?: false
            quizViewModel.setCheaterStatus(isCheater)
        }
    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView!!.setText(questionTextResId)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userPressedTrue == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUEST_CODE_CHEAT = 0
        private const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
    }
}
















