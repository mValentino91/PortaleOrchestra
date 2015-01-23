package anmadvancedservices.model;

public class Transito {
	private String codiceLinea;
	private String ord;
	private String verso;
	
	public Transito(String codiceLinea, String ord, String verso) {
		super();
		this.codiceLinea = codiceLinea;
		this.ord = ord;
		this.verso = verso;
	}
	
	@Override
	public String toString() {
		return "{\"codicelinea\":\""+ codiceLinea +"\",\"ord\":\""+ord+"\",\"verso\":\""+verso+"\"}";
	}
}
