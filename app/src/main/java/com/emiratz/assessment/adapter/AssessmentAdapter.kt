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
import java.text.SimpleDateFormat

class AssessmentAdapter(var data : List<AssessmentDetailResponse?>, var context: FragmentActivity): RecyclerView.Adapter<AssessmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment, parent, false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val outputFormat = SimpleDateFormat("dd-MM-yyy HH:mm:ss")

        val date = inputFormat.parse(data.get(position)?.endDate)
        val formattedDate = outputFormat.format(date)
        holder.txtAssessmentTitle.text = data.get(position)?.title
        holder.txtAssessmentCloseDate.text = formattedDate.toString()

        holder.itemView.setOnClickListener{
            context.supportFragmentManager.beginTransaction()
                .replace(R.id.frmFragmentRoot, QuestionFragment(data.get(position)?.questions,
                    data.get(position)?.endDate!!, data.get(position)?.id))
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