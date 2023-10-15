package com.emiratz.assessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.AssessmentDetailResponse
import com.emiratz.assessment.model.QuestionResponse

class QuestionAdapter(var data : List<QuestionResponse?>?, var context: Context): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder =
        QuestionAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.txtQuestion.text = data?.get(position)?.text
        holder.txtChoice1.text = data?.get(position)?.choices?.get(0)?.value.toString()
        holder.txtChoice2.text = data?.get(position)?.choices?.get(1)?.value.toString()
        holder.txtChoice3.text = data?.get(position)?.choices?.get(2)?.value.toString()
        holder.txtChoice4.text = data?.get(position)?.choices?.get(3)?.value.toString()
    }
    override fun getItemCount():Int = data?.size ?:0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtQuestion = itemView.findViewById<TextView>(R.id.txtQuestion)
        val txtChoice1 = itemView.findViewById<TextView>(R.id.choice1)
        val txtChoice2 = itemView.findViewById<TextView>(R.id.choice2)
        val txtChoice3 = itemView.findViewById<TextView>(R.id.choice3)
        val txtChoice4 = itemView.findViewById<TextView>(R.id.choice4)
    }
}