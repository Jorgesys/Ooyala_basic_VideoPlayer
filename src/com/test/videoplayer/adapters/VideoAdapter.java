package com.test.videoplayer.adapters;

import java.util.List;

import com.test.videoplayer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VideoAdapter extends BaseAdapter {
	TextView titulo_lbl_menu, txtResumen;
	Context context;
	List<com.test.videoplayer.objects.Video> lstVideo;
	
	public VideoAdapter(Context context,List<com.test.videoplayer.objects.Video> lstVideo){
		this.context = context;
		this.lstVideo = lstVideo;
	}
	
	@Override
	public int getCount() {
		if(lstVideo!=null){
		return lstVideo.size();
		}else{
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return lstVideo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup container) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(com.test.videoplayer.R.layout.row_menu, container, false);
		titulo_lbl_menu = (TextView) v.findViewById(R.id.titulo_txt_menu);
		titulo_lbl_menu.setText(lstVideo.get(position).getTitulo());
		txtResumen = (TextView) v.findViewById(R.id.txtResumen);
		txtResumen.setText(lstVideo.get(position).getResumen());
		return v;
	}
}