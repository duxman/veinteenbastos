package com.pxr.tutorials.widget.basic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetActivity extends AppWidgetProvider 
{
	public static WidgetActivity Widget = null;
	public static Context context;
	public static AppWidgetManager appWidgetManager;
	public static int appWidgetIds[];
	Timer t;
	
	@Override
    public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )    {		
		if (null == context) context = WidgetActivity.context;
	    if (null == appWidgetManager) appWidgetManager = WidgetActivity.appWidgetManager;
	    if (null == appWidgetIds) appWidgetIds = WidgetActivity.appWidgetIds;

	    WidgetActivity.Widget = this;
	    WidgetActivity.context = context;
	    WidgetActivity.appWidgetManager = appWidgetManager;
	    WidgetActivity.appWidgetIds = appWidgetIds;
	    
		Log.i("PXR", "onUpdate");
		 
		final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) 
        {
            int appWidgetId = appWidgetIds[i];             
            updateAppWidget(context,appWidgetManager, appWidgetId);            
        }
        CreateTimer();
        
    }
	private void CreateTimer()
	{
		 if ( t == null )
	     {
	      CharSequence text = "INSTALLING";
	      int duration = Toast.LENGTH_SHORT;
	      Toast toast = Toast.makeText(context, text, duration);
	      toast.show();

	      t = new Timer();
	      Calendar cal = Calendar.getInstance();
	      cal.add(Calendar.SECOND, 1);
	      cal.set(Calendar.MILLISECOND, 0);
	      t.scheduleAtFixedRate(new MyTime(),cal.getTime(), 1000);
	     }
	}

	
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId) 
	{
        
        DateFormat format = SimpleDateFormat.getTimeInstance( SimpleDateFormat.MEDIUM, Locale.getDefault() );
        CharSequence text = format.format( new Date());
                
        Intent intent = new Intent(context, UpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        remoteViews.setOnClickPendingIntent(R.id.LinearLayout01, pendingIntent);
        
        remoteViews.setTextViewText(R.id.widget_textview, text);
 
        // Tell the widget manager
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
	
	
	
	public static class UpdateService extends Service 
	{
        @Override
        public void onStart(Intent intent, int startId) 
        {
        	WidgetActivity.Widget.onUpdate(context, appWidgetManager, appWidgetIds);
        	Toast.makeText(context, "Update Widget", Toast.LENGTH_SHORT).show();
        }
		@Override
		public IBinder onBind(Intent arg0) {
			return null;
		}
    }
	 private class MyTime extends TimerTask
	 {

	    @Override
	    public void run()
	     {
	      try
	      {
	    	  WidgetActivity.Widget.onUpdate(context, appWidgetManager, appWidgetIds);	      
	      }
	      catch (Exception e)
	       {
	        CharSequence text = "tim_excp : " + e.getMessage();
	        int duration = Toast.LENGTH_SHORT;
	        Toast toast = Toast.makeText(context, text, duration);
	        toast.show();
	       }
	     }
	   }	
}