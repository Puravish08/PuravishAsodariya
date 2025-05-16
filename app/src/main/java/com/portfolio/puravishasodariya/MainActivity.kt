package com.portfolio.puravishasodariya

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.portfolio.puravishasodariya.fragments.ContactFragment
import com.portfolio.puravishasodariya.fragments.ExperienceFragment
import com.portfolio.puravishasodariya.fragments.HomeFragment
import com.portfolio.puravishasodariya.fragments.ProjectsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_projects -> loadFragment(ProjectsFragment())
                R.id.nav_experience -> loadFragment(ExperienceFragment())
                R.id.nav_contact -> loadFragment(ContactFragment())
            }
            true
        }

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
