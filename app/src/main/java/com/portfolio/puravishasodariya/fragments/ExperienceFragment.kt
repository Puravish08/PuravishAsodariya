package com.portfolio.puravishasodariya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.puravishasodariya.R
import com.portfolio.puravishasodariya.adapters.ExperienceAdapter
import com.portfolio.puravishasodariya.models.Experience

class ExperienceFragment : Fragment() {
    private lateinit var experienceRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                listOf("Android", "Kotlin", "Flutter", "API Integration", "MVVM", "Ci/CD","Postman Api Testing")
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
                listOf("Android", "Java", "API Integration","SQLite")
            )
        )

        experienceRecyclerView.adapter = ExperienceAdapter(experiences)
    }
}
