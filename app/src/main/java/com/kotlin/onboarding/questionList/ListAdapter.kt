package com.kotlin.onboarding.questionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.onboarding.R
import com.kotlin.onboarding.data.Question

class ListAdapter(private val list: List<Question>?) : RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val que = holder.itemView.findViewById<TextView>(R.id.question_tv)
        val ans = holder.itemView.findViewById<TextView>(R.id.answer_tv)
        // set data
        que.text = list?.get(position)?.text
        ans.text = list?.get(position)?.answer
    }

}

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view)