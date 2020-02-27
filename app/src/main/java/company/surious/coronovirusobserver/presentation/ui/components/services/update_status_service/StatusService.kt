package company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.domain.use_case.GetStatusUseCase
import company.surious.coronovirusobserver.presentation.ui.components.activities.main.MainActivity
import company.surious.coronovirusobserver.presentation.ui.components.widget.WidgetUtils
import dagger.android.DaggerService
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class StatusService : DaggerService() {
    private val CHANNEL_ID = "StatusServiceChannel"

    @Inject
    lateinit var getStatusUseCase: GetStatusUseCase

    private var disposable: Disposable? = null

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        startForeground(1, createNotification())
        requestStatus()
        return Service.START_STICKY_COMPATIBILITY
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun createNotification() =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.updating_application_widget))
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    0
                )
            )
            .build()

    private fun requestStatus() {
        disposable?.dispose()
        disposable = getStatusUseCase.execute(null).subscribe(
            { status ->
                WidgetUtils.updateWidget(this, status)
                stop()
            },
            {
                Log.e("statusWidget", "error on status retrieving", it)
                stop()
            }
        )
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)!!
            manager.createNotificationChannel(serviceChannel)
        }
    }
}
