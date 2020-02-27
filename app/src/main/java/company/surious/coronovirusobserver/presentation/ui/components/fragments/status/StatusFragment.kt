package company.surious.coronovirusobserver.presentation.ui.components.fragments.status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.databinding.FragmentStatusBinding
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.presentation.ui.base.ViewModelFactory
import company.surious.coronovirusobserver.presentation.ui.components.activities.main.NavigationProvider
import company.surious.coronovirusobserver.presentation.ui.components.widget.WidgetUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class StatusFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var statusViewModel: StatusViewModel
    private lateinit var binding: FragmentStatusBinding
    private lateinit var displayingStatusEntity: StatusEntity
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
        binding.eventHandler = StatusEventHandler()
        binding.stateModel = statusViewModel.statusState
        binding.lifecycleOwner = this
        statusViewModel.statusState.statusEntity.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val status = statusViewModel.statusState.statusEntity.get()!!
                displayingStatusEntity = status
                WidgetUtils.updateWidget(requireActivity(), status)
            }

        })
    }

    inner class StatusEventHandler {
        fun onSwipeToRefresh() {
            statusViewModel.updateStatus()
        }

        fun onConfirmedClick() {
            navigationProvider.showCountriesFragment(displayingStatusEntity, PatientState.INFECTED)
        }

        fun onDeathsClick() {
            navigationProvider.showCountriesFragment(displayingStatusEntity, PatientState.DEAD)
        }

        fun onRecoveredClick() {
            navigationProvider.showCountriesFragment(displayingStatusEntity, PatientState.RECOVERED)
        }
    }
}
