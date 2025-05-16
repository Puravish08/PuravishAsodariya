package com.portfolio.puravishasodariya.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.portfolio.puravishasodariya.R
import com.portfolio.puravishasodariya.adapters.CertificationsAdapter
import com.portfolio.puravishasodariya.adapters.ExperienceAdapter
import com.portfolio.puravishasodariya.adapters.ProjectsAdapter
import com.portfolio.puravishasodariya.models.Certification
import com.portfolio.puravishasodariya.models.Experience
import com.portfolio.puravishasodariya.models.Project
import androidx.core.net.toUri

class HomeFragment : Fragment() {
    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var experienceRecyclerView: RecyclerView
    private lateinit var certificationsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProjectsRecyclerView(view)
        setupExperienceRecyclerView(view)
        setupCertificationsRecyclerView(view)
        setupViewAllButtons(view)
    }

    private fun setupProjectsRecyclerView(view: View) {
        projectsRecyclerView = view.findViewById(R.id.projectsRecyclerView)
        projectsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

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
            )
        )

        projectsRecyclerView.adapter = ProjectsAdapter(projects) { project ->
            // Handle project click - open GitHub link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubLink))
            startActivity(intent)
        }
    }

    private fun setupExperienceRecyclerView(view: View) {
        experienceRecyclerView = view.findViewById(R.id.experienceRecyclerView)
        experienceRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val experiences = listOf(
            Experience(
                "Senior Android Developer",
                "Hindava Infotech.",
                "Jan 2021 - Present • 10+ years",
                "• Led a team of 5 developers to build and maintain multiple Android applications\n" +
                        "• Implemented Clean Code principles",
                R.drawable.company_logo_placeholder,
                listOf("Kotlin", "Android", "Firebase")
            ),
            Experience(
                "Android & Flutter Developer",
                "Mobile Innovations",
                "Mar 2019 - Dec 2020 • 1 year 9 months",
                "• Developed cross-platform applications using Flutter framework\n" +
                        "• Integrated RESTful APIs and implemented state management",
                R.drawable.company_logo_placeholder,
                listOf("Flutter", "Dart", "Firebase")
            )
        )

        experienceRecyclerView.adapter = ExperienceAdapter(experiences)
    }

    private fun setupCertificationsRecyclerView(view: View) {
        certificationsRecyclerView = view.findViewById(R.id.certificationsRecyclerView)
        certificationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val certifications = listOf(
            Certification(
                "Android Developer",
                "Red & White Multimedia",
                "Issued Jun 2022 • No Expiration",
                R.drawable.certification_logo_placeholder,
                "https://www.credential.net/android-developer"
            ),
            Certification(
                "Flutter Development Bootcamp",
                "Udemy",
                "Issued Mar 2021",
                R.drawable.certification_logo_placeholder,
                "https://www.udemy.com/certificate/flutter-bootcamp"
            )
        )

        certificationsRecyclerView.adapter = CertificationsAdapter(certifications) { certification ->
            // Handle certificate click - open certificate link
            val intent = Intent(Intent.ACTION_VIEW, certification.certificateLink.toUri())
            startActivity(intent)
        }
    }

    private fun setupViewAllButtons(view: View) {
        // Set up "View All Projects" button
        view.findViewById<TextView>(R.id.btnViewAllProjects).setOnClickListener {
            navigateToTab(R.id.nav_projects)
        }

        // Set up "View All Experience" button
        view.findViewById<TextView>(R.id.btnViewAllExperience).setOnClickListener {
            navigateToTab(R.id.nav_experience)
        }
    }

    private fun navigateToTab(tabId: Int) {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav?.selectedItemId = tabId
    }
}

