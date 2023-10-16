package com.emiratz.assessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.fragment.QuestionFragment
import com.emiratz.assessment.fragment.QuestionResultFragment
import com.emiratz.assessment.model.AssessmentDetailResponse
import com.emiratz.assessment.model.AssessmentResultDetailResponse

class AssessmentResultAdapter(var data : List<AssessmentResultDetailResponse?>, var context: FragmentActivity): RecyclerView.Adapter<AssessmentResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment_result, parent, false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtAssessmentTitle.text = data.get(position)?.title
        holder.txtAssessmentCloseDate.text = data.get(position)?.endDate

        holder.itemView.setOnClickListener{
            context.supportFragmentManager.beginTransaction()
                .replace(R.id.frmFragmentRoot, QuestionResultFragment(data.get(position)?.questions))
                .addToBackStack(null)
                .commit()
        }
    }
    override fun getItemCount():Int = data.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAssessmentTitle = itemView.findViewById<TextView>(R.id.txtAssessmentTitle)
        val txtAssessmentCloseDate = itemView.findViewById<TextView>(R.id.txtAssessmentCloseDate)
    }
}