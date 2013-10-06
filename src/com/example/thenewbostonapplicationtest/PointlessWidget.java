package com.example.thenewbostonapplicationtest;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PointlessWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Random r = new Random();
		int randomInt = r.nextInt(1000000);
		String rand = String.valueOf(randomInt);

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);
			remoteViews.setTextViewText(R.id.tvWidgetUpdate, rand);
			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "bye..", Toast.LENGTH_SHORT).show();
	}

}
