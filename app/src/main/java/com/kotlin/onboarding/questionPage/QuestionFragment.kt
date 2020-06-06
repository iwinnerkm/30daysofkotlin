package com.kotlin.onboarding.questionPage

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kotlin.onboarding.BaseViewModel
import com.kotlin.onboarding.R
import com.kotlin.onboarding.data.Question
import com.kotlin.onboarding.utils.Constants

class QuestionFragment : Fragment() {

    private lateinit var questionTv: TextView
    private lateinit var answerEt: EditText
    private lateinit var nextButton: Button
    private var listener: Navigation? = null

    private var currentQuestion: Question? = null

    companion object {
        fun newInstance() = QuestionFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Navigation
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(BaseViewModel::class.java)
        currentQuestion = viewModel.nextQuestion()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.question_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionTv = view.findViewById(R.id.question_tv)
        answerEt = view.findViewById(R.id.answer_et)
        nextButton = view.findViewById(R.id.next_btn)

        currentQuestion?.run {
            questionTv.text = text
            if (type.equals(Constants.INTEGER)) {
                answerEt.inputType = InputType.TYPE_CLASS_NUMBER
            } else if (type.equals(Constants.STRING)) {
                answerEt.inputType = InputType.TYPE_CLASS_TEXT
            }
        }

        nextButton.setOnClickListener {
            currentQuestion?.answer = answerEt.text.toString().trim()
            viewModel.markAttempted(currentQuestion)
            listener?.changePage()
        }
    }

    interface Navigation {
        fun changePage()
    }
}
