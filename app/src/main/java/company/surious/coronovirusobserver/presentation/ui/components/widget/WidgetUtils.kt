package company.surious.coronovirusobserver.presentation.ui.components.widget

import android.content.Context
import android.content.Intent
import company.surious.coronovirusobserver.domain.entities.StatusEntity

object WidgetUtils {
    fun updateWidget(context: Context, statusEntity: StatusEntity) {
        val widgetIntent = Intent(context, CoronaWidget::class.java)
        widgetIntent.putExtra(CoronaWidget.STATUS_KEY, statusEntity)
        context.sendBroadcast(widgetIntent)
    }
}