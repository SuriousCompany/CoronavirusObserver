package company.surious.coronavirusobserver.presentation.ui.components.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import company.surious.coronavirusobserver.R
import company.surious.coronavirusobserver.databinding.FragmentSettingsBinding
import company.surious.coronavirusobserver.presentation.ui.base.showWillBeImplementedToast


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.eventsHandler = SettingsEventsHandler()
    }

    private fun showFeedbackScreen() {
        Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.coronavirus_observer_feedback))
            type = "message/rfc822"
            startActivity(Intent.createChooser(this, getString(R.string.choose_email_client)))
        }
    }

    inner class SettingsEventsHandler {
        fun onSendFeedbackClick() {
            showFeedbackScreen()
        }

        fun onUpdateIntervalSectionClicked() {
            requireContext().showWillBeImplementedToast()
        }
    }

}
