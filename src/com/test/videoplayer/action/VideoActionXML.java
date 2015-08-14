package com.test.videoplayer.action;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;


public class VideoActionXML {

	private URL url;
	public VideoActionXML(String url){
		try{
			this.url = (new URL(url));
		}catch(Exception ex){
			Log.d("MenuFragmentAsyntasck : CONSTRUCT", ex.getMessage());
		}
	}
	
	
	public List<com.test.videoplayer.objects.Video> parse(){
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		try{
			SAXParser saxParser = parserFactory.newSAXParser();
			com.test.videoplayer.models.VideoModelXML handler = new com.test.videoplayer.models.VideoModelXML();
			saxParser.parse(this.getInputStream(), handler);
			return handler.getListVideo(); 
		}catch(Exception ex){
			Log.d("ERROR_ACTION_PARSE",ex.getMessage());
		}
		return null;
	}
	
	private InputStream getInputStream(){
		try{
			return url.openConnection().getInputStream();
		}catch(Exception ex){
			Log.d("ERROR_ACTION_INPUTSTREAM",ex.getMessage());		
		}
		return null;
	}
}