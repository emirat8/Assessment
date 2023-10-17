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
import com.emiratz.assessment.model.ResultResponse
import java.text.SimpleDateFormat

class AssessmentResultAdapter(var data : List<AssessmentResultDetailResponse?>, var userId : Int, var context: FragmentActivity): RecyclerView.Adapter<AssessmentResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_assessment_result, parent, false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val date = inputFormat.parse(data.get(position)?.endDate)
        val formattedDate = outputFormat.format(date)
        val finalScore = getFinalScoreByUserId(data[position]?.results, userId)

        holder.txtAssessmentScore.text = finalScore.toString()
        holder.txtAssessmentTitle.text = data.get(position)?.title
        holder.txtAssessmentCloseDate.text = formattedDate.toString()

//        holder.itemView.setOnClickListener{
//            context.supportFragmentManager.beginTransaction()
//                .replace(R.id.frmFragmentRoot, QuestionResultFragment(data.get(position)?.questions))
//                .addToBackStack(null)
//                .commit()
//        }
    }

    fun getFinalScoreByUserId(results: List<ResultResponse?>?, userIdToFind: Int): Int {
        results?.forEach { result ->
            if (result?.user?.id == userIdToFind) {
                return result.finalScore ?: 0 // Jika ditemukan, kembalikan finalScore, atau 0 jika null.
            }
        }
        return 0 // Jika tidak ditemukan, kembalikan 0 atau nilai default yang sesuai.
    }
    override fun getItemCount():Int = data.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAssessmentTitle = itemView.findViewById<TextView>(R.id.txtAssessmentResultTitle)
        val txtAssessmentScore = itemView.findViewById<TextView>(R.id.txtAssessmentResultScore)
        val txtAssessmentCloseDate = itemView.findViewById<TextView>(R.id.txtAssessmentResultTakeDate)
    }
}