package com.emiratz.assessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.fragment.QuestionFragment
import com.emiratz.assessment.model.AssessmentDetailResponse

class AssessmentAdapter(var data : List<AssessmentDetailResponse?>, var context: FragmentActivity): RecyclerView.Adapter<AssessmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment, parent, false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtAssessmentTitle.text = data.get(position)?.title
        holder.txtAssessmentCloseDate.text = data.get(position)?.endDate

        holder.itemView.setOnClickListener{
            context.supportFragmentManager.beginTransaction()
                .replace(R.id.frmFragmentRoot, QuestionFragment(data.get(position)?.questions))
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