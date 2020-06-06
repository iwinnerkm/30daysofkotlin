package com.kotlin.onboarding.data.transformer

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlin.onboarding.data.Question
import org.json.JSONObject
import java.io.IOException

object QuestionsTransformer {

    fun getListData(context: Activity): List<Question>? {
        getJsonDataFromAsset(context, "questions.json")?.let {
            val jsonObject = JSONObject(it)
            val questionList = jsonObject.getJSONArray("questions").toString()
            val listPersonType = object : TypeToken<List<Question>>() {}.type
            return Gson().fromJson(questionList, listPersonType)
        }
        return null
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}