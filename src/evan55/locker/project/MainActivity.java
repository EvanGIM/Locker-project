package evan55.locker.project;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public class LockScreenReceiver extends BroadcastReceiver {

		 @Override
		 public void onReceive(Context context, Intent intent) {

		  if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
		   Intent i = new Intent(context, LockScreenActivity.class);
		   i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   context.startActivity(i);
		  } 
		 }
	}
	
	public class LockScreenService extends Service {

		 private LockScreenReceiver mReceiver = null;

		 @Override
		 public void onCreate() {
		  super.onCreate();
		  mReceiver = new LockScreenReceiver();
		  IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		  registerReceiver(mReceiver, filter);
		 }

		 @Override
		 public int onStartCommand(Intent intent, int flags, int startId) {
		  super.onStartCommand(intent, flags, startId);
		  if (intent != null) {
		   if (intent.getAction() == null) {
		    if (mReceiver == null) {
		     mReceiver = new LockScreenReceiver();
		     IntentFilter filter = new IntentFilter(
		      Intent.ACTION_SCREEN_OFF);
		     registerReceiver(mReceiver, filter);
		    }
		   }
		  }
		  return START_REDELIVER_INTENT;
		 }

		 @Override
		 public void onDestroy() {
		  super.onDestroy();

		  if (mReceiver != null) {
		   unregisterReceiver(mReceiver);
		  }
		 }

		 @Override
		 public IBinder onBind(Intent intent) {
		  return null;
		 }
	}
		 public class BootCompleteReceiver extends BroadcastReceiver {

			 @Override
			 public void onReceive(Context context, Intent intent) {

			  if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			   Intent i = new Intent(context, LockScreenService.class);
			   context.startService(i);
			  }
			 }
		 }
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// Handle action bar item clicks here. The action bar will
				// automatically handle clicks on the Home/Up button, so long
				// as you specify a parent activity in AndroidManifest.xml.
				int id = item.getItemId();
				if (id == R.id.action_settings) {
					Intent SettingActivity = new Intent(this, SettingsActivity.class);
					startActivity(SettingActivity);
					return true;
				}
				return super.onOptionsItemSelected(item);
			}
	}




