package company.surious.coronavirusobserver.presentation.ui.components.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.domain.entities.PatientState
import company.surious.coronavirusobserver.presentation.ui.base.showWillBeImplementedToast
import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.news.NewsFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity(), NavigationProvider {

    private val patientsStateFragment = StatusByPatientsStateFragment()
    private val statusFragment = StatusFragment()
    private val newsFragment = NewsFragment()

    private lateinit var displayingFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationView()
        initFragments()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainNavigationContainer, patientsStateFragment)
            .hide(patientsStateFragment)
            .add(R.id.mainNavigationContainer, newsFragment)
            .hide(newsFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.mainNavigationContainer, statusFragment)
            .commit()
        displayingFragment = statusFragment
    }

    private fun initNavigationView() {
        bottomNavigationView.selectedItemId = R.id.homeNavigationItem
        enableNavigationListener()
    }

    private fun enableNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(::handleNavigationItemSelected)
    }

    private fun disableNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(null)
    }

    private fun handleNavigationItemSelected(item: MenuItem): Boolean {
        val isDuplicateClick = bottomNavigationView.selectedItemId == item.itemId
        return if (!isDuplicateClick) {
            when (item.itemId) {
                R.id.mapNavigationItem -> {
                    showWillBeImplementedToast()
                    false
                }
                R.id.countriesNavigationItem -> {
                    switchFragment(patientsStateFragment)
                    true
                }
                R.id.homeNavigationItem -> {
                    switchFragment(statusFragment)
                    true
                }
                R.id.newsNavigationItem -> {
                    switchFragment(newsFragment)
                    true
                }
                R.id.settingsNavigationItem -> {
                    showWillBeImplementedToast()
                    false
                }
                else -> {
                    throw IllegalArgumentException("Unknown navigation item:" + item.title)
                }
            }
        } else {
            false
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(displayingFragment)
            .show(fragment)
            .commit()
        displayingFragment = fragment
    }

    override fun showCountriesFragment(patientState: PatientState) {
        if (displayingFragment !is StatusByPatientsStateFragment) {
            switchFragment(patientsStateFragment)
        }
        patientsStateFragment.showState(patientState)
        disableNavigationListener()
        bottomNavigationView.selectedItemId = R.id.countriesNavigationItem
        enableNavigationListener()
    }

    override fun onBackPressed() {
        if (displayingFragment is StatusFragment) {
            super.onBackPressed()
        } else {
            bottomNavigationView.selectedItemId = R.id.homeNavigationItem
        }
    }
}
