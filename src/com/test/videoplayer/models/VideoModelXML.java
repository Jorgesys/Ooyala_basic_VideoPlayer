package com.test.videoplayer.models;

import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.test.videoplayer.objects.Video;

import android.util.Log;


public class VideoModelXML extends DefaultHandler {
	
	private List<Video> lstVideos;
	private Video VideoActual;
	private StringBuilder strBuilder;
	
	public VideoModelXML(){
	}
	
	public List<Video> getListVideo(){
		return lstVideos;
	}
	
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException{
		super.characters(ch, start, length);
		if(VideoActual != null){
			strBuilder.append(ch, start, length);
		}
	}
	
	@Override
	public void startDocument() throws SAXException{
		super.startDocument();
		lstVideos = new ArrayList<Video>();
		strBuilder = new StringBuilder();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("videogaleria")){
			VideoActual = new Video();
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		super.endElement(uri, localName, qName);
		if(this.VideoActual != null){
			if(localName.equals("id")){
				VideoActual.setId(strBuilder.toString());
			}else if(localName.equals("titulo")){
				VideoActual.setTitulo(strBuilder.toString());
			}else if(localName.equals("categoria")){
				VideoActual.setCategoria(strBuilder.toString());
			}else if(localName.equals("resumen")){
				VideoActual.setResumen(strBuilder.toString());
			}else if(localName.equals("url")){
				VideoActual.setUrl(strBuilder.toString());
				Log.d("MENSAJE_URL_MODEL",strBuilder.toString());
			}else if(localName.equals("horapub")){
				VideoActual.setHorapub(strBuilder.toString());
			}else if(localName.equals("embedCode")){
				VideoActual.setEmbedCode(strBuilder.toString());
			}else if(localName.equals("providerCode")){
				VideoActual.setPartnerCode(strBuilder.toString());
			}else if(localName.equals("playerDomain")){
				VideoActual.setPlayerDomain(strBuilder.toString());
			}else if(localName.equals("videogaleria")){
				if(VideoActual.getEmbedCode().length() > 3){
					Log.d("EMBED : ",""+VideoActual.getEmbedCode().length());
					lstVideos.add(VideoActual);
				}
			}
			strBuilder.setLength(0);
		}
	}
}