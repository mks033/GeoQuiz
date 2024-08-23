package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        val answerTextView: TextView = findViewById(R.id.answer_text_view)
        val showAnswerButton: Button = findViewById(R.id.show_answer_button)
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()

        }
        Toast.makeText(this, "Press 'Back To Quiz' to return to the quiz", Toast.LENGTH_SHORT).show()



        showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) R.string.true_button else R.string. false_button
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
        const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
