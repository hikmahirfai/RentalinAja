package com.tugas_besar.rentalaja.ui.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.tugas_besar.rentalaja.R
import com.tugas_besar.rentalaja.model.Mobil
import com.tugas_besar.rentalaja.utils.Helper

/**
 * Implementation of App Widget functionality.
 */
class DataAppWidget : AppWidgetProvider() {


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
private var dataList = 8

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val widgetText = context.getString(R.string.appwidget_text)
    val jumlahMobil= dataList.toString()
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.data_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)
    views.setTextViewText(R.id.appwidget_text1, jumlahMobil)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}