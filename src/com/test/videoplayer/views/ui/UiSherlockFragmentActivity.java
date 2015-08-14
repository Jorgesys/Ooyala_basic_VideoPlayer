package com.test.videoplayer.views.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.ooyala.android.OoyalaPlayer;

public class UiSherlockFragmentActivity extends SherlockFragmentActivity implements com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener {
	
	public DrawerLayout Main_dwl;
	public ListView Menu_lsv;
	protected MediaPlayer player;
	protected OoyalaPlayer oplayer;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()){
	        case android.R.id.home:
	            if (Main_dwl.isDrawerOpen(Menu_lsv)){
	            	Main_dwl.closeDrawers();
	            }else{
	            	Main_dwl.openDrawer(Menu_lsv);
	            }
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	   
	  if (keyCode == KeyEvent.KEYCODE_BACK) {
	   
	    new AlertDialog.Builder(this)
	      .setIcon(android.R.drawable.ic_dialog_alert)
	      .setTitle("Quit?")
	      .setMessage("Are you sure?")
	      .setNegativeButton(android.R.string.cancel, null)
	      .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which){
	        	try {
	        		UiSherlockFragmentActivity.this.finish();
				} catch (Throwable e) {
					e.printStackTrace();
				}
	        }
	      })
	      .show();
	    return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	
	public void validateSignal(){
		@SuppressWarnings("static-access")
		ConnectivityManager conManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conManager.getActiveNetworkInfo();
		if(netInfo == null || !netInfo.isConnected()){
			new AlertDialog.Builder(this)
		      .setIcon(android.R.drawable.ic_dialog_alert)
		      .setTitle("Quit")
		      .setMessage("turn on Wifi?")
		      .setNegativeButton(android.R.string.ok, null)
		      .setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which){
		        	try {
		        		UiSherlockFragmentActivity.this.finish();
					} catch (Throwable e) {
						e.printStackTrace();
					}
		        }
		      })
		      .show();
		}
	}
}
