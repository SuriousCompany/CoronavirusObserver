package company.surious.coronavirusobserver.presentation.ui.components.fragments.status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.databinding.FragmentStatusBinding
import company.surious.coronavirusobserver.domain.entities.PatientState
import company.surious.coronavirusobserver.presentation.ui.base.ViewModelFactory
import company.surious.coronavirusobserver.presentation.ui.components.activities.main.NavigationProvider
import company.surious.coronavirusobserver.presentation.ui.components.widget.WidgetUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class StatusFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var statusViewModel: StatusViewModel
    private lateinit var binding: FragmentStatusBinding
    private lateinit var statusState: StatusState
    private lateinit var navigationProvider: NavigationProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_status, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationProvider = requireActivity() as NavigationProvider
        initView()
    }

    private fun initView() {
        statusViewModel = ViewModelProvider(this, viewModelFactory)[StatusViewModel::class.java]
        statusState = statusViewModel.statusState
        initBinding()
        initStateListeners()
    }

    private fun initBinding() {
        binding.eventHandler = StatusEventHandler()
        binding.stateModel = statusViewModel.statusState
        binding.lifecycleOwner = this
    }

    private fun initStateListeners() {
        statusState.statusEntity.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val status = statusState.statusEntity.get()
                if (status != null) {
                    WidgetUtils.updateWidget(requireActivity(), status)
                }

            }
        })
        statusState.isLoading.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val isLoading = statusState.isLoading.get()
                if (isLoading) {
                    navigationProvider.disableBottomNavigation()
                } else {
                    navigationProvider.enableBottomNavigation()
                }
            }
        })
    }

    inner class StatusEventHandler {
        fun onSwipeToRefresh() {
            statusViewModel.updateStatus()
        }

        fun onConfirmedClick() {
            navigationProvider.showCountriesFragment(PatientState.INFECTED)
        }

        fun onDeathsClick() {
            navigationProvider.showCountriesFragment(PatientState.DEAD)
        }

        fun onRecoveredClick() {
            navigationProvider.showCountriesFragment(PatientState.RECOVERED)
        }
    }
}
