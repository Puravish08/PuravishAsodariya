package com.portfolio.puravishasodariya

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialSharedAxis
import com.portfolio.puravishasodariya.fragments.ContactFragment
import com.portfolio.puravishasodariya.fragments.ExperienceFragment
import com.portfolio.puravishasodariya.fragments.HomeFragment
import com.portfolio.puravishasodariya.fragments.ProjectsFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private var currentFragment: Fragment? = null
    private var isBottomNavVisible = true
    private var lastScrollY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply splash screen with extended duration
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        // Delay to show splash screen longer
        lifecycleScope.launch {
            delay(1000) // 1 second delay
            splashScreen.setKeepOnScreenCondition { false }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(insets.left, insets.top, insets.right, 0)
            WindowInsetsCompat.CONSUMED
        }

        setupBottomNavigation()

        // Load default fragment with entrance transition
        if (savedInstanceState == null) {
            val homeFragment = HomeFragment()
            setupFragmentTransitions(homeFragment)
            loadFragment(homeFragment, "home")
        }

        // Setup back stack listener to sync UI with back navigation
        supportFragmentManager.addOnBackStackChangedListener {
            updateBottomNavFromBackStack()
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation)

        // Apply initial animation to bottom nav
        bottomNavigation.alpha = 0f
        bottomNavigation.translationY = 100f
        bottomNavigation.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(500)
            .setInterpolator(DecelerateInterpolator())
            .start()

        bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_projects -> ProjectsFragment()
                R.id.nav_experience -> ExperienceFragment()
                R.id.nav_contact -> ContactFragment()
                else -> HomeFragment()
            }

            setupFragmentTransitions(fragment)
            loadFragment(fragment, item.title.toString().lowercase())
            true
        }

        bottomNavigation.setOnItemReselectedListener { /* Do nothing to prevent fragment recreation */ }
    }

    private fun setupFragmentTransitions(fragment: Fragment) {
        fragment.apply {
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                duration = 300
            }
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                duration = 300
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                duration = 300
            }
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                duration = 300
            }
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        if (currentFragment?.javaClass == fragment.javaClass) return

        currentFragment = fragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    private fun updateBottomNavFromBackStack() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        when (currentFragment) {
            is HomeFragment -> bottomNavigation.selectedItemId = R.id.nav_home
            is ProjectsFragment -> bottomNavigation.selectedItemId = R.id.nav_projects
            is ExperienceFragment -> bottomNavigation.selectedItemId = R.id.nav_experience
            is ContactFragment -> bottomNavigation.selectedItemId = R.id.nav_contact
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun handleScroll(scrollY: Int) {
        if (scrollY > lastScrollY + 20 && isBottomNavVisible) {
            // Scrolling down, hide the nav
            animateBottomNavigationVisibility(false)
        } else if (scrollY < lastScrollY - 20 && !isBottomNavVisible) {
            // Scrolling up, show the nav
            animateBottomNavigationVisibility(true)
        }

        // Update last scroll position
        if (scrollY >= 0) {
            lastScrollY = scrollY
        }
    }

    fun animateBottomNavigationVisibility(isVisible: Boolean) {
        if (isBottomNavVisible == isVisible) return
        isBottomNavVisible = isVisible

        val height = bottomNavigation.height.toFloat()

        if (isVisible) {
            bottomNavigation.visibility = View.VISIBLE
            bottomNavigation.alpha = 0f
            bottomNavigation.translationY = height

            bottomNavigation.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(250)
                .setInterpolator(DecelerateInterpolator())
                .start()
        } else {
            bottomNavigation.animate()
                .translationY(height)
                .alpha(0f)
                .setDuration(250)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {
                    if (!isBottomNavVisible) {
                        bottomNavigation.visibility = View.GONE
                    }
                }
                .start()
        }
    }

    fun setBottomNavigationVisibility(isVisible: Boolean) {
        bottomNavigation.visibility = if (isVisible) View.VISIBLE else View.GONE
        isBottomNavVisible = isVisible
    }
}


/*
package com.portfolio.puravishasodariya

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.portfolio.puravishasodariya.fragments.ContactFragment
import com.portfolio.puravishasodariya.fragments.ExperienceFragment
import com.portfolio.puravishasodariya.fragments.HomeFragment
import com.portfolio.puravishasodariya.fragments.ProjectsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private var currentFragment: Fragment? = null
    private var isBottomNavVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupBottomNavigation()

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "home")
        }

        // Setup back stack listener to sync UI with back navigation
        supportFragmentManager.addOnBackStackChangedListener {
            updateBottomNavFromBackStack()
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment(), "home")
                R.id.nav_projects -> loadFragment(ProjectsFragment(), "projects")
                R.id.nav_experience -> loadFragment(ExperienceFragment(), "experience")
                R.id.nav_contact -> loadFragment(ContactFragment(), "contact")
            }
            true
        }

        bottomNavigation.setOnItemReselectedListener { */
/* Do nothing to prevent fragment recreation *//*
 }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        if (currentFragment?.javaClass == fragment.javaClass) return

        currentFragment = fragment

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    private fun updateBottomNavFromBackStack() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        when (currentFragment) {
            is HomeFragment -> bottomNavigation.selectedItemId = R.id.nav_home
            is ProjectsFragment -> bottomNavigation.selectedItemId = R.id.nav_projects
            is ExperienceFragment -> bottomNavigation.selectedItemId = R.id.nav_experience
            is ContactFragment -> bottomNavigation.selectedItemId = R.id.nav_contact
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun animateBottomNavigationVisibility(isVisible: Boolean) {
        if (isBottomNavVisible == isVisible) return
        isBottomNavVisible = isVisible

        val height = bottomNavigation.height.toFloat()

        if (isVisible) {
            bottomNavigation.visibility = View.VISIBLE
            bottomNavigation.alpha = 0f
            bottomNavigation.translationY = height

            bottomNavigation.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(300)
                .setInterpolator(DecelerateInterpolator())
                .start()
        } else {
            bottomNavigation.animate()
                .translationY(height)
                .alpha(0f)
                .setDuration(300)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {
                    if (!isBottomNavVisible) {
                        bottomNavigation.visibility = View.GONE
                    }
                }
                .start()
        }
    }

    fun setBottomNavigationVisibility(isVisible: Boolean) {
        bottomNavigation.visibility = if (isVisible) View.VISIBLE else View.GONE
        isBottomNavVisible = isVisible
    }
}
*/
