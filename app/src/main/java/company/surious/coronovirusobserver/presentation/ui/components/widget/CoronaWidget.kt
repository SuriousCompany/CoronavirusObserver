package company.surious.coronovirusobserver.presentation.ui.components.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import company.surious.coronovirusobserver.R
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.presentation.ui.components.services.update_status_service.StatusService


class CoronaWidget : AppWidgetProvider() {
    companion object {
        const val STATUS_KEY = "status"
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        updateAppWidgets(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val appWidgetManager =
            AppWidgetManager.getInstance(context!!.applicationContext)
        val thisWidget = ComponentName(
            context.applicationContext,
            CoronaWidget::class.java
        )
        val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
        updateAppWidgets(context, appWidgetManager, appWidgetIds, intent)
    }

    private fun updateAppWidgets(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        intent: Intent? = null
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, intent)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        intent: Intent? = null
    ) {
        if (intent == null) {
            startStatusService(context)
        } else {
            val status = intent.getParcelableExtra<StatusEntity>(STATUS_KEY)
            if (status != null) {
                updateUi(context, appWidgetManager, appWidgetId, status)
            } else {
                startStatusService(context)
            }
        }
    }

    private fun updateUi(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        status: StatusEntity
    ) {
        val views = RemoteViews(context.packageName, R.layout.corona_widget)
        views.setTextViewText(R.id.confirmedWidgetTextView, status.totalInfected.toString())
        views.setTextViewText(R.id.deadWidgetTextView, status.totalDead.toString())
        views.setTextViewText(
            R.id.recoveredWidgetTextView,
            status.totalRecovered.toString()
        )
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun startStatusService(context: Context) {
        val serviceIntent = Intent(context, StatusService::class.java)
        ContextCompat.startForegroundService(context, serviceIntent)
    }

    override fun onEnabled(context: Context) {}

    override fun onDisabled(context: Context) {}
}
