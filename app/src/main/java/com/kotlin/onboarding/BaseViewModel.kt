package com.kotlin.onboarding

import androidx.lifecycle.ViewModel
import com.kotlin.onboarding.data.Question

open class BaseViewModel : ViewModel() {

    var questions: List<Question>? = null

    fun nextQuestion(): Question? {
        var unAttemptedQuestion: Question? = null
        questions?.forEach {
            if (!it.attempted) {
                unAttemptedQuestion = it
                return unAttemptedQuestion
            }
        }
        return unAttemptedQuestion
    }

    fun markAttempted(currentQuestion: Question?) {
        questions?.let {
            for (question in it) {
                if (currentQuestion?.text == question.text) {
                    question.answer = currentQuestion?.answer
                    question.attempted = true
                }
            }
        }
    }

}