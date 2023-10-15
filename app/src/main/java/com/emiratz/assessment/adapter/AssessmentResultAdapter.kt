package com.emiratz.assessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.AssessmentResultDetailResponse

class AssessmentResultAdapter(var data : List<AssessmentResultDetailResponse?>, var context: Context): RecyclerView.Adapter<AssessmentResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssessmentResultAdapter.ViewHolder =
        AssessmentResultAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_assessment_result, parent, false)
        )
    override fun getItemCount():Int = data.size
    override fun onBindViewHolder(holder: AssessmentResultAdapter.ViewHolder, position: Int) {
        holder.txtAssessmentResultTitle.text = data.get(position)?.title
        holder.txtAssessmentResultTakeDate.text = data.get(position)?.takeDate
        holder.txtAssessmentResultScore.text = data.get(position)?.score.toString()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAssessmentResultTitle = itemView.findViewById<TextView>(R.id.txtAssessmentResultTitle)
        val txtAssessmentResultTakeDate = itemView.findViewById<TextView>(R.id.txtAssessmentResultTakeDate)
        val txtAssessmentResultScore = itemView.findViewById<TextView>(R.id.txtAssessmentResultScore)
    }
}