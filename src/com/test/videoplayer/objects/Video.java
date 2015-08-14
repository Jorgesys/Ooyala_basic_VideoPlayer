package com.test.videoplayer.objects;

public class Video {
	
	private String categoria;
	private String id;
	private String titulo;
	private String resumen;
	private String url;
	private String horapub;
	private String embedCode;
	private String partnerCode;
	private String playerDomain;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHorapub() {
		return horapub;
	}
	public void setHorapub(String horapub) {
		this.horapub = horapub;
	}
	public String getEmbedCode() {
		return embedCode;
	}
	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String providerCode) {
		this.partnerCode = providerCode;
	}
	public String getPlayerDomain() {
		return playerDomain;
	}
	public void setPlayerDomain(String playerDomain) {
		this.playerDomain = playerDomain;
	}
	

}
