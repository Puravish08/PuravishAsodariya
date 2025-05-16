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
                "Senior Android Developer",
                "Tech Solutions Inc.",
                "Jan 2021 - Present • 2+ years",
                "• Led a team of 5 developers to build and maintain multiple Android applications\n" +
                        "• Implemented MVVM architecture and Clean Code principles\n" +
                        "• Reduced app crash rate by 75% and improved app performance\n" +
                        "• Mentored junior developers and conducted code reviews\n" +
                        "• Collaborated with product and design teams to define app features",
                R.drawable.company_logo_placeholder,
                listOf("Kotlin", "Android", "Firebase", "MVVM")
            ),
            Experience(
                "Flutter Developer",
                "Mobile Innovations",
                "Mar 2019 - Dec 2020 • 1 year 9 months",
                "• Developed cross-platform applications using Flutter framework\n" +
                        "• Integrated RESTful APIs and implemented state management\n" +
                        "• Collaborated with design team to implement UI/UX designs\n" +
                        "• Optimized app performance and reduced load times\n" +
                        "• Implemented CI/CD pipelines for automated testing and deployment",
                R.drawable.company_logo_placeholder,
                listOf("Flutter", "Dart", "Firebase", "BLoC")
            ),
            Experience(
                "Android Developer",
                "AppTech Solutions",
                "Jun 2017 - Feb 2019 • 1 year 8 months",
                "• Developed and maintained Android applications for various clients\n" +
                        "• Implemented RESTful API integrations and local data storage\n" +
                        "• Fixed bugs and improved app stability\n" +
                        "• Participated in agile development process\n" +
                        "• Collaborated with QA team to ensure app quality",
                R.drawable.company_logo_placeholder,
                listOf("Java", "Android", "SQLite", "MVP")
            )
        )

        experienceRecyclerView.adapter = ExperienceAdapter(experiences)
    }
}
