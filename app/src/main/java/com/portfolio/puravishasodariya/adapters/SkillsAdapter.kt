package com.portfolio.puravishasodariya.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.puravishasodariya.R


class SkillAdapter(private val skills: List<Skill>) :
    RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.skillIcon)
        val name: TextView = view.findViewById(R.id.skillName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_skill, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        holder.icon.setImageResource(skill.iconResId)
        holder.name.text = skill.name
    }

    override fun getItemCount() = skills.size
}

data class Skill(
    val name: String,
    val iconResId: Int
)