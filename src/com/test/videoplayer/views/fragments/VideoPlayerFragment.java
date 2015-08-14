package com.test.videoplayer.views.fragments;


import java.util.Observable;
import java.util.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.OoyalaPlayerLayout;
import com.ooyala.android.PlayerDomain;
import com.ooyala.android.ui.OoyalaPlayerLayoutController;
import com.test.videoplayer.R;
import com.test.videoplayer.objects.Video;

@SuppressLint("ShowToast")
public class VideoPlayerFragment extends Fragment implements Observer{

	private static final String TAG = "VideoPlayer";
	private OoyalaPlayer oyalaPlayer;
	public static final String PLAY_COMPLETED_NOTIFICATION = "playCompleted";
	private View view;
	private Boolean isSuspended = false;
	String PCODE = "";
	String embedCode = "";
	String playerDomain= "http://android_jorgesys.ooyala.com";
	private boolean adCompleted;

	public VideoPlayerFragment(){}
	public VideoPlayerFragment(Video video){
		PCODE = cleanString( video.getPartnerCode() );
		embedCode = cleanString( video.getEmbedCode() );
		playerDomain = cleanString( video.getPlayerDomain() );
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		if(embedCode != ""){
			view = inflater.inflate(R.layout.videocontainer_fragment, container, false);
			 OoyalaPlayerLayout playerLayout = (OoyalaPlayerLayout) view.findViewById(R.id.ooyalaPlayer);
			 oyalaPlayer = new OoyalaPlayer(PCODE, new PlayerDomain(playerDomain));
			    new OoyalaPlayerLayoutController(playerLayout, oyalaPlayer);
			
			oyalaPlayer.addObserver(this);
			if(oyalaPlayer.setEmbedCode(embedCode)){
				oyalaPlayer.play();
			}else{
				Log.d(this.getClass().getName(), " Something Went Wrong!");
			}
		}else{
			view = inflater.inflate(R.layout.webplayer_fragment, container, false);
		}
		return view;
	}

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (oyalaPlayer != null && !isSuspended) {
			Log.e(TAG, "onStop() : suspending!!!");
			oyalaPlayer.suspend();
			isSuspended = true;
		}
	}
	@Override
	public void onStart() {
		super.onStart();
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		oyalaPlayer = null;
	}
	private String cleanString(String value){
		value = value.substring(3);
		return value;
	}


	@Override
	public void update(Observable observable, Object data) {
		if(oyalaPlayer == null)
			return;
		Log.i(TAG, "oyalaPlayer.getState():" + oyalaPlayer.getState());
		Log.i(TAG, "PlayheadPercentage: " + oyalaPlayer.getPlayheadPercentage() + "%");
		Log.i(TAG, "PlayheadPercentage: " + oyalaPlayer.getPlayheadTime());
		Log.i(TAG, "Duration: " + oyalaPlayer.getDuration());

		if(data == OoyalaPlayer.AD_COMPLETED_NOTIFICATION ){
			Log.i(TAG, "Carga AD completa!!! " + data + " - state: " + oyalaPlayer.getState());
			adCompleted = true;	
		}
		if(data == OoyalaPlayer.PLAY_COMPLETED_NOTIFICATION){
			Log.e(TAG, "Video completo!!! " + data + " - state: " + oyalaPlayer.getState());			
		}
		if (((String)data).equals(OoyalaPlayer.STATE_CHANGED_NOTIFICATION) && ((OoyalaPlayer)observable).getState() == State.COMPLETED){
			Toast.makeText(getActivity().getApplicationContext(),  "El video ha finalizado!!!  - state: " + oyalaPlayer.getState(), Toast.LENGTH_LONG).show();
			Log.e(TAG, "Video completo!!! " + data + " - state: " + oyalaPlayer.getState());		   
		}
		if(oyalaPlayer.getState() == State.COMPLETED){
			Toast.makeText(getActivity().getApplicationContext(),  "El video ha finalizado!!!  - state: " + oyalaPlayer.getState(), Toast.LENGTH_LONG).show();
			Log.e(TAG, "Video completo!!! " + data + " - state: " + oyalaPlayer.getState());
		}		
		if(oyalaPlayer.getState() == State.INIT){		   
			Log.i(TAG, "Video Inicio! " + data + " - state: " + oyalaPlayer.getState());
		}		
		if(oyalaPlayer.getState() == State.ERROR && adCompleted){
			Log.e(TAG, "Error al reproducir!, Posible ADSET sin anuncio!!!.");			
			if(oyalaPlayer != null){
				oyalaPlayer.resume();
				oyalaPlayer.seek(0);
				oyalaPlayer.play();
				Log.e(TAG, "* ooyala player started!");
			}			
		}
		
		if(oyalaPlayer.getState() == State.ERROR){
         Log.e(TAG, "Error al reproducir!");			         
		}

		if (data == OoyalaPlayer.CONTENT_TREE_READY_NOTIFICATION) {
			Log.d(TAG, "AD - metadata true!");
		} else if (data == OoyalaPlayer.METADATA_READY_NOTIFICATION) {
			Log.d(TAG, "Woot, here is the current metadata: " + oyalaPlayer.getMetadata());
		}
	}

}
