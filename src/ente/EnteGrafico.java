package ente;

public class EnteGrafico{
	protected String url;
	public EnteGrafico(String url){
		this.url=url;
	}
	public void setSkin(String s) {this.url=s;}
	public String getSkin() {return this.url;}
}
