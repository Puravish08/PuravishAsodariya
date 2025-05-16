package com.portfolio.puravishasodariya.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.puravishasodariya.R
import com.portfolio.puravishasodariya.adapters.ProjectsAdapter
import com.portfolio.puravishasodariya.models.Project

class ProjectsFragment : Fragment() {
    private lateinit var projectsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectsRecyclerView = view.findViewById(R.id.projectsRecyclerView)
        projectsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val projects = listOf(
            Project(
                "E-Commerce App",
                "A full-featured e-commerce application with payment integration and real-time tracking.",
                R.drawable.project_placeholder,
                listOf("Kotlin", "MVVM", "Firebase"),
                "https://github.com/puravish/ecommerce-app"
            ),
            Project(
                "Weather Forecast",
                "Real-time weather forecasting app with location tracking and notifications.",
                R.drawable.project_placeholder,
                listOf("Flutter", "Dart", "API Integration"),
                "https://github.com/puravish/weather-app"
            ),
            Project(
                "Task Manager",
                "Productivity app for managing tasks with reminders and categories.",
                R.drawable.project_placeholder,
                listOf("Java", "Room", "MVVM"),
                "https://github.com/puravish/task-manager"
            ),
            Project(
                "Social Media App",
                "Social networking platform with real-time messaging and content sharing.",
                R.drawable.project_placeholder,
                listOf("Kotlin", "Firebase", "MVVM"),
                "https://github.com/puravish/social-app"
            ),
            Project(
                "Fitness Tracker",
                "Health and fitness tracking application with workout plans and progress monitoring.",
                R.drawable.project_placeholder,
                listOf("Flutter", "Firebase", "BLoC"),
                "https://github.com/puravish/fitness-tracker"
            )
        )

        projectsRecyclerView.adapter = ProjectsAdapter(projects) { project ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubLink))
            startActivity(intent)
        }
    }
}
