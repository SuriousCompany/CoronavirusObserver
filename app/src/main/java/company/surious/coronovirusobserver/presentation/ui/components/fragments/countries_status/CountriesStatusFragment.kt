package company.surious.coronovirusobserver.presentation.ui.components.fragments.countries_status


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.domain.entities.PatientState
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import kotlinx.android.synthetic.main.fragment_countries_status.*

class CountriesStatusFragment : Fragment() {
    private val adapter =
        StatusByCountryAdapter()

    companion object {
        private const val STATUS_KEY = "status"
        private const val PATIENT_STATE_KEY = "patientState"

        fun newInstance(
            statusEntity: StatusEntity,
            patientState: PatientState
        ): CountriesStatusFragment =
            CountriesStatusFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(STATUS_KEY, statusEntity)
                    putString(PATIENT_STATE_KEY, patientState.toString())
                }
            }
    }

    private lateinit var statusEntity: StatusEntity
    private lateinit var patientState: PatientState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries_status, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            statusEntity = it.getParcelable(STATUS_KEY)!!
            patientState = PatientState.valueOf(it.getString(PATIENT_STATE_KEY)!!)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusByPatientStateRecyclerView.layoutManager = LinearLayoutManager(context)
        statusByPatientStateRecyclerView.adapter = adapter
        adapter.updateData(StatusByCountryMapper.map(statusEntity, patientState))
    }
}
