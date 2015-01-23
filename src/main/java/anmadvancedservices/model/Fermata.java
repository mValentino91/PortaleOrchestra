package anmadvancedservices.model;

import java.util.ArrayList;
import java.util.List;

public class Fermata {

	private String codice;
	private String lat;
	private String lon;
	private List<Transito> transiti;
	
	public Fermata(String codice, String lat, String lon) {
		super();
		this.codice = codice;
		this.lat = lat;
		this.lon = lon;
		this.transiti = new ArrayList<Transito>();
	}
	
	public void addTransito(Transito t){
		this.transiti.add(t);
	}
	
	@Override
	public String toString() {
		String jsonTransiti = "";
		for(Transito t : this.transiti){
			jsonTransiti+=t+",";
		}
		
		jsonTransiti="["+jsonTransiti.substring(0, jsonTransiti.length()-1)+"]";
		
		return "{\"codicefermata\":\""+ codice +"\""
				+",\"location\":[\"" + lat + "\",\"" + lon + "\"]"
				+",\"transiti\":"+jsonTransiti+"}";
	}

}
