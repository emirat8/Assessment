package com.emiratz.assessment.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emiratz.assessment.R
import com.emiratz.assessment.model.AssessmentDetailResponse

class AssessmentAdapter(var data : List<AssessmentDetailResponse?>, var context: Context): RecyclerView.Adapter<AssessmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment, parent, false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtAssessmentTitle.text = data.get(position)?.title
        holder.txtAssessmentCloseDate.text = data.get(position)?.endDate
    }
    override fun getItemCount():Int = data.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAssessmentTitle = itemView.findViewById<TextView>(R.id.txtAssessmentTitle)
        val txtAssessmentCloseDate = itemView.findViewById<TextView>(R.id.txtAssessmentCloseDate)
    }
}