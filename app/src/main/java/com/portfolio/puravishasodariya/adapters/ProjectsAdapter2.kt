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
import com.portfolio.puravishasodariya.models.Project2

class ProjectsAdapter2(
    private val projects: List<Project2>,
    private val onProjectClick: (Project2) -> Unit
) : RecyclerView.Adapter<ProjectsAdapter2.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int = projects.size

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectImage: ImageView = itemView.findViewById(R.id.projectImage)
        private val projectTitle: TextView = itemView.findViewById(R.id.projectTitle)
        private val projectDescription: TextView = itemView.findViewById(R.id.projectDescription)
        private val techStackChipGroup: ChipGroup = itemView.findViewById(R.id.projectTechChipGroup)

        fun bind(project: Project2) {
            projectImage.setImageResource(project.imageResId)
            projectTitle.text = project.title
            projectDescription.text = project.description

            // Clear existing chips and add new ones
            techStackChipGroup.removeAllViews()
            for (tech in project.technologies) {
                val chip = Chip(itemView.context)
                chip.text = tech
                chip.setTextColor(itemView.context.getColor(android.R.color.white))
                chip.setChipBackgroundColorResource(R.color.chip_background)
                chip.setChipStrokeColorResource(R.color.accent_color)
                chip.chipStrokeWidth = 1f
                techStackChipGroup.addView(chip)
            }

//            btnViewProject.setOnClickListener {
//                onProjectClick(project)
//            }
        }
    }
}
