package com.kotlin.onboarding.data

class Question(
        val text: String? = null,
        val type: String? = null,
        var answer: String? = null,
        var attempted: Boolean = false
)