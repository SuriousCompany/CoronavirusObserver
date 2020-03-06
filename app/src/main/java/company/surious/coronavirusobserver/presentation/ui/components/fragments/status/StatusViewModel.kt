package company.surious.coronavirusobserver.presentation.ui.components.fragments.status

import androidx.lifecycle.ViewModel
import com.crashlytics.android.Crashlytics
import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.use_case.GetStatusUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StatusViewModel @Inject constructor(
    private val getStatusUseCase: GetStatusUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val statusState = StatusState()

    init {
        updateStatus()
    }

    fun updateStatus() {
        statusState.isLoading.set(true)
        compositeDisposable.add(
            getStatusUseCase.execute(null).subscribe(
                ::displayStatus,
                ::displayError
            )
        )
    }

    private fun displayStatus(status: StatusEntity) {
        with(statusState) {
            dead.set(status.totalDead)
            infected.set(status.totalInfected)
            recovered.set(status.totalRecovered)
            isLoading.set(false)
            statusEntity.set(status)
        }
    }

    private fun displayError(error: Throwable) {
        Crashlytics.logException(error)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}