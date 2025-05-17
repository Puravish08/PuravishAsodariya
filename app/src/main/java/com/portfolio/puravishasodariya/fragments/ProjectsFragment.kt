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
import com.portfolio.puravishasodariya.adapters.ProjectsAdapter2
import com.portfolio.puravishasodariya.models.Project
import com.portfolio.puravishasodariya.models.Project2
import androidx.core.net.toUri

class ProjectsFragment : Fragment() {
    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var projectsRecyclerView2: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectsRecyclerView = view.findViewById(R.id.projectsRecyclerView)
        projectsRecyclerView2 = view.findViewById(R.id.projectsRecyclerView2)
//        projectsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        projectsRecyclerView2.layoutManager = LinearLayoutManager(requireContext())
// For horizontal layout
        projectsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        projectsRecyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        projectsRecyclerView2

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

       val projects2 = listOf(
            Project2(
                "Photo Editor",
                "Developed a feature-rich photo editing app with filters, cropping, and image enhancement using Java and third-party libraries.",
                R.drawable.ic_photo_editor,
                listOf("Java", "Library"),
            ),
            Project2(
                "Plant App",
                "Created a plant care app integrating SQLite for local storage, Firebase Authentication, and RESTful APIs.",
                R.drawable.ic_plant_app,
                listOf("Java", "Sqlite Database","Api Integration"),
            ),
            Project2(
                "Attendance App",
                "Built an attendance tracking app with real-time updates using Kotlin, MVVM architecture, API integration, and automated testing.",
                R.drawable.ic_attendance_app,
                listOf("Kotlin", "Api Integration", "MVVM Architecture", "CI/CD Pipeline", "Appium"),
            ),
            Project2(
                "Hotel Booking App",
                "Developed a cross-platform hotel booking app with Flutter, implementing clean architecture, API integration, and CI/CD pipelines.",
                R.drawable.ic_hotel_booking,
                listOf("Flutter", "Api Integration", "Clean Architecture", "CI/CD Pipeline"),
            )
        )

        projectsRecyclerView.adapter = ProjectsAdapter(projects) { project ->
            val intent = Intent(Intent.ACTION_VIEW, project.githubLink.toUri())
            startActivity(intent)
        }

        projectsRecyclerView2.adapter = ProjectsAdapter2(projects2) {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubLink))
//            startActivity(intent)
        }

    }
}
