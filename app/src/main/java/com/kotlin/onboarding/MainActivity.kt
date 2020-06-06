package com.kotlin.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kotlin.onboarding.questionList.QuestionListFragment
import com.kotlin.onboarding.questionPage.QuestionFragment
import com.kotlin.onboarding.data.transformer.QuestionsTransformer

class MainActivity : AppCompatActivity(), QuestionFragment.Navigation {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        getQuestions()
    }

    private fun getQuestions() {
        viewModel.questions = QuestionsTransformer.getListData(this)
        launchQuestionFragment()
    }

    private fun launchQuestionFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(getContainerResId(), QuestionFragment.newInstance())
        fragmentTransaction.commit()
    }

    private fun launchListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(getContainerResId(), QuestionListFragment.newInstance())
        fragmentTransaction.commit()
    }

    private fun getContainerResId() = R.id.frag_container

    override fun changePage() {
        if (viewModel.nextQuestion() != null) {
            launchQuestionFragment()
        } else {
            launchListFragment()
        }
    }

}