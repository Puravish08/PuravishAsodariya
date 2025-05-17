package com.portfolio.puravishasodariya.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
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
        projectsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val projects = listOf(
            Project(
                "VoiceScreenLock App",
                "Developed a secure Android lock screen using voice recognition for user authentication.",
                R.drawable.ic_voice,
                listOf("Java", "VoiceRecognition"),
                "https://github.com/Puravish08/VoiceScreenLock"
            ),
            Project(
                "Don't Touch My Phone",
                "Created an anti-theft app that triggers alerts using motion sensor detection.",
                R.drawable.ic_donttouch,
                listOf("Java", "Sensor Integration"),
                "https://github.com/Puravish08/DontTouchmyphone"
            ),
            Project(
                "Weather App",
                "Built a weather forecasting app with real-time update using API integration and location services.",
                R.drawable.ic_weater,
                listOf("Kotlin", "Api Integration"),
                "https://github.com/Puravish08/Wetherapp"
            )
        )

        projectsRecyclerView.adapter = ProjectsAdapter(projects) { project ->
            // Handle project click - open GitHub link
            val intent = Intent(Intent.ACTION_VIEW, project.githubLink.toUri())
            startActivity(intent)
        }
    }

    private fun setupExperienceRecyclerView(view: View) {
        experienceRecyclerView = view.findViewById(R.id.experienceRecyclerView)
        experienceRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val experiences = listOf(
            Experience(
                "Android & Flutter Developer",
                "Digitalk Techno LLP.",
                "Dec 2024 - Present • 6+ Months",
                """
            • Developed high-performance cross-platform applications using the Flutter & Android
            • Integrated RESTful APIs and implemented efficient state management solutions
            • Translated complex UI/UX designs into responsive and intuitive user interfaces
            • Conducted performance optimizations to reduce app load times and memory usage
            • Contributed to CI/CD pipeline integration for seamless testing and automated deployment """.trimIndent(),
                R.drawable.ic_d,
                listOf("Android", "Kotlin", "Flutter", "API Integration", "MVVM", "Trello")
            ),
            Experience(
                "Android Developer",
                "Hindava Info-tech.",
                "Jan 2024 - Dec 2024 • 10+ months",
                """
                   • Started my professional career as an Android developer at Digitalk Techno LLP
                   • Contributed to application development using Java, focusing on clean and maintainable code
                   • Learned and implemented RESTful API integration to fetch and display dynamic data
                   • Collaborated with senior developers to understand and apply MVVM architecture patterns
                   • Participated in team meetings, code reviews, and sprint planning to align with project goals
                   • Proactively worked on improving debugging skills and understanding of Android lifecycle components
                   """.trimIndent(),
                R.drawable.ic_h,
                listOf("Android", "Java", "API Integration")
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
                "Issued Dec 2023 • No Expiration",
                R.drawable.redandwhite,
            )
//            Certification(
//                "Flutter Development Bootcamp",
//                "Udemy",
//                "Issued Mar 2021",
//                R.drawable.certification_logo_placeholder,
//                "https://www.udemy.com/certificate/flutter-bootcamp"
//            )
        )

        certificationsRecyclerView.adapter =
            CertificationsAdapter(certifications) { certification ->
                // Handle certificate click - open certificate link
//            val intent = Intent(Intent.ACTION_VIEW, certification.certificateLink.toUri())
//            startActivity(intent)
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

