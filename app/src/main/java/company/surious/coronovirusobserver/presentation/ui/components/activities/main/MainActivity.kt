package company.surious.coronovirusobserver.presentation.ui.components.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import company.surious.coronovirusobserver.MainNavigationGraphDirections
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.presentation.ui.base.showWillBeImplementedToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity(), NavigationProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationView()
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
                R.id.chartNavigationItem -> {
                    showWillBeImplementedToast()
                    false
                }
                R.id.countriesNavigationItem -> {
                    showCountriesFragment()
                    true
                }
                R.id.homeNavigationItem -> {
                    showHomeFragment()
                    true
                }
                R.id.newsNavigationItem -> {
                    showNewsFragment()
                    true
                }
                R.id.settingsNavigationItem -> {
                    true
                }
                else -> {
                    throw IllegalArgumentException("Unknown navigation item:" + item.title)
                }
            }
        } else {
            false
        }
    }

    private fun showHomeFragment() {
        findNavController(mainNavigationHost.id).navigate(R.id.action_global_statusFragment)
    }

    private fun showSettingsFragment() {

    }

    private fun showNewsFragment() {
        findNavController(mainNavigationHost.id).navigate(R.id.action_global_newsFragment)
    }

    override fun showCountriesFragment() {
        findNavController(mainNavigationHost.id).navigate(R.id.action_global_statusByPatientsStateFragment)
    }

    override fun showCountriesFragment(statusEntity: StatusEntity, patientState: PatientState) {
        disableNavigationListener()
        bottomNavigationView.selectedItemId = R.id.countriesNavigationItem
        val action = MainNavigationGraphDirections.actionGlobalStatusByPatientsStateFragment(
            statusEntity,
            patientState
        )
        findNavController(mainNavigationHost.id).navigate(action)
        enableNavigationListener()
    }
}
