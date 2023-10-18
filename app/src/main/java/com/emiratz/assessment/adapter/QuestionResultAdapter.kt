package com.emiratz.assessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.ChoiceResult
import com.emiratz.assessment.model.QuestionResultResponse

class QuestionResultAdapter(var data : List<QuestionResultResponse?>?, var context: Context): RecyclerView.Adapter<QuestionResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionResultAdapter.ViewHolder =
        QuestionResultAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    override fun onBindViewHolder(holder: QuestionResultAdapter.ViewHolder, position: Int) {
        val question = data?.get(position)

        holder.txtQuestion.text = question?.text
        holder.txtChoice1.text = question?.choices?.get(0)?.value
        holder.txtChoice2.text = question?.choices?.get(1)?.value
        holder.txtChoice3.text = question?.choices?.get(2)?.value
        holder.txtChoice4.text = question?.choices?.get(3)?.value

    }
    override fun getItemCount():Int = data?.size ?:0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtQuestion = itemView.findViewById<TextView>(R.id.txtQuestion)
        val txtChoice1 = itemView.findViewById<TextView>(R.id.choice1)
        val txtChoice2 = itemView.findViewById<TextView>(R.id.choice2)
        val txtChoice3 = itemView.findViewById<TextView>(R.id.choice3)
        val txtChoice4 = itemView.findViewById<TextView>(R.id.choice4)
        val grpChoice = itemView.findViewById<RadioGroup>(R.id.grpChoice)
    }
}