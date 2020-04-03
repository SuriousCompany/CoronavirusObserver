package company.surious.coronavirusobserver.presentation.ui.components.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.domain.entities.PatientState
import company.surious.coronavirusobserver.presentation.ui.base.ViewModelFactory
import company.surious.coronavirusobserver.presentation.ui.base.showWillBeImplementedToast
import company.surious.coronavirusobserver.presentation.ui.components.fragments.countries_status.StatusByPatientsStateFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.news.NewsFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.settings.SettingsFragment
import company.surious.coronavirusobserver.presentation.ui.components.fragments.status.StatusFragment
import company.surious.coronavirusobserver.presentation.ui.components.views.network_state_snackbar.NetworkStateSnackbarDelegate
import company.surious.coronavirusobserver.presentation.ui.components.views.network_state_snackbar.NetworkStateViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationProvider {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val patientsStateFragment = StatusByPatientsStateFragment()
    private val statusFragment = StatusFragment()
    private val newsFragment = NewsFragment()
    private val settingsFragment = SettingsFragment()
    private var displayingFragment: Fragment? = null
    private lateinit var networkStateDelegate: NetworkStateSnackbarDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationView()
        initFragments()
        initNetworkStateDelegate()
    }

    override fun onStart() {
        super.onStart()
        networkStateDelegate.attach(mainNavigationContainer)
    }

    override fun onStop() {
        super.onStop()
        networkStateDelegate.detach()
    }

    private fun initFragments() {
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainNavigationContainer, patientsStateFragment)
                .hide(patientsStateFragment)
                .add(R.id.mainNavigationContainer, newsFragment)
                .hide(newsFragment)
                .add(R.id.mainNavigationContainer, settingsFragment)
                .hide(settingsFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .add(R.id.mainNavigationContainer, statusFragment)
                .commit()
        }
        switchFragment(statusFragment)
    }

    private fun initNavigationView() {
        bottomNavigationView.selectedItemId = R.id.homeNavigationItem
        enableNavigationListener()
    }

    private fun initNetworkStateDelegate() {
        val networkStateViewModel =
            ViewModelProvider(this, viewModelFactory)[NetworkStateViewModel::class.java]
        networkStateDelegate = NetworkStateSnackbarDelegate(networkStateViewModel)
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
                    switchFragment(settingsFragment)
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

    private fun switchFragment(fragment: Fragment) {
        displayingFragment?.let {
            supportFragmentManager.beginTransaction()
                .hide(it)
                .show(fragment)
                .commit()
        }
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

    override fun disableBottomNavigation() {
        bottomNavigationView.menu.forEach {
            it.isEnabled = false
        }
    }

    override fun enableBottomNavigation() {
        bottomNavigationView.menu.forEach {
            it.isEnabled = true
        }
    }

    override fun onBackPressed() {
        if (displayingFragment is StatusFragment) {
            super.onBackPressed()
        } else {
            bottomNavigationView.selectedItemId = R.id.homeNavigationItem
        }
    }
}
