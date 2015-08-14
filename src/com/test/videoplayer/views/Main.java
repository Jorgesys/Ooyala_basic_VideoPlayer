package com.test.videoplayer.views;




import com.actionbarsherlock.view.MenuItem;
import com.test.videoplayer.R;
//import com.test.videoplayer.asyntasck.VideoAsyntasckDB;
import com.test.videoplayer.constants.*;
import com.test.videoplayer.objects.Video;
import com.test.videoplayer.views.ui.UiSherlockFragmentActivity;
import com.test.videoplayer.views.fragments.VideoPlayerFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

public class Main extends UiSherlockFragmentActivity {
	private com.test.videoplayer.asyntasck.VideoAsyntasckXML mfa;
	//private com.test.videoplayer.asyntasck.VideoAsyntasckDB mdb;
	VideoPlayerFragment fragVideo;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); //BASE
		Main_dwl = (DrawerLayout)findViewById(R.id.main_dwl);
		Menu_lsv = (ListView)findViewById(R.id.menu_lsv_main);
		mfa = new com.test.videoplayer.asyntasck.VideoAsyntasckXML(this.getApplicationContext(),Menu_lsv ,Main_dwl,this);
		mfa.execute(new Constants().url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}
	
	public void passValue(com.test.videoplayer.objects.Video video){
		openVideo(video);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		return true;
	}
	
	public void openVideo(Video video){
		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		if(video!=null){
			fragVideo = new VideoPlayerFragment(video); 
		}else{
			fragVideo = new VideoPlayerFragment();			
		}
		t.replace(R.id.main_frame, fragVideo);
		t.commit();
	}
}
