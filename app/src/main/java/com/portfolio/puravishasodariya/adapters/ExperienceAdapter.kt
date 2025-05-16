package com.portfolio.puravishasodariya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.portfolio.puravishasodariya.R
import com.portfolio.puravishasodariya.models.Experience

class ExperienceAdapter(
    private val experiences: List<Experience>
) : RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.experience_item_layout, parent, false)
        return ExperienceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.bind(experiences[position])
    }

    override fun getItemCount() = experiences.size

    inner class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
        private val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        private val companyName: TextView = itemView.findViewById(R.id.companyName)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val jobDescription: TextView = itemView.findViewById(R.id.jobDescription)
        private val techStackChipGroup: ChipGroup = itemView.findViewById(R.id.techStackChipGroup)

        fun bind(experience: Experience) {
            companyLogo.setImageResource(experience.companyLogoResId)
            jobTitle.text = experience.jobTitle
            companyName.text = experience.companyName
            duration.text = experience.duration
            jobDescription.text = experience.description

            // Clear previous chips
            techStackChipGroup.removeAllViews()

            // Add technology chips
            for (tech in experience.technologies) {
                val chip = Chip(itemView.context)
                chip.text = tech
                chip.isCheckable = false
                chip.setChipBackgroundColorResource(R.color.chip_background)
                chip.setChipStrokeColorResource(R.color.accent_color)
                chip.chipStrokeWidth = 1f
                techStackChipGroup.addView(chip)
            }
        }
    }
}
