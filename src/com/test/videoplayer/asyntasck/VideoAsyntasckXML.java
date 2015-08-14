package com.test.videoplayer.asyntasck;

import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.test.videoplayer.action.VideoActionXML;
import com.test.videoplayer.adapters.VideoAdapter;
import com.test.videoplayer.objects.Video;
import com.test.videoplayer.views.Main;

public class VideoAsyntasckXML extends AsyncTask<String, Float, String> implements OnItemClickListener {
	Main main;
	private Context context;
	private List<Video> video_list;
	private ListView menu_lsv;
	private DrawerLayout main_dwl;
	private VideoActionXML videoAction;
	
	
	public VideoAsyntasckXML(Context context, ListView lsvMenu, DrawerLayout dwlMain, Main main){
		this.context = context;
		this.menu_lsv = lsvMenu;
		this.main_dwl = dwlMain;
		this.main = main;
	}
	
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(String... params) {
		videoAction = new VideoActionXML(params[0]);
		video_list = videoAction.parse();
		publishProgress(2f);
    	return "Wait..";
	}
	
	@Override
    protected void onProgressUpdate(Float... values){
		super.onProgressUpdate(values);
    }
	
	@Override
    protected void onPostExecute(String result) {
		menu_lsv.setAdapter(new VideoAdapter(context, video_list));
		menu_lsv.setOnItemClickListener(this);
		main.openVideo(null);
	}
	
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
    	main.passValue((Video)menu_lsv.getAdapter().getItem(position));
        main_dwl.closeDrawers();
    }
}
