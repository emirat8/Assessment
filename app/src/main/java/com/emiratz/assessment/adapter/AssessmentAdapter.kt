package com.emiratz.assessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.AssessmentResponse

class AssessmentAdapter(var assessmentList: List<AssessmentResponse>): RecyclerView.Adapter<AssessmentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtAssessmentTitle = itemView.findViewById(R.id.txtAssessmentTitle) as TextView
        val txtAssessmentCloseDate = itemView.findViewById(R.id.txtAssessmentCloseDate) as TextView

        fun bind(assessment: AssessmentResponse){
            with(itemView){
                txtAssessmentTitle.text = assessment.title
                txtAssessmentCloseDate.text = assessment.closeDate
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssessmentAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment, parent, false))
    }

    override fun onBindViewHolder(holder: AssessmentAdapter.ViewHolder, position: Int) {
        return holder.bind(assessmentList[position])
    }

    override fun getItemCount(): Int {
        return  assessmentList.size
    }
}