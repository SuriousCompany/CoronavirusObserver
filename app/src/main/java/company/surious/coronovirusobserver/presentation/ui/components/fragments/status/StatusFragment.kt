package company.surious.coronovirusobserver.presentation.ui.components.fragments.status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.databinding.FragmentStatusBinding
import company.surious.coronovirusobserver.presentation.ui.base.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class StatusFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var statusViewModel: StatusViewModel
    private lateinit var binding: FragmentStatusBinding

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
        initView()
    }

    private fun initView() {
        statusViewModel = ViewModelProvider(this, viewModelFactory)[StatusViewModel::class.java]
        binding.eventHandler = StatusEventHandler()
        binding.stateModel = statusViewModel.statusState
        binding.lifecycleOwner = this
    }

    inner class StatusEventHandler {
        fun onSwipeToRefresh() {
            statusViewModel.updateStatus()
        }
    }
}
