package company.surious.coronovirusobserver.presentation.ui.components.fragments.status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import kotlinx.android.synthetic.main.fragment_status_by_country.*

//todo implement data refreshing
class StatusByCountryFragment : Fragment() {
    private val adapter = StatusByCountryAdapter()
    val args: StatusByCountryFragmentArgs by navArgs()

    companion object {
        private const val STATUS_KEY = "status"
        private const val PATIENT_STATE_KEY = "patientState"

        fun newInstance(
            statusEntity: StatusEntity,
            patientState: PatientState
        ): StatusByCountryFragment =
            StatusByCountryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(STATUS_KEY, statusEntity)
                    putString(PATIENT_STATE_KEY, patientState.toString())
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_status_by_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusByPatientStateRecyclerView.layoutManager = LinearLayoutManager(context)
        statusByPatientStateRecyclerView.adapter = adapter
        adapter.updateData(StatusByCountryMapper.map(args.entityStatus, args.patientState))
    }


}
