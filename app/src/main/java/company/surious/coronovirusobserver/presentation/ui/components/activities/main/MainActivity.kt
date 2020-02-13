package company.surious.coronovirusobserver.presentation.ui.components.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.presentation.ui.base.showWillBeImplementedToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationView()
    }

    private fun initNavigationView() {
        with(bottomNavigationView) {
            selectedItemId = R.id.homeNavigationItem
            setOnNavigationItemSelectedListener(::handleNavigationItemSelected)
        }
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

    private fun showCountriesFragment() {
        findNavController(mainNavigationHost.id).navigate(R.id.action_global_statusByPatientsStateFragment)
    }

}
