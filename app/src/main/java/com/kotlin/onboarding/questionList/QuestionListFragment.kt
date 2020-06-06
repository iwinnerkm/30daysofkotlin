package com.kotlin.onboarding.questionList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.onboarding.BaseViewModel
import com.kotlin.onboarding.R

class QuestionListFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionListFragment()
    }

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var adapter: ListAdapter

    private lateinit var listViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProviders.of(activity!!).get(BaseViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.question_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listRecyclerView = view.findViewById(R.id.list_rv)
        initAdaptor()
        setupAdaptor()
    }

    private fun initAdaptor() {
        listRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupAdaptor() {
        adapter = ListAdapter(listViewModel.questions)
        listRecyclerView.adapter = adapter
    }

}
